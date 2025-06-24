package views;

import javax.swing.*;
import models.AppUser;
import views.category.ManageCategoryView;
import models.Role;
import models.DAO.RoleDAO;
import views.components.AppView;
import views.furnisher.ManageFurnisherView;
import views.product.ManageProductView;
import views.sale.SaleView;

import java.awt.*;

public class MainMenuView extends AppView {
    private JButton manageFurnishersBtn;
    private JButton manageCategoriesBtn;
    private JButton manageProductsBtn;
    private JButton manageSalesBtn;
    private JButton createUserBtn;
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

        gbc.gridy = 6;
        createUserBtn = new JButton("Créer un Utilisateur");
        createUserBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(createUserBtn, gbc);

        // Empty Space
        addEmptySpace(0, 7, 10);

        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.NONE;

        // Empty Space
        addEmptySpace(0, 9, 10);

        // Back Button
        gbc.gridy = 10;
        gbc.fill = GridBagConstraints.NONE;
        backBtn = new JButton("Retour");
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(backBtn, gbc);

        

        // Insteractions
        manageProductsBtn.addActionListener(e -> {
            new ManageProductView(user);
            dispose();
        });

        manageSalesBtn.addActionListener(e ->{
            new SaleView();
            dispose();
        });


        manageFurnishersBtn.addActionListener(e -> {
            new ManageFurnisherView(user);
            dispose();
        });

        manageCategoriesBtn.addActionListener(e -> {
            new ManageCategoryView(user);
            dispose();
        });

        createUserBtn.addActionListener(e -> {
            new CreateUserView(user);

            dispose();
        });

        backBtn.addActionListener(e -> {
            new LoginView();
            dispose();
        });

        // Role Management
        Role userRole = new RoleDAO().getRoleByID(user.getUserRole());

        if (!userRole.getRoleName().equals("Manager") && !userRole.getRoleName().equals("Administrateur")) {
            manageFurnishersBtn.setEnabled(false);
            manageCategoriesBtn.setEnabled(false);
            manageProductsBtn.setEnabled(false);
            manageSalesBtn.setEnabled(false);
        }

        if (!userRole.getRoleName().equals("Administrateur"))
            createUserBtn.setEnabled(false);

        setVisible(true);
    }
}
