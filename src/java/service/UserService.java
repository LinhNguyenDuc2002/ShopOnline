/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import config.DBConnection;
import dao.UserDAO;
import java.text.ParseException;
import java.util.Date;
import util.DateUtil;

/**
 *
 * @author LinhNguyenDuc
 */
public class UserService {
    private UserDAO userDAO;
    
    private DateUtil dateUtil;

    public UserService() {
        userDAO = new UserDAO();
        dateUtil = new DateUtil();
    }
    
    public boolean addUser(String fullname, String username, String password,
            String birthday, String email, String phone) throws ParseException {
        Date birthdayToDate = dateUtil.convertDateFromString(birthday);
        
        return userDAO.addUser(fullname, username, password, birthdayToDate, email, phone);
    }
}
