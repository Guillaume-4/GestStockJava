package views;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controllers.ProductController;
import models.AppUser;
import models.Product;
import models.DAO.ProductDAO;

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
                new ProductController(new ProductView(), new ProductDAO());
            }
        });

        updateProductBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productName = JOptionPane.showInputDialog("Entrez le nom du produit à modifier :");

                if (productName == null)
                    return;
                else if (productName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Produit non trouvé !");
                    return;
                }

                Product product = new ProductDAO().getProductByName(productName);

                if (product == null) {
                    JOptionPane.showMessageDialog(null, "Produit non trouvé !");
                    return;
                }

                new ProductController(new ProductView(product), new ProductDAO());
            }
        });

        deleteProductBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (user.getUserRole().equals("manager")) {
                    JOptionPane.showMessageDialog(null, "Vous n'avez pas la permission de supprimer des produits.");
                    return;
                }

                String productName = JOptionPane.showInputDialog("Entrez le nom du produit à supprimer :");

                if (productName == null)
                    return;
                else if (productName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Produit non trouvé !");
                    return;
                }

                Product product = new ProductDAO().getProductByName(productName);

                if (product == null) {
                    JOptionPane.showMessageDialog(null, "Produit non trouvé !");
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
            }
        });

        if (user.getUserRole().equals("manager"))
            deleteProductBtn.setEnabled(false);

        frame.add(addProductBtn);
        frame.add(updateProductBtn);
        frame.add(deleteProductBtn);

        frame.setLocation(800, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}