package views;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import controllers.FurnisherController;
import models.AppUser;
import models.Furnisher;
import models.Role;
import models.DAO.FurnisherDAO;
import models.DAO.RoleDAO;
import views.components.AppView;
import views.furnisher.FurnisherView;

public class ManageFurnisherView extends AppView {
    private JButton addFurnisherBtn;
    private JButton updateFurnisherBtn;
    private JButton deleteFurnisherBtn;
    private JButton viewFurnishersBtn;
    private JButton backBtn;
    private AppUser user;

    public ManageFurnisherView(AppUser user) {
        super("Gestion des Fournisseurs", 600, 400, false);
        this.user = user;
        Role userRole = new RoleDAO().getRoleByID(user.getUserRole());

        // Title
        addTitleComponent(0, 0, 1);

        // Buttons
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 2;
        addFurnisherBtn = new JButton("Ajouter Fournisseur");
        addFurnisherBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(addFurnisherBtn, gbc);

        gbc.gridy = 3;
        updateFurnisherBtn = new JButton("Modifier Fournisseur");
        updateFurnisherBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(updateFurnisherBtn, gbc);

        gbc.gridy = 4;
        deleteFurnisherBtn = new JButton("Supprimer Fournisseur");
        deleteFurnisherBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(deleteFurnisherBtn, gbc);

        gbc.gridy = 5;
        viewFurnishersBtn = new JButton("Voir Fournisseurs");
        viewFurnishersBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(viewFurnishersBtn, gbc);

        // Empty Space
        addEmptySpace(0, 6, 10);

        // Back Button
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.NONE;
        backBtn = new JButton("Retour");
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(backBtn, gbc);

        // Interactions
        addFurnisherBtn.addActionListener(e -> {
            new FurnisherController(new FurnisherView(this.user, null), new FurnisherDAO());
            dispose();
        });

        updateFurnisherBtn.addActionListener(e -> {
            List<Furnisher> furnishers = new FurnisherDAO().getFurnishers();

            if (furnishers.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Aucun fournisseur trouvé !");
                return;
            }

            String[] furnisherNames = new String[furnishers.size()];
            for (int i = 0; i < furnishers.size(); i++)
                furnisherNames[i] = furnishers.get(i).getFurnisherName();

            String furnisherName = (String) JOptionPane.showInputDialog(
                    null,
                    "Choisissez le fournisseur à modifier :",
                    "Modification de Fournisseur",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    furnisherNames,
                    furnisherNames[0]);

            if (furnisherName == null)
                return;

            Furnisher furnisher = new FurnisherDAO().getFurnisherByName(furnisherName);

            if (furnisher == null) {
                JOptionPane.showMessageDialog(null, "Le fournisseur " + furnisherName + " n'a pas été trouvé !");
                return;
            }

            new FurnisherController(new FurnisherView(this.user, furnisher), new FurnisherDAO());
            dispose();
        });

        deleteFurnisherBtn.addActionListener(e -> {
            if (userRole.getRoleName().equals("Manager")) {
                JOptionPane.showMessageDialog(null, "Vous n'avez pas la permission de supprimer des fournisseurs.");
                return;
            }

            List<Furnisher> furnishers = new FurnisherDAO().getFurnishers();

            if (furnishers.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Aucun fournisseur trouvé !");
                return;
            }

            String[] furnisherNames = new String[furnishers.size()];
            for (int i = 0; i < furnishers.size(); i++)
                furnisherNames[i] = furnishers.get(i).getFurnisherName();

            String furnisherName = (String) JOptionPane.showInputDialog(
                    null,
                    "Choisissez le fournisseur à supprimer :",
                    "Suppression de Fournisseur",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    furnisherNames,
                    furnisherNames[0]);

            if (furnisherName == null)
                return;

            Furnisher furnisher = new FurnisherDAO().getFurnisherByName(furnisherName);

            if (furnisher == null) {
                JOptionPane.showMessageDialog(null, "Le fournisseur " + furnisherName + " n'a pas été trouvé !");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null,
                    "Êtes-vous sûr de vouloir supprimer ce fournisseur ?",
                    "Confirmation de Suppression",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                new FurnisherDAO().deleteFurnisher(furnisher.getFurnisherId());
                JOptionPane.showMessageDialog(null, "Fournisseur supprimé avec succès !");
            } else {
                JOptionPane.showMessageDialog(null, "Suppression annulée !");
            }
        });

        backBtn.addActionListener(e -> {
            new MainMenuView(user);
            dispose();
        });

        if (userRole.getRoleName().equals("Manager"))
            deleteFurnisherBtn.setEnabled(false);

        setVisible(true);
    }
}