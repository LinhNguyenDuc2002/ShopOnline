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
        else if(action.equals("add") && user != null) {
            getToAddProduct(request, response);
        }
        else {
            request.getRequestDispatcher("PageNotFound.jsp").forward(request, response);
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
        
        if(user != null) {
            if(action.equals("add")) {
                postToAddProduct(request, response);
            }
            
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
        List<Category> categories = categoryservice.getAllCategory();
        request.setAttribute("categories", categories);
        
        request.getRequestDispatcher("addsp.jsp").forward(request, response);
    }
    
    private void postToAddProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Map<String, String> input = new HashMap<>();
        
        input.put("productName",request.getParameter("productName"));
        input.put("price",request.getParameter("price"));
        input.put("category",request.getParameter("category"));
        input.put("available", request.getParameter("available"));
        input.put("description", request.getParameter("description"));
        
        InputStream inputStream = null;
        Part filePart = request.getPart("image");

        if (filePart != null) {
            // Đọc dữ liệu từ file ảnh upload
            inputStream = filePart.getInputStream();
        }
        
        productService.addProduct(input, inputStream);
        response.sendRedirect("/shop/home");
    }

}
