package views;

import javax.swing.*;
import java.awt.*;
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

    public static void main(String[] args) {
        new CategoryView();
    }
}
