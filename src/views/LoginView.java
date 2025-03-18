package views;

import javax.swing.*;

import models.AppUser;
import models.DAO.AppUserDAO;
import utils.PasswordUtil;
import views.components.AppView;

import java.awt.*;

public class LoginView extends AppView {
    private JTextField nameTxtField;
    private JPasswordField passwordTxtField;
    private JButton loginBtn;

    public LoginView() {
        super("Connexion", 600, 400, false);

        // Title
        addTitleComponent(0, 0, 1);

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

        // Empty Space
        addEmptySpace(0, 6, 10);

        // Login Button
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.CENTER;
        loginBtn = new JButton("Se Connecter");
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(loginBtn, gbc);

        // Interactions
        passwordTxtField.addActionListener(e -> loginAction());
        loginBtn.addActionListener(e -> loginAction());

        setVisible(true);
    }

    // Functions
    private void loginAction() {
        String name = nameTxtField.getText();
        String password = new String(passwordTxtField.getPassword()).trim();

        if (name.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs !");
            return;
        }

        AppUser user = new AppUserDAO().authenticateUser(name, password);

        if (user == null) {
            JOptionPane.showMessageDialog(this, "Nom d'utilisateur ou mot de passe incorrect !");
            passwordTxtField.requestFocus();
            return;
        }

        new MainMenuView(user);
        dispose();
    }
}