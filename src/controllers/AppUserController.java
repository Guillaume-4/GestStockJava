package controllers;

import javax.swing.JOptionPane;

import models.AppUser;
import models.Role;
import models.DAO.AppUserDAO;
import models.DAO.RoleDAO;
import utils.PasswordUtil;
import views.user.AppUserView;

public class AppUserController {
    private AppUserView view;
    private AppUserDAO userDAO;

    public AppUserController(AppUserView view, AppUserDAO userDAO) {
        this.view = view;
        this.userDAO = userDAO;

        this.view.setUserListener(e -> {
            String name = view.getUserName();
            String password = view.getUserPassword();
            String roleName = view.getUserRoleName();
            Role role = new RoleDAO().getRoleByName(roleName);

            // Validation des champs
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Veuillez saisir un nom d'utilisateur !");
                return;
            }

            if (view.getTargetUser() == null && password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Veuillez saisir un mot de passe !");
                return;
            }

            if (role == null) {
                JOptionPane.showMessageDialog(null, "Rôle invalide !");
                return;
            }

            if (view.getTargetUser() != null) {
                // Modification d'un utilisateur existant
                AppUser userToUpdate = view.getTargetUser();

                // Vérifier si le nom d'utilisateur est déjà pris par un autre utilisateur
                if (!userToUpdate.getUserName().equals(name) && userDAO.checkUser(name)) {
                    JOptionPane.showMessageDialog(null,
                            "Ce nom d'utilisateur est déjà utilisé par un autre utilisateur.");
                    return;
                }

                userToUpdate.setUserName(name);
                userToUpdate.setUserRole(role.getRoleId());

                // Mettre à jour les informations de base (nom et rôle)
                userDAO.updateUserWithoutPassword(userToUpdate);

                // Mettre à jour le mot de passe seulement s'il n'est pas vide
                if (!password.isEmpty()) {
                    String hashedPassword = PasswordUtil.hashPassword(password);
                    userDAO.updateUserPassword(userToUpdate.getUserId(), hashedPassword);
                }

                JOptionPane.showMessageDialog(null,
                        "L'utilisateur ID " + userToUpdate.getUserId() + " a été modifié avec succès !");
            } else {
                // Création d'un nouvel utilisateur
                if (userDAO.checkUser(name)) {
                    JOptionPane.showMessageDialog(null, "Ce nom d'utilisateur est déjà utilisé.");
                    return;
                }

                String hashedPassword = PasswordUtil.hashPassword(password);
                AppUser newUser = userDAO.createUser(name, hashedPassword, role.getRoleId());

                if (newUser == null) {
                    JOptionPane.showMessageDialog(null, "Cet utilisateur n'a pas pu être créé.");
                    return;
                }

                JOptionPane.showMessageDialog(null, "Utilisateur créé avec succès !");
            }
        });
    }
}
