/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.CategoryDAO;
import dao.ProductDAO;
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

    public void addProduct(Map<String, String> input, InputStream inputStream) throws IOException {
        Product product = new Product();
        
        product.setProductName(input.get("productName"));
        product.setPrice(Double.valueOf(input.get("price")));
        product.setAvailable(Long.valueOf(input.get("available")));
        product.setDescription(input.get("description"));
        
        Category category = categoryDAO.getCategory(input.get("category"));
        product.setCategory(category);
        product.setSold(0);
        product.setUpdateDay(DateUtil.getDateNow());
        product.setImage(handleImage(inputStream));
        
        productDAO.addProduct(product);
    }
    
    private byte[] handleImage(InputStream inputStream) throws IOException {
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
