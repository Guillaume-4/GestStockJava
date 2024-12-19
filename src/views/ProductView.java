package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProductView {
    private JFrame frame;
    private JTextField nameTxtField;
    private JTextField quantityTxtField;
    private JTextField unitPriceTxtField;
    private JButton btnAddProduct;

    public ProductView() {
        frame = new JFrame("Gestion de Stock");
        frame.setLayout(new FlowLayout());
        frame.setSize(300, 200);

        JLabel labelName = new JLabel("Nom du produit :");
        nameTxtField = new JTextField(20);

        JLabel labelQuantity = new JLabel("Quantité :");
        quantityTxtField = new JTextField(20);

        JLabel labelPrice = new JLabel("Prix :");
        unitPriceTxtField = new JTextField(20);

        btnAddProduct = new JButton("Ajouter Produit");

        frame.add(labelName);
        frame.add(nameTxtField);
        frame.add(labelQuantity);
        frame.add(quantityTxtField);
        frame.add(labelPrice);
        frame.add(unitPriceTxtField);
        frame.add(btnAddProduct);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public String getProductName() {
        return nameTxtField.getText();
    }

    public int getProductQuantity() {
        return Integer.parseInt(quantityTxtField.getText());
    }

    public double getProductUnitPrice() {
        return Double.parseDouble(unitPriceTxtField.getText());
    }

    public void setAddProductListener(ActionListener listener) {
        btnAddProduct.addActionListener(listener);
    }
}
