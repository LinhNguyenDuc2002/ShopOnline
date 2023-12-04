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
import java.util.List;
import model.Bill;
import model.DetailOrder;
import model.Product;
import model.User;
import service.BillService;
import service.CartService;
import service.UserService;

/**
 *
 * @author thanh
 */
@WebServlet(name = "OrderServlet", urlPatterns = {"/order"})
public class OrderServlet extends HttpServlet {

    private UserService userService;

    private CartService cartService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
        this.cartService = new CartService();
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
            request.setAttribute("user", user);

            // Hiển thị thông tin người dùng trên trang dathang.jsp
            request.setAttribute("user", user);

            CartService cartService = new CartService();

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

            CartService cartService = new CartService();

            //1. Tạo bill: Lấy thông tin đơn hàng từ request
            String deliveryAddress = request.getParameter("deliveryAddress");

            // Tạo đối tượng Bill từ thông tin thu thập được từ request
            Bill newBill = new Bill();
            newBill.setUser(user);

            newBill.setDeliveryAddress(deliveryAddress);
            newBill.setOrderDate(new java.util.Date()); // Lấy thời gian hiện tại

            // Gọi phương thức addBill từ BillService để lưu thông tin đơn hàng vào cơ sở dữ liệu
            BillService billService = new BillService();
            billService.addBill(user, deliveryAddress);

            //2. Cập nhật sl kho: Lấy thông tin chi tiết đơn hàng từ giỏ hàng của người dùng
            List<DetailOrder> cart = cartService.getCart(user);
            
            // Tạo một đối tượng ProductDAO
            ProductDAO productDAO = new ProductDAO();

            // Lặp qua từng sản phẩm trong giỏ hàng để cập nhật số lượng trong cơ sở dữ liệu
            for (DetailOrder item : cart) {
                Long productId = item.getProduct().getId(); // Lấy ID của sản phẩm
                Long quantityOrdered = item.getQuantity(); // Lấy số lượng sản phẩm đã đặt

                // Lấy thông tin sản phẩm từ cơ sở dữ liệu dựa trên ID
                Product product = productDAO.getProduct(productId);

                if (product != null) {
                    Long currentAvailable = product.getAvailable(); // Lấy số lượng hiện có

                    // Tính toán số lượng mới sau khi đặt hàng
                    Long newAvailable = currentAvailable - quantityOrdered;

                    // Cập nhật số lượng mới vào cơ sở dữ liệu
                    product.setAvailable(newAvailable);
                    productDAO.updateProductAvailability(product); // Phương thức updateProductAvailability cần được thêm vào ProductDAO
                }
            }

            //3. Xác nhận đặt hàng thành công            
            cartService.deleteCartAll(user.getId()); // Xóa toàn bộ sản phẩm trong giỏ hàng của người dùng

            request.setAttribute("message", "Đặt hàng thành công!");
            request.getRequestDispatcher("dathang.jsp").forward(request, response);
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
