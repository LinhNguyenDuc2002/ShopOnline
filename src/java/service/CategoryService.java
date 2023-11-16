/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.CategoryDAO;
import java.util.List;
import model.Category;

/**
 *
 * @author LinhNguyenDuc
 */
public class CategoryService {
    private CategoryDAO categoryDAO;

    public CategoryService() {
        this.categoryDAO = new CategoryDAO();
    }
    
    public List<Category> getAllCategory() {
        return categoryDAO.getAllCategory();
    }
}
