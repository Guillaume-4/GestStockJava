package views.sale;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import models.AppUser;
import models.DAO.SaleDAO;
import views.components.AppView;

import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class SalesReportView extends AppView {
    private JComboBox<String> reportTypeCombo;
    private JSpinner startDateSpinner;
    private JSpinner endDateSpinner;
    private JButton generateReportBtn;
    private JButton backBtn;
    private JScrollPane reportScrollPane;
    private JPanel reportPanel;
    private AppUser user;
    private SaleDAO saleDAO;

    public SalesReportView(AppUser user) {
        super("Rapports de Vente", 1000, 700, true);
        this.user = user;
        this.saleDAO = new SaleDAO();

        initializeComponents();
        setupLayout();
        setupListeners();
        setVisible(true);
    }

    private void initializeComponents() {
        // Report type selection
        String[] reportTypes = {
                "Rapport Global",
                "Produits les Plus Vendus",
                "Ventes par Catégorie",
                "Rapport Quotidien",
                "Chiffre d'Affaires"
        };
        reportTypeCombo = new JComboBox<>(reportTypes);

        // Date selection
        LocalDate today = LocalDate.now();
        LocalDate monthAgo = today.minusMonths(1);

        startDateSpinner = new JSpinner(new SpinnerDateModel());
        endDateSpinner = new JSpinner(new SpinnerDateModel());

        JSpinner.DateEditor startEditor = new JSpinner.DateEditor(startDateSpinner, "dd/MM/yyyy");
        JSpinner.DateEditor endEditor = new JSpinner.DateEditor(endDateSpinner, "dd/MM/yyyy");

        startDateSpinner.setEditor(startEditor);
        endDateSpinner.setEditor(endEditor);

        startDateSpinner.setValue(Date.valueOf(monthAgo));
        endDateSpinner.setValue(Date.valueOf(today));

        // Buttons
        generateReportBtn = new JButton("Générer le Rapport");
        generateReportBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        backBtn = new JButton("Retour");
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Report panel
        reportPanel = new JPanel(new BorderLayout());
        reportScrollPane = new JScrollPane(reportPanel);
    }

    private void setupLayout() {
        // Title
        addTitleComponent(0, 0, 4);

        // Controls panel
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel controlsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints controlGbc = new GridBagConstraints();
        controlGbc.insets = new Insets(5, 5, 5, 5);

        // Report type
        controlGbc.gridx = 0;
        controlGbc.gridy = 0;
        controlsPanel.add(new JLabel("Type de Rapport :"), controlGbc);

        controlGbc.gridx = 1;
        controlsPanel.add(reportTypeCombo, controlGbc);

        // Start date
        controlGbc.gridx = 0;
        controlGbc.gridy = 1;
        controlsPanel.add(new JLabel("Date de Début :"), controlGbc);

        controlGbc.gridx = 1;
        controlsPanel.add(startDateSpinner, controlGbc);

        // End date
        controlGbc.gridx = 2;
        controlGbc.gridy = 1;
        controlsPanel.add(new JLabel("Date de Fin :"), controlGbc);

        controlGbc.gridx = 3;
        controlsPanel.add(endDateSpinner, controlGbc);

        contentPanel.add(controlsPanel, gbc);

        // Buttons
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_START;

        gbc.gridx = 0;
        contentPanel.add(generateReportBtn, gbc);

        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        contentPanel.add(backBtn, gbc);

        // Report area
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        contentPanel.add(reportScrollPane, gbc);
    }

    private void setupListeners() {
        generateReportBtn.addActionListener(e -> generateReport());

        backBtn.addActionListener(e -> {
            new ManageSaleView(user);
            dispose();
        });
    }

    private void generateReport() {
        String reportType = (String) reportTypeCombo.getSelectedItem();
        Date startDate = new Date(((java.util.Date) startDateSpinner.getValue()).getTime());
        Date endDate = new Date(((java.util.Date) endDateSpinner.getValue()).getTime());

        // Validate dates
        if (startDate.after(endDate)) {
            JOptionPane.showMessageDialog(this, "La date de début doit être antérieure à la date de fin !");
            return;
        }

        reportPanel.removeAll();

        switch (reportType) {
            case "Rapport Global":
                generateGlobalReport(startDate, endDate);
                break;
            case "Produits les Plus Vendus":
                generateTopProductsReport(startDate, endDate);
                break;
            case "Ventes par Catégorie":
                generateCategoryReport(startDate, endDate);
                break;
            case "Rapport Quotidien":
                generateDailyReport(startDate, endDate);
                break;
            case "Chiffre d'Affaires":
                generateRevenueReport(startDate, endDate);
                break;
        }

        reportPanel.revalidate();
        reportPanel.repaint();
    }

    private void generateGlobalReport(Date startDate, Date endDate) {
        JPanel globalPanel = new JPanel(new BorderLayout());

        // Summary statistics
        double totalRevenue = saleDAO.getTotalRevenueByDateRange(startDate, endDate);
        List<Object[]> topProducts = saleDAO.getTopSellingProducts(startDate, endDate, 5);

        JPanel summaryPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        summaryPanel.setBorder(BorderFactory.createTitledBorder("Résumé de la Période"));

        summaryPanel.add(new JLabel("Période :"));
        summaryPanel.add(new JLabel(startDate + " au " + endDate));

        summaryPanel.add(new JLabel("Chiffre d'Affaires Total :"));
        summaryPanel.add(new JLabel(String.format("%.2f €", totalRevenue)));

        summaryPanel.add(new JLabel("Nombre de Produits Différents Vendus :"));
        summaryPanel.add(new JLabel(String.valueOf(topProducts.size())));

        globalPanel.add(summaryPanel, BorderLayout.NORTH);

        // Top products table
        generateTopProductsTable(globalPanel, startDate, endDate);

        reportPanel.add(globalPanel, BorderLayout.CENTER);
    }

    private void generateTopProductsReport(Date startDate, Date endDate) {
        generateTopProductsTable(reportPanel, startDate, endDate);
    }

    private void generateTopProductsTable(JPanel container, Date startDate, Date endDate) {
        List<Object[]> topProducts = saleDAO.getTopSellingProducts(startDate, endDate, 10);

        String[] columnNames = { "Produit", "Prix Unitaire", "Quantité Vendue", "Chiffre d'Affaires" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Object[] product : topProducts) {
            model.addRow(new Object[] {
                    product[0], // product_name
                    String.format("%.2f €", (Double) product[1]), // unit_price
                    product[2], // total_quantity
                    String.format("%.2f €", (Double) product[3]) // total_revenue
            });
        }

        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Produits les Plus Vendus"));

        container.add(tableScrollPane, BorderLayout.CENTER);
    }

    private void generateCategoryReport(Date startDate, Date endDate) {
        List<Object[]> categoryData = saleDAO.getSalesByCategory(startDate, endDate);

        String[] columnNames = { "Catégorie", "Quantité Totale", "Chiffre d'Affaires" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Object[] category : categoryData) {
            model.addRow(new Object[] {
                    category[0], // category_name
                    category[1], // total_quantity
                    String.format("%.2f €", (Double) category[2]) // total_revenue
            });
        }

        JTable table = new JTable(model);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Ventes par Catégorie"));

        reportPanel.add(tableScrollPane, BorderLayout.CENTER);
    }

    private void generateDailyReport(Date startDate, Date endDate) {
        List<Object[]> dailyData = saleDAO.getDailySalesReport(startDate, endDate);

        String[] columnNames = { "Date", "Nombre de Ventes", "Quantité Totale", "Chiffre d'Affaires" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Object[] day : dailyData) {
            model.addRow(new Object[] {
                    day[0], // sale_date
                    day[1], // number_of_sales
                    day[2], // total_quantity
                    String.format("%.2f €", (Double) day[3]) // daily_revenue
            });
        }

        JTable table = new JTable(model);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Rapport Quotidien"));

        reportPanel.add(tableScrollPane, BorderLayout.CENTER);
    }

    private void generateRevenueReport(Date startDate, Date endDate) {
        double totalRevenue = saleDAO.getTotalRevenueByDateRange(startDate, endDate);

        JPanel revenuePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        JLabel titleLabel = new JLabel("CHIFFRE D'AFFAIRES");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel periodLabel = new JLabel("Période : " + startDate + " au " + endDate);
        periodLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        periodLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel revenueLabel = new JLabel(String.format("%.2f €", totalRevenue));
        revenueLabel.setFont(new Font("Arial", Font.BOLD, 36));
        revenueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        revenueLabel.setForeground(new Color(0, 128, 0));

        gbc.gridy = 0;
        revenuePanel.add(titleLabel, gbc);
        gbc.gridy = 1;
        revenuePanel.add(periodLabel, gbc);
        gbc.gridy = 2;
        revenuePanel.add(revenueLabel, gbc);

        reportPanel.add(revenuePanel, BorderLayout.CENTER);
    }
}
