package controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JOptionPane;

import models.Category;
import models.Furnisher;
import models.Product;
import models.Sale;
import models.DAO.CategoryDAO;
import models.DAO.FurnisherDAO;
import models.DAO.ProductDAO;
import models.DAO.SaleDAO;
import views.ProductView;

public class ProductController {
    private ProductView view;
    private ProductDAO productDAO;
    private SaleDAO saleDAO;

    public ProductController(ProductView view, ProductDAO productDAO, SaleDAO saleDAO) {
        this.view = view;
        this.productDAO = productDAO;
        this.saleDAO = saleDAO;

        this.view.setAddProductListener(_ -> {
            String name = view.getProductName();
            int quantity = view.getProductQuantity();
            double unitPrice = view.getProductUnitPrice();
            String categoryName = view.getProductCategoryName();
            String furnisherName = view.getProductFurnisherName();
            Category category = new CategoryDAO().getCategoryByName(categoryName);
            Furnisher furnisher = new FurnisherDAO().getFurnisherByName(furnisherName);

            Product product = new Product(name, quantity, unitPrice, category, furnisher, null);

            productDAO.addProduct(product);

            JOptionPane.showMessageDialog(null, "Produit ajouté !");
        });
    }

    public void generateReport(String productName, LocalDate startDate, LocalDate endDate) {
        Product product = new ProductDAO().getProductByName(productName);

        if (product != null) {
            Date start = Date.valueOf(startDate);
            Date end = Date.valueOf(endDate);

            List<Sale> sales = new SaleDAO().getSalesByProductAndDateRange(product.getProductId(), start, end);
            int totalSales = 0;

            for (Sale sale : sales)
                totalSales += sale.getSaleQuantity();

            view.showReport(productName, product.getProductUnitPrice(), totalSales);
        } else {
            JOptionPane.showMessageDialog(null, "Produit non trouvé !");
        }
    }
}
