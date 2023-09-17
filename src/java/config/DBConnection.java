/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Connect to database by using JDBC
 * Access https://dev.mysql.com/downloads/connector/j/ to download MySQL JDBC Driver
 * @author LinhNguyenDuc
 */
public class DBConnection {
    protected static Connection connection; 

    public static Connection getConnection() {
        Properties properties = new Properties();
        
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("app-config.properties");
        try {
            properties.load(inputStream);
        } catch (IOException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        final String dbURL = properties.getProperty("DB_URL");
        final String username = properties.getProperty("USERNAME");
        final String password = properties.getProperty("PASSWORD");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            System.out.println("connect successfully!");
            return connection;
        } catch (Exception e) {
            System.out.println("connect failure!");
            e.printStackTrace();
            return null;
        }
    }
}
