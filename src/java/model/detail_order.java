/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author TrungAnhNguyen
 */
public class detail_order {
    private int id;
    private int bill_id;
    private int product_id;
    private int quantity;

    public detail_order(int id, int bill_id, int product_id, int quantity) {
        this.id = id;
        this.bill_id = bill_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public detail_order(int bill_id, int product_id, int quantity) {
        this.bill_id = bill_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
