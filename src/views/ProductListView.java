package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.AppUser;
import models.Product;
import models.DAO.ProductDAO;
import views.components.AppView;
import views.managers.ManageProductView;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class ProductListView extends AppView {
    private JTextField searchField;
    private DefaultTableModel tableModel;
    private JTable productTable;
    private JButton backBtn;
    private JButton refreshBtn;
    private List<Product> products;
    private AppUser user;

    public ProductListView(AppUser user) {
        super("Liste des produits", 800, 600, true);
        this.user = user;

        // Title
        addTitleComponent(0, 0, 2);

        // Search
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        searchField = new JTextField(20);
        searchField.setToolTipText("Recherchez un produit..");
        add(searchField, gbc);

        // Table
        String[] columnNames = { "ID", "Nom", "Quantité", "Prix Unitaire", "Catégorie", "Fournisseur" };
        tableModel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);

        loadProducts();

        gbc.gridy = 4;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(scrollPane, gbc);

        // Buttons
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.weighty = 0;
        gbc.weightx = 0;
        gbc.gridy = 5;

        gbc.gridx = 0;
        refreshBtn = new JButton("Rafraîchir");
        add(refreshBtn, gbc);

        gbc.gridx = 1;
        backBtn = new JButton("Retour");
        add(backBtn, gbc);

        // Interactions
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterProducts();
            }
        });

        refreshBtn.addActionListener(e -> loadProducts());

        backBtn.addActionListener(e -> {
            dispose();
            new ManageProductView(this.user);
        });

        setVisible(true);
    }

    private void loadProducts() {
        tableModel.setRowCount(0);

        products = new ProductDAO().getAllProducts();

        for (Product product : products)
            tableModel.addRow(new Object[] {
                    product.getProductId(),
                    product.getProductName(),
                    product.getProductQuantity(),
                    product.getProductUnitPrice(),
                    product.getCategory().getCategoryName(),
                    product.getFurnisher().getFurnisherName()
            });
    }

    private void filterProducts() {
        tableModel.setRowCount(0);

        String searchString = searchField.getText().trim().toLowerCase();

        for (Product product : products)
            if (product.getProductName().toLowerCase().contains(searchString))
                tableModel.addRow(new Object[] {
                        product.getProductId(),
                        product.getProductName(),
                        product.getProductQuantity(),
                        product.getProductUnitPrice(),
                        product.getCategory().getCategoryName(),
                        product.getFurnisher().getFurnisherName()
                });
    }
}
