/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.BillDAO;
import dao.UserDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.TKUser;
import model.User;

/**
 *
 * @author LinhNguyenDuc
 */
public class TKUserService {
    private UserDAO userDAO;
    
    private BillDAO billDAO;

    public TKUserService() {
        userDAO = new UserDAO();
        billDAO = new BillDAO();
    }
    
    public List<TKUser> tkUsers() {
        List<TKUser> users = userDAO.getAllUsers();
        for(TKUser user : users) {
            List<Long> billId = billDAO.getBillsOfUser(user.getId());

            double total = 0;
            for(Long it : billId) {
                total += billDAO.getTotalOfDetail(it);
            }
            
            user.setTotalAmount(total);
        }
        
        Collections.sort(users);
        
        return users;
    }
}
