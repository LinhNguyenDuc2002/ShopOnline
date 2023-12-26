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
import model.Transport;
import model.User;

/**
 *
 * @author LinhNguyenDuc
 */
public class TransportDAO {
    private Connection connection;

    public TransportDAO() {
        this.connection = DBConnection.getConnection();
    }
    
    public List<Transport> getTransport() {
        String sql = "SELECT * FROM Transport";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            
            List<Transport> transports = new ArrayList<>();
            while (resultSet.next()) {
                Transport transport = new Transport(resultSet.getLong(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getString(4));
                transports.add(transport);
            }
            
            return transports;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }
    
    public Transport getTransportById(Long id) {
        String sql = "SELECT * FROM Transport WHERE id = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                Transport transport = new Transport(resultSet.getLong(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getString(4));
                return transport;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
