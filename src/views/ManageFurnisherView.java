package views;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controllers.FurnisherController;
import models.AppUser;
import models.Furnisher;
import models.DAO.FurnisherDAO;
import views.furnisher.FurnisherView;

public class ManageFurnisherView {
    private JFrame frame;

    private JButton addFurnisherBtn;
    private JButton updateFurnisherBtn;
    private JButton deleteFurnisherBtn;

    private AppUser user;

    public ManageFurnisherView(AppUser user) {
        this.user = user;

        frame = new JFrame("Gestion des Fournisseurs");
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 200);

        addFurnisherBtn = new JButton("Ajouter Fournisseur");
        updateFurnisherBtn = new JButton("Modifier Fournisseur");
        deleteFurnisherBtn = new JButton("Supprimer Fournisseur");

        addFurnisherBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FurnisherController(new FurnisherView(user, null), new FurnisherDAO());
            }
        });

        updateFurnisherBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String FurnisherName = JOptionPane.showInputDialog("Entrez le nom du Fournisseur à modifier :");

                if (FurnisherName == null)
                    return;
                else if (FurnisherName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Fournisseur non trouvé !");
                    return;
                }

                Furnisher furnisher = new FurnisherDAO().getFurnisherByName(FurnisherName);

                if (furnisher == null) {
                    JOptionPane.showMessageDialog(null, "Fournisseur non trouvé !");
                    return;
                }

                new FurnisherController(new FurnisherView(user, furnisher), new FurnisherDAO());
            }
        });

        deleteFurnisherBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (user.getUserRole().equals("manager")) {
                    JOptionPane.showMessageDialog(null, "Vous n'avez pas la permission de supprimer des Fournisseurs.");
                    return;
                }

                String FurnisherName = JOptionPane.showInputDialog("Entrez le nom du Fournisseur à supprimer :");

                if (FurnisherName == null)
                    return;
                else if (FurnisherName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Fournisseur non trouvé !");
                    return;
                }

                Furnisher Furnisher = new FurnisherDAO().getFurnisherByName(FurnisherName);

                if (Furnisher == null) {
                    JOptionPane.showMessageDialog(null, "Fournisseur non trouvé !");
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
            }
        });

        if (user.getUserRole().equals("manager"))
            deleteFurnisherBtn.setEnabled(false);

        frame.add(addFurnisherBtn);
        frame.add(updateFurnisherBtn);
        frame.add(deleteFurnisherBtn);

        frame.setLocation(800, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}