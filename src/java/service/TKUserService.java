/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.BillDAO;
import dao.UserDAO;
import java.text.ParseException;
import java.util.Collections;
import java.sql.Date;
import java.util.List;
import model.TKUser;
import util.DateUtil;

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
    
    public List<TKUser> tkUsers(String start, String end) throws ParseException {
        if(start == null || end == null) {
            return List.of();
        }
        
        Date startAt = DateUtil.convertStringToDate(start);
        Date endAt = DateUtil.convertStringToDate(end);
        
        List<TKUser> users = userDAO.getUsersHaveBill(startAt, endAt);
        for(TKUser user : users) {
            List<Long> billId = billDAO.getBillsOfUser(user.getId(), startAt, endAt);

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
