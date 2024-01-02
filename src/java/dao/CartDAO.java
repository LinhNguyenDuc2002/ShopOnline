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
import java.util.ArrayList;
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
    
    private UserDAO userDAO;
    
    private ProductDAO productDAO;

    public CartDAO() {
        this.connection = DBConnection.getConnection();
        this.userDAO = new UserDAO();
        this.productDAO = new ProductDAO();
    }

    public boolean addCart(Long userId, Long productId, Long quantity) {
        String checkExistQuery = "SELECT id, quantity FROM detail_order WHERE product_id = ? AND user_id = ? AND status = false";
        String updateQuery = "UPDATE detail_order SET quantity = ? WHERE id = ?";
        String insertQuery = "INSERT INTO detail_order (product_id, user_id, quantity, status) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement checkExistStatement = connection.prepareStatement(checkExistQuery);
            checkExistStatement.setLong(1, productId);
            checkExistStatement.setLong(2, userId);

            ResultSet resultSet = checkExistStatement.executeQuery();

            if (resultSet.next()) {
                // Nếu sản phẩm đã tồn tại, cập nhật số lượng
                Long existingId = resultSet.getLong("id");
                Long existingQuantity = resultSet.getLong("quantity");

                Long newQuantity = existingQuantity + quantity;

                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setLong(1, newQuantity);
                updateStatement.setLong(2, existingId);

                updateStatement.executeUpdate();
            } else {
                // Nếu sản phẩm chưa tồn tại, thêm mới vào giỏ hàng
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                insertStatement.setLong(1, productId);
                insertStatement.setLong(2, userId);
                insertStatement.setLong(3, quantity);
                insertStatement.setBoolean(4, false);

                insertStatement.executeUpdate();
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<DetailOrder> getAllDetailsOrder(Long id) {
        try {
            String query = "select * from detail_order where bill_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            
            ps.setLong(1, id);
            
            ResultSet rs = ps.executeQuery();
            List<DetailOrder> list = new ArrayList<>();
            
            while (rs.next()) {
                DetailOrder a = new DetailOrder(rs.getLong(1), 
                                                rs.getDouble(2),
                                                userDAO.getUser(rs.getLong(5)), 
                                                productDAO.getProduct(rs.getLong(4)),
                                                rs.getLong(6), 
                                                rs.getBoolean(7));

                list.add(a);
            }
            return list;
        } catch (Exception e) {
            e.getMessage();
        }

        return List.of();
    }

    public Map<Integer, List<String>> getCart(Long id) {
        try {
            String query = "select * from detail_order where user_id = ? AND status = false";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            Map<Integer, List<String>> map = new HashMap<>();
            int i = 0;
            while (rs.next()) {
                map.put(i++,
                        List.of(
                                rs.getString(1),
                                rs.getString(4),
                                rs.getString(5),
                                rs.getString(6),
                                rs.getString(7)
                        ));
            }
            return map;
        } catch (Exception e) {
        }
        return null;
    }
    
    public List<Long> getDetailOrder(Long id) {
        try {
            String query = "select * from detail_order where user_id = ? AND status = false";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            List<Long> value = new ArrayList<>();
            while (rs.next()) {
                value.add(rs.getLong(1));
            }
            return value;
        } catch (Exception e) {
        }
        return null;
    }
    
    public void updateDetailOrder(Long id, Long billId) {
        double unit = getPriceOfProduct(id);
        String sql = "UPDATE detail_order SET " +
                "    unit_price = ?, " +
                "    bill_id = ?, " +
                "    status = true " +
                "    WHERE id = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, unit);
            preparedStatement.setLong(2, billId);
            preparedStatement.setLong(3, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCart(Long id) {
        try {
            String query = "DELETE FROM detail_order WHERE id = ? AND status = false";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);

            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public boolean addToCart(Long userId) {
        String sql = "INSERT INTO cart (user_id) VALUES (?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deleteCartAll(Long userId) {
        String sql = "DELETE FROM detail_order WHERE user_id = ? AND status = false";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ nếu cần thiết
        }
    }
    
    private double getPriceOfProduct(Long id) {
        String sql = "SELECT product.price FROM detail_order " + 
                "JOIN product ON detail_order.product_id = product.id " + 
                "WHERE detail_order.id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ nếu cần thiết
        }
        return 0;
    }
}
