/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.CartDAO;
import dao.CategoryDAO;
import dao.ProductDAO;
import java.util.List;
import java.util.Map;
import model.DetailOrder;
import model.User;

/**
 *
 * @author LinhNguyenDuc
 */
public class CartService {
    private ProductDAO productDAO;
    
    private CartDAO cartDAO;

    public CartService() {
        this.productDAO = new ProductDAO();
        this.cartDAO = new CartDAO();
    }
    
    public void addCart(User user, Long productId, Long quantity) {
        cartDAO.addCart(user.getId(), productId, quantity);
    }
    
    public List<DetailOrder> getCart(User user) {
        Map<Integer, List<String>> detailOrder = cartDAO.getCart(user.getId());
        
        System.out.println(detailOrder.toString());
        return List.of();
    }
}
