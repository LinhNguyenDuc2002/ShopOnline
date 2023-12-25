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
import java.util.Collections;
import java.util.Comparator;
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
    private final int pageSize = 8;
    
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
    
    public List<Product> getAllProduct(Integer page) {
        int start = page * pageSize;
        return productDAO.getAllProduct(start, pageSize);
    }
    
    public List<Product> getAllProduct(String filterParam, String sort) {
        Long filter = 0L;
        try {
            filter = Long.valueOf(filterParam);
            List<Product> products; 
            if(filter == 0) {
                products = productDAO.getAllProduct();
            }
            else {
                products = productDAO.getAllProductsByCategory(filter);
            }
            
            switch (sort) {
                case "ID":
                    break;
                case "available":
                    products = sortByAvailable(products);
                    break;
                case "price":
                    products = sortByPrice(products);
                    break;
                case "sold":
                    products = sortBySold(products);
                    break;
                default:
                    break;
            }
            return products;
        }
        catch(Exception e) {
            return productDAO.getAllProduct();
        }
    }
    
    public int getProductQuantity() {
        return productDAO.getProductQuantity(pageSize);
    }
    
    public List<Product> getAllProductsByCategory(Long id){
        return productDAO.getAllProductsByCategory(id);
    }
    
    public List<Product> getAllProductByKey(String key) {
        return productDAO.getAllProductByKey(key);
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
        if (filePart != null && filePart.getSize() > 0) {
            old.setImage(handleImage(filePart));
            productDAO.editProduct(old);
        }
        else {
            old.setImage(null);
            productDAO.editProductWithoutImage(old);
        }
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
    
    private List<Product> sortByAvailable(List<Product> products) {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p2.getAvailable(), p1.getAvailable());
            }
        });
        
        return products;
    }
    
    private List<Product> sortByPrice(List<Product> products) {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p2.getPrice(), p1.getPrice());
            }
        });
        
        return products;
    }
    
    private List<Product> sortBySold(List<Product> products) {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p2.getSold(), p1.getSold());
            }
        });
        
        return products;
    }

}
