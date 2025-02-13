package views.category;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import controllers.CategoryController;
import models.AppUser;
import models.Category;
import models.DAO.CategoryDAO;
import views.MainMenuView;
import views.components.AppView;

public class ManageCategoryView extends AppView {
    private JButton addCategoryBtn;
    private JButton updateCategoryBtn;
    private JButton deleteCategoryBtn;
    private JButton viewCategoriesBtn;
    private JButton backBtn;

    private AppUser user;

    public ManageCategoryView(AppUser user) {
        super("Gestion des Catégories", 600, 400, false);
        this.user = user;

        // Title
        addTitleComponent(0, 0, 1);

        // Buttons
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 2;
        addCategoryBtn = new JButton("Ajouter Catégorie");
        addCategoryBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(addCategoryBtn, gbc);

        gbc.gridy = 3;
        updateCategoryBtn = new JButton("Modifier Catégorie");
        updateCategoryBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(updateCategoryBtn, gbc);

        gbc.gridy = 4;
        deleteCategoryBtn = new JButton("Supprimer Catégorie");
        deleteCategoryBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(deleteCategoryBtn, gbc);

        gbc.gridy = 5;
        viewCategoriesBtn = new JButton("Voir Catégories");
        viewCategoriesBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(viewCategoriesBtn, gbc);

        // Empty Space
        gbc.gridy = 6;
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)), gbc);

        // Back Button
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.NONE;
        backBtn = new JButton("Retour");
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contentPanel.add(backBtn, gbc);

        // Insteractions
        addCategoryBtn.addActionListener(e -> {
            new CategoryController(new CategoryView(this.user, null), new CategoryDAO());
            dispose();
        });

        updateCategoryBtn.addActionListener(e -> {
            List<Category> categories = new CategoryDAO().getAllCategories();

            if (categories.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Aucune catégories trouvée !");
                return;
            }

            String[] categoryNames = new String[categories.size()];
            for (int i = 0; i < categories.size(); i++)
                categoryNames[i] = categories.get(i).getCategoryName();

            String categoryName = (String) JOptionPane.showInputDialog(
                    null,
                    "Choisissez la catégorie à modifier :",
                    "Modification de Catégorie",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    categoryNames,
                    categoryNames[0]);

            if (categoryName == null)
                return;

            Category category = new CategoryDAO().getCategoryByName(categoryName);

            if (category == null) {
                JOptionPane.showMessageDialog(null, "La catégorie " + categoryName + " n'a pas été trouvée !");
                return;
            }

            new CategoryController(new CategoryView(this.user, category), new CategoryDAO());
            dispose();
        });

        deleteCategoryBtn.addActionListener(e -> {
            if (user.getUserRole().equals("manager")) {
                JOptionPane.showMessageDialog(null, "Vous n'avez pas la permission de supprimer des catégories.");
                return;
            }

            List<Category> categories = new CategoryDAO().getAllCategories();

            if (categories.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Catégorie non trouvé !");
                return;
            }
                
            String[] categoryNames = new String[categories.size()];
            for (int i = 0; i < categories.size(); i++)
                categoryNames[i] = categories.get(i).getCategoryName();

            String categoryName = (String) JOptionPane.showInputDialog(
                    null,
                    "Choisissez la catégorie à supprimer :",
                    "Suppression de Catégorie",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    categoryNames,
                    categoryNames[0]);

            if (categoryName == null)
                return;


            Category category = new CategoryDAO().getCategoryByName(categoryName);

            if (category == null) {
                JOptionPane.showMessageDialog(null, "Le produit " + categoryName + " n'a pas été trouvé !");
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
        });

        viewCategoriesBtn.addActionListener(e -> {
            new CategoryListView(user);
            dispose();
        });

        backBtn.addActionListener(e -> {
            new MainMenuView(user);
            dispose();
        });

        if (user.getUserRole().equals("manager"))
            deleteCategoryBtn.setEnabled(false);

        setVisible(true);
    }
}
