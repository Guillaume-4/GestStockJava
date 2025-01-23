package controllers;

import javax.swing.JOptionPane;

import models.Category;
import models.DAO.CategoryDAO;
import views.CategoryView;

public class CategoryController {
    private CategoryView view;
    private CategoryDAO categoryDAO;

    public CategoryController(CategoryView view, CategoryDAO categoryDAO) {
        this.view = view;
        this.categoryDAO = categoryDAO;

        this.view.setAddCategoryListener(_ -> {
            String name = view.getCategoryName();
            Category category = new Category(name);

            categoryDAO.addSale(category);
            JOptionPane.showMessageDialog(null, "Catégorie ajoutée !");
        });
    }
}
