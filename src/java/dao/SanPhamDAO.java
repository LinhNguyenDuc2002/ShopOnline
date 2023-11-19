/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

//import com.mysql.cj.jdbc.Blob;
import config.DBConnection;
import java.util.List;
import model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Category;

/**
 *
 * @author Hue
 */
public class SanPhamDAO extends DBConnection {

    public SanPhamDAO() {
        super();
    }

    public List<Product> getAllSP() {
        try {
            String query = "select * from product";
            PreparedStatement ps = getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            List<Product> list = new ArrayList<>();
            while(rs.next()){
//                Blob blob= (Blob) rs.getBlob(9);
                Product a = new Product(rs.getLong(1),rs.getString(2),rs.getString(4),rs.getDouble(5),rs.getDouble(6),
                        rs.getLong(7),rs.getLong(8),rs.getBytes(9),rs.getDate(10),rs.getString(11));
                list.add(a);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
    public Product TimSP(long id){
           try {
            String query ="select * from product where id = ?";
            PreparedStatement ps = getConnection().prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            Product a = null;
            while (rs.next()){
                Category b = TimLoai(rs.getLong(3));
//                Blob blob= (Blob) rs.getBlob(9);
                
                a = new Product(rs.getLong(1),rs.getString(2),rs.getString(4)
                        ,rs.getDouble(5),rs.getDouble(6),rs.getLong(7),rs.getLong(8)
                        ,rs.getBytes(9),rs.getDate(10),rs.getString(11));
                a.setCategory(b);
            }
            return a;
        } catch (Exception e) {
        }
           return null;
    }
    public Category TimLoai(long id){
        try {
            String query = "select * from category where id= ?";
            PreparedStatement ps = getConnection().prepareStatement(query);
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
      public List<Category> TimCategory(){
        try {
            String query = "select * from category";
            PreparedStatement ps = getConnection().prepareStatement(query);
           
            ResultSet rs = ps.executeQuery();
            List<Category> list = new ArrayList<>();
            while(rs.next()){
                Category a = new Category(rs.getLong(1), rs.getString(2), rs.getString(3));
                list.add(a);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
    public void Update(Product a){
        try {
            String query = "UPDATE product SET product_name = ?,category_id = ?,"
                    + "price = ?, available = ?, image= ? ,description= ? WHERE (id= ?)";
            PreparedStatement ps = getConnection().prepareStatement(query);
            ps.setString(1, a.getProductName());
            ps.setLong(2, a.getCategory().getId());
            ps.setDouble(3, a.getPrice());
            ps.setLong(4, a.getAvailable());
            ps.setBytes(5,a.getImage2());
            ps.setString(6, a.getDescription());
            ps.setLong(7, a.getId());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    public void Delete(Long id){
        try {
            String query = "delete from product where id = ?";
            PreparedStatement ps = getConnection().prepareStatement(query);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
