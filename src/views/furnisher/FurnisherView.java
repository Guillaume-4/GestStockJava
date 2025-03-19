package views.furnisher;

import javax.swing.*;
import javax.swing.text.AbstractDocument;

import models.AppUser;
import models.Category;
import models.Furnisher;
import models.DAO.CategoryDAO;
import models.DAO.FurnisherDAO;
import views.components.AppView;
import views.components.NumericFilter;
import models.Furnisher;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.util.List;

public class FurnisherView extends AppView {
    private JTextField nameTxtField;
    private JTextField addressTxtField;
    private JTextField cityTxtField;
    private JTextField phoneTxtField;
    private JTextField zipcodeTxtField;
    private JComboBox<String> furnisherSelector;
    private JButton FurnisherActionBtn;
    private JButton backBtn;
    private Furnisher Furnisher;
    private AppUser user;

    // Constructors
    public FurnisherView(AppUser user, Furnisher Furnisher) {
        super(Furnisher == null ? "Ajout du Fournisseur" : "Modification du Fournisseur", 600, 600, false);
        this.user = user;
        this.Furnisher = Furnisher;

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
        JLabel nameLabel = new JLabel("Nom du Fournisseur");
        contentPanel.add(nameLabel, gbc);

        gbc.gridy = 3;
        nameTxtField = new JTextField(20);
        contentPanel.add(nameTxtField, gbc);

        // Quantity
        gbc.gridy = 4;
        JLabel addressLabel = new JLabel("Addresse :");
        contentPanel.add(addressLabel, gbc);

        gbc.gridy = 5;
        addressTxtField = new JTextField(20);
        contentPanel.add(addressTxtField, gbc);

        // Unit Price
        gbc.gridy = 6;
        JLabel cityLabel = new JLabel("Ville :");
        contentPanel.add(cityLabel, gbc);

        gbc.gridy = 7;
        cityTxtField = new JTextField(20);
        contentPanel.add(cityTxtField, gbc);

        // Category
        gbc.gridy = 8;
        JLabel phoneLabel = new JLabel("Numéro de téléphone :");
        contentPanel.add(phoneLabel, gbc);

        // Furnisher
        gbc.gridy = 10;
        JLabel cpLabel = new JLabel("Code Postale :");
        contentPanel.add(cpLabel, gbc);

        // Selectors
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 9;
        phoneTxtField = new JTextField(20);
        ((AbstractDocument) phoneTxtField.getDocument()).setDocumentFilter(new NumericFilter(true));
        contentPanel.add(phoneTxtField, gbc);
        contentPanel.add(phoneTxtField, gbc);

        gbc.gridy = 11;
        zipcodeTxtField = new JTextField(20);
        ((AbstractDocument) zipcodeTxtField.getDocument()).setDocumentFilter(new NumericFilter(true));
        contentPanel.add(zipcodeTxtField, gbc);

        // Empty Space
        addEmptySpace(0, 12, 10);

        // Furnisher Action Button
        gbc.gridy = 13;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.NONE;
        FurnisherActionBtn = new JButton(this.Furnisher == null ? "Ajouter Fournisseur" : "Modifier Fournisseur");
        FurnisherActionBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(FurnisherActionBtn, gbc);

        // Back Button
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        backBtn = new JButton("Retour");
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(backBtn, gbc);

        // Fields Initialization if necessary
        if (this.Furnisher != null) {
            nameTxtField.setText(Furnisher.getFurnisherName());
            addressTxtField.setText(String.valueOf(Furnisher.getFurnisherAdress()));
            cityTxtField.setText(String.valueOf(Furnisher.getFurnisherCity()));
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

            new ManageFurnisherView(user);
            dispose();
        });

        setVisible(true);
    }

    // Getters
    public String getFurnisherName() {
        return nameTxtField.getText();
    }

    public String getFurnisherAdress() {
        if (addressTxtField.getText().isEmpty())
            return null;

        return addressTxtField.getText();
    }

    public String getFurnisherCity() {
        if (cityTxtField.getText().isEmpty())
            return null;

        return cityTxtField.getText();
    }

    public String getFurnisherZipCode() {
        if (cityTxtField.getText().isEmpty())
            return null;

        return zipcodeTxtField.getText();
    }

    public String getFurnisherFurnisherName() {
        return furnisherSelector.getSelectedItem().toString();
    }

    public Furnisher getFurnisher() {
        return Furnisher;
    }

    // Setters
    public void setFurnisherListener(ActionListener listener) {
        FurnisherActionBtn.addActionListener(listener);
    }

    public String getFurnisherComplement() {
        return null;
    }

    public String getFurnisherCountry() {
        return null;
    }

    public String getFurnisherPhone() {
        return phoneTxtField.getText();
    }
}
