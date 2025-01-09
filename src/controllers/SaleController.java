package controllers;

import java.sql.Date;

import javax.swing.JOptionPane;

import models.Product;
import models.Sale;
import models.DAO.SaleDAO;
import views.SaleView;

public class SaleController {
    private SaleView vue;
    private SaleDAO SaleDAO;

    public SaleController(SaleView vue, SaleDAO SaleDAO) {
        this.vue = vue;
        this.SaleDAO = SaleDAO;

        this.vue.setAddSaleListener(e -> {
            java.util.Date date = vue.getDate();
            Integer quantity = vue.getQuantity();

            Sale sale = new Sale(quantity, date, null);

            SaleDAO.addSale(sale);
            JOptionPane.showMessageDialog(null, "Catégorie ajoutée !");
        });
    }
}
