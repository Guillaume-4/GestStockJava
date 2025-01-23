package views;

import javax.swing.*;

import models.Category;
import models.Furnisher;
import models.DAO.CategoryDAO;
import models.DAO.FurnisherDAO;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class ProductView {
    private JFrame frame;

    private JTextField nameTxtField;
    private JTextField quantityTxtField;
    private JTextField unitPriceTxtField;
    private JComboBox<String> categorySelector;
    private JComboBox<String> furnisherSelector;

    private JTextField startDateTxtField;
    private JTextField endDateTxtField;
    private JTextArea reportArea;

    private JButton addProductBtn;
    private JButton generateReportBtn;

    public ProductView() {
        frame = new JFrame("Gestion de Stock");
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
        addProductBtn = new JButton("Ajouter Produit");

        // Report generation fields
        JLabel startDateLabel = new JLabel("Date de Début (YYYY-MM-DD) :");
        startDateTxtField = new JTextField(10);
        JLabel endDateLabel = new JLabel("Date de Fin (YYYY-MM-DD) :");
        endDateTxtField = new JTextField(10);
        generateReportBtn = new JButton("Générer Rapport");

        reportArea = new JTextArea(10, 30);
        reportArea.setEditable(false);

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
        frame.add(addProductBtn);

        frame.add(startDateLabel);
        frame.add(startDateTxtField);
        frame.add(endDateLabel);
        frame.add(endDateTxtField);
        frame.add(generateReportBtn);
        frame.add(new JScrollPane(reportArea));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    public LocalDate getStartDate() {
        return LocalDate.parse(startDateTxtField.getText());
    }

    public LocalDate getEndDate() {
        return LocalDate.parse(endDateTxtField.getText());
    }

    // Setters
    public void setAddProductListener(ActionListener listener) {
        addProductBtn.addActionListener(listener);
    }

    public void setGenerateReportListener(ActionListener listener) {
        generateReportBtn.addActionListener(listener);
    }

    // Methods
    public void showReport(String produitNom, double prix, int ventesTotales) {
        reportArea.setText("Rapport pour le produit : " + produitNom + "\n");
        reportArea.append("Prix du produit : " + prix + "\n");
        reportArea.append("Ventes totales dans la période : " + ventesTotales + "\n");
    }
}
