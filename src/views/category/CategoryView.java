package views.category;

import javax.swing.*;

import models.AppUser;
import models.Category;
import views.components.AppView;
import views.product.ManageProductView;

import java.awt.*;
import java.awt.event.ActionListener;

public class CategoryView extends AppView{
    private JTextField nameTxtField;
    
    private JButton categoryActionBtn;
    private JButton backBtn;

    private AppUser user;
    private Category category;

    // Constructor    
    public CategoryView(AppUser user, Category category) {
        super("Gestion des Catégories", 600, 400, false);
        this.category = category;
        this.user = user;


        // Title
        addTitleComponent(0, 0, 2);
        
        // Name
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        JLabel nameLabel = new JLabel("Nom de la Catégorie :");
        contentPanel.add(nameLabel, gbc);

        gbc.gridy = 3;
        nameTxtField = new JTextField(20);
        contentPanel.add(nameTxtField, gbc);

        // Category Action Button
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.NONE;
        categoryActionBtn = new JButton(this.category == null ? "Ajouter Catégorie" : "Modifier Catégorie");
        categoryActionBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(categoryActionBtn, gbc);

        // Back Button
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        backBtn = new JButton("Retour");
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(backBtn, gbc);

        // Fields Initialization if necessary
        if (this.category != null) {
            nameTxtField.setText(category.getCategoryName());
        }

        // Interactions
        backBtn.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(
                    this,
                    "Voulez-vous vraiment revenir en arrière ? Le champ de saisie sera perdu.",
                    "Confirmation",
                    JOptionPane.OK_CANCEL_OPTION);

            if (response == JOptionPane.CANCEL_OPTION)
                return;

            new ManageCategoryView(user);
            dispose();
        });


        setVisible(true);
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
