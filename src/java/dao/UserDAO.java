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
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.User;
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
    
    public boolean addUser(User user) {
        String sql = "INSERT INTO user (fullname, username, password, birthday, email, phone, join_date, role, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFullname());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setDate(4, (Date) user.getBirthday());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPhone());
            preparedStatement.setDate(7, (Date) user.getJoin_date());
            preparedStatement.setString(8, "USER");
            preparedStatement.setBoolean(9, true);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public User getUser(long id ) {
        String sql = "SELECT * FROM User WHERE id = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            
            User user = new User();
            while (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setFullname(resultSet.getString("fullname"));
                user.setUsername(resultSet.getString("username"));
                user.setPhone(resultSet.getString("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setCity(resultSet.getString("city"));
                user.setCountry(resultSet.getString("country"));
                user.setDetail_address(resultSet.getString("detail_address"));
                user.setRole(resultSet.getString("role"));
                user.setNote(resultSet.getString("note"));
                user.setJoin_date(resultSet.getDate("join_date"));
                user.setStatus(resultSet.getBoolean("status"));
                user.setSex(resultSet.getBoolean("sex"));
                
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<User> getAllUser() {
        String sql = "SELECT * FROM User";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setFullname(resultSet.getString("fullname"));
                user.setUsername(resultSet.getString("username"));
                user.setPhone(resultSet.getString("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setCity(resultSet.getString("city"));
                user.setCountry(resultSet.getString("country"));
                user.setDetail_address(resultSet.getString("detail_address"));
                user.setRole(resultSet.getString("role"));
                user.setNote(resultSet.getString("note"));
                user.setJoin_date(resultSet.getDate("join_date"));
                user.setStatus(resultSet.getBoolean("status"));
                user.setSex(resultSet.getBoolean("sex"));
                
                users.add(user);
            }
            
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean checkExistUserByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean checkExistUserByEmail(String email) {
        String sql = "SELECT * FROM User WHERE email = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateUser(User user) {
        String sql = "UPDATE user SET " +
                "    username = ?, " +
                "    fullname = ?, " +
                "    birthday = ?, " +
                "    sex = ?, " +
                "    phone = ?, " +
                "    email = ?, " +
                "    detail_address = ?, " +
                "    city = ?, " +
                "    country = ?, " +
                "    note = ? " +
                "    WHERE id = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getFullname());
            preparedStatement.setDate(3, (Date) user.getBirthday());
            preparedStatement.setBoolean(4, user.getSex());
            preparedStatement.setString(5, user.getPhone());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getDetail_address());
            preparedStatement.setString(8, user.getCity());
            preparedStatement.setString(9, user.getCountry());
            preparedStatement.setString(10, user.getNote());
            preparedStatement.setLong(11, user.getId());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public User getCurrentUser(String username) {
        String sql = "SELECT * FROM User WHERE username = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            User user = new User();
            if(resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setFullname(resultSet.getString("fullname"));
                user.setUsername(resultSet.getString("username"));
                user.setPhone(resultSet.getString("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setCity(resultSet.getString("city"));
                user.setCountry(resultSet.getString("country"));
                user.setDetail_address(resultSet.getString("detail_address"));
                user.setRole(resultSet.getString("role"));
                user.setNote(resultSet.getString("note"));
                user.setJoin_date(resultSet.getDate("join_date"));
                user.setStatus(resultSet.getBoolean("status"));
                user.setSex(resultSet.getBoolean("sex"));
                
                return user;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean authenticate(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? and password = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void changePassword(String password, Long id) {
        String sql = "UPDATE user SET " +
                "    password = ? " +
                "    WHERE id = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, password);
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
