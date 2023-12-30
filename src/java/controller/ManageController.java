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
@WebServlet(name="ManageController", urlPatterns={"/manage"})
public class ManageController extends HttpServlet {
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
            out.println("<title>Servlet TKProduct</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TKProduct at " + request.getContextPath () + "</h1>");
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
        User user = userService.getCurrentUser(request.getSession(false));
        String action = request.getParameter("action");
        
        if(user != null && user.getRole().equals("ADMIN") && action != null){
            request.setAttribute("user", user);
            
            switch (action) {
                case "products":
                    manageProducts(request, response);
                    break;
                case "users":
                    manageUsers(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
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
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private void manageProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filter = request.getParameter("filter");
        String sort = request.getParameter("sort");
            
        if(filter == null) {
            request.setAttribute("categories", categoryservice.getAllCategory());
            request.setAttribute("sanpham", productService.getAllProduct(null, sort));
            request.getRequestDispatcher("manageProduct.jsp").forward(request, response);
        }
        else {
            response.setContentType("text/html;charset=UTF-8");
        
            PrintWriter out = response.getWriter();
            for(Product i : productService.getAllProduct(filter, sort)) {
                out.println("<tr class='product-list active' link='/shop/products?action=show&id=" + i.getId() + "' >");
                out.println("<td>" + i.getId() + "</td>");
                out.println("<td>" + i.getProductName() + "</td>");
                out.println("<td>" + i.getCategory().getName() + "</td>");
                out.println("<td>" + i.getAvailable() + "</td>");
                out.println("<td class='price'>" + i.getPrice() + "</td>");
                out.println("<td>" + i.getSold() + "</td>");
                out.println("<td>" + i.getDescription() + "</td>");
                out.println("<td class='actions'>");
                out.println("<a href='/shop/products?action=edit&id=" + i.getId() + "'><i class='fa-solid fa-pen-to-square'></i></a>");
                out.println("<a href='/shop/products?action=delete&id=" + i.getId() + "'><i class='fa-solid fa-trash'></i></a>");
                out.println("</td>");
                out.println("</tr>");
            }
        }
        
    }
    
    private void manageUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {            
        request.setAttribute("users", userService.getAllUser());
        request.getRequestDispatcher("manageUser.jsp").forward(request, response);
    }

}
