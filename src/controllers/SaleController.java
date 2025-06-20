package controllers;

import java.sql.Date;

import javax.swing.JOptionPane;

import models.Product;
import models.Sale;
import models.DAO.CategoryDAO;
import models.DAO.ProductDAO;
import models.DAO.SaleDAO;
import views.product.ProductView;
import views.sale.SaleView;

public class SaleController {
    private SaleView view;
    private SaleDAO saleDAO;

    public SaleController(SaleView view, SaleDAO saleDAO) {
        this.view = view;
        this.saleDAO = saleDAO;

        this.view.setSaleListener(e -> {
            Product product = new ProductDAO().getProductByName(view.getProductName());
            int quantity = view.getSaleQuantity();
            Date date = view.getSaleDate();

            if (view.getSale() != null) {
                Sale sale = new Sale(view.getSale().getSaleId(), quantity, date, product);

                saleDAO.updateSale(sale);

                JOptionPane.showMessageDialog(null,
                        "La vente numéro " + view.getSale().getSaleId() + " a été modifiée :\n\n"
                                + sale.saleDetails());
            } else {
                Sale sale = new Sale(quantity, date, product);

                saleDAO.addSale(sale);

                JOptionPane.showMessageDialog(null,
                        "La vente a été ajoutée :\n\n" + sale.saleDetails());
            }
        });
    }
}
