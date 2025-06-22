package views.user;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.AppUser;
import models.Role;
import models.DAO.AppUserDAO;
import models.DAO.RoleDAO;
import views.components.AppView;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class AppUserListView extends AppView {
    private JTextField searchField;
    private DefaultTableModel tableModel;
    private JTable userTable;
    private JButton backBtn;
    private JButton refreshBtn;
    private List<AppUser> users;
    private AppUser user;

    public AppUserListView(AppUser user) {
        super("Liste des utilisateurs", 800, 600, true);
        this.user = user;

        // Title
        addTitleComponent(0, 0, 2);

        // Search
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        searchField = new JTextField(20);
        searchField.setToolTipText("Recherchez un utilisateur..");
        contentPanel.add(searchField, gbc);

        // Table
        String[] columnNames = { "ID", "Nom d'utilisateur", "Rôle" };
        tableModel = new DefaultTableModel(columnNames, 0);
        userTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(userTable);

        loadUsers();

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
                filterUsers();
            }
        });

        refreshBtn.addActionListener(e -> loadUsers());

        backBtn.addActionListener(e -> {
            new ManageAppUserView(this.user);
            dispose();
        });

        setVisible(true);
    }

    private void loadUsers() {
        tableModel.setRowCount(0);

        users = new AppUserDAO().getAllUsers();
        RoleDAO roleDAO = new RoleDAO();

        for (AppUser user : users) {
            Role role = roleDAO.getRoleByID(user.getUserRole());
            String roleName = role != null ? role.getRoleName() : "Inconnu";

            tableModel.addRow(new Object[] {
                    user.getUserId(),
                    user.getUserName(),
                    roleName
            });
        }
    }

    private void filterUsers() {
        tableModel.setRowCount(0);

        String searchString = searchField.getText().trim().toLowerCase();
        RoleDAO roleDAO = new RoleDAO();

        for (AppUser user : users) {
            if (user.getUserName().toLowerCase().contains(searchString)) {
                Role role = roleDAO.getRoleByID(user.getUserRole());
                String roleName = role != null ? role.getRoleName() : "Inconnu";

                tableModel.addRow(new Object[] {
                        user.getUserId(),
                        user.getUserName(),
                        roleName
                });
            }
        }
    }
}
