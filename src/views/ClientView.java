package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.border.EmptyBorder;

public class ClientView extends JFrame {
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
        frame = new JFrame("Ajout d'un Client");

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

        imageLabel = new JLabel(new ImageIcon("path_to_image.jpg"));

        

        btnAjouter = new JButton("Ajouter");
        btnAjouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                addClient();
        }});
        

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

    public void addClient(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String url = "jdbc:sqlserver://163.5.143.146:1433;databaseName=GestStockJava;encrypt=false";
            String user = "connect";
            String password = "?5Q7_53RV3R?*";

            Connection con = DriverManager.getConnection(url, user, password);


            String query = "INSERT INTO Client (client_lastname, client_firstname, client_adress, client_complement, client_zipcode, client_city, client_country, client_phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);

            String LastName = txtLastName.getText();
            String FirstName = txtFirstName.getText();
            String Address = txtAddress.getText();
            String City = txtCity.getText();
            String Complement = txtComplement.getText();
            String Country = txtCountry.getText();
            String Zipcode = txtZipcode.getText();
            String Phone = txtPhone.getText();

            pstmt.setString(1, LastName);
            pstmt.setString(2, FirstName);
            pstmt.setString(3, Address);
            pstmt.setString(4, Complement);
            pstmt.setString(5, Zipcode);
            pstmt.setString(6, City);
            pstmt.setString(7, Country);
            pstmt.setString(8, Phone);

            int rowsAffected = pstmt.executeUpdate();

            pstmt.close();
            con.close();

            JOptionPane.showMessageDialog(frame, "Clients ajoutée avec succès !");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur lors de l'ajout du Clients : " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur lors de l'ajout du Clients : " + ex.getMessage());
        }

    }
    public static void main(String[] args) {
        new ClientView();
    }

}