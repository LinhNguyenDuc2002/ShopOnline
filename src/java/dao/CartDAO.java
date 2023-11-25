/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Category;
import model.DetailOrder;
import model.Product;

/**
 *
 * @author LinhNguyenDuc
 */
public class CartDAO {
    private Connection connection;

    public CartDAO() {
        this.connection = DBConnection.getConnection();
    }
    
    public boolean addCart(Long userId, Long productId, Long quantity) {
        String sql = "INSERT INTO detail_order (product_id, user_id, quantity, status) "
                + "VALUES (?, ?, ?, ?)";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setLong(1, productId);
            preparedStatement.setLong(2, userId);
            preparedStatement.setLong(3, quantity);
            preparedStatement.setBoolean(4, false);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Map<Integer, List<String>> getCart(Long id) {
        try {
            String query ="select * from detail_order where user_id = ? AND status = false";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            
            ResultSet rs = ps.executeQuery();
            Map<Integer, List<String>> map = new HashMap<>();
            int i = 0;
            while (rs.next()){
                map.put(i++, 
                        List.of(
                                rs.getString(1),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5),
                                rs.getString(6)
                        ));
            }
            return map;
        } catch (Exception e) {
        }
        return null;
    }
    
    public void deleteCart(Long id) {
        try {
            String query ="DELETE FROM detail_order WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
