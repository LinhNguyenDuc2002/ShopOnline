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
    
    private Product product;
    
    private long quantity;

    public DetailOrder(long id, Product product, long quantity) {
        this.id = id;
       
        this.product = product;
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
    
    
}
