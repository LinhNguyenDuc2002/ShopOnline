/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author LinhNguyenDuc
 */
public class Product {
    private long id;
    
    private String productName;
    
    private Category category;
    
    private String unit;
    
    private double price;
    
    private double discount;
    
    private long available;
    
    private long sold;
    
    private byte [] image;
    
    private Date updateDay;
    
    private String description;

    public Product() {
    }

    public Product(long id, String productName, Category category, String unit, double price, double discount, long available, long sold, byte[] image, Date updateDay, String description) {
        this.id = id;
        this.productName = productName;
        this.category = category;
        this.unit = unit;
        this.price = price;
        this.discount = discount;
        this.available = available;
        this.sold = sold;
        this.image = image;
        this.updateDay = updateDay;
        this.description = description;
    }

    public Product(long id, String productName, String unit, double price, double discount, long available, long sold,  byte[] image, Date updateDay, String description) {
        this.id = id;
        this.productName = productName;
        this.unit = unit;
        this.price = price;
        this.discount = discount;
        this.available = available;
        this.sold = sold;
        this.image = image;
        this.updateDay = updateDay;
        this.description = description;
    }

    public Product( String productName, Category category, double price, double discount, long available,  byte[] image, String description) {
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.discount = discount;
        this.available = available;
        this.image = image;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public long getAvailable() {
        return available;
    }

    public void setAvailable(long available) {
        this.available = available;
    }

    public long getSold() {
        return sold;
    }

    public void setSold(long sold) {
        this.sold = sold;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getUpdateDay() {
        return updateDay;
    }

    public void setUpdateDay(Date updateDay) {
        this.updateDay = updateDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
