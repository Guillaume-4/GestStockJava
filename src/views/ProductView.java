package views;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class ProductView {
    private JFrame frame;
    private JTextField txtNom;
    private JTextField txtQuantite;
    private JTextField txtPrixUnitaire;
    private JTextField txtFournisseur;
    private JLabel labelNom;
    private JLabel labelQuantite;
    private JLabel labelPrixUnitaire;
    private JLabel labelFournisseur;
    private JLabel imageLabel;
    private JButton btnAjouter;

    public ProductView() {
        frame = new JFrame("Ajout d'un Produit");

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(8, 2, 10, 10));
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        labelNom = new JLabel("Nom du Produit :");
        labelQuantite = new JLabel("Quantité du Produit :");
        labelPrixUnitaire = new JLabel("Prix Unitaire du Produit :");
        labelFournisseur = new JLabel("Fournisseur du Produit :");

        txtNom = new JTextField(15);
        txtQuantite = new JTextField(15);
        txtPrixUnitaire = new JTextField(15);
        txtFournisseur = new JTextField(15);

        btnAjouter = new JButton("Ajouter");

        imageLabel = new JLabel(new ImageIcon("path_to_image.jpg"));

        contentPanel.add(labelNom);
        contentPanel.add(txtNom);
        contentPanel.add(labelQuantite);
        contentPanel.add(txtQuantite);
        contentPanel.add(labelPrixUnitaire);
        contentPanel.add(txtPrixUnitaire);
        contentPanel.add(labelFournisseur);
        contentPanel.add(txtFournisseur);
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
        new ProductView();
    }
}
