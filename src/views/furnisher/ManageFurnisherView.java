package views.furnisher;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import controllers.FurnisherController;
import models.AppUser;
import models.Furnisher;
import models.DAO.FurnisherDAO;
import views.MainMenuView;
import views.components.AppView;

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
            List<Furnisher> Furnishers = new FurnisherDAO().getFurnishers();

            if (Furnishers.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Aucun Fournisseur trouvé !");
                return;
            }

            String[] FurnisherNames = new String[Furnishers.size()];
            for (int i = 0; i < Furnishers.size(); i++)
                FurnisherNames[i] = Furnishers.get(i).getFurnisherName();

            String FurnisherName = (String) JOptionPane.showInputDialog(
                    null,
                    "Choisissez le Fournisseur à modifier :",
                    "Modification de Fournisseur",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    FurnisherNames,
                    FurnisherNames[0]);

            if (FurnisherName == null)
                return;

            Furnisher Furnisher = new FurnisherDAO().getFurnisherByName(FurnisherName);

            if (Furnisher == null) {
                JOptionPane.showMessageDialog(null, "Le Fournisseur " + FurnisherName + " n'a pas été trouvé !");
                return;
            }

            new FurnisherController(new FurnisherView(this.user, Furnisher), new FurnisherDAO());
            dispose();
        });

        deleteFurnisherBtn.addActionListener(e -> {
            if (user.getUserRole() == 1) {
                JOptionPane.showMessageDialog(null, "Vous n'avez pas la permission de supprimer des Fournisseurs.");
                return;
            }

            List<Furnisher> Furnishers = new FurnisherDAO().getFurnishers();

            if (Furnishers.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Aucun Fournisseur trouvé !");
                return;
            }

            String[] FurnisherNames = new String[Furnishers.size()];
            for (int i = 0; i < Furnishers.size(); i++)
                FurnisherNames[i] = Furnishers.get(i).getFurnisherName();

            String FurnisherName = (String) JOptionPane.showInputDialog(
                    null,
                    "Choisissez le Fournisseur à supprimer :",
                    "Suppression de Fournisseur",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    FurnisherNames,
                    FurnisherNames[0]);

            if (FurnisherName == null)
                return;

            Furnisher Furnisher = new FurnisherDAO().getFurnisherByName(FurnisherName);

            if (Furnisher == null) {
                JOptionPane.showMessageDialog(null, "Le Fournisseur " + FurnisherName + " n'a pas été trouvé !");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null,
                    "Êtes-vous sûr de vouloir supprimer ce Fournisseur ?",
                    "Confirmation de Suppression",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                new FurnisherDAO().deleteFurnisher(Furnisher.getFurnisherId());
                JOptionPane.showMessageDialog(null, "Fournisseur supprimé avec succès !");
            } else {
                JOptionPane.showMessageDialog(null, "Suppression annulée !");
            }
        });

        viewFurnishersBtn.addActionListener(e -> {
            new FurnisherListView(user);
            dispose();
        });

        backBtn.addActionListener(e -> {
            new MainMenuView(user);
            dispose();
        });

        if (user.getUserRole() == 1)
            deleteFurnisherBtn.setEnabled(false);

        setVisible(true);
    }
}