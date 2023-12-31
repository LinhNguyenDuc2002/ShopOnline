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
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TKUser;
import model.User;
import service.TKUserService;
import service.UserService;

/**
 *
 * @author LinhNguyenDuc
 */
@WebServlet(name="ThongKeController", urlPatterns={"/customers"})
public class ThongKeController extends HttpServlet {
    private TKUserService tkUserService;
    
    private UserService userService;
    
    @Override
    public void init() throws ServletException {
        super.init();
        tkUserService = new TKUserService();
        userService = new UserService();
    }
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        
        String start = request.getParameter("start");
        String end = request.getParameter("end");
            
        int stt = 1;
        PrintWriter out = response.getWriter();
        for(TKUser i : tkUserService.tkUsers(start, end)) {
            out.println("<tr class='product-list active' link='/shop/bill?id=" + i.getId() + "'>");
            out.println("<td>" + stt++ + "</td>");
            out.println("<td>" + i.getFullname() + "</td>");
            out.println("<td>" + i.getBirthday() + "</td>");
            out.println("<td>" + i.getCity() + " - " + i.getCountry() + "</td>");
            out.println("<td>" + i.getPhone() + "</td>");
            out.println("<td class='price'>" + i.getTotalAmount() + "</td>");
            out.println("<td>" + i.getJoin_date() + "</td>");
            out.println("</tr>");
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
        
        if(user != null && user.getRole().equals("ADMIN")) {
            request.setAttribute("user", user);
            
            String start = request.getParameter("start");
            String end = request.getParameter("end");
            
            if(start == null || end == null) {
                request.getRequestDispatcher("tkKhachHang.jsp").forward(request, response);
            }
            else {
                try {
                    processRequest(request, response);
                } catch (ParseException ex) {
                    Logger.getLogger(ThongKeController.class.getName()).log(Level.SEVERE, null, ex);
                }
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
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
