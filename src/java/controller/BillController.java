/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import service.BillService;
import service.ProductService;
import service.UserService;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "BillController", urlPatterns = {"/bill"})
public class BillController extends HttpServlet {
    private ProductService productService;
    
    private BillService billService;

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        productService = new ProductService();
        billService = new BillService();
        userService = new UserService();
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
        User user = userService.getCurrentUser(request.getSession(false));
        
        if(user == null) {
            request.getRequestDispatcher("PageNotFound.jsp").forward(request, response);
        }
        else {
            request.setAttribute("user", user);
            String dateStart = request.getParameter("start");
            String dateEnd = request.getParameter("end");
            
            if(user.getRole().equals("USER")) {
                try {
                    request.setAttribute("bill", billService.getAllBillByUserId(user.getId(), dateStart, dateEnd));
                } catch (ParseException ex) {
                    Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.getRequestDispatcher("buyhistory.jsp").forward(request, response);
            }
            else if(user.getRole().equals("ADMIN")){
                String id = request.getParameter("id");

                try {
                    request.setAttribute("bill", billService.getAllBillByUserId(Long.valueOf(id), dateStart, dateEnd));
                } catch (ParseException ex) {
                    Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.getRequestDispatcher("listBill.jsp").forward(request, response);
            }
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
        String action = request.getParameter("action");
        User user = userService.getCurrentUser(request.getSession(false));
        
        if(user == null) {
            request.getRequestDispatcher("PageNotFound.jsp").forward(request, response);
        }
        else if(user.getRole().equals("USER") && action.equals("update")) {
            String id = request.getParameter("id");
            
            billService.update(Long.valueOf(id));
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
