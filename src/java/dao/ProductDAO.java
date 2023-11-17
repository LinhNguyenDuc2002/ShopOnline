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
import model.Product;

/**
 *
 * @author LinhNguyenDuc
 */
public class ProductDAO {

    private Connection connection;

    public ProductDAO() {
        this.connection = DBConnection.getConnection();
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
}
