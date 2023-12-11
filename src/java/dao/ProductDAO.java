/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author LinhNguyenDuc
 */
public class ProductDAO {

    private Connection connection;

    private CategoryDAO categoryDAO;

    public ProductDAO() {
        this.connection = DBConnection.getConnection();
        this.categoryDAO = new CategoryDAO();
    }

    public void addProduct(Product product) {
        String sql = "INSERT INTO `shop_online`.`product`\n"
                + "(`product_name`, `category_id`, `price`, `available`, `sold`, `image`, `update_day`, `description`)\n"
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
      
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setLong(2, product.getCategory().getId());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setLong(4, product.getAvailable());
            preparedStatement.setLong(5, product.getSold());
            preparedStatement.setBytes(6, product.getImage());
            preparedStatement.setDate(7, (Date) product.getUpdateDay());
            preparedStatement.setString(8, product.getDescription());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(Long id) {
        try {
            String query = "delete from product where id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Product getProduct(Long id) {
        try {
            String query = "select * from product where id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            Product a = null;
            while (rs.next()) {
                Category b = categoryDAO.getCategoryById(rs.getLong(3));

                a = new Product(rs.getLong(1), rs.getString(2), rs.getString(4),
                         rs.getDouble(5), rs.getDouble(6), rs.getLong(7), rs.getLong(8),
                         rs.getBytes(9), rs.getDate(10), rs.getString(11));
                a.setCategory(b);
            }
            return a;
        } catch (Exception e) {
        }
        return null;
    }

    public List<Product> getAllProduct() {
        try {
            String query = "select * from product";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            List<Product> list = new ArrayList<>();
            while (rs.next()) {
                Category category = categoryDAO.getCategoryById(rs.getLong(3));
                Product a = new Product(rs.getLong(1), rs.getString(2), category, rs.getString(4), rs.getDouble(5), rs.getDouble(6),
                        rs.getLong(7), rs.getLong(8), rs.getBytes(9), rs.getDate(10), rs.getString(11));

                list.add(a);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public void editProduct(Product a) {
        try {
            String query = "UPDATE product SET product_name = ?,category_id = ?,"
                    + "price = ?, available = ?, image= ? ,description= ? WHERE (id= ?)";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, a.getProductName());
            ps.setLong(2, a.getCategory().getId());
            ps.setDouble(3, a.getPrice());
            ps.setLong(4, a.getAvailable());
            ps.setBytes(5, a.getImage());
            ps.setString(6, a.getDescription());
            ps.setLong(7, a.getId());

            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void editProductWithoutImage(Product a) {
        try {
            String query = "UPDATE product SET product_name = ?,category_id = ?,"
                    + "price = ?, available = ?, description= ? WHERE (id= ?)";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, a.getProductName());
            ps.setLong(2, a.getCategory().getId());
            ps.setDouble(3, a.getPrice());
            ps.setLong(4, a.getAvailable());
            ps.setString(5, a.getDescription());
            ps.setLong(6, a.getId());

            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Product> getAllProductsByCategory(Long id) {
        try {
            String query = "select * from product where category_id = ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            List<Product> list = new ArrayList<>();
            while (rs.next()) {
                Product a = new Product(rs.getLong(1), rs.getString(2), rs.getString(4), rs.getDouble(5), rs.getDouble(6),
                        rs.getLong(7), rs.getLong(8), rs.getBytes(9), rs.getDate(10), rs.getString(11));

                list.add(a);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public void updateProductAvailability(Long id) {
        try {
            String query = "UPDATE product p " +
                    "INNER JOIN detail_order d ON p.id = d.product_id SET " +
                    "p.available = p.available - d.quantity, " +
                    "p.sold = p.sold + d.quantity " +
                    "WHERE d.id = ?";
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setLong(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Product> getAllProductByKey(String key) {
        try {
            String query = "select * from product where product_name LIKE ?";
            String searchTerm = "%" + key + "%";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, searchTerm);

            ResultSet rs = ps.executeQuery();
            List<Product> list = new ArrayList<>();
            while (rs.next()) {
                Product a = new Product(rs.getLong(1), rs.getString(2), rs.getString(4), rs.getDouble(5), rs.getDouble(6),
                        rs.getLong(7), rs.getLong(8), rs.getBytes(9), rs.getDate(10), rs.getString(11));

                list.add(a);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
}
