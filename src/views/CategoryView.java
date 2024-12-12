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

public class CategoryView {
    private JFrame frame;
    private JTextField txtNom;
    private JLabel labelNom;
    private JLabel imageLabel;
    private JButton btnAjouter;

    public CategoryView() {
        frame = new JFrame("Ajout d'une Catégorie");

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(5, 5, 10, 10));
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        labelNom = new JLabel("Nom de la catégorie :");

        txtNom = new JTextField(2);

        btnAjouter = new JButton("Ajouter");
        btnAjouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                addCategory();
            }
        });

        imageLabel = new JLabel(new ImageIcon("path_to_image.jpg"));

        contentPanel.add(labelNom);
        contentPanel.add(txtNom);
        contentPanel.add(new JLabel());
        contentPanel.add(imageLabel);
        contentPanel.add(new JLabel());
        contentPanel.add(btnAjouter);

        frame.add(contentPanel, BorderLayout.CENTER);

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void addCategory() {
        try {
            // Connexion à la base de données
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // URL JDBC
            String url = "jdbc:sqlserver://163.5.143.146:1433;databaseName=GestStockJava;encrypt=false";
            String user = "connect";
            String password = "?5Q7_53RV3R?*";

            // Connexion à la base de données
            Connection con = DriverManager.getConnection(url, user, password);

            // Préparation de la requête SQL
            String query = "INSERT INTO Category (category_name) VALUES (?)";
            PreparedStatement pstmt = con.prepareStatement(query);

            // Récupération des données saisies par l'utilisateur
            String name = txtNom.getText();

            // Remplissage des paramètres de la requête SQL
            pstmt.setString(1, name);

            // Exécution de la requête SQL
            int rowsAffected = pstmt.executeUpdate();

            // Fermeture de la connexion et du PreparedStatement
            pstmt.close();
            con.close();

            // Affichage d'un message de succès
            JOptionPane.showMessageDialog(frame, "Catégorie ajoutée avec succès !");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur lors de l'ajout de la catégorie : " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur lors de l'ajout de la catégorie : " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new CategoryView();
    }
}
