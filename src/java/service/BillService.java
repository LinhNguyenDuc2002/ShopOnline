/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.BillDAO;
import dao.CartDAO;
import dao.ProductDAO;
import dao.TransportDAO;
import java.util.Date;
import java.util.List;
import java.util.Map;
import model.Bill;
import model.DetailOrder;
import model.Transport;
import model.User;
import util.DateUtil;

/**
 *
 * @author Thanh
 */
public class BillService {
    
    private BillDAO billDAO;
    
    private CartDAO cartDAO;
    
    private ProductDAO productDAO;
    
    private TransportDAO transportDAO;

    public BillService() {
        this.billDAO = new BillDAO();
        this.cartDAO = new CartDAO();
        this.productDAO = new ProductDAO();
        this.transportDAO = new TransportDAO();
    }

    public void addBill(User user, Map<String, String> input) {
        Transport transport = transportDAO.getTransportById(Long.valueOf(input.get("ship")));
        
        Bill newBill = new Bill();
        newBill.setUser(user);
        newBill.setOrderDate(DateUtil.getDateNow());
        newBill.setDeliveryAddress(formatAddress(input));
        newBill.setStatus(false);
        newBill.setTransport(transport);
        newBill.setNote(input.get("note"));
        
        Long billId = billDAO.addBill(newBill); 
        
        List<Long> detailOrderId = cartDAO.getDetailOrder(user.getId());
        for(Long it : detailOrderId) {
            cartDAO.updateDetailOrder(it, billId);
            productDAO.updateProductAvailability(it);
        }
    }
    
    private String formatAddress(Map<String, String> input) {
        return input.get("detail") + " - " + input.get("city") + " - " + input.get("country");
    }
    
}
