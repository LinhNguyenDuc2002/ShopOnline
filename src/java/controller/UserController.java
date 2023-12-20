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
import util.DateUtil;

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
        User user = userService.getCurrentUser(request);
        
        request.setAttribute("user", user);
        if(action == null) {
            if(user != null) {
                request.getRequestDispatcher("UserInfo.jsp").forward(request, response);
            }
            else {
                request.getRequestDispatcher("PageNotFound.jsp").forward(request, response);
            }
        }
        else {
            if(action.equals("login") && user == null) {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            else if(action.equals("signup") && user == null) {
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            }
            else if(action.equals("change-pwd") && user != null) {
                getToChangePwd(request, response);
            }
            else if(action.equals("logout") && user != null) {
                try {
                    logout(request, response);
                } catch (ParseException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
                response.sendRedirect("/shop/users?action=login");
            }
            else {
                request.getRequestDispatcher("PageNotFound.jsp").forward(request, response);
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
        
        request.setAttribute("user", user);
        
        switch (action) {
            case "login":
                try {
                    login(request, response);
                } catch (ParseException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "signup":
                try {
                    signup(request, response);
                } catch (ParseException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "update":
                if(user!=null) {
                    try {
                        update(request, response);
                    } catch (ParseException ex) {
                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case "change-pwd":
                if(user!=null) {
                    try {
                        postToChangePwd(request, response);
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            default:
                throw new AssertionError();
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
        
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        
        String errorUsername = InvalidUser.checkUsername(username);
        String errorEmail = InvalidUser.checkEmail(email);

        if(errorUsername != null) {
            errors.add(errorUsername);
        }
        if(errorEmail != null) {
            errors.add(errorEmail);
        }
        if(request.getParameter("birthday") == null) {
            errors.add("Birthday field is not null");
        }
        
        Map<String, String> input = new HashMap<>();
        input.put("fullname", request.getParameter("fullname"));
        input.put("username", request.getParameter("username"));
        input.put("password", request.getParameter("password"));
        input.put("birthday", request.getParameter("birthday"));
        input.put("email", request.getParameter("email"));
        input.put("phone", request.getParameter("phone"));
        
        System.out.println(input.values().toString());
        
        if(!errors.isEmpty()) {
            request.setAttribute("input", input);
            request.setAttribute("error", errors);
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
        else {
            userService.addUser(input);
            request.getRequestDispatcher("SignupSuccess.jsp").forward(request, response);
        } 
    }
    
    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException {
        User currentUser = userService.getCurrentUser(request);
        
        User user = new User();
        user.setId(currentUser.getId());
        user.setFullname(request.getParameter("fullname"));
        user.setUsername(request.getParameter("username"));
        user.setPhone(request.getParameter("phone"));
        user.setEmail(request.getParameter("email"));
        user.setBirthday(DateUtil.convertStringToDate(request.getParameter("birthday")));
        user.setCity(request.getParameter("city"));
        user.setCountry(request.getParameter("country"));
        user.setDetail_address(request.getParameter("detail_address"));
        user.setNote(request.getParameter("note"));
        user.setSex(request.getParameter("gender").equals("male")?true:false);
        
        List<String> errors = userService.updateUser(user, currentUser);
        if(!errors.isEmpty()) {
            request.setAttribute("user", user);
            request.setAttribute("error", errors);
            request.getRequestDispatcher("UserInfo.jsp").forward(request, response);
        }
        else {
            response.sendRedirect("/shop/users");
        }
    }
    
    private void login(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException, NoSuchAlgorithmException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        
        
        if(userService.authenticate(username, password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            session.setMaxInactiveInterval(3600);
            
            response.sendRedirect("/shop/home");
        }
        else {
            request.setAttribute("error", "Username or password is incorrect! Please re-enter");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
    
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException, NoSuchAlgorithmException, ServletException {
        HttpSession session = request.getSession(false);
        session.invalidate();
    }
    
    private void getToChangePwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("changePwd.jsp").forward(request, response);
    }
    
    private void postToChangePwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
        Map<String, String> input = new HashMap<>();
        input.put("old", request.getParameter("old"));
        input.put("new", request.getParameter("new"));
        input.put("again", request.getParameter("again"));
        
        String error = userService.changePassword(input, userService.getCurrentUser(request));
        if(error == null) {
            error = "success";
        }
        
        request.setAttribute("message", error);
        
        request.getRequestDispatcher("changePwd.jsp").forward(request, response);
    }
}
