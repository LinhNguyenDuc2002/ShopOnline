/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package invalid;

import dao.UserDAO;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import service.UserService;

/**
 *
 * @author LinhNguyenDuc
 */
public class InvalidUser {
    public static String checkFullname(String fullname) {
        if(fullname.equals("") || fullname == null) {
            return "Fullname cannot be empty";
        }
        if(!invalidFullname(fullname)) {
            return "Fullname can only contain special characters like 'a-z'";
        }
        return null;
    }
    
    public static String checkUsername(String username) throws IOException {
        if(username.equals("") || username == null) {
            return "Username cannot be empty";
        }
        if(!invalidUsername(username)) {
            return "Username can only contain special characters like 'a-z','0-9', '@', '_', '.'";
        }
        if(checkExistUsername(username)) {
            return "This username already exists, please enter another username!";
        }
        return null;
    }
    
    public static String checkUsernameToUpdate(String username) throws IOException {
        if(username.equals("") || username == null) {
            return "Username cannot be empty";
        }
        if(!invalidUsername(username)) {
            return "Username can only contain special characters like 'a-z','0-9', '@', '_', '.'";
        }
        if(checkExistUsername(username)) {
            return "This username already exists, please enter another username!";
        }
        return null;
    }
    
    public static String checkPassword(String password) {
        if(password.equals("") || password == null) {
            return "Password cannot be empty";
        }
        if(password.length()<6) {
            return "Minimum password length is 6 characters";
        }
        return null;
    }
    
    public static boolean invalidUsername(String username) {
        String allow = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.@_";
        String regex = "^[ " + Pattern.quote(allow) + "]+$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);

        return matcher.matches();
    }
    
    public static boolean invalidFullname(String fullname) {
        String allow = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String regex = "^[ " + Pattern.quote(allow) + "]+$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fullname);

        return matcher.matches();
    }
    
    public static boolean checkExistUsername(String username) throws IOException {
        UserDAO userDAO = new UserDAO();
        return userDAO.checkExistUserByUsername(username);
    }
    
    public static String checkPhoneNumber(String phone) {
        if(!phone.matches("[0-9]+")) {
            return "Invalid phone number";
        }
        return null;
    }
    
    public static String checkEmail(String email) {
        UserDAO userDAO = new UserDAO();
        if(userDAO.checkExistUserByEmail(email)) {
            return "This email is already in use";
        }
        return null;
    }
}
