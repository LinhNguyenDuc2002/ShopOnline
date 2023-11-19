/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;

/**
 *
 * @author LinhNguyenDuc
 */
public class CategoryDAO {
    private Connection connection;

    public CategoryDAO() {
        this.connection = DBConnection.getConnection();
    }
    
    public List<Category> getAllCategory() {
        String sql = "SELECT * FROM Category";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            List<Category> categories = new ArrayList<>();
            while (resultSet.next()) {
                Category category = new Category(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3));
                categories.add(category);
            }
            
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Category getCategory(String name) {
        String sql = "SELECT * FROM Category WHERE name = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                Category category = new Category(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3));
                return category;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Category getCategoryById(Long id) {
        try {
            String query = "select * from category where id= ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            Category a = null;
            while(rs.next()){
                a = new Category(rs.getLong(1), rs.getString(2), rs.getString(3));
            }
            return a;
        } catch (Exception e) {
        }
        return null;
    }
}
