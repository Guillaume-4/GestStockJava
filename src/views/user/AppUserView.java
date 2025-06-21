package views.user;

import javax.swing.*;

import models.AppUser;
import models.Role;
import models.DAO.RoleDAO;
import views.components.AppView;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.util.List;

public class AppUserView extends AppView {
    private JTextField nameTxtField;
    private JPasswordField passwordTxtField;
    private JComboBox<String> roleSelector;
    private JButton userActionBtn;
    private JButton backBtn;
    private AppUser targetUser; // L'utilisateur à modifier/créer
    private AppUser currentUser; // L'utilisateur actuellement connecté

    public AppUserView(AppUser currentUser, AppUser targetUser) {
        super(targetUser == null ? "Ajout d'Utilisateur" : "Modification d'Utilisateur", 600, 500, false);
        this.currentUser = currentUser;
        this.targetUser = targetUser;

        // Title
        addTitleComponent(0, 0, 2);

        // Role list
        List<Role> roles = new RoleDAO().getAllRoles();
        String[] roleNames = new String[roles.size()];

        for (int i = 0; i < roles.size(); i++)
            roleNames[i] = roles.get(i).getRoleName();

        // Username
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        JLabel nameLabel = new JLabel("Nom d'Utilisateur :");
        contentPanel.add(nameLabel, gbc);

        gbc.gridy = 3;
        nameTxtField = new JTextField(20);
        contentPanel.add(nameTxtField, gbc);

        // Password
        gbc.gridy = 4;
        JLabel passwordLabel = new JLabel("Mot de Passe :");
        contentPanel.add(passwordLabel, gbc);

        gbc.gridy = 5;
        passwordTxtField = new JPasswordField(20);
        contentPanel.add(passwordTxtField, gbc);

        // Role
        gbc.gridy = 6;
        JLabel roleLabel = new JLabel("Rôle :");
        contentPanel.add(roleLabel, gbc);

        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        roleSelector = new JComboBox<String>(roleNames);
        contentPanel.add(roleSelector, gbc);

        // Note for password modification
        if (targetUser != null) {
            gbc.gridy = 8;
            gbc.fill = GridBagConstraints.NONE;
            JLabel noteLabel = new JLabel("<html><i>Laissez le mot de passe vide pour le conserver</i></html>");
            contentPanel.add(noteLabel, gbc);
        }

        // Empty Space
        addEmptySpace(0, targetUser != null ? 9 : 8, 10);

        // User Action Button
        gbc.gridy = targetUser != null ? 10 : 9;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.NONE;
        userActionBtn = new JButton(this.targetUser == null ? "Ajouter Utilisateur" : "Modifier Utilisateur");
        userActionBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(userActionBtn, gbc);

        // Back Button
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        backBtn = new JButton("Retour");
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(backBtn, gbc);

        // Fields Initialization if editing
        if (this.targetUser != null) {
            nameTxtField.setText(targetUser.getUserName());
            Role userRole = new RoleDAO().getRoleByID(targetUser.getUserRole());
            if (userRole != null) {
                roleSelector.setSelectedItem(userRole.getRoleName());
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

            new ManageAppUserView(currentUser);
            dispose();
        });

        setVisible(true);
    }

    // Getters
    public String getUserName() {
        return nameTxtField.getText().trim();
    }

    public String getUserPassword() {
        return new String(passwordTxtField.getPassword()).trim();
    }

    public String getUserRoleName() {
        return roleSelector.getSelectedItem().toString();
    }

    public AppUser getTargetUser() {
        return targetUser;
    }

    public AppUser getCurrentUser() {
        return currentUser;
    }

    // Setters
    public void setUserListener(ActionListener listener) {
        userActionBtn.addActionListener(listener);
    }
}
