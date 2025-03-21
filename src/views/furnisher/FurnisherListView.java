package views.furnisher;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.AppUser;
import models.Furnisher;
import models.DAO.FurnisherDAO;
import views.components.AppView;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class FurnisherListView extends AppView {
    private JTextField searchField;
    private DefaultTableModel tableModel;
    private JTable FurnisherTable;
    private JButton backBtn;
    private JButton refreshBtn;
    private List<Furnisher> Furnishers;
    private AppUser user;

    public FurnisherListView(AppUser user) {
        super("Liste des Fournisseurs", 800, 600, true);
        this.user = user;

        // Title
        addTitleComponent(0, 0, 2);

        // Search
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        searchField = new JTextField(20);
        searchField.setToolTipText("Recherchez un Fournisseur..");
        contentPanel.add(searchField, gbc);

        // Table
        String[] columnNames = { "ID", "Nom", "Addresse", "Code Postale", "Ville", "Pays", "Téléphone" };
        tableModel = new DefaultTableModel(columnNames, 0);
        FurnisherTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(FurnisherTable);

        loadFurnishers();

        gbc.gridy = 3;
        gbc.weightx = 1;
        gbc.weighty = 1;
        contentPanel.add(scrollPane, gbc);

        // Buttons
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.weighty = 0;
        gbc.weightx = 0;
        gbc.gridy = 4;

        gbc.gridx = 0;
        refreshBtn = new JButton("Rafraîchir");
        contentPanel.add(refreshBtn, gbc);

        gbc.gridx = 1;
        backBtn = new JButton("Retour");
        contentPanel.add(backBtn, gbc);

        // Interactions
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterFurnishers();
            }
        });

        refreshBtn.addActionListener(e -> loadFurnishers());

        backBtn.addActionListener(e -> {
            new ManageFurnisherView(this.user);
            dispose();
        });

        setVisible(true);
    }

    private void loadFurnishers() {
        tableModel.setRowCount(0);

        Furnishers = new FurnisherDAO().getFurnishers();

        for (Furnisher Furnisher : Furnishers)
            tableModel.addRow(new Object[] {
                    Furnisher.getFurnisherId(),
                    Furnisher.getFurnisherName(),
                    Furnisher.getFurnisherAdress(),
                    Furnisher.getFurnisherZipcode(),
                    Furnisher.getFurnisherCity(),
                    Furnisher.getFurnisherCountry(),
                    Furnisher.getFurnisherPhone(),

            });
    }

    private void filterFurnishers() {
        tableModel.setRowCount(0);

        String searchString = searchField.getText().trim().toLowerCase();

        for (Furnisher Furnisher : Furnishers)
            if (Furnisher.getFurnisherName().toLowerCase().contains(searchString))
                tableModel.addRow(new Object[] {
                    Furnisher.getFurnisherId(),
                    Furnisher.getFurnisherName(),
                    Furnisher.getFurnisherAdress(),
                    Furnisher.getFurnisherCity(),
                    Furnisher.getFurnisherPhone(),
                    Furnisher.getFurnisherZipcode(),
                });
    }
}
