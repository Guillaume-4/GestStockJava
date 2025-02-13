package views.category;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import models.AppUser;
import models.Category;
import models.DAO.CategoryDAO;
import views.components.AppView;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class CategoryListView extends AppView {
    private JTextField searchField;
    private DefaultTableModel tableModel;
    private JTable categoryTable;
    private JButton backBtn;
    private JButton refreshBtn;
    private List<Category> categories;
    private AppUser user;

    public CategoryListView(AppUser user) {
        super("Liste des catégories", 800, 600, true);
        this.user = user;

        // Title
        addTitleComponent(0, 0, 2);

        // Search
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        searchField = new JTextField(20);
        searchField.setToolTipText("Recherchez une catégorie..");
        contentPanel.add(searchField, gbc);

        // Table
        String[] columnNames = { "Nom" };
        tableModel = new DefaultTableModel(columnNames, 0);
        categoryTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(categoryTable);

        loadCategories();

        gbc.gridy = 4;
        gbc.weightx = 1;
        gbc.weighty = 1;
        contentPanel.add(scrollPane, gbc);

        // Buttons
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 1;
        gbc.weighty = 0;
        gbc.weightx = 0;
        gbc.gridy = 5;

        gbc.gridx = 0;
        refreshBtn = new JButton("Rafraîchir");
        contentPanel.add(refreshBtn, gbc);

        gbc.gridx = 1;
        backBtn = new JButton("Retour");
        contentPanel.add(backBtn, gbc);

        // Interactions
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterCategory();
            }
        });

        refreshBtn.addActionListener(e -> loadCategories());

        backBtn.addActionListener(e -> {
            new ManageCategoryView(this.user);
            dispose();
        });

        setVisible(true);
    }

    private void loadCategories() {
        tableModel.setRowCount(0);

        categories = new CategoryDAO().getAllCategories();

        for (Category category : categories)
            tableModel.addRow(new Object[] {
                    category.getCategoryName()
            });
    }

    private void filterCategory() {
        tableModel.setRowCount(0);

        String searchString = searchField.getText().trim().toLowerCase();

        for (Category category : categories)
            if (category.getCategoryName().toLowerCase().contains(searchString))
                tableModel.addRow(new Object[] {
                        category.getCategoryId(),
                        category.getCategoryName()
                });
    }
}
