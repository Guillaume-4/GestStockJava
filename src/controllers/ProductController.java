package controllers;

import javax.swing.JOptionPane;

import models.Product;
import models.DAO.ProductDAO;
import views.ProductView;

public class ProductController {
    private ProductView vue;
    private ProductDAO ProductDAO;

    public ProductController(ProductView vue, ProductDAO ProductDAO) {
        this.vue = vue;
        this.ProductDAO = ProductDAO;

        this.vue.setAddProductListener(e -> {
            String nom = vue.getProductName();
            Product Product = new Product(nom, 0, 0, null, null, null);

            ProductDAO.addProduct(Product);
            JOptionPane.showMessageDialog(null, "Catégorie ajoutée !");
        });
    }
}
