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
import model.DetailOrder;
import model.User;
import service.CartService;
import service.CategoryService;
import service.ProductService;
import service.UserService;

/**
 *
 * @author LinhNguyenDuc
 */
@WebServlet(name="CartController", urlPatterns={"/carts"})
public class CartController extends HttpServlet {
    private ProductService productService;
    
    private UserService userService;
    
    private CartService cartService;
    
    @Override
    public void init() throws ServletException {
         super.init();
         productService = new ProductService();
         userService = new UserService();
         this.cartService = new CartService();
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
            out.println("<title>Servlet CartController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartController at " + request.getContextPath () + "</h1>");
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
        
        if(action == null) {
            request.getRequestDispatcher("PageNotFound.jsp").forward(request, response);
        }
        else {
            if(user != null && user.getRole().equals("USER")) {
                request.setAttribute("user", user);
                switch (action) {
                    case "show":
                        showCart(request, response, user);
                        break;
                    case "delete":
                        deleteCart(request, response);
                        break;
                    default:
                        throw new AssertionError();
                }
            }
            else {
                response.sendRedirect("/shop/users?action=login");
            }
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
        
        if(user != null && user.getRole().equals("USER") && action != null) {
            request.setAttribute("user", user);
            
            switch (action) {
                case "add":
                    addCart(request, response, user);
                    break;
                default:
                    throw new AssertionError();
            }
            
        }
        else {
            response.sendRedirect("/shop/users?action=login");
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
    
    private void showCart(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
//<<<<<<< HEAD
        System.out.print("abc"+user.getId());
        List<DetailOrder> cart = cartService.getCart(user);
        request.setAttribute("cart", cart);
        
//=======
        request.setAttribute("cart", cartService.getCart(user));
//>>>>>>> dev
        request.getRequestDispatcher("thanhtoan.jsp").forward(request, response);
    }
    
    private void addCart(HttpServletRequest request, HttpServletResponse response, User user) throws IOException {
        String productId = request.getParameter("id");
        String quantity = request.getParameter("quantity");
        
        cartService.addCart(user, Long.valueOf(productId), Long.valueOf(quantity));
        
        response.sendRedirect("/shop/products?action=show&id="+productId);
    }
    
    private void deleteCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        
        cartService.deleteCart(Long.valueOf(id));
        response.sendRedirect("/shop/carts?action=show");
    }

}
