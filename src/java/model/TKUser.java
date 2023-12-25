/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LinhNguyenDuc
 */
public class TKUser extends User implements Comparable<TKUser>{
    private Double totalAmount;  

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public int compareTo(TKUser o) {
        return Double.compare(o.totalAmount, this.totalAmount);
    }
}
