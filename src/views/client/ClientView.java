package views.client;

import javax.swing.*;
import javax.swing.text.AbstractDocument;

import models.AppUser;
import models.Category;
import models.Client;
import models.DAO.CategoryDAO;
import models.DAO.ClientDAO;
import views.components.AppView;
import views.components.NumericFilter;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.util.List;

public class ClientView extends AppView {
    private JTextField nameTxtField;
    private JTextField addressTxtField;
    private JTextField cityTxtField;
    private JTextField phoneTxtField;
    private JTextField zipcodeTxtField;
    private JTextField countryTxTField;
    private JComboBox<String> clientSelector;
    private JButton clientActionBtn;
    private JButton backBtn;
    private Client client;
    private AppUser user;

    // Constructors
    public ClientView(AppUser user, Client client) {
        super(client == null ? "Ajout du Client" : "Modification du Client", 600, 600, false);
        this.user = user;
        this.client = client;

        // Title
        addTitleComponent(0, 0, 2);

        // Category list
        List<Category> categories = new CategoryDAO().getAllCategories();
        String[] categoryNames = new String[categories.size()];

        for (int i = 0; i < categories.size(); i++)
            categoryNames[i] = categories.get(i).getCategoryName();

        // client List
        List<Client> clients = new ClientDAO().getclients();
        String[] clientNames = new String[clients.size()];

        for (int i = 0; i < clients.size(); i++)
            clientNames[i] = clients.get(i).getClient_name();

        // Name
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        JLabel nameLabel = new JLabel("Nom du Client");
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
        gbc.gridy = 11;
        JLabel phoneLabel = new JLabel("Numéro de téléphone :");
        contentPanel.add(phoneLabel, gbc);

        // client
        gbc.gridy = 13;
        JLabel cpLabel = new JLabel("Code Postale :");
        contentPanel.add(cpLabel, gbc);

         // Country
         gbc.gridy = 9;
         gbc.anchor = GridBagConstraints.LINE_START;
         JLabel nameCountry = new JLabel("Pays");
         contentPanel.add(nameCountry, gbc);
        
         gbc.gridy = 10;
         countryTxTField = new JTextField(20);
         contentPanel.add(countryTxTField, gbc);

        // Selectors
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 12;
        phoneTxtField = new JTextField(20);
        ((AbstractDocument) phoneTxtField.getDocument()).setDocumentFilter(new NumericFilter(true));
        contentPanel.add(phoneTxtField, gbc);
        contentPanel.add(phoneTxtField, gbc);

        gbc.gridy = 14;
        zipcodeTxtField = new JTextField(20);
        ((AbstractDocument) zipcodeTxtField.getDocument()).setDocumentFilter(new NumericFilter(true));
        contentPanel.add(zipcodeTxtField, gbc);

        // Empty Space
        addEmptySpace(0, 14, 10);

        // client Action Button
        gbc.gridy = 15;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.NONE;
        clientActionBtn = new JButton(this.client == null ? "Ajouter Client" : "Modifier Client");
        clientActionBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(clientActionBtn, gbc);

        // Back Button
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        backBtn = new JButton("Retour");
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(backBtn, gbc);

        // Fields Initialization if necessary
        if (this.client != null) {
            nameTxtField.setText(client.getClient_name());
            addressTxtField.setText(String.valueOf(client.getClient_address()));
            cityTxtField.setText(String.valueOf(client.getClient_city()));
            countryTxTField.setText(String.valueOf(client.getClient_country()));
            phoneTxtField.setText(String.valueOf(client.getClient_phone()));
            zipcodeTxtField.setText(String.valueOf(client.getClient_zipcode()));
            
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

            new ManageClientView(user);
            dispose();
        });

        setVisible(true);
    }

    // Getters
    public String getclientName() {
        return nameTxtField.getText();
    }

    public String getclientAdress() {
        if (addressTxtField.getText().isEmpty())
            return null;

        return addressTxtField.getText();
    }

    public String getclientCity() {
        if (cityTxtField.getText().isEmpty())
            return null;

        return cityTxtField.getText();
    }

    public String getclientZipCode() {
        if (cityTxtField.getText().isEmpty())
            return null;

        return zipcodeTxtField.getText();
    }

    public String getclientclientName() {
        return clientSelector.getSelectedItem().toString();
    }

    public Client getclient() {
        return client;
    }

    // Setters
    public void setclientListener(ActionListener listener) {
        clientActionBtn.addActionListener(listener);
    }

    public String getclientComplement() {
        return null;
    }

    public String getclientCountry() {
        return countryTxTField.getText();
    }

    public String getclientPhone() {
        return phoneTxtField.getText();
    }
}
