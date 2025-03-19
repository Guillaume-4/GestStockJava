package views;

import javax.swing.*;
import models.AppUser;
import views.components.AppView;
import views.furnisher.ManageFurnisherView;
import views.product.ManageProductView;

import java.awt.*;

public class MainMenuView extends AppView {
    private JButton manageFurnishersBtn;
    private JButton manageCategoriesBtn;
    private JButton manageProductsBtn;
    private JButton manageSalesBtn;
    private JButton backBtn;
    private AppUser user;

    public MainMenuView(AppUser user) {
        super("Menu Principal", 600, 400, false);
        this.user = user;

        // Title
        addTitleComponent(0, 0, 1);

        // Buttons
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 2;
        manageFurnishersBtn = new JButton("Gérer les Fournisseurs");
        manageFurnishersBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(manageFurnishersBtn, gbc);

        gbc.gridy = 3;
        manageCategoriesBtn = new JButton("Gérer les Catégories");
        manageCategoriesBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(manageCategoriesBtn, gbc);

        gbc.gridy = 4;
        manageProductsBtn = new JButton("Gérer les Produits");
        manageProductsBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(manageProductsBtn, gbc);

        gbc.gridy = 5;
        manageSalesBtn = new JButton("Gérer les Ventes");
        manageSalesBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(manageSalesBtn, gbc);

        // Empty Space
        addEmptySpace(0, 6, 10);

        // Back Button
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.NONE;
        backBtn = new JButton("Retour");
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(backBtn, gbc);

        // Insteractions
        manageProductsBtn.addActionListener(e -> {
            new ManageProductView(user);
            dispose();
        });

        manageFurnishersBtn.addActionListener(e -> {
            new ManageFurnisherView(user);
            dispose();
        });

        backBtn.addActionListener(e -> {
            new LoginView();
            dispose();
        });

        // Role Management
        if (!user.getUserRole().equals("manager") && !user.getUserRole().equals("administrator")) {
            manageFurnishersBtn.setEnabled(false);
            manageCategoriesBtn.setEnabled(false);
            manageProductsBtn.setEnabled(false);
            manageSalesBtn.setEnabled(false);
        }

        setVisible(true);
    }
}
