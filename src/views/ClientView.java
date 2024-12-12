package views;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class ClientView {
    private JFrame frame;
    private JTextField txtLastName;
    private JTextField txtFirstName;
    private JTextField txtAddress;
    private JTextField txtComplement;
    private JTextField txtZipcode;
    private JTextField txtCity;
    private JTextField txtCountry;
    private JTextField txtPhone;
    private JLabel labelLastName;
    private JLabel labelFirstName;
    private JLabel labelAddress;
    private JLabel labelComplement;
    private JLabel labelZipcode;
    private JLabel labelCity;
    private JLabel labelCountry;
    private JLabel labelPhone;
    private JLabel imageLabel;
    private JButton btnAjouter;

    public ClientView() {
        frame = new JFrame("Ajout d'un Produit");

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(10, 8, 10, 10));
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        labelLastName = new JLabel("Nom :");
        labelFirstName = new JLabel("Prénom :");
        labelAddress = new JLabel("Addresse :");
        labelCity = new JLabel("Ville :");
        labelComplement = new JLabel("Complement :");
        labelCountry = new JLabel("Pays :");
        labelZipcode = new JLabel("Code Postal :");
        labelPhone = new JLabel("Téléphone :");

        txtFirstName = new JTextField(15);
        txtLastName = new JTextField(15);
        txtAddress = new JTextField(15);
        txtCity = new JTextField(15);
        txtComplement = new JTextField(15);
        txtCountry = new JTextField(15);
        txtZipcode = new JTextField(15);
        txtPhone = new JTextField(15);
        

        btnAjouter = new JButton("Ajouter");

        imageLabel = new JLabel(new ImageIcon("path_to_image.jpg"));

        contentPanel.add(labelLastName);
        contentPanel.add(txtLastName);

        contentPanel.add(labelFirstName);
        contentPanel.add(txtFirstName);
        
        contentPanel.add(labelAddress);
        contentPanel.add(txtAddress);

        contentPanel.add(labelCity);
        contentPanel.add(txtCity);

        contentPanel.add(labelComplement);
        contentPanel.add(txtComplement);

        contentPanel.add(labelCountry);
        contentPanel.add(txtCountry);

        contentPanel.add(labelZipcode);
        contentPanel.add(txtZipcode);

        contentPanel.add(labelPhone);
        contentPanel.add(txtPhone);

        contentPanel.add(new JLabel());
        contentPanel.add(imageLabel);
        contentPanel.add(new JLabel());
        contentPanel.add(btnAjouter);

        frame.add(contentPanel, BorderLayout.CENTER);

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ClientView();
    }
}
