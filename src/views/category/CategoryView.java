package views.category;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CategoryView {
    private JFrame frame;
    private JTextField nameTxtField;
    private JButton addCategoryBtn;

    // Constructor
    public CategoryView() {
        frame = new JFrame("Gestion de Stock");
        frame.setLayout(new FlowLayout());
        frame.setSize(300, 200);

        JLabel nameLabel = new JLabel("Nom de la Catégorie :");
        nameTxtField = new JTextField(20);

        addCategoryBtn = new JButton("Ajouter Catégorie");

        frame.add(nameLabel);
        frame.add(nameTxtField);
        frame.add(addCategoryBtn);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }

    // Getter
    public String getCategoryName() {
        return nameTxtField.getText();
    }

    // Setter
    public void setAddCategoryListener(ActionListener listener) {
        addCategoryBtn.addActionListener(listener);
    }
}
