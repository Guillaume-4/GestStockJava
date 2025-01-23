package views;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import models.AppUser;

public class ManageProductView {
    private JFrame frame;

    private JButton addProductBtn;
    private JButton updateProductBtn;
    private JButton deleteProductBtn;

    private AppUser user;

    public ManageProductView(AppUser user) {
        this.user = user;

        frame = new JFrame("Gestion des Produits");
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 200);

        addProductBtn = new JButton("Ajouter Produit");
        updateProductBtn = new JButton("Modifier Produit");
        deleteProductBtn = new JButton("Supprimer Produit");

        addProductBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code pour ajouter un produit
            }
        });

        updateProductBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code pour modifier un produit
            }
        });

        deleteProductBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (user.getUserRole().equals("manager"))
                    JOptionPane.showMessageDialog(null, "Vous n'avez pas la permission de supprimer des produits.");
                else {
                    // Code pour supprimer un produit
                }
            }
        });

        if (user.getUserRole().equals("manager"))
            deleteProductBtn.setEnabled(false);

        frame.add(addProductBtn);
        frame.add(updateProductBtn);
        frame.add(deleteProductBtn);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}