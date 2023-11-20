/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.CategoryDAO;
import dao.ProductDAO;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author LinhNguyenDuc
 */
public class CategoryService {
    private CategoryDAO categoryDAO;
    
    private ProductDAO productDAO;

    public CategoryService() {
        this.categoryDAO = new CategoryDAO();
        this.productDAO = new ProductDAO();
    }
    
    public List<Category> getAllCategory() {
        return categoryDAO.getAllCategory();
    }
    
    public List<Product> getAllProductsByCategory(Long id) {
        return productDAO.getAllProductsByCategory(id);
    }
}
