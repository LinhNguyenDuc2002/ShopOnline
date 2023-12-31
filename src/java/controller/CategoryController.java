/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Category;
import model.Product;
import model.User;
import org.apache.tomcat.util.codec.binary.Base64;
import service.CategoryService;
import service.ProductService;
import service.UserService;

/**
 *
 * @author LinhNguyenDuc
 */
@WebServlet(name="CategoryController", urlPatterns={"/categories"})
public class CategoryController extends HttpServlet {
    private final Integer pageSize = 6;
    
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
        
        String id = request.getParameter("id");
        String sort = request.getParameter("sort");
        String by = request.getParameter("by");
        int page = Integer.parseInt(request.getParameter("page"));
        
        PrintWriter out = response.getWriter();
        for(Product i : productService.getAllProductsByCategory(id, page, pageSize, sort, by)) {
            out.println("<li>");
            out.println("<div class='product-item'>");
            out.println("<div class='product-top'>");
            out.println("<a href='/shop/products?action=show&id=" + i.getId() + "' class='hien-thi'>");
            out.println("<img src='data:image/png;base64, " + Base64.encodeBase64String(i.getImage()) + "' alt='Picture' />");
            out.println("</a>");
            
            String role = (String) request.getSession(false).getAttribute("role");
            if(role == null || !role.equals("ADMIN")) {
                out.println("<a href='/shop/products?action=show&id=" + i.getId() + "' class='buy-now'>Buy now</a>");
            }
            else {
                out.println("<a href='/shop/products?action=edit&id=" + i.getId() + "' class=\"buy-now\">Edit</a>");
            }
            out.println("</div>");
            out.println("<div class='product-info'>");
            out.println("<a href='/shop/products?action=show&id=" + i.getId() + "' class='product-name'>" + i.getProductName() + "</a>");
            out.println("<div class='product-price'>");
            out.println("<p class='price'>" + i.getPrice() + "</p>");
            out.println("<p class='status'>");
            
            if(i.getAvailable()>0) {
                out.println("<span>Available: " + i.getAvailable() + " | Sold: " + i.getSold() + "</span>");
            }
            else {
                out.println("<span style='color: red;'>Sold out</span>");
            }
            
            out.println("</p>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</li>");
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
        String pageParam = request.getParameter("page");
        if(pageParam == null) {
            getCategory(request, response);
        }
        else {
            processRequest(request, response);
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
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private void getCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getCurrentUser(request.getSession(false));
        String id = request.getParameter("id");
        String sort = request.getParameter("sort");
        String by = request.getParameter("by");
        
        List<Product> products = productService.getAllProductsByCategory(id, 0, pageSize, sort, by);
        
        request.setAttribute("user", user);
        request.setAttribute("categories", categoryservice.getAllCategory());
        request.setAttribute("products", products);
        request.setAttribute("id", id);
        request.setAttribute("totalPage", productService.getProductQuantity(id, pageSize));
        request.setAttribute("currentPage", 0);
        
        request.getRequestDispatcher("category.jsp").forward(request, response);
    }
}
