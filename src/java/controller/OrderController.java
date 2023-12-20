/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Bill;
import model.DetailOrder;
import model.Product;
import model.User;
import service.BillService;
import service.CartService;
import service.UserService;
import util.DateUtil;

/**
 *
 * @author thanh
 */
@WebServlet(name = "OrderServlet", urlPatterns = {"/order"})
public class OrderController extends HttpServlet {

    private UserService userService;

    private CartService cartService;
    
    private BillService billService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = new UserService();
        this.cartService = new CartService();
        this.billService = new BillService();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = userService.getCurrentUser(request);

        if (user != null && user.getRole().equals("USER")) {
            // Hiển thị thông tin người dùng trên trang dathang.jsp
            request.setAttribute("user", user);

            // Lấy danh sách sản phẩm trong giỏ hàng của người dùng
            List<DetailOrder> cart = cartService.getCart(user);
            request.setAttribute("cart", cart);
            
            request.getRequestDispatcher("dathang.jsp").forward(request, response);
        } else {
            response.sendRedirect("/shop/users?action=login");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = userService.getCurrentUser(request);

        if (user != null && user.getRole().equals("USER")) {
            request.setAttribute("user", user);
            
            Map<String, String> input = new HashMap<>();
            input.put("country", request.getParameter("country"));
            input.put("city", request.getParameter("city"));
            input.put("detail", request.getParameter("detailAddress"));

            billService.addBill(user, input);

            //3. Xác nhận đặt hàng thành công            
            cartService.deleteCartAll(user.getId()); // Xóa toàn bộ sản phẩm trong giỏ hàng của người dùng

            response.sendRedirect("/shop/carts?action=show");
        } else {
            response.sendRedirect("/shop/users?action=login");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
