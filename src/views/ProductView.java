package views;

import javax.swing.*;

import models.Category;
import models.Furnisher;
import models.DAO.CategoryDAO;
import models.DAO.FurnisherDAO;
import models.Product;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductView {
    private JFrame frame;

    private JTextField nameTxtField;
    private JTextField quantityTxtField;
    private JTextField unitPriceTxtField;
    private JComboBox<String> categorySelector;
    private JComboBox<String> furnisherSelector;

    private JButton productActionBtn;

    private Product product;

    // Constructors
    public ProductView() {
        this(null);
    }

    public ProductView(Product product) {
        this.product = product;
        frame = new JFrame("Gestion des Produits");
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 300);

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

        // Add product fields
        JLabel nameLabel = new JLabel("Nom du Produit :");
        nameTxtField = new JTextField(20);

        JLabel quantityLabel = new JLabel("Quantité :");
        quantityTxtField = new JTextField(20);

        JLabel unitPriceLabel = new JLabel("Prix :");
        unitPriceTxtField = new JTextField(20);

        JLabel categoryLabel = new JLabel("Catégorie :");
        categorySelector = new JComboBox<String>(categoryNames);

        JLabel furnisherLabel = new JLabel("Fournisseur :");
        furnisherSelector = new JComboBox<String>(furnisherNames);

        productActionBtn = new JButton("Ajouter Produit");

        frame.add(nameLabel);
        frame.add(nameTxtField);
        frame.add(quantityLabel);
        frame.add(quantityTxtField);
        frame.add(unitPriceLabel);
        frame.add(unitPriceTxtField);
        frame.add(categoryLabel);
        frame.add(categorySelector);
        frame.add(furnisherLabel);
        frame.add(furnisherSelector);
        frame.add(productActionBtn);

        if (this.product != null) {
            nameTxtField.setText(product.getProductName());

            quantityTxtField.setText(String.valueOf(product.getProductQuantity()));

            unitPriceTxtField.setText(String.valueOf(product.getProductUnitPrice()));

            categorySelector.setSelectedItem(product.getCategory().getCategoryName());

            furnisherSelector.setSelectedItem(product.getFurnisher().getFurnisherName());

            productActionBtn.setText("Modifier Produit");
        }

        frame.setLocation(800, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    // Getters
    public String getProductName() {
        return nameTxtField.getText();
    }

    public int getProductQuantity() {
        return Integer.parseInt(quantityTxtField.getText());
    }

    public double getProductUnitPrice() {
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
