package views;

import javax.swing.*;

import models.Category;

import java.awt.*;
import java.awt.event.ActionListener;

public class CategoryView {
    private JFrame frame;

    private JTextField nameTxtField;
    
    private JButton categoryActionBtn;

    private Category category;

    // Constructor
    public CategoryView() {
        this(null);
    }
    
    public CategoryView(Category category) {
        this.category = category;
        frame = new JFrame("Gestion des Catégories");
        frame.setLayout(new FlowLayout());
        frame.setSize(300, 200);

        JLabel nameLabel = new JLabel("Nom de la Catégorie :");
        nameTxtField = new JTextField(20);

        categoryActionBtn = new JButton("Ajouter Catégorie");

        frame.add(nameLabel);
        frame.add(nameTxtField);
        frame.add(categoryActionBtn);

        if (this.category != null) {
            nameTxtField.setText(category.getCategoryName());

            categoryActionBtn.setText("Modifier Catégorie");
        }

        frame.setLocation(800, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    // Getter
    public String getCategoryName() {
        return nameTxtField.getText();
    }

    public Category getCategory() {
        return category;
    }

    // Setter
    public void setCategoryListener(ActionListener listener) {
        categoryActionBtn.addActionListener(listener);
    }
}
