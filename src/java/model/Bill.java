/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author LinhNguyenDuc
 */
public class Bill {
    private long id;
    
    private User user;
    
    private Date orderDate;
    
    private String deliveryAddress;
    
    private boolean status;
    
    private String note;
    
    private List<DetailOrder> detailOrders;

    public Bill() {
    }

    public Bill(long id, User user, Date orderDate, String deliveryAddress, boolean status) {
        this.id = id;
        this.user = user;
        this.orderDate = orderDate;
        this.deliveryAddress = deliveryAddress;
        this.status = status;
    }
    public Bill(long id, User user, Date orderDate, String deliveryAddress) {
        this.id = id;
        this.user = user;
        this.orderDate = orderDate;
        this.deliveryAddress = deliveryAddress;
    }
    public Bill(long id, Date orderDate, String deliveryAddress, boolean status) {
        this.id = id;
        this.orderDate = orderDate;
        this.deliveryAddress = deliveryAddress;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<DetailOrder> getDetailOrders() {
        return detailOrders;
    }

    public void setDetailOrders(List<DetailOrder> detailOrders) {
        this.detailOrders = detailOrders;
    }

    

    
}
