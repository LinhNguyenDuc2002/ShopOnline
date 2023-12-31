/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.BillDAO;
import dao.CartDAO;
import dao.ProductDAO;
import dao.TransportDAO;
import dao.UserDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import model.Bill;
import model.DetailOrder;
import model.Product;
import model.Transport;
import model.User;

import model.listData;
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

    private UserDAO userDAO;

    public BillService() {
        this.billDAO = new BillDAO();
        this.cartDAO = new CartDAO();
        this.productDAO = new ProductDAO();
        this.transportDAO = new TransportDAO();
        this.userDAO = new UserDAO();
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
    
    public List<DetailOrder> FindAllDetailsOrder(int id){
        return billDAO.FindAllDetailsOrder(id);
    }
    
    public List<Bill> FindAllOrder(int id, String dateStar, String dateEnd){
        return billDAO.FindAllOrder(id, dateStar, dateEnd);
    }
    public boolean updateTrangThai(int id){
        return billDAO.updateTrangThai(id);
    }
    
    public List<listData> FindAllDataOrder(List<Bill> listBill){
        System.out.println("85-BillService");
        List<listData> data = new ArrayList<>();
        
         if(listBill != null){
            
            for(Bill bill : listBill){
                int sum = 0;
                Bill bills = new Bill();
                
                List<Product> productList = new ArrayList<>();
                System.out.println("96-BillService");
                List<DetailOrder> listDetai = billDAO.FindAllDetailsOrder((int) bill.getId());
                
                if(listDetai != null){
                    System.out.println("98-BillService");
                    for(DetailOrder details : listDetai){
                        
                        Product prooduct = productDAO.getProduct((long)details.getProduct().getId());
                        if(prooduct != null){
                            productList.add(prooduct);
                            sum += (details.getQuantity() * prooduct.getPrice()) + bill.getTransport().getPrice();
                        }
                    }
                }
                User findOne = userDAO.getUser(bill.getUser().getId());
                bills.setId(bill.getId());
                bills.setUser(findOne);
                bills.setOrderDate(bill.getOrderDate());
                bills.setStatus(bill.isStatus());
                bills.setDeliveryAddress(bill.getDeliveryAddress());
                listData listdata = new listData(bills, productList,sum);
                data.add(listdata);
            }
            
        }
         
         return data;
    }
}

