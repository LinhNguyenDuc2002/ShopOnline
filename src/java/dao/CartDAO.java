/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import config.DBConnection;
import model.Product;
/**
 *
 * @author thanh
 */
public class CartDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<Cart> getCartByAccountID(int accountID) {
    	 List<Cart> list = new ArrayList<>();
        String query = "select * from Cart where accountID = ?";
        try {
            conn = new DBConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountID);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Cart(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    
    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product";
        try {
            conn = new DBConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public Cart checkCartExist(int accountID,int productID) {

       String query = "select * from Cart\r\n"
       		+ "where [accountID] = ? and [productID] = ?";
       try {
           conn = new DBConnection().getConnection();//mo ket noi voi sql
           ps = conn.prepareStatement(query);
           ps.setInt(1, accountID);
           ps.setInt(2, productID);
           rs = ps.executeQuery();
           while (rs.next()) {
               return new Cart(rs.getInt(1),
                       rs.getInt(2),
                       rs.getInt(3),
                       rs.getInt(4),
                       rs.getString(5));
           }
       } catch (Exception e) {
       }
      return null;
   }
    
    public void editAmountAndSizeCart(int accountID, int productID, int amount) {
        String query = "update Cart set [amount]=?,\r\n"
        		+ "where [accountID]=? and [productID]=?";
        try {
            conn = new DBConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, amount);
            
            ps.setInt(2, accountID);
            ps.setInt(3, productID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void insertCart(int accountID, int productID, int amount) {
        String query = "insert Cart(accountID, productID, amount)\r\n"
        		+ "values(?,?,?)";
        try {
            conn = new DBConnection().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountID);
            ps.setInt(2, productID);
            ps.setInt(3, amount);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
}
