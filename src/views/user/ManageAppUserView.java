package views.user;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import controllers.AppUserController;
import models.AppUser;
import models.Role;
import models.DAO.AppUserDAO;
import models.DAO.RoleDAO;
import views.MainMenuView;
import views.components.AppView;

public class ManageAppUserView extends AppView {
    private JButton addUserBtn;
    private JButton updateUserBtn;
    private JButton deleteUserBtn;
    private JButton viewUsersBtn;
    private JButton backBtn;
    private AppUser user;

    public ManageAppUserView(AppUser user) {
        super("Gestion des Utilisateurs", 600, 400, false);
        this.user = user;
        Role userRole = new RoleDAO().getRoleByID(user.getUserRole());

        // Title
        addTitleComponent(0, 0, 1);

        // Buttons
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 2;
        addUserBtn = new JButton("Ajouter Utilisateur");
        addUserBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(addUserBtn, gbc);

        gbc.gridy = 3;
        updateUserBtn = new JButton("Modifier Utilisateur");
        updateUserBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(updateUserBtn, gbc);

        gbc.gridy = 4;
        deleteUserBtn = new JButton("Supprimer Utilisateur");
        deleteUserBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(deleteUserBtn, gbc);

        gbc.gridy = 5;
        viewUsersBtn = new JButton("Voir Utilisateurs");
        viewUsersBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(viewUsersBtn, gbc);

        // Empty Space
        addEmptySpace(0, 6, 10);

        // Back Button
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.NONE;
        backBtn = new JButton("Retour");
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(backBtn, gbc);

        // Interactions
        addUserBtn.addActionListener(e -> {
            new AppUserController(new AppUserView(this.user, null), new AppUserDAO());
            dispose();
        });

        updateUserBtn.addActionListener(e -> {
            List<AppUser> users = new AppUserDAO().getAllUsers();

            if (users.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Aucun utilisateur trouvé !");
                return;
            }

            String[] userNames = new String[users.size()];
            for (int i = 0; i < users.size(); i++)
                userNames[i] = users.get(i).getUserName();

            String userName = (String) JOptionPane.showInputDialog(
                    null,
                    "Choisissez l'utilisateur à modifier :",
                    "Modification d'Utilisateur",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    userNames,
                    userNames[0]);

            if (userName == null)
                return;

            AppUser selectedUser = null;
            for (AppUser u : users) {
                if (u.getUserName().equals(userName)) {
                    selectedUser = u;
                    break;
                }
            }

            if (selectedUser == null) {
                JOptionPane.showMessageDialog(null, "L'utilisateur " + userName + " n'a pas été trouvé !");
                return;
            }

            new AppUserController(new AppUserView(this.user, selectedUser), new AppUserDAO());
            dispose();
        });

        deleteUserBtn.addActionListener(e -> {
            if (!userRole.getRoleName().equals("Administrateur")) {
                JOptionPane.showMessageDialog(null, "Vous n'avez pas la permission de supprimer des utilisateurs.");
                return;
            }

            List<AppUser> users = new AppUserDAO().getAllUsers();

            if (users.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Aucun utilisateur trouvé !");
                return;
            }

            String[] userNames = new String[users.size()];
            for (int i = 0; i < users.size(); i++)
                userNames[i] = users.get(i).getUserName();

            String userName = (String) JOptionPane.showInputDialog(
                    null,
                    "Choisissez l'utilisateur à supprimer :",
                    "Suppression d'Utilisateur",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    userNames,
                    userNames[0]);

            if (userName == null)
                return;

            AppUser selectedUser = null;
            for (AppUser u : users) {
                if (u.getUserName().equals(userName)) {
                    selectedUser = u;
                    break;
                }
            }

            if (selectedUser == null) {
                JOptionPane.showMessageDialog(null, "L'utilisateur " + userName + " n'a pas été trouvé !");
                return;
            }

            // Empêcher la suppression de son propre compte
            if (selectedUser.getUserId() == this.user.getUserId()) {
                JOptionPane.showMessageDialog(null, "Vous ne pouvez pas supprimer votre propre compte !");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null,
                    "Êtes-vous sûr de vouloir supprimer cet utilisateur ?",
                    "Confirmation de Suppression",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                new AppUserDAO().deleteUser(selectedUser.getUserId());
                JOptionPane.showMessageDialog(null, "Utilisateur supprimé avec succès !");
            } else {
                JOptionPane.showMessageDialog(null, "Suppression annulée !");
            }
        });

        viewUsersBtn.addActionListener(e -> {
            new AppUserListView(user);
            dispose();
        });

        backBtn.addActionListener(e -> {
            new MainMenuView(user);
            dispose();
        });

        // Seuls les administrateurs peuvent supprimer des utilisateurs
        if (!userRole.getRoleName().equals("Administrateur"))
            deleteUserBtn.setEnabled(false);

        setVisible(true);
    }
}
