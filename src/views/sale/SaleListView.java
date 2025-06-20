package views.sale;

import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.AppUser;
import models.Product;
import models.Sale;
import models.DAO.ProductDAO;
import models.DAO.SaleDAO;
import views.components.AppView;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class SaleListView extends AppView{
    private JTextField searchField;
    private DefaultTableModel tableModel;
    private JTable saleTable;
    private JButton backBtn;
    private JButton refreshBtn;
    private List<Sale> sales;
    private AppUser user;
    
    public SaleListView(AppUser user) {
        super("Liste des ventes", 800, 600, true);
        this.user = user;

        // Title
        addTitleComponent(0, 0, 2);

        // Search
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        searchField = new JTextField(20);
        searchField.setToolTipText("Rechercher une vente..");
        contentPanel.add(searchField, gbc);

        // Table
        String[] columnNames = { "Numéro de Vente", "Nom du Produit", "Quantité" , "Date" };
        tableModel = new DefaultTableModel(columnNames, 0);
        saleTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(saleTable);

        loadSales();

        gbc.gridy = 3;
        gbc.weightx = 1;
        gbc.weighty = 1;
        contentPanel.add(scrollPane, gbc);

        // Buttons
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.weighty = 0;
        gbc.weightx = 0;
        gbc.gridy = 4;

        gbc.gridx = 0;
        refreshBtn = new JButton("Rafraîchir");
        contentPanel.add(refreshBtn, gbc);

        gbc.gridx = 1;
        backBtn = new JButton("Retour");
        contentPanel.add(backBtn, gbc);

        // Interactions
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterSales();
            }
        });

        refreshBtn.addActionListener(e -> loadSales());

        backBtn.addActionListener(e -> {
            new ManageSaleView(this.user);
            dispose();
        });

        setVisible(true);
    }

    private void loadSales() {
        tableModel.setRowCount(0);

        sales = new SaleDAO().getAllSales();

        for (Sale sale : sales)
            tableModel.addRow(new Object[] {
                    sale.getSaleId(),
                    sale.getProduct().getProductName(),
                    sale.getSaleQuantity(),
                    sale.getSaleDate()
            });
    }

    private void filterSales() {
        tableModel.setRowCount(0);

        Integer searchInteger = Integer.parseInt(searchField.getText().trim());

        for (Sale sale : sales)
            if (sale.getSaleId() == searchInteger)
                tableModel.addRow(new Object[] {
                    sale.getSaleId(),
                    sale.getProduct().getProductName(),
                    sale.getSaleQuantity(),
                    sale.getSaleDate()
                });
    }
}
