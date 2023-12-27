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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Bill;
import model.Category;
import model.DetailOrder;
import model.Product;
import model.User;
//<<<<<<< HEAD
import model.detail_order;
//=======
//>>>>>>> dev

public class BillDAO {

    private Connection connection;
    private UserDAO dao = new UserDAO();

    public BillDAO() {
        this.connection = DBConnection.getConnection();
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

//<<<<<<< HEAD
    public List<detail_order> FindAllDetailsOrder(int id) {
        try {

            String query = "select * from detail_order where bill_id = " + id + "";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            List<detail_order> list = new ArrayList<detail_order>();
            while (rs.next()) {
                detail_order a = new detail_order(rs.getInt(1), rs.getInt(2), rs.getInt(4));

                list.add(a);
            }
            return list;
        } catch (Exception e) {
            e.getMessage();
        }

        return null;
    }

    public List<Bill> FindAllOrder(int id) {
        try {

            String query = "select * from bill where user_id = " + id + "";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            List<Bill> list = new ArrayList<>();
            while (rs.next()) {
                int idUser = rs.getInt(2);
                User user = dao.getUser(idUser);
                Bill a = new Bill(rs.getLong(1), user, rs.getDate(3), rs.getString(4));
                // long id, User user, Date orderDate, String deliveryAddress, boolean status

                list.add(a);
            }
            return list;
        } catch (Exception e) {
            e.getMessage();
        }

        return null;
    }
//=======

    public List<Long> getBillsOfUser(Long id, Date start, Date end) {
        String sql = "SELECT id FROM bill WHERE user_id = ? and status = true and order_date >= ? and order_date <= ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            preparedStatement.setDate(2, start);
            preparedStatement.setDate(3, end);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Long> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(resultSet.getLong(1));
            }

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ nếu cần thiết
        }
        return List.of();
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
//>>>>>>> dev
    }
}
