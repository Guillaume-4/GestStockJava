package controllers;

import java.sql.Date;

import javax.swing.JOptionPane;

import models.Product;
import models.Sale;
import models.DAO.ProductDAO;
import models.DAO.SaleDAO;
import views.sale.SaleView;

public class SaleController {
    private SaleView view;
    private SaleDAO saleDAO;

    public SaleController(SaleView view, SaleDAO saleDAO) {
        this.view = view;
        this.saleDAO = saleDAO;

        this.view.setSaleListener(e -> {
            try {
                Product product = new ProductDAO().getProductByName(view.getProductName());
                int quantity = view.getSaleQuantity();
                Date date = view.getSaleDate();

                // Validation des données
                if (product == null) {
                    JOptionPane.showMessageDialog(null, "Produit non trouvé !");
                    return;
                }

                if (quantity <= 0) {
                    JOptionPane.showMessageDialog(null, "La quantité doit être supérieure à 0 !");
                    return;
                }

                if (date == null) {
                    // L'erreur de format de date est déjà gérée dans la vue
                    return;
                }

                if (view.getSale() != null) {
                    // Modification d'une vente existante
                    Sale sale = new Sale(view.getSale().getSaleId(), quantity, date, product);

                    saleDAO.updateSale(sale);

                    // Rafraîchir la liste des produits avec les stocks mis à jour
                    view.refreshProductList();

                    JOptionPane.showMessageDialog(null,
                            "La vente numéro " + view.getSale().getSaleId() + " a été modifiée avec succès !\n\n"
                                    + sale.saleDetails());
                } else {
                    // Création d'une nouvelle vente
                    Sale sale = new Sale(quantity, date, product);

                    saleDAO.addSale(sale);

                    // Rafraîchir la liste des produits avec les stocks mis à jour
                    view.refreshProductList();

                    JOptionPane.showMessageDialog(null,
                            "La vente a été ajoutée avec succès !\n\n" + sale.saleDetails());
                }
            } catch (IllegalArgumentException ex) {
                // Erreur de stock insuffisant ou autres erreurs de validation
                JOptionPane.showMessageDialog(null,
                        "Erreur : " + ex.getMessage(),
                        "Erreur de Stock",
                        JOptionPane.ERROR_MESSAGE);
            } catch (RuntimeException ex) {
                // Erreur de base de données
                JOptionPane.showMessageDialog(null,
                        "Erreur lors de l'opération : " + ex.getMessage(),
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                // Toute autre erreur inattendue
                JOptionPane.showMessageDialog(null,
                        "Une erreur inattendue s'est produite : " + ex.getMessage(),
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
