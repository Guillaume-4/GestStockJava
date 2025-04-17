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
import views.product.ManageProductView;

public class SaleView extends AppView {
    private JTextField quantityTxtField;
    private JTextField dateTxtField;
    private JComboBox<String> productSelector;

    private Sale sale;
    private JButton saleActionBtn;
    private JButton backBtn;
    private AppUser user;
    
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

        List<Product> products = new ProductDAO().getAllProducts();

        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucun produit trouvé !");
            return;
        }

        String[] productNames = new String[products.size()];
        for (int i = 0; i < products.size(); i++)
            productNames[i] = products.get(i).getProductName();

        gbc.gridy = 3;
        productSelector = new JComboBox<String>(productNames);
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
        JLabel dateLabel = new JLabel("Date :");
        contentPanel.add(dateLabel, gbc);

        gbc.gridy = 7;
        dateTxtField = new JTextField(20);
        contentPanel.add(dateTxtField, gbc);
            
        // Empty Space
        addEmptySpace(0, 8, 10);

        // Sale Action Button
        gbc.gridy =9;
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
            productSelector.setSelectedItem(sale.getProduct().getProductName());
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
        return Date.valueOf(dateTxtField.getText());
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
        return productSelector.getSelectedItem().toString();
    }

    // Setters
    public void setSaleListener(ActionListener listener) {
        saleActionBtn.addActionListener(listener);
    }    
}
