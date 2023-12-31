/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Thanh
 */
import config.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Bill;
import model.DetailOrder;


public class BillDAO {
    private Connection connection;
    
    private UserDAO userDAO;
    
    private ProductDAO productDAO;
    
    private TransportDAO transportDAO;

    public BillDAO() {
        this.connection = DBConnection.getConnection();
        this.userDAO = new UserDAO();
        this.productDAO = new ProductDAO();
        this.transportDAO = new TransportDAO();
    }

    public Long addBill(Bill bill) {
        String sql = "INSERT INTO bill (user_id, transport_id, order_date, delivery_address, status, note) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, bill.getUser().getId());
            preparedStatement.setLong(2, bill.getTransport().getId());
            preparedStatement.setDate(3, (Date) bill.getOrderDate());
            preparedStatement.setString(4, bill.getDeliveryAddress());
            preparedStatement.setBoolean(5, bill.isStatus());
            preparedStatement.setString(6, bill.getNote());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ nếu cần thiết
        }
        return null;
    }
    
    public List<Long> getAllBillByUserId(Long id) {
        try {
            String sql = "SELECT id FROM bill WHERE user.id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            List<Long> bill = new ArrayList<>();
            while(resultSet.next()) {
                bill.add(resultSet.getLong(1));
            }
            
            return bill;
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ nếu cần thiết
        }
        return List.of();
    }
    
    public List<Bill> getAllBillByUserId(Long id, Date start, Date end) {
        List<Bill> bills = new ArrayList<>();
        
        if(start == null || end == null) {
            try {
                String sql = "SELECT * FROM bill WHERE user_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();
                
                while(resultSet.next()) {
                    Bill bill = new Bill();
                    bill.setId(resultSet.getLong(1));
                    bill.setOrderDate(resultSet.getDate(4));
                    bill.setDeliveryAddress(resultSet.getString(5));
                    bill.setStatus(resultSet.getBoolean(6));
                    bill.setNote(resultSet.getString(7));
                    
                    bills.add(bill);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Xử lý ngoại lệ nếu cần thiết
            }
        }
        else {
            try {
                String sql = "SELECT * FROM bill WHERE user_id = ? and order_date >= ? and order_date <= ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, id);
                preparedStatement.setDate(2, start);
                preparedStatement.setDate(3, end);

                ResultSet resultSet = preparedStatement.executeQuery();
                
                while(resultSet.next()) {
                    Bill bill = new Bill();
                    bill.setId(resultSet.getLong(1));
                    bill.setOrderDate(resultSet.getDate(4));
                    bill.setDeliveryAddress(resultSet.getString(5));
                    bill.setStatus(resultSet.getBoolean(6));
                    bill.setNote(resultSet.getString(7));
                    
                    bills.add(bill);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Xử lý ngoại lệ nếu cần thiết
            }
        }
        
        return bills;
    }

    public void update(Long id){
        String sql = "UPDATE bill SET status = true WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Long> getBillsOfUser(Long id, Date start, Date end) {
        String sql = "SELECT id FROM bill WHERE user_id = ? and status = true and order_date >= ? and order_date <= ?";
        List<Long> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.setDate(2, start);
            preparedStatement.setDate(3, end);

            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                result.add(resultSet.getLong(1));
            }

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ nếu cần thiết
        }
        return result;
    }

    public double getTotalOfDetail(Long id) {
        String sql = "SELECT detail_order.quantity * product.price AS total "
                + "FROM detail_order "
                + "JOIN product ON detail_order.product_id = product.id "
                + "WHERE detail_order.bill_id = ?";

        double result = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result += resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ nếu cần thiết
        }

        return result;
    }
}
