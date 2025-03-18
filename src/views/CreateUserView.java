package views;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import models.AppUser;
import models.Role;
import models.DAO.AppUserDAO;
import models.DAO.RoleDAO;
import utils.PasswordUtil;
import views.components.AppView;

public class CreateUserView extends AppView {
    private JTextField nameTxtField;
    private JPasswordField passwordTxtField;
    private JComboBox<String> roleSelector;
    private JButton createUserBtn;
    private JButton backBtn;
    private AppUser user;

    public CreateUserView(AppUser user) {
        super("Création d'Utilisateur", 600, 400, false);
        this.user = user;

        // Title
        addTitleComponent(0, 0, 2);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        JLabel usernameLabel = new JLabel("Nom d'Utilisateur");
        contentPanel.add(usernameLabel, gbc);

        gbc.gridy = 3;
        nameTxtField = new JTextField(15);
        contentPanel.add(nameTxtField, gbc);

        // Password
        gbc.gridy = 4;
        JLabel passwordLabel = new JLabel("Mot de Passe");
        contentPanel.add(passwordLabel, gbc);

        gbc.gridy = 5;
        passwordTxtField = new JPasswordField(15);
        contentPanel.add(passwordTxtField, gbc);

        // Role Selector
        List<Role> roles = new RoleDAO().getAllRoles();
        String[] roleNames = new String[roles.size()];

        for (int i = 0; i < roles.size(); i++)
            roleNames[i] = roles.get(i).getRoleName();

        gbc.gridy = 6;
        JLabel roleLabel = new JLabel("Rôle");
        contentPanel.add(roleLabel, gbc);

        gbc.gridy = 7;
        roleSelector = new JComboBox<String>(roleNames);
        contentPanel.add(roleSelector, gbc);
        gbc.fill = GridBagConstraints.NONE;

        // Empty Space
        addEmptySpace(0, 8, 10);

        // Create User Button
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.CENTER;
        createUserBtn = new JButton("Créer le Compte");
        createUserBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(createUserBtn, gbc);

        // Empty Space
        addEmptySpace(0, 10, 10);

        // Back Button
        gbc.gridy = 11;
        backBtn = new JButton("Retour");
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(backBtn, gbc);

        // Interactions
        passwordTxtField.addActionListener(e -> createUserAction());
        createUserBtn.addActionListener(e -> createUserAction());
        backBtn.addActionListener(e -> {
            new MainMenuView(this.user);
            dispose();
        });

        setVisible(true);
    }

    // Functions
    private void createUserAction() {
        String name = nameTxtField.getText();
        String password = new String(passwordTxtField.getPassword()).trim();
        String roleName = (String) roleSelector.getSelectedItem();
        Role role = new RoleDAO().getRoleByName(roleName);

        if (name.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs !");
            return;
        }

        AppUserDAO userDAO = new AppUserDAO();

        if (userDAO.checkUser(name)) {
            JOptionPane.showMessageDialog(this, "Ce nom d'utilisateur est déjà utilisé.");
            nameTxtField.requestFocus();
            return;
        }

        String hashedPassword = PasswordUtil.hashPassword(password);
        AppUser newUser = userDAO.createUser(name, hashedPassword, role.getRoleId());

        if (newUser == null) {
            JOptionPane.showMessageDialog(this, "Cet utilisateur n'a pas pu être créé.");
            passwordTxtField.requestFocus();
            return;
        }

        JOptionPane.showMessageDialog(this, "Utilisateur créé avec succès !");
        new MainMenuView(this.user);
        dispose();
    }
}