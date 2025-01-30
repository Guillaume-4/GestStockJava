package controllers;

import java.sql.Date;

import javax.swing.JOptionPane;

import models.Sale;
import models.DAO.SaleDAO;
import views.SaleView;

public class SaleController {
    private SaleView view;
    private SaleDAO SaleDAO;

    public SaleController(SaleView view, SaleDAO SaleDAO) {
        this.view = view;
        this.SaleDAO = SaleDAO;

        this.view.setAddSaleListener(_ -> {
            Date date = view.getDate();
            Integer quantity = view.getQuantity();

            Sale sale = new Sale(quantity, date, null);

            SaleDAO.addSale(sale);
            JOptionPane.showMessageDialog(null, "Catégorie ajoutée !");
        });
    }
}
