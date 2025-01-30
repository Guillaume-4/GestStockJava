package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Date;

public class SaleView {
    private JFrame frame;
    private JTextField quantityTxtField;
    private JTextField dateTxtField;
    private JTextField productIDTxtField;
    private JButton addSaleBtn;

    // Constructor
    public SaleView() {
        frame = new JFrame("Ajout d'un Produit");
        frame.setLayout(new BorderLayout());
        frame.setSize(400, 300);

        JLabel quantityLabel = new JLabel("Quantité :");
        quantityTxtField = new JTextField(15);
        JLabel dateLabel = new JLabel("Date :");
        dateTxtField = new JTextField(15);
        JLabel productIDLabel = new JLabel("ProductID :");
        productIDTxtField = new JTextField(15);

        addSaleBtn = new JButton("Ajouter");

        frame.add(quantityLabel);
        frame.add(quantityTxtField);
        frame.add(dateLabel);
        frame.add(dateTxtField);
        frame.add(productIDLabel);
        frame.add(productIDTxtField);
        frame.add(addSaleBtn);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }

    // Getters
    public int getQuantity() {
        return Integer.parseInt(quantityTxtField.getText(), 10);
    }

    public Date getDate() {
        return Date.valueOf(dateTxtField.getText());
    }

    public int getProductID() {
        return Integer.parseInt(productIDTxtField.getText(), 10);
    }

    // Setters
    public void setAddSaleListener(ActionListener listener) {
        addSaleBtn.addActionListener(listener);
    }
}
