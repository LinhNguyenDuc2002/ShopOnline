/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.CartDAO;
import dao.CategoryDAO;
import dao.ProductDAO;
import java.util.ArrayList;
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
        Map<Integer, List<String>> result = cartDAO.getCart(user.getId());
        
        List<DetailOrder> detailOrders = new ArrayList<>();
        for(List<String> detail : result.values()) {
            DetailOrder detailOrder = new DetailOrder();
            detailOrder.setId(Long.valueOf(detail.get(0)));
            detailOrder.setProduct(productDAO.getProduct(Long.valueOf(detail.get(1))));
            detailOrder.setUser(user);
            detailOrder.setQuantity(Long.valueOf(detail.get(3)));
            detailOrder.setStatus(Boolean.valueOf(detail.get(4)));
            
            detailOrders.add(detailOrder);
        }
        
        return detailOrders;
    }
    
    public void deleteDetailOrder(Long id) {
        cartDAO.deleteDetailOrder(id);
    }
    
    public boolean saveToCart(User user) {
        return cartDAO.addToCart(user.getId());
    }
    
    public void deleteCartAll(long userId) {
        cartDAO.deleteCart(userId); // Gọi phương thức từ CartDAO để xóa giỏ hàng của người dùng
    }
}
