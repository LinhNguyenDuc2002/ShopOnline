/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import config.DBConnection;
import dao.UserDAO;
import invalid.InvalidUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.User;
import util.DateUtil;

/**
 *
 * @author LinhNguyenDuc
 */
public class UserService {
    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }
    
    public boolean authenticate(String username, String password) throws ParseException, NoSuchAlgorithmException {
        boolean checkuser = userDAO.authenticate(username, hashPassWord(password));
        
        if(!checkuser) {
            return false;
        }
        return true;
    }
    
    public User getCurrentUser(HttpServletRequest request) {
        HttpSession session = getSession(request);
        if(session == null || session.getAttribute("username") == null) {
            return null;
        }
        
        String username = session.getAttribute("username").toString();
        
        return userDAO.getCurrentUser(username);
    }
    
    public List<String> addUser(User user) throws ParseException, NoSuchAlgorithmException, IOException {
        List<String> errors = new ArrayList<>();
        
        String errorUsername = InvalidUser.checkUsername(user.getUsername());
        String errorEmail = InvalidUser.checkEmail(user.getEmail());

        if(errorUsername != null) {
            errors.add(errorUsername);
        }
        if(errorEmail != null) {
            errors.add(errorEmail);
        }
        if(user.getBirthday() == null) {
            errors.add("Birthday field is not null");
        }
        
        Date now = DateUtil.getDateNow();
        user.setJoin_date(now);
        
        if(errors.isEmpty()) {
            userDAO.addUser(user);
        }
        
        return errors;
    }
    
    public List<User> getAllUser() {
        return userDAO.getAllUser();
    }
    
    public List<String> updateUser(User user, User currentUser) throws ParseException, IOException {
        List<String> errors = new ArrayList<>();
        
        if(!currentUser.getUsername().equals(user.getUsername())) {
            String errorUsername = InvalidUser.checkUsername(user.getUsername());
            if(errorUsername != null) {
                errors.add(errorUsername);
            }
        }
        
        if(!currentUser.getEmail().equals(user.getEmail())) {
            String errorEmail = InvalidUser.checkEmail(user.getEmail());
            if(errorEmail != null) {
                errors.add(errorEmail);
            }
        }
        
        if(user.getBirthday() == null) {
            errors.add("Birthday field is not null");
        }
        
        if(errors.isEmpty()) {
            userDAO.updateUser(user);
        }
        
        return errors;
    }
    
    public String changePassword(Map<String, String> input, User user) throws NoSuchAlgorithmException {
        if(!input.get("new").equals(input.get("again"))) {
            return "Password is incorrect!";
        }
        
        String oldPwd = hashPassWord(input.get("old"));
        if(!oldPwd.equals(user.getPassword())) {
            return "Old password is incorrect!";
        }
        
        if(input.get("new").equals(input.get("old"))) {
            return "The new password must be different from the old password!";
        }
        
        userDAO.changePassword(hashPassWord(input.get("new")), user.getId());
        return null;
    }
    
    private String hashPassWord(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
            
        byte[] hash = digest.digest(password.getBytes());

        // Transfer hash to hex form
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }
    
    private HttpSession getSession(HttpServletRequest request) {
        return request.getSession(false);
    }
}
