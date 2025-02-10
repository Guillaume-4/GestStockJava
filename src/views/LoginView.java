package views;

import javax.swing.*;

import models.AppUser;
import models.DAO.AppUserDAO;
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
        add(usernameLabel, gbc);

        gbc.gridy = 3;
        nameTxtField = new JTextField(15);
        add(nameTxtField, gbc);

        // Password
        gbc.gridy = 4;
        JLabel passwordLabel = new JLabel("Mot de Passe");
        add(passwordLabel, gbc);

        gbc.gridy = 5;
        passwordTxtField = new JPasswordField(15);
        add(passwordTxtField, gbc);

        // Empty Space
        gbc.gridy = 6;
        add(Box.createRigidArea(new Dimension(0, 10)), gbc);

        // Login Button
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.CENTER;
        loginBtn = new JButton("Se Connecter");
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(loginBtn, gbc);

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

        AppUser user = new AppUserDAO().getUser(name, password);

        if (user == null) {
            JOptionPane.showMessageDialog(this, "Nom d'utilisateur ou mot de passe incorrect !");
            passwordTxtField.requestFocus();
            return;
        }

        new MainMenuView(user);
        dispose();
    }
}