/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LinhNguyenDuc
 */
public class DetailOrder {
    private long id;
    
    private double unit_price;
    
    private User user;
    
    private Product product;
    
    private long quantity;
    
    private boolean status;

    public DetailOrder() {
    }

    public DetailOrder(long id, double unit_price, User user, Product product, long quantity, boolean status) {
        this.id = id;
        this.unit_price = unit_price;
        this.user = user;
        this.product = product;
        this.quantity = quantity;
        this.status = status;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
    
}
