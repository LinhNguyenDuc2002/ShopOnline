/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Product;
import model.User;
import service.CategoryService;
import service.ProductService;
import service.UserService;

/**
 *
 * @author LinhNguyenDuc
 */
@WebServlet(name="ProductController", urlPatterns={"/products"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
public class ProductController extends HttpServlet {
    private ProductService productService;
    
    private UserService userService;
    
    private CategoryService categoryservice;
    
    @Override
    public void init() throws ServletException {
         super.init();
         productService = new ProductService();
         userService = new UserService();
         categoryservice = new CategoryService();
    }
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getParameter("action");
        User user = userService.getCurrentUser(request);
        
        request.setAttribute("user", user);
        
        if(action == null) {
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
        else if(action.equals("add") && user.getRole().equals("ADMIN")) {
            getToAddProduct(request, response);
        }
        else if(action.equals("delete") && user.getRole().equals("ADMIN")) {
            deleteProduct(request, response);
        }
        else if(action.equals("edit") && user.getRole().equals("ADMIN")) {
            getToEditProduct(request, response);
        }
        else if(action.equals("show")) {
            getToShowProduct(request, response);
        }
        else if(action.equals("find")) {
            getToFindProduct(request, response, user);
        }
        else {
            response.sendRedirect("/shop/404");
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getParameter("action");
        User user = userService.getCurrentUser(request);
        
        request.setAttribute("user", user);
        
        if(user != null && user.getRole().equals("ADMIN")) {
            if(action.equals("add")) {
                postToAddProduct(request, response);
            }
            else if(action.equals("edit")) {
                postToEditProduct(request, response);
            }
        }
        else {
            response.sendRedirect("/shop/404");
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private void getToAddProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categories", categoryservice.getAllCategory());
        request.getRequestDispatcher("addsp.jsp").forward(request, response);
    }
    
    private void postToAddProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Product product = new Product();
        product.setProductName(request.getParameter("productName"));
        product.setPrice(Double.valueOf(request.getParameter("price")));
        product.setAvailable(Long.valueOf(request.getParameter("available")));
        product.setDescription(request.getParameter("description"));
        
        Part filePart = request.getPart("image");

        productService.addProduct(product, request.getParameter("category"), filePart);
        response.sendRedirect("/shop/manage?action=products");
    }
    
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        productService.deleteProduct(Long.valueOf(request.getParameter("id")));
        response.sendRedirect("/shop/manage?action=products");
    }
    
    private void getToEditProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product a = productService.getProduct(Long.valueOf(request.getParameter("id")));
        
        if(a == null) {
            response.sendRedirect("/shop/404");
        }
        else {
            request.setAttribute("sanphamchitiet", a);
            request.setAttribute("list", categoryservice.getAllCategory());
            request.getRequestDispatcher("fixsp.jsp").forward(request, response);
        }
    }
    
    private void getToShowProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product a = productService.getProduct(Long.valueOf(request.getParameter("id")));
        request.setAttribute("list_category", productService.getAllProductsByCategory(String.valueOf(a.getCategory().getId()), null));
        request.setAttribute("sanphamchitiet", a);
        request.getRequestDispatcher("product.jsp").forward(request, response);
    }
    
    private void postToEditProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        Product product = new Product();
        product.setId(Long.valueOf(id));
        product.setProductName(request.getParameter("ten-san-pham"));
        product.setPrice(Double.valueOf(request.getParameter("gia")));
        product.setAvailable(Long.valueOf(request.getParameter("so-luong")));
        product.setDescription(request.getParameter("mo-ta"));
       
        Part filePart = request.getPart("image");

        productService.editProduct(product, request.getParameter("category"), filePart);
        
        response.sendRedirect("/shop/products?action=edit&id="+id.toString());
    }
    
    private void getToFindProduct(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        String key = request.getParameter("key");
        request.setAttribute("sanpham", productService.getAllProductByKey(key));
        request.setAttribute("key", key);
        
        if(user != null && user.getRole().equals("ADMIN")) {
            request.getRequestDispatcher("homeAdmin.jsp").forward(request, response);
        }
        else {
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    }

}
