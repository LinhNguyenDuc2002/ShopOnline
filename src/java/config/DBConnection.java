/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Connect to database by using JDBC
 * Access https://dev.mysql.com/downloads/connector/j/ to download MySQL JDBC Driver
 * @author LinhNguyenDuc
 */
public class DBConnection {
    protected Connection connection; 

    public DBConnection() {
        Properties properties = new Properties();
        
        final String dbURL = properties.getProperty("DB_URL");
        final String username = properties.getProperty("USERNAME");
        final String password = properties.getProperty("PASSWORD");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(dbURL, username, password);
            System.out.println("connect successfully!");
        } catch (Exception e) {
            System.out.println("connect failure!");
            e.printStackTrace();
        } finally {
            if (this.connection != null) {
                try {
                    this.connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
