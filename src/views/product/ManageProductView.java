package views.product;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import controllers.ProductController;
import models.AppUser;
import models.Product;
import models.DAO.ProductDAO;
import views.MainMenuView;
import views.components.AppView;

public class ManageProductView extends AppView {
    private JButton addProductBtn;
    private JButton updateProductBtn;
    private JButton deleteProductBtn;
    private JButton viewProductsBtn;
    private JButton backBtn;
    private AppUser user;

    public ManageProductView(AppUser user) {
        super("Gestion des Produits", 600, 400, false);
        this.user = user;

        // Title
        addTitleComponent(0, 0, 1);

        // Buttons
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 2;
        addProductBtn = new JButton("Ajouter Produit");
        addProductBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(addProductBtn, gbc);

        gbc.gridy = 3;
        updateProductBtn = new JButton("Modifier Produit");
        updateProductBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(updateProductBtn, gbc);

        gbc.gridy = 4;
        deleteProductBtn = new JButton("Supprimer Produit");
        deleteProductBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(deleteProductBtn, gbc);

        gbc.gridy = 5;
        viewProductsBtn = new JButton("Voir Produits");
        viewProductsBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(viewProductsBtn, gbc);

        // Empty Space
        addEmptySpace(0, 6, 10);

        // Back Button
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.NONE;
        backBtn = new JButton("Retour");
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(backBtn, gbc);

        // Interactions
        addProductBtn.addActionListener(e -> {
            new ProductController(new ProductView(this.user, null), new ProductDAO());
            dispose();
        });

        updateProductBtn.addActionListener(e -> {
            List<Product> products = new ProductDAO().getAllProducts();

            if (products.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Aucun produit trouvé !");
                return;
            }

            String[] productNames = new String[products.size()];
            for (int i = 0; i < products.size(); i++)
                productNames[i] = products.get(i).getProductName();

            String productName = (String) JOptionPane.showInputDialog(
                    null,
                    "Choisissez le produit à modifier :",
                    "Modification de Produit",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    productNames,
                    productNames[0]);

            if (productName == null)
                return;

            Product product = new ProductDAO().getProductByName(productName);

            if (product == null) {
                JOptionPane.showMessageDialog(null, "Le produit " + productName + " n'a pas été trouvé !");
                return;
            }

            new ProductController(new ProductView(this.user, product), new ProductDAO());
            dispose();
        });

        deleteProductBtn.addActionListener(e -> {
            if (user.getUserRole().equals("manager")) {
                JOptionPane.showMessageDialog(null, "Vous n'avez pas la permission de supprimer des produits.");
                return;
            }

            List<Product> products = new ProductDAO().getAllProducts();

            if (products.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Aucun produit trouvé !");
                return;
            }

            String[] productNames = new String[products.size()];
            for (int i = 0; i < products.size(); i++)
                productNames[i] = products.get(i).getProductName();

            String productName = (String) JOptionPane.showInputDialog(
                    null,
                    "Choisissez le produit à supprimer :",
                    "Suppression de Produit",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    productNames,
                    productNames[0]);

            if (productName == null)
                return;

            Product product = new ProductDAO().getProductByName(productName);

            if (product == null) {
                JOptionPane.showMessageDialog(null, "Le produit " + productName + " n'a pas été trouvé !");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null,
                    "Êtes-vous sûr de vouloir supprimer ce produit ?",
                    "Confirmation de Suppression",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                new ProductDAO().deleteProduct(product.getProductId());
                JOptionPane.showMessageDialog(null, "Produit supprimé avec succès !");
            } else {
                JOptionPane.showMessageDialog(null, "Suppression annulée !");
            }
        });

        viewProductsBtn.addActionListener(e -> {
            new ProductListView(user);
            dispose();
        });

        backBtn.addActionListener(e -> {
            new MainMenuView(user);
            dispose();
        });

        if (user.getUserRole().equals("manager"))
            deleteProductBtn.setEnabled(false);

        setVisible(true);
    }
}