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
        List<Category> categories = new CategoryDAO().getCategories();
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
        add(nameLabel, gbc);

        gbc.gridy = 3;
        nameTxtField = new JTextField(20);
        add(nameTxtField, gbc);

        // Quantity
        gbc.gridy = 4;
        JLabel quantityLabel = new JLabel("Quantité :");
        add(quantityLabel, gbc);

        gbc.gridy = 5;
        quantityTxtField = new JTextField(20);
        ((AbstractDocument) quantityTxtField.getDocument()).setDocumentFilter(new NumericFilter(false));
        add(quantityTxtField, gbc);

        // Unit Price
        gbc.gridy = 6;
        JLabel unitPriceLabel = new JLabel("Prix :");
        add(unitPriceLabel, gbc);

        gbc.gridy = 7;
        unitPriceTxtField = new JTextField(20);
        ((AbstractDocument) unitPriceTxtField.getDocument()).setDocumentFilter(new NumericFilter(true));
        add(unitPriceTxtField, gbc);

        // Category
        gbc.gridy = 8;
        JLabel categoryLabel = new JLabel("Catégorie :");
        add(categoryLabel, gbc);

        // Furnisher
        gbc.gridy = 10;
        JLabel furnisherLabel = new JLabel("Fournisseur :");
        add(furnisherLabel, gbc);

        // Selectors
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 9;
        categorySelector = new JComboBox<String>(categoryNames);
        add(categorySelector, gbc);

        gbc.gridy = 11;
        furnisherSelector = new JComboBox<String>(furnisherNames);
        add(furnisherSelector, gbc);

        // Empty Space
        gbc.gridy = 12;
        add(Box.createRigidArea(new Dimension(0, 10)), gbc);

        // Product Action Button
        gbc.gridy = 13;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.NONE;
        productActionBtn = new JButton(this.product == null ? "Ajouter Produit" : "Modifier Produit");
        productActionBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(productActionBtn, gbc);

        // Back Button
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        backBtn = new JButton("Retour");
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(backBtn, gbc);

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

            dispose();
            new ManageProductView(user);
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
