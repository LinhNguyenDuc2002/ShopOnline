/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import config.DBConnection;
import dao.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.sql.Date;
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
        String username = session.getAttribute("username").toString();
        System.out.println("session: "+username);
        return userDAO.getCurrentUser(username);
    }
    
    public boolean addUser(Map<String, String> map) throws ParseException, NoSuchAlgorithmException {
        Date birthdayToDate = DateUtil.convertStringToDate(map.get("birthday"));
        Date now = DateUtil.getDateNow();
        
        User user = new User(map.get("username"), hashPassWord(map.get("password")), map.get("fullname"), 
                            birthdayToDate, map.get("phone"), map.get("email"), now);
        
        return userDAO.addUser(user);
    }
    
    public boolean updateUser(Map<String, String> map, long id) throws ParseException {
        Date birthdayToDate = DateUtil.convertStringToDate(map.get("birthday"));
        
        User user = new User();
        user.setId(id);
        user.setFullname(map.get("fullname"));
//        user.setUsername(map.get("username"));
        user.setPhone(map.get("phone"));
        user.setEmail(map.get("email"));
        user.setPassword(map.get("password"));
        user.setBirthday(DateUtil.convertStringToDate(map.get("birthday")));
        user.setCity(map.get("city"));
        user.setCountry(map.get("country"));
        user.setDetail_address(map.get("detail_address"));
        user.setNote(map.get("note"));
        user.setSex(map.get("sex")=="1"?true:false);
        
        return userDAO.updateUser(user);
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
