package views.sale;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

import models.AppUser;
import models.Product;
import models.Sale;
import models.DAO.ProductDAO;

import views.components.AppView;
import views.components.NumericFilter;

public class SaleView extends AppView {
    private JTextField quantityTxtField;
    private JTextField dateTxtField;
    private JComboBox<String> productSelector;

    private Sale sale;
    private JButton saleActionBtn;
    private JButton backBtn;
    private AppUser user;
    private List<Product> products; // Stocker la liste des produits

    // Constructor
    public SaleView(AppUser user, Sale sale) {
        super(sale == null ? "Ajout de Vente" : "Modification de Vente", 600, 400, false);
        this.user = user;
        this.sale = sale;

        // Title
        addTitleComponent(0, 0, 2);

        // Product List
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        JLabel namLabel = new JLabel("Nom du Produit");
        contentPanel.add(namLabel, gbc);

        gbc.gridy = 3;
        productSelector = new JComboBox<String>();
        loadProducts(); // Charger les produits initialement
        contentPanel.add(productSelector, gbc);

        // Quantity
        gbc.gridy = 4;
        JLabel quantityLabel = new JLabel("Quantité :");
        contentPanel.add(quantityLabel, gbc);

        gbc.gridy = 5;
        quantityTxtField = new JTextField(20);
        ((AbstractDocument) quantityTxtField.getDocument()).setDocumentFilter(new NumericFilter(false));
        contentPanel.add(quantityTxtField, gbc);

        // Date
        gbc.gridy = 6;
        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD) :");
        contentPanel.add(dateLabel, gbc);

        gbc.gridy = 7;
        dateTxtField = new JTextField(20);
        dateTxtField.setToolTipText("Format: YYYY-MM-DD (ex: 1970-01-01)");
        // Pré-remplir avec la date d'aujourd'hui par défaut
        if (this.sale == null) {
            dateTxtField.setText(new Date(System.currentTimeMillis()).toString());
        }
        contentPanel.add(dateTxtField, gbc);

        // Empty Space
        addEmptySpace(0, 8, 10);

        // Sale Action Button
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.NONE;
        saleActionBtn = new JButton(this.sale == null ? "Ajouter Vente" : "Modifier Vente");
        saleActionBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(saleActionBtn, gbc);

        // Back Button
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        backBtn = new JButton("Retour");
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(backBtn, gbc);

        // Fields Initialization if necessary
        if (this.sale != null) {
            quantityTxtField.setText(String.valueOf(sale.getSaleQuantity()));
            dateTxtField.setText(String.valueOf(sale.getSaleDate()));

            // Trouver et sélectionner le bon produit dans la liste
            String productName = sale.getProduct().getProductName();
            for (int i = 0; i < productSelector.getItemCount(); i++) {
                String item = productSelector.getItemAt(i);
                if (item.startsWith(productName + " (Stock: ")) {
                    productSelector.setSelectedIndex(i);
                    break;
                }
            }
        }

        // Interactions
        backBtn.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(
                    this,
                    "Voulez-vous vraiment revenir en arrière ? Les modifications seront perdues.",
                    "Confirmation",
                    JOptionPane.OK_CANCEL_OPTION);

            if (response == JOptionPane.CANCEL_OPTION)
                return;

            new ManageSaleView(user);
            dispose();
        });

        setVisible(true);
    }

    // Getters
    public Date getSaleDate() {
        try {
            return Date.valueOf(dateTxtField.getText());
        } catch (IllegalArgumentException e) {
            // Si le format est incorrect, afficher un message d'erreur
            JOptionPane.showMessageDialog(this,
                    "Format de date invalide !\nUtilisez le format: YYYY-MM-DD\nExemple: 1970-01-01",
                    "Erreur de Format",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public Integer getSaleQuantity() {
        if (quantityTxtField.getText().isEmpty())
            return 0;

        return Integer.parseInt(quantityTxtField.getText());
    }

    public Sale getSale() {
        return this.sale;
    }

    public String getProductName() {
        String selectedItem = productSelector.getSelectedItem().toString();
        // Extraire seulement le nom du produit (avant " (Stock: ")
        int stockIndex = selectedItem.indexOf(" (Stock: ");
        if (stockIndex > 0) {
            return selectedItem.substring(0, stockIndex);
        }
        return selectedItem;
    }

    // Setters
    public void setSaleListener(ActionListener listener) {
        saleActionBtn.addActionListener(listener);
    }

    // Méthodes pour gérer la liste des produits
    private void loadProducts() {
        products = new ProductDAO().getAllProducts();

        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucun produit trouvé !");
            return;
        }

        updateProductSelector();
    }

    private void updateProductSelector() {
        String selectedProductName = null;

        // Sauvegarder la sélection actuelle si elle existe
        if (productSelector.getItemCount() > 0 && productSelector.getSelectedItem() != null) {
            selectedProductName = getProductName();
        }

        // Vider et recharger la liste
        productSelector.removeAllItems();

        for (Product product : products) {
            String itemText = product.getProductName() + " (Stock: " + product.getProductQuantity() + ")";
            productSelector.addItem(itemText);
        }

        // Restaurer la sélection si possible
        if (selectedProductName != null) {
            for (int i = 0; i < productSelector.getItemCount(); i++) {
                String item = productSelector.getItemAt(i);
                if (item.startsWith(selectedProductName + " (Stock: ")) {
                    productSelector.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    public void refreshProductList() {
        // Recharger les produits depuis la base de données
        products = new ProductDAO().getAllProducts();
        updateProductSelector();
    }
}
