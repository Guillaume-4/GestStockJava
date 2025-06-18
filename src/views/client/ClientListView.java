package views.client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.AppUser;
import models.Client;
import models.DAO.ClientDAO;
import views.components.AppView;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class ClientListView extends AppView {
    private JTextField searchField;
    private DefaultTableModel tableModel;
    private JTable ClientTable;
    private JButton backBtn;
    private JButton refreshBtn;
    private List<Client> Clients;
    private AppUser user;

    public ClientListView(AppUser user) {
        super("Liste des Clients", 800, 600, true);
        this.user = user;

        // Title
        addTitleComponent(0, 0, 2);

        // Search
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        searchField = new JTextField(20);
        searchField.setToolTipText("Recherchez un Client..");
        contentPanel.add(searchField, gbc);

        // Table
        String[] columnNames = { "ID", "Nom", "Addresse", "Code Postale", "Ville", "Pays", "Téléphone" };
        tableModel = new DefaultTableModel(columnNames, 0);
        ClientTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(ClientTable);

        loadClients();

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
                filterClients();
            }
        });

        refreshBtn.addActionListener(e -> loadClients());

        backBtn.addActionListener(e -> {
            new ManageClientView(this.user);
            dispose();
        });

        setVisible(true);
    }

    private void loadClients() {
        tableModel.setRowCount(0);

        Clients = new ClientDAO().getclients();

        for (Client Client : Clients)
            tableModel.addRow(new Object[] {
                    Client.getClient_id(),
                    Client.getClient_name(),
                    Client.getClient_address(),
                    Client.getClient_zipcode(),
                    Client.getClient_city(),
                    Client.getClient_country(),
                    Client.getClient_phone(),

            });
    }

    private void filterClients() {
        tableModel.setRowCount(0);

        String searchString = searchField.getText().trim().toLowerCase();

        for (Client Client : Clients)
            if (Client.getClient_name().toLowerCase().contains(searchString))
                tableModel.addRow(new Object[] {
                    Client.getClient_id(),
                    Client.getClient_name(),
                    Client.getClient_address(),
                    Client.getClient_zipcode(),
                    Client.getClient_city(),
                    Client.getClient_country(),
                    Client.getClient_phone(),
                });
    }
}
