/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import config.DBConnection;
import dao.UserDAO;
import java.io.IOException;
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
    
    public boolean addUser(Map<String, String> map) throws ParseException {
        Date birthdayToDate = DateUtil.convertStringToDate(map.get("birthday"));
        Date now = DateUtil.getDateNow();
        
        User user = new User(map.get("username"), map.get("password"), map.get("fullname"), 
                            birthdayToDate, map.get("phone"), map.get("email"), now);
        
        return userDAO.addUser(user);
    }
    
    public boolean updateUser(Map<String, String> map, long id) throws ParseException {
        Date birthdayToDate = DateUtil.convertStringToDate(map.get("birthday"));
        
        User user = new User();
        user.setId(id);
        user.setFullname(map.get("fullname"));
        user.setUsername(map.get("username"));
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
    
    public User getUser(long id) {
        return userDAO.getUser(id);
    }
    
    public boolean checkExistUserByUsername(String username) {
        return userDAO.checkExistUserByUsername(username);
    }
}
