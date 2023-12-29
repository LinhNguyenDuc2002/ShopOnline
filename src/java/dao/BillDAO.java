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
import model.detail_order;


public class BillDAO {

    private Connection connection;
    private UserDAO dao = new UserDAO();
    private ProductDAO productDAO = new ProductDAO();

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
    public List<DetailOrder> FindAllDetailsOrder(int id) {
        try {

            String query = "select * from detail_order where bill_id = " + id + "";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            List<DetailOrder> list = new ArrayList<DetailOrder>();
            while (rs.next()) {
                int product_id = rs.getInt(3);
                int user_id = rs.getInt(4);
                Product pr = productDAO.getProduct((long) product_id);
                User users = dao.getUser((long) user_id);
                DetailOrder a = new DetailOrder(rs.getInt(1), users, pr, rs.getInt(5), rs.getBoolean(6));

                list.add(a);
            }
            return list;
        } catch (Exception e) {
            e.getMessage();
        }

        return null;
    }

    public List<Bill> FindAllOrder(int id, String dateStar, String endStart) {
        try {
            String query = "";
            if(id != 0 && (dateStar != "" || dateStar != null) && (endStart != null || endStart != "")){
                query = "select * from bill where user_id = " + id + " and order_date BETWEEN ? and ?";
            }else if(id != 0 && (dateStar == "" || dateStar == null) && (endStart == null || endStart == "")){
                query = "select * from bill where user_id = " + id + "";
            }else if(id == 0 && (dateStar == "" || dateStar == null) && (endStart == null || endStart == "")){
                query = "select * from bill";
            }
            
//            query = "select * from bill";
            PreparedStatement ps = connection.prepareStatement(query);
           if(id != 0 && (dateStar != "" || dateStar != null) && (endStart != null || endStart != "")){
                ps.setString(1, dateStar);
                ps.setString(2, endStart);
           }
            ResultSet rs = ps.executeQuery();
            List<Bill> list = new ArrayList<>();
            while (rs.next()) {
                int idUser = rs.getInt(2);
                User user = dao.getUser(idUser);
                
                Bill a = new Bill(rs.getLong(1), user, rs.getDate(4), rs.getString(5), rs.getBoolean(6));
                // long id, User user, Date orderDate, String deliveryAddress, boolean status

                list.add(a);
                System.out.println("Tên" + user.getFullname());
            }
            return list;
        } catch (Exception e) {
            e.getMessage();
        }

        return null;
    }
    
    public boolean updateTrangThai(int id){
        String sql = "Update bill set status = true where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);
            if(preparedStatement.executeUpdate() > 0){
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ nếu cần thiết
            return false;
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
