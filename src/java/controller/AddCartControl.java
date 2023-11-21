/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CartDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.User;

/**
 *
 * @author thanh
 */
@WebServlet(name = "AddCartControl", urlPatterns = {"/addCart"})
public class AddCartControl extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        int productID = Integer.parseInt(request.getParameter("pid"));
        HttpSession session = request.getSession();
        User a = (User) session.getAttribute("acc");
        if (a == null) {
            response.sendRedirect("login");
            return;
        }
        int accountID = (int) a.getId();
        int amount = Integer.parseInt(request.getParameter("quantity"));

        CartDAO dao = new CartDAO();
        Cart cartExisted = dao.checkCartExist(accountID, productID);
        int amountExisted;
        String sizeExisted;

        if (cartExisted != null) {
            amountExisted = cartExisted.getAmount();
            dao.editAmountAndSizeCart(accountID, productID, (amountExisted + amount));
            request.setAttribute("mess", "Da tang so luong san pham!");
            request.getRequestDispatcher("managerCart").forward(request, response);
        } else {
            dao.insertCart(accountID, productID, amount);
            request.setAttribute("mess", "Da them san pham vao gio hang!");
            request.getRequestDispatcher("managerCart").forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
