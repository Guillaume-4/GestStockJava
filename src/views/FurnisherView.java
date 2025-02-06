package views;

import javax.swing.*;

import models.Furnisher;

import java.awt.*;
import java.awt.event.ActionListener;

public class FurnisherView {
    private JFrame frame;
    private JTextField nameTxtField;
    private JTextField adressTxtField;
    private JTextField complementTxtField;
    private JTextField zipCodeTxtField;
    private JTextField cityTxtField;
    private JTextField countryTxtField;
    private JTextField phoneTxtField;
    private JButton addFurnisherBtn;

    private Furnisher furnisher;

    public FurnisherView() {
        this(null);
    }

    // Constructor
    public FurnisherView(Furnisher furnisher) {
        this.furnisher = furnisher;
        frame = new JFrame("Gestion de Stock");
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 300);

        JLabel nameLabel = new JLabel("Nom :");
        nameTxtField = new JTextField(15);

        JLabel adressLabel = new JLabel("Addresse :");
        adressTxtField = new JTextField(15);

        JLabel cityLabel = new JLabel("Ville :");
        cityTxtField = new JTextField(15);

        JLabel complementLabel = new JLabel("Complement :");
        complementTxtField = new JTextField(15);

        JLabel countryLabel = new JLabel("Pays :");
        countryTxtField = new JTextField(15);

        JLabel zipCodeLabel = new JLabel("Code Postal :");
        zipCodeTxtField = new JTextField(15);

        JLabel phoneLabel = new JLabel("Téléphone :");
        phoneTxtField = new JTextField(15);

        addFurnisherBtn = new JButton("Ajouter Fournisseur");

        frame.add(nameLabel);
        frame.add(nameTxtField);
        frame.add(adressLabel);
        frame.add(adressTxtField);
        frame.add(cityLabel);
        frame.add(cityTxtField);
        frame.add(complementLabel);
        frame.add(complementTxtField);
        frame.add(countryLabel);
        frame.add(countryTxtField);
        frame.add(zipCodeLabel);
        frame.add(zipCodeTxtField);
        frame.add(phoneLabel);
        frame.add(phoneTxtField);
        frame.add(addFurnisherBtn);

        if (this.furnisher != null) {
            nameTxtField.setText(furnisher.getFurnisherName());

            adressTxtField.setText(String.valueOf(furnisher.getFurnisherAdress()));

            cityTxtField.setText(String.valueOf(furnisher.getFurnisherCity()));

            complementTxtField.setText(String.valueOf(furnisher.getFurnisherComplement()));
            countryTxtField.setText(String.valueOf(furnisher.getFurnisherCountry()));
            cityTxtField.setText(String.valueOf(furnisher.getFurnisherCity()));
            zipCodeTxtField.setText(String.valueOf(furnisher.getFurnisherZipcode()));
            phoneTxtField.setText(String.valueOf(furnisher.getFurnisherPhone()));
            

            addFurnisherBtn.setText("Modifier Fournisseur");
        }

        frame.setLocation(800, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    // Getter
    public String getFurnisherName() {
        return nameTxtField.getText();
    }

    public String getFurnisherAdress() {
        return adressTxtField.getText();
    }

    public String getFurnisherComplement() {
        return complementTxtField.getText();
    }

    public String getFurnisherZipCode() {
        return zipCodeTxtField.getText();
    }

    public String getFurnisherCity() {
        return cityTxtField.getText();
    }

    public String getFurnisherCountry() {
        return countryTxtField.getText();
    }

    public String getFurnisherPhone() {
        return phoneTxtField.getText();
    }

    // Setter
    public void setAddFurnisherListener(ActionListener listener) {
        addFurnisherBtn.addActionListener(listener);
    }

    public Furnisher getFurnisher() {
        return furnisher;
    }
    
}
