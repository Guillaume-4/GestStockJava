package controllers;

import javax.swing.JOptionPane;

import models.Category;
import models.DAO.CategoryDAO;
import views.category.CategoryView;

public class CategoryController {
    private CategoryView view;
    private CategoryDAO categoryDAO;

    public CategoryController(CategoryView view, CategoryDAO categoryDAO) {
        this.view = view;
        this.categoryDAO = categoryDAO;

        this.view.setCategoryListener(e -> {
            String name = view.getCategoryName();

            if (view.getCategory() != null) {
                Category category = new Category(view.getCategory().getCategoryId(), name);

                categoryDAO.updateCategory(category);

                JOptionPane.showMessageDialog(null, "Catégorie modifié !");
            } else {
                Category category = new Category(name);

                categoryDAO.addCategory(category);

                JOptionPane.showMessageDialog(null, "Catégorie ajoutée !");
            }
        });
    }
}
