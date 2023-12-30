/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import cache.TempUser;
import cache.UserCache;
import config.DBConnection;
import dao.UserDAO;
import invalid.InvalidUser;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.TKUser;
import model.User;
import util.DateUtil;
import util.OtpUtil;

/**
 *
 * @author LinhNguyenDuc
 */
public class UserService {
    private UserCache userCache;
    
    private UserDAO userDAO;
    
    private OtpService otpService;

    public UserService() {
        this.userDAO = new UserDAO();
        this.userCache = new UserCache();
        this.otpService = new OtpService();
    }
    
    public String authenticate(String username, String password) throws ParseException, NoSuchAlgorithmException {
        String checkuser = userDAO.authenticate(username, hashPassWord(password));
        
        return checkuser;
    }
    
    public User getCurrentUser(HttpSession session) {
        if(session == null || session.getAttribute("username") == null) {
            return null;
        }
        
        String username = session.getAttribute("username").toString();
        
        return userDAO.getCurrentUser(username);
    }
    
    public List<String> saveTempUser(TempUser user, HttpSession session) throws IOException, UnsupportedEncodingException, MessagingException {
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
        
        if(errors.isEmpty()) {
            session.setAttribute("username", user.getUsername());
            session.setMaxInactiveInterval(600);
            
            String otp = OtpUtil.generateOTP();
            user.setOtp(otp);
            otpService.sendOTPViaEmail(otp, user.getEmail(), user.getFullname());
            userCache.getCache().put(user.getUsername(), user);
        }
        
        return errors;
    }
    
    public String addUser(String username, String otp) throws ParseException, NoSuchAlgorithmException, IOException {
        String error = "";
        try {
            TempUser user = this.userCache.getCache().getIfPresent(username);
            if(user.getOtp().equals(otp)) {
                User u = new User();
                u.setFullname(user.getFullname());
                u.setUsername(user.getUsername());
                u.setPhone(user.getPhone());
                u.setEmail(user.getEmail());
                u.setPassword(hashPassWord(user.getPassword()));
                u.setBirthday(user.getBirthday());
                
                Date now = DateUtil.getDateNow();
                u.setJoin_date(now);
                
                userDAO.addUser(u); 
            }
            else {
                error = "OTP code is invalid";
            }
        }
        catch(Exception e) {
            error = "OTP code is invalid";
        }
        
        return error;
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
    
    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }
    
    public void unableUser(Long id) {
        userDAO.unableUser(id);
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
}
