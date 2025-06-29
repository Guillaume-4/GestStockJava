package controllers;

import javax.swing.JOptionPane;

import models.Category;
import models.Furnisher;
import models.Product;
import models.DAO.CategoryDAO;
import models.DAO.FurnisherDAO;
import models.DAO.ProductDAO;
import views.product.ProductView;

public class ProductController {
    private ProductView view;
    private ProductDAO productDAO;

    public ProductController(ProductView view, ProductDAO productDAO) {
        this.view = view;
        this.productDAO = productDAO;

        this.view.setProductListener(e -> {
            String name = view.getProductName();
            int quantity = view.getProductQuantity();
            double unitPrice = view.getProductUnitPrice();
            String categoryName = view.getProductCategoryName();
            String furnisherName = view.getProductFurnisherName();
            Category category = new CategoryDAO().getCategoryByName(categoryName);
            Furnisher furnisher = new FurnisherDAO().getFurnisherByName(furnisherName);

            if (view.getProduct() != null) {
                Product product = new Product(view.getProduct().getProductId(), name, quantity, unitPrice, category,
                        furnisher);

                productDAO.updateProduct(product);

                JOptionPane.showMessageDialog(null,
                        "Le produit ID " + view.getProduct().getProductId() + " a été modifié :\n\n"
                                + product.productDetails());
            } else {
                Product product = new Product(name, quantity, unitPrice, category, furnisher, null);

                productDAO.addProduct(product);

                JOptionPane.showMessageDialog(null,
                        "Le produit suivant a été ajouté :\n\n" + product.productDetails());
            }
        });
    }
}
