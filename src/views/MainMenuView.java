package views;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import models.AppUser;

public class MainMenuView {
    private JFrame frame;

    private JButton manageFurnishersBtn;
    private JButton manageCategoriesBtn;
    private JButton manageProductsBtn;
    private JButton manageSalesBtn;

    private AppUser user;

    public MainMenuView(AppUser user) {
        this.user = user;

        frame = new JFrame("Menu Principal");
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 200);

        manageFurnishersBtn = new JButton("Gérer les Fournisseurs");
        manageCategoriesBtn = new JButton("Gérer les Catégories");
        manageProductsBtn = new JButton("Gérer les Produits");
        manageSalesBtn = new JButton("Gérer les Ventes");

        manageFurnishersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // new ManageFurnisherView(user);
            }
        });

        manageCategoriesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // new ManageCategoryView(user);
            }
        });

        manageProductsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageProductView(user);
            }
        });

        manageSalesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // new ManageSaleView(user);
            }
        });

        frame.add(manageFurnishersBtn);
        frame.add(manageCategoriesBtn);
        frame.add(manageProductsBtn);
        frame.add(manageSalesBtn);

        if (user.getUserRole().equals("manager")) {
            manageFurnishersBtn.setEnabled(true);
            manageCategoriesBtn.setEnabled(true);
            manageProductsBtn.setEnabled(true);
            manageSalesBtn.setEnabled(true);
        } else {
            manageFurnishersBtn.setEnabled(false);
            manageCategoriesBtn.setEnabled(false);
            manageProductsBtn.setEnabled(false);
            manageSalesBtn.setEnabled(false);
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
