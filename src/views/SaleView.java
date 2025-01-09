package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.border.EmptyBorder;

public class SaleView {
    private JFrame frame;
    private JTextField txtQuantity;
    private JTextField txtDate;
    private JTextField txtProductID;

    private JLabel labelQuantity;
    private JLabel labelDate;
    private JLabel labelProductID;
    private JLabel imageLabel;
    private JButton btnAjouter;

    public SaleView() {
        frame = new JFrame("Ajout d'un Produit");

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(10, 8, 10, 10));
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        labelQuantity = new JLabel("Quantité :");
        labelDate = new JLabel("Date :");
        labelProductID = new JLabel("ProductID :");

        txtQuantity = new JTextField(15);
        txtDate = new JTextField(15);
        txtProductID = new JTextField(15);
        

        btnAjouter = new JButton("Ajouter");
        //btnAjouter.addActionListener(new ActionListener() {
            //public void actionPerformed(ActionEvent event){
            //    addSale();
            //}
        //});

        imageLabel = new JLabel(new ImageIcon("path_to_image.jpg"));

        contentPanel.add(labelQuantity);
        contentPanel.add(txtQuantity);
        
        contentPanel.add(labelDate);
        contentPanel.add(txtDate);

        contentPanel.add(labelProductID);
        contentPanel.add(txtProductID);

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
        new SaleView();
    }

    public Integer getQuantity() {
        return Integer.parseInt(txtQuantity.getText(),10);
    }

    @SuppressWarnings("deprecation")
    public Date getDate() {
        return new Date(txtDate.getText());
    }

    public void setAddSaleListener(ActionListener listener) {
        btnAjouter.addActionListener(listener);
    }
}
