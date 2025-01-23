package views;

import javax.swing.*;

import models.AppUser;
import models.DAO.AppUserDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView {
    private JFrame frame;

    private JTextField nameTxtField;
    private JPasswordField passwordTxtField;
    private JButton loginBtn;

    public LoginView() {
        frame = new JFrame("Connexion");
        frame.setLayout(new FlowLayout());
        frame.setSize(300, 150);

        JLabel nameLabel = new JLabel("Nom d'Utilisateur:");
        nameTxtField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Mot de Passe:");
        passwordTxtField = new JPasswordField(15);

        loginBtn = new JButton("Se Connecter");

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTxtField.getText();

                String password = new String(passwordTxtField.getPassword());

                AppUser user = new AppUserDAO().getUser(name, password);

                if (user != null) {
                    new MainMenuView(user);
                    frame.dispose();
                } else
                    JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou mot de passe incorrect !");
            }
        });

        frame.add(nameLabel);
        frame.add(nameTxtField);

        frame.add(passwordLabel);
        frame.add(passwordTxtField);

        frame.add(loginBtn);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}