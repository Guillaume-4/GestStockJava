package views;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controllers.CategoryController;
import models.AppUser;
import models.Category;
import models.DAO.CategoryDAO;

public class ManageCategoryView {
    private JFrame frame;

    private JButton addCategoryBtn;
    private JButton updateCategoryBtn;
    private JButton deleteCategoryBtn;

    private AppUser user;

    public ManageCategoryView(AppUser user) {
        this.user = user;

        frame = new JFrame("Gestion des Catégories");
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 200);

        addCategoryBtn = new JButton("Ajouter Catégorie");
        updateCategoryBtn = new JButton("Modifier Catégorie");
        deleteCategoryBtn = new JButton("Supprimer Catégorie");

        addCategoryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CategoryController(new CategoryView(), new CategoryDAO());
            }
        });

        updateCategoryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String categoryName = JOptionPane.showInputDialog("Entrez le nom de la catégorie à modifier :");

                if (categoryName == null)
                    return;
                else if (categoryName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Catégorie non trouvé !");
                    return;
                }

                Category category = new CategoryDAO().getCategoryByName(categoryName);

                if (category == null) {
                    JOptionPane.showMessageDialog(null, "Catégorie non trouvé !");
                    return;
                }

                new CategoryController(new CategoryView(category), new CategoryDAO());
            }
        });

        deleteCategoryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (user.getUserRole().equals("manager")) {
                    JOptionPane.showMessageDialog(null, "Vous n'avez pas la permission de supprimer des catégories.");
                    return;
                }

                String productName = JOptionPane.showInputDialog("Entrez le nom de la catégorie à supprimer :");

                if (productName == null)
                    return;
                else if (productName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Catégorie non trouvé !");
                    return;
                }

                Category category = new CategoryDAO().getCategoryByName(productName);

                if (category == null) {
                    JOptionPane.showMessageDialog(null, "Catégorie non trouvé !");
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(null,
                        "Êtes-vous sûr de vouloir supprimer cette catégorie ?",
                        "Confirmation de Suppression",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    new CategoryDAO().deleteCategory(category.getCategoryId());
                    JOptionPane.showMessageDialog(null, "Catégorie supprimé avec succès !");
                } else {
                    JOptionPane.showMessageDialog(null, "Suppression annulée !");
                }
            }
        });

        if (user.getUserRole().equals("manager"))
            deleteCategoryBtn.setEnabled(false);

        frame.add(addCategoryBtn);
        frame.add(updateCategoryBtn);
        frame.add(deleteCategoryBtn);

        frame.setLocation(800, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
