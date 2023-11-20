/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.CategoryDAO;
import dao.ProductDAO;
import jakarta.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import model.Category;
import model.Product;
import util.DateUtil;

/**
 *
 * @author LinhNguyenDuc
 */
public class ProductService {
    private ProductDAO productDAO;
    
    private CategoryDAO categoryDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
        this.categoryDAO = new CategoryDAO();
    }

    public void addProduct(Map<String, String> input, Part filePart) throws IOException {
        Product product = new Product();
        
        product.setProductName(input.get("productName"));
        product.setPrice(Double.valueOf(input.get("price")));
        product.setAvailable(Long.valueOf(input.get("available")));
        product.setDescription(input.get("description"));
        
        Category category = categoryDAO.getCategory(input.get("category"));
        product.setCategory(category);
        product.setSold(0);
        product.setUpdateDay(DateUtil.getDateNow());
        product.setImage(handleImage(filePart));
        
        productDAO.addProduct(product);
    }
    
    public Product getProduct(Long id) {
        return productDAO.getProduct(id);
    }
    
    public void deleteProduct(Long id) {
        productDAO.deleteProduct(id);
    }
    
    public List<Product> getAllProduct() {
        return productDAO.getAllProduct();
    }
    
    public void editProduct(Map<String, String> input, Part filePart) throws IOException {
        Category a = categoryDAO.getCategoryById(Long.parseLong(input.get("category")));
        
        Product old = new Product();

        old.setId(Long.valueOf(input.get("id")));
        old.setProductName(input.get("productName"));
        old.setDescription(input.get("description"));
        old.setAvailable(Long.parseLong(input.get("available")));
        old.setPrice(Double.parseDouble(input.get("price")));
        old.setCategory(a);
        old.setImage(handleImage(filePart));
        
        productDAO.editProduct(old);
    }
    
    private byte[] handleImage(Part filePart) throws IOException {
        InputStream inputStream = null;
        if (filePart != null) {
            // Đọc dữ liệu từ file ảnh upload
            inputStream = filePart.getInputStream();
        }
        
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        byte[] imageBytes = buffer.toByteArray();
        
        return imageBytes;
    }

}
