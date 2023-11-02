/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import invalid.InvalidUser;
import jakarta.servlet.ServletConfig;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import service.UserService;

/**
 *
 * @author LinhNguyenDuc
 */
@WebServlet(name="UserController", urlPatterns={"/users"})
public class UserController extends HttpServlet {
    private UserService userService;
    @Override
    public void init() throws ServletException {
         super.init();
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
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserController at " + request.getContextPath () + "</h1>");
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
        
        if(action == null) {
            User user = userService.getUser(1);
            request.setAttribute("user", user);
            request.getRequestDispatcher("user_info.jsp").forward(request, response);
        }
        else if(action.equals("login")) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        else if(action.equals("signup")) {
            request.getRequestDispatcher("signup.jsp").forward(request, response);
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
        
        if(action.equals("login")) {
            try {
                login(request, response);
            } catch (ParseException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(action.equals("signup")) {
            try {
                signup(request, response);
            } catch (ParseException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(action.equals("update")) {
            try {
                update(request, response);
            } catch (ParseException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void signup(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException, NoSuchAlgorithmException {
        List<String> errors = new ArrayList<>();
        
        String errorUsername = InvalidUser.checkUsername(request.getParameter("username"));

        if(errorUsername != null) {
            errors.add(errorUsername);
        }
        
        Map<String, String> input = new HashMap<>();
        input.put("fullname", request.getParameter("fullname"));
        input.put("username", request.getParameter("username"));
        input.put("password", request.getParameter("password"));
        input.put("birthday", request.getParameter("birthday"));
        input.put("email", request.getParameter("email"));
        input.put("phone", request.getParameter("phone"));
        
        if(!errors.isEmpty()) {
            request.setAttribute("input", input);
            request.setAttribute("error", errors);
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
        
        userService.addUser(input);
        request.getRequestDispatcher("signup_success.jsp").forward(request, response);
    }
    
    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException {
        List<String> errors = new ArrayList<>();
        
        Map<String, String> input = new HashMap<>();
        input.put("fullname", request.getParameter("fullname"));
        input.put("username", request.getParameter("username"));
        input.put("password", request.getParameter("password"));
        input.put("birthday", request.getParameter("birthday"));
        input.put("email", request.getParameter("email"));
        input.put("phone", request.getParameter("phone"));
        
        if(!errors.isEmpty()) {
            request.setAttribute("user", userService.getUser(1));
            request.setAttribute("error", errors);
            request.getRequestDispatcher("user_info.jsp").forward(request, response);
        }
        System.out.println(input);
        userService.updateUser(input, 1);
        response.sendRedirect("/shop/users");
    }
    
    private void login(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException, NoSuchAlgorithmException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if(userService.authenticate(username, password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            response.sendRedirect("/shop/users");
        }
        
        request.setAttribute("error", "Username or password is incorrect! Please re-enter");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
