package views.sale;

import views.MainMenuView;
import views.components.AppView;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import controllers.SaleController;
import models.AppUser;
import models.Role;
import models.DAO.SaleDAO;
import models.DAO.RoleDAO;
import models.Sale;

public class ManageSaleView extends AppView {
    private JButton addSaleBtn;
    private JButton updateSaleBtn;
    private JButton deleteSaleBtn;
    private JButton viewSalesBtn;
    private JButton backBtn;
    private AppUser user;

    public ManageSaleView(AppUser user) {
        super("Gestion des Ventes", 600, 400, false);
        this.user = user;
        Role userRole = new RoleDAO().getRoleByID(user.getUserRole());

        // Title
        addTitleComponent(0, 0, 1);

        // Button
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 2;
        addSaleBtn = new JButton("Ajouter Vente");
        addSaleBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(addSaleBtn, gbc);

        gbc.gridy = 3;
        updateSaleBtn = new JButton("Modifier Vente");
        updateSaleBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(updateSaleBtn, gbc);

        gbc.gridy = 4;
        deleteSaleBtn = new JButton("Supprimer Vente");
        deleteSaleBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(deleteSaleBtn, gbc);

        gbc.gridy = 5;
        viewSalesBtn = new JButton("Voir Vente");
        viewSalesBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(viewSalesBtn, gbc);

        // Empty Space
        addEmptySpace(0, 6, 10);

        // Back Button
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.NONE;
        backBtn = new JButton("Retour");
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(backBtn, gbc);

        // Interactions
        addSaleBtn.addActionListener(e -> {
            new SaleController(new SaleView(this.user, null), new SaleDAO());
            dispose();
        });

        updateSaleBtn.addActionListener(e -> {
            List<Sale> sales = new SaleDAO().getAllSales();

            if (sales.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Aucune vente trouvée !");
                return;
            }

            Integer[] saleIDs = new Integer[sales.size()];
            for (int i = 0; i < sales.size(); i++)
                saleIDs[i] = sales.get(i).getSaleId();

            Integer saleID = (Integer) JOptionPane.showInputDialog(
                    null,
                    "Choisissez le numéro de la vente à modifier :",
                    "Modification de Vente",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    saleIDs,
                    saleIDs[0]);

            if (saleID == null)
                return;

            Sale sale = new SaleDAO().getSaleByID(saleID);

            if (sale == null) {
                JOptionPane.showMessageDialog(null, "La vente numéro " + saleID + " n'a pas été trouvée !");
                return;
            }

            new SaleController(new SaleView(this.user, sale), new SaleDAO());
            dispose();
        });

        deleteSaleBtn.addActionListener(e -> {
            if (userRole.getRoleName().equals("Manager")) {
                JOptionPane.showMessageDialog(null, "Vous n'avez pas la permission de supprimer des ventes.");
                return;
            }

            List<Sale> sales = new SaleDAO().getAllSales();

            if (sales.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Aucune vente trouvée !");
                return;
            }

            Integer[] saleIDs = new Integer[sales.size()];
            for (int i = 0; i < sales.size(); i++)
                saleIDs[i] = sales.get(i).getSaleId();

            Integer saleID = (Integer) JOptionPane.showInputDialog(
                    null,
                    "Choisissez le numéro de la vente à supprimer :",
                    "Suppression de Vente",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    saleIDs,
                    saleIDs[0]);

            if (saleID == null)
                return;

            Sale sale = new SaleDAO().getSaleByID(saleID);

            if (sale == null) {
                JOptionPane.showMessageDialog(null, "La vente numéro " + saleID + " n'a pas été trouvée !");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null,
                    "Êtes-vous sûr de vouloir supprimer cette vente ?",
                    "Confirmation de Suppression",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                new SaleDAO().deleteSale(sale.getSaleId());
                JOptionPane.showMessageDialog(null, "Vente supprimée avec succès !");
            } else {
                JOptionPane.showMessageDialog(null, "Vente annulée !");
            }
        });

        viewSalesBtn.addActionListener(e -> {
            new SaleListView(user);
            dispose();
        });

        backBtn.addActionListener(e -> {
            new MainMenuView(user);
            dispose();
        });

        if (userRole.getRoleName().equals("Manager"))
            deleteSaleBtn.setEnabled(false);

        setVisible(true);
    }

}
