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
    
    private long billId;
    
    private long productId;
    
    private long quantity;

    public DetailOrder(long id, long billId, long productId, long quantity) {
        this.id = id;
        this.billId = billId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public DetailOrder() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
    
    
}
