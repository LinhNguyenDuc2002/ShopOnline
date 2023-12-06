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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Bill;
import model.Category;
import model.DetailOrder;
import model.Product;

public class BillDAO {
    private Connection connection;

    public BillDAO() {
        this.connection = DBConnection.getConnection();
    }

    public Long addBill(Bill bill) {
        String sql = "INSERT INTO bill (user_id, order_date, delivery_address, status) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, bill.getUser().getId());
            preparedStatement.setDate(2, (Date) bill.getOrderDate());
            preparedStatement.setString(3, bill.getDeliveryAddress());
            preparedStatement.setBoolean(4, bill.isStatus()); // Trạng thái mặc định là "Pending"

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
}

