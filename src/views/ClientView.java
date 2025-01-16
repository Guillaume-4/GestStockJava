package views;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ClientView extends JFrame {
    private JFrame frame;
    private JTextField lastNameTxtField;
    private JTextField firstNameTxtField;
    private JTextField adressTxtField;
    private JTextField complementTxtField;
    private JTextField zipCodeTxtField;
    private JTextField cityTxtField;
    private JTextField countryTxtField;
    private JTextField phoneTxtField;
    private JButton addClientBtn;

    // Constructor
    public ClientView() {
        frame = new JFrame("Ajout d'un Client");
        frame.setSize(400, 300);

        JLabel labelLastName = new JLabel("Nom :");
        lastNameTxtField = new JTextField(15);
        JLabel labelFirstName = new JLabel("Prénom :");
        firstNameTxtField = new JTextField(15);
        JLabel labelAddress = new JLabel("Addresse :");
        adressTxtField = new JTextField(15);
        JLabel labelCity = new JLabel("Ville :");
        cityTxtField = new JTextField(15);
        JLabel labelComplement = new JLabel("Complement :");
        complementTxtField = new JTextField(15);
        JLabel labelCountry = new JLabel("Pays :");
        countryTxtField = new JTextField(15);
        JLabel labelZipcode = new JLabel("Code Postal :");
        zipCodeTxtField = new JTextField(15);
        JLabel labelPhone = new JLabel("Téléphone :");
        phoneTxtField = new JTextField(15);

        addClientBtn = new JButton("Ajouter");

        frame.add(labelLastName);
        frame.add(lastNameTxtField);
        frame.add(labelFirstName);
        frame.add(firstNameTxtField);
        frame.add(labelAddress);
        frame.add(adressTxtField);
        frame.add(labelCity);
        frame.add(cityTxtField);
        frame.add(labelComplement);
        frame.add(complementTxtField);
        frame.add(labelCountry);
        frame.add(countryTxtField);
        frame.add(labelZipcode);
        frame.add(zipCodeTxtField);
        frame.add(labelPhone);
        frame.add(phoneTxtField);
        frame.add(addClientBtn);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }

    // Getters
    public String getLastName() {
        return lastNameTxtField.getText();
    }

    public String getFirstName() {
        return firstNameTxtField.getText();
    }

    public String getAddress() {
        return adressTxtField.getText();
    }

    public String getComplement() {
        return complementTxtField.getText();
    }

    public String getCity() {
        return cityTxtField.getText();
    }

    public String getCountry() {
        return countryTxtField.getText();
    }

    public String getZipCode() {
        return zipCodeTxtField.getText();
    }

    public String getPhone() {
        return phoneTxtField.getText();
    }

    // Setter
    public void setAddClientListener(ActionListener listener) {
        addClientBtn.addActionListener(listener);
    }
}