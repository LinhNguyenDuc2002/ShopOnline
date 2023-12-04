/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.BillDAO;
import java.util.Date;
import model.Bill;
import model.User;
import util.DateUtil;

/**
 *
 * @author Thanh
 */
public class BillService {
    
    private BillDAO billDAO;

    public BillService() {
        this.billDAO = new BillDAO();
    }

    public void addBill(User user, String deliveryAddress) {
        Bill newBill = new Bill();
        newBill.setUser(user);
        newBill.setOrderDate(new Date(System.currentTimeMillis()));
        newBill.setDeliveryAddress(deliveryAddress);
        newBill.setStatus(false);
        
        billDAO.addBill(newBill);
    }

    

    
}
