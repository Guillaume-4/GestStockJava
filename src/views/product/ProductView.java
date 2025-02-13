package views.product;

import javax.swing.*;
import javax.swing.text.AbstractDocument;

import models.AppUser;
import models.Category;
import models.Furnisher;
import models.DAO.CategoryDAO;
import models.DAO.FurnisherDAO;
import views.components.AppView;
import views.components.NumericFilter;
import models.Product;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductView extends AppView {
    private JTextField nameTxtField;
    private JTextField quantityTxtField;
    private JTextField unitPriceTxtField;
    private JComboBox<String> categorySelector;
    private JComboBox<String> furnisherSelector;
    private JButton productActionBtn;
    private JButton backBtn;
    private Product product;
    private AppUser user;

    // Constructors
    public ProductView(AppUser user, Product product) {
        super(product == null ? "Ajout de Produit" : "Modification de Produit", 600, 600, false);
        this.user = user;
        this.product = product;

        // Title
        addTitleComponent(0, 0, 2);

        // Category list
        List<Category> categories = new CategoryDAO().getAllCategories();
        String[] categoryNames = new String[categories.size()];

        for (int i = 0; i < categories.size(); i++)
            categoryNames[i] = categories.get(i).getCategoryName();

        // Furnisher List
        List<Furnisher> furnishers = new FurnisherDAO().getFurnishers();
        String[] furnisherNames = new String[furnishers.size()];

        for (int i = 0; i < furnishers.size(); i++)
            furnisherNames[i] = furnishers.get(i).getFurnisherName();

        // Name
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        JLabel nameLabel = new JLabel("Nom du Produit");
        contentPanel.add(nameLabel, gbc);

        gbc.gridy = 3;
        nameTxtField = new JTextField(20);
        contentPanel.add(nameTxtField, gbc);

        // Quantity
        gbc.gridy = 4;
        JLabel quantityLabel = new JLabel("Quantité :");
        contentPanel.add(quantityLabel, gbc);

        gbc.gridy = 5;
        quantityTxtField = new JTextField(20);
        ((AbstractDocument) quantityTxtField.getDocument()).setDocumentFilter(new NumericFilter(false));
        contentPanel.add(quantityTxtField, gbc);

        // Unit Price
        gbc.gridy = 6;
        JLabel unitPriceLabel = new JLabel("Prix :");
        contentPanel.add(unitPriceLabel, gbc);

        gbc.gridy = 7;
        unitPriceTxtField = new JTextField(20);
        ((AbstractDocument) unitPriceTxtField.getDocument()).setDocumentFilter(new NumericFilter(true));
        contentPanel.add(unitPriceTxtField, gbc);

        // Category
        gbc.gridy = 8;
        JLabel categoryLabel = new JLabel("Catégorie :");
        contentPanel.add(categoryLabel, gbc);

        // Furnisher
        gbc.gridy = 10;
        JLabel furnisherLabel = new JLabel("Fournisseur :");
        contentPanel.add(furnisherLabel, gbc);

        // Selectors
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 9;
        categorySelector = new JComboBox<String>(categoryNames);
        contentPanel.add(categorySelector, gbc);

        gbc.gridy = 11;
        furnisherSelector = new JComboBox<String>(furnisherNames);
        contentPanel.add(furnisherSelector, gbc);

        // Empty Space
        gbc.gridy = 12;
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)), gbc);

        // Product Action Button
        gbc.gridy = 13;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.NONE;
        productActionBtn = new JButton(this.product == null ? "Ajouter Produit" : "Modifier Produit");
        productActionBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(productActionBtn, gbc);

        // Back Button
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        backBtn = new JButton("Retour");
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(backBtn, gbc);

        // Fields Initialization if necessary
        if (this.product != null) {
            nameTxtField.setText(product.getProductName());
            quantityTxtField.setText(String.valueOf(product.getProductQuantity()));
            unitPriceTxtField.setText(String.valueOf(product.getProductUnitPrice()));
            categorySelector.setSelectedItem(product.getCategory().getCategoryName());
            furnisherSelector.setSelectedItem(product.getFurnisher().getFurnisherName());
        }

        // Interactions
        backBtn.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(
                    this,
                    "Voulez-vous vraiment revenir en arrière ? Les modifications seront perdues.",
                    "Confirmation",
                    JOptionPane.OK_CANCEL_OPTION);

            if (response == JOptionPane.CANCEL_OPTION)
                return;

            new ManageProductView(user);
            dispose();
        });

        setVisible(true);
    }

    // Getters
    public String getProductName() {
        return nameTxtField.getText();
    }

    public int getProductQuantity() {
        if (quantityTxtField.getText().isEmpty())
            return 0;

        return Integer.parseInt(quantityTxtField.getText());
    }

    public double getProductUnitPrice() {
        if (unitPriceTxtField.getText().isEmpty())
            return 0;

        return Double.parseDouble(unitPriceTxtField.getText());
    }

    public String getProductCategoryName() {
        return categorySelector.getSelectedItem().toString();
    }

    public String getProductFurnisherName() {
        return furnisherSelector.getSelectedItem().toString();
    }

    public Product getProduct() {
        return product;
    }

    // Setters
    public void setProductListener(ActionListener listener) {
        productActionBtn.addActionListener(listener);
    }
}
