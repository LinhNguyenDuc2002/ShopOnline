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
    
    private List<DetailOrder> detailOrders;

    public Bill(long id, User user, Date orderDate, String deliveryAddress, List<DetailOrder> detailOrders) {
        this.id = id;
        this.user = user;
        this.orderDate = orderDate;
        this.deliveryAddress = deliveryAddress;
        this.detailOrders = detailOrders;
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

    public List<DetailOrder> getDetailOrders() {
        return detailOrders;
    }

    public void setDetailOrders(List<DetailOrder> detailOrders) {
        this.detailOrders = detailOrders;
    }
    
    

}