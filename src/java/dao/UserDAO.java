/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import util.DateUtil;

/**
 *
 * @author LinhNguyenDuc
 */
public class UserDAO {
    private Connection connection;

    public UserDAO() {
        this.connection = DBConnection.getConnection();
    }
    
    public boolean addUser(String fullname, String username, String password,
            Date birthday, String email, String phone) {
        String sql = "INSERT INTO user (fullname, username, password, birthday, email, phone, join_date) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try {
            System.out.println(connection);
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, fullname);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setDate(4, (java.sql.Date) birthday);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, phone);
            preparedStatement.setDate(7, (java.sql.Date) DateUtil.getDateNow());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean checkExistUserByUsername(String username) {
        String sql = "SELECT * FROM User WHERE username = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                System.out.println("A="+resultSet);
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
