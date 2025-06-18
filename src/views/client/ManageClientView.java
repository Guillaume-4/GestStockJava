package views.client;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import controllers.ClientController;
import models.AppUser;
import models.Client;
import models.DAO.ClientDAO;
import views.MainMenuView;
import views.components.AppView;

public class ManageClientView extends AppView {
    private JButton addClientBtn;
    private JButton updateClientBtn;
    private JButton viewClientsBtn;
    private JButton backBtn;
    private AppUser user;

    public ManageClientView(AppUser user) {
        super("Gestion des Clients", 600, 400, false);
        this.user = user;

        // Title
        addTitleComponent(0, 0, 1);

        // Buttons
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 2;
        addClientBtn = new JButton("Ajouter Client");
        addClientBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(addClientBtn, gbc);

        gbc.gridy = 3;
        updateClientBtn = new JButton("Modifier Client");
        updateClientBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(updateClientBtn, gbc);

        gbc.gridy = 4;
        viewClientsBtn = new JButton("Voir Clients");
        viewClientsBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(viewClientsBtn, gbc);

        // Empty Space
        addEmptySpace(0, 6, 10);

        // Back Button
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.NONE;
        backBtn = new JButton("Retour");
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(backBtn, gbc);

        // Interactions
        addClientBtn.addActionListener(e -> {
            new ClientController(new ClientView(this.user, null), new ClientDAO());
            dispose();
        });

        updateClientBtn.addActionListener(e -> {
            List<Client> Clients = new ClientDAO().getclients();

            if (Clients.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Aucun Client trouvé !");
                return;
            }

            String[] ClientNames = new String[Clients.size()];
            for (int i = 0; i < Clients.size(); i++)
                ClientNames[i] = Clients.get(i).getClient_name();

            String ClientName = (String) JOptionPane.showInputDialog(
                    null,
                    "Choisissez le Client à modifier :",
                    "Modification de Client",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    ClientNames,
                    ClientNames[0]);

            if (ClientName == null)
                return;

            Client Client = new ClientDAO().getclientByName(ClientName);

            if (Client == null) {
                JOptionPane.showMessageDialog(null, "Le Client " + ClientName + " n'a pas été trouvé !");
                return;
            }

            new ClientController(new ClientView(this.user, Client), new ClientDAO());
            dispose();
        });


        viewClientsBtn.addActionListener(e -> {
            new ClientListView(user);
            dispose();
        });

        backBtn.addActionListener(e -> {
            new MainMenuView(user);
            dispose();
        });


        setVisible(true);
    }
}