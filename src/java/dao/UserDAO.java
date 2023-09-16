/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author LinhNguyenDuc
 */
public class UserDAO {
    private DBConnection dBConnection;
    
    private Connection connection;

    public UserDAO() {
        this.dBConnection = new DBConnection();
        this.connection = this.dBConnection.getConnection();
    }
    
    public boolean addUser(String fullname, String username, String password,
            Date birthday, String email, String phone) {
        String sql = "INSERT INTO user (fullname, username, password, birthday, email, phone) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, fullname);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setDate(4, (java.sql.Date) birthday);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, phone);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
