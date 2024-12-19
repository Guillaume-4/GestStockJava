package controllers;

import javax.swing.JOptionPane;

import models.Category;
import models.DAO.CategoryDAO;
import views.CategoryView;

public class CategoryController {
    private CategoryView vue;
    private CategoryDAO categoryDAO;

    public CategoryController(CategoryView vue, CategoryDAO categoryDAO) {
        this.vue = vue;
        this.categoryDAO = categoryDAO;

        this.vue.setAddCategoryListener(e -> {
            String nom = vue.getCategoryName();
            Category category = new Category(nom);

            categoryDAO.addSale(category);
            JOptionPane.showMessageDialog(null, "Catégorie ajoutée !");
        });
    }
}
