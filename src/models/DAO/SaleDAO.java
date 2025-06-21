package models.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Product;
import models.Sale;

public class SaleDAO {
    public void addSale(Sale sale) {
        // Vérifier d'abord si on a assez de stock
        ProductDAO productDAO = new ProductDAO();
        if (!productDAO.hasEnoughStock(sale.getProduct().getProductId(), sale.getSaleQuantity())) {
            throw new IllegalArgumentException("Stock insuffisant pour ce produit");
        }
        
        String query = "INSERT INTO Sale (sale_quantity, sale_date, product_id) VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, sale.getSaleQuantity());
            statement.setDate(2, Date.valueOf(sale.getSaleDate().toLocalDate()));
            statement.setInt(3, sale.getProduct().getProductId());

            statement.executeUpdate();
            
            // Réduire la quantité en stock
            if (!productDAO.reduceProductQuantity(sale.getProduct().getProductId(), sale.getSaleQuantity())) {
                throw new SQLException("Impossible de mettre à jour le stock");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la vente : " + e.getMessage());
            throw new RuntimeException("Erreur lors de l'ajout de la vente : " + e.getMessage());
        }
    }

    public List<Sale> getSalesByProductAndDateRange(int productId, Date startDate, Date endDate) {
        List<Sale> sales = new ArrayList<>();

        String query = "SELECT * FROM Sale WHERE product_id = ? AND sale_date BETWEEN ? AND ?";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productId);
            statement.setDate(2, startDate);
            statement.setDate(3, endDate);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int saleId = resultSet.getInt("sale_id");
                Date date = resultSet.getDate("sale_date");
                int quantite = resultSet.getInt("sale_quantity");

                Product produit = new ProductDAO().getProductByID(productId);

                sales.add(new Sale(saleId, quantite, date, produit));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des ventes : " + e.getMessage());
        }

        return sales;
    }

    public List<Sale> getAllSales() {
        List<Sale> sales = new ArrayList<>();

        String query = "SELECT * FROM Sale";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int saleId = resultSet.getInt("sale_id");
                Date date = resultSet.getDate("sale_date");
                int quantite = resultSet.getInt("sale_quantity");
                int productId = resultSet.getInt("product_id");

                Product produit = new ProductDAO().getProductByID(productId);

                sales.add(new Sale(saleId, quantite, date, produit));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des ventes : " + e.getMessage());
        }

        return sales;
    }

    public Sale getSaleByID(int saleID) {
        
        String query = "SELECT * FROM Sale WHERE sale_id = ?";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, saleID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int saleId = resultSet.getInt("sale_id");
                Date date = resultSet.getDate("sale_date");
                int quantite = resultSet.getInt("sale_quantity");
                int productId = resultSet.getInt("product_id");

                Product produit = new ProductDAO().getProductByID(productId);

                return new Sale(saleId, quantite, date, produit);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des ventes : " + e.getMessage());
        }

        return null;
    }

    public void updateSale(Sale sale) {
        // Récupérer l'ancienne vente pour comparer les quantités
        Sale oldSale = getSaleByID(sale.getSaleId());
        if (oldSale == null) {
            throw new IllegalArgumentException("Vente non trouvée");
        }
        
        ProductDAO productDAO = new ProductDAO();
        
        // Calculer la différence de quantité
        int oldQuantity = oldSale.getSaleQuantity();
        int newQuantity = sale.getSaleQuantity();
        int quantityDifference = newQuantity - oldQuantity;
        
        // Si on augmente la quantité vendue, vérifier qu'on a assez de stock
        if (quantityDifference > 0) {
            if (!productDAO.hasEnoughStock(sale.getProduct().getProductId(), quantityDifference)) {
                throw new IllegalArgumentException("Stock insuffisant pour cette modification");
            }
        }
        
        String query = "UPDATE Sale SET sale_quantity=?, sale_date=?, product_id=? WHERE sale_id=?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, sale.getSaleQuantity());
            statement.setDate(2, sale.getSaleDate());
            statement.setInt(3, sale.getProduct().getProductId());
            statement.setInt(4, sale.getSaleId());

            statement.executeUpdate();
            
            // Ajuster le stock selon la différence
            if (quantityDifference > 0) {
                // On vend plus, donc on réduit le stock
                if (!productDAO.reduceProductQuantity(sale.getProduct().getProductId(), quantityDifference)) {
                    throw new SQLException("Impossible de mettre à jour le stock");
                }
            } else if (quantityDifference < 0) {
                // On vend moins, donc on augmente le stock
                if (!productDAO.increaseProductQuantity(sale.getProduct().getProductId(), -quantityDifference)) {
                    throw new SQLException("Impossible de mettre à jour le stock");
                }
            }
            // Si quantityDifference == 0, pas de changement de stock nécessaire
            
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification de la vente : " + e.getMessage());
            throw new RuntimeException("Erreur lors de la modification de la vente : " + e.getMessage());
        }
    }

    public void deleteSale(int saleId) {
        // Récupérer la vente avant de la supprimer pour remettre le stock
        Sale saleToDelete = getSaleByID(saleId);
        if (saleToDelete == null) {
            throw new IllegalArgumentException("Vente non trouvée");
        }
        
        String query = "DELETE FROM Sale WHERE sale_id=?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, saleId);
            statement.executeUpdate();
            
            // Remettre la quantité vendue en stock
            ProductDAO productDAO = new ProductDAO();
            if (!productDAO.increaseProductQuantity(saleToDelete.getProduct().getProductId(), saleToDelete.getSaleQuantity())) {
                throw new SQLException("Impossible de remettre le stock");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la vente : " + e.getMessage());
            throw new RuntimeException("Erreur lors de la suppression de la vente : " + e.getMessage());
        }
    }

    // Méthodes pour les rapports de vente
    public List<Sale> getSalesByDateRange(Date startDate, Date endDate) {
        List<Sale> sales = new ArrayList<>();
        String query = "SELECT * FROM Sale WHERE sale_date BETWEEN ? AND ? ORDER BY sale_date DESC";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, startDate);
            statement.setDate(2, endDate);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int saleId = resultSet.getInt("sale_id");
                Date date = resultSet.getDate("sale_date");
                int quantite = resultSet.getInt("sale_quantity");
                int productId = resultSet.getInt("product_id");

                Product produit = new ProductDAO().getProductByID(productId);
                sales.add(new Sale(saleId, quantite, date, produit));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des ventes par période : " + e.getMessage());
        }

        return sales;
    }

    public double getTotalRevenueByDateRange(Date startDate, Date endDate) {
        String query = """
            SELECT SUM(s.sale_quantity * p.product_unit_price) as total_revenue 
            FROM Sale s 
            JOIN Product p ON s.product_id = p.product_id 
            WHERE s.sale_date BETWEEN ? AND ?
        """;

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, startDate);
            statement.setDate(2, endDate);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble("total_revenue");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors du calcul du chiffre d'affaires : " + e.getMessage());
        }

        return 0.0;
    }

    public List<Object[]> getTopSellingProducts(Date startDate, Date endDate, int limit) {
        List<Object[]> topProducts = new ArrayList<>();
        String query = """
            SELECT TOP (?) p.product_name, p.product_unit_price, SUM(s.sale_quantity) as total_quantity,
                   SUM(s.sale_quantity * p.product_unit_price) as total_revenue
            FROM Sale s 
            JOIN Product p ON s.product_id = p.product_id 
            WHERE s.sale_date BETWEEN ? AND ?
            GROUP BY p.product_id, p.product_name, p.product_unit_price
            ORDER BY total_quantity DESC
        """;

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, limit);
            statement.setDate(2, startDate);
            statement.setDate(3, endDate);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Object[] row = {
                    resultSet.getString("product_name"),
                    resultSet.getDouble("product_unit_price"),
                    resultSet.getInt("total_quantity"),
                    resultSet.getDouble("total_revenue")
                };
                topProducts.add(row);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des produits les plus vendus : " + e.getMessage());
        }

        return topProducts;
    }

    public List<Object[]> getSalesByCategory(Date startDate, Date endDate) {
        List<Object[]> salesByCategory = new ArrayList<>();
        String query = """
            SELECT c.category_name, SUM(s.sale_quantity) as total_quantity,
                   SUM(s.sale_quantity * p.product_unit_price) as total_revenue
            FROM Sale s 
            JOIN Product p ON s.product_id = p.product_id 
            JOIN Category c ON p.category_id = c.category_id
            WHERE s.sale_date BETWEEN ? AND ?
            GROUP BY c.category_id, c.category_name
            ORDER BY total_revenue DESC
        """;

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, startDate);
            statement.setDate(2, endDate);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Object[] row = {
                    resultSet.getString("category_name"),
                    resultSet.getInt("total_quantity"),
                    resultSet.getDouble("total_revenue")
                };
                salesByCategory.add(row);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des ventes par catégorie : " + e.getMessage());
        }

        return salesByCategory;
    }

    public List<Object[]> getDailySalesReport(Date startDate, Date endDate) {
        List<Object[]> dailySales = new ArrayList<>();
        String query = """
            SELECT s.sale_date, COUNT(*) as number_of_sales, SUM(s.sale_quantity) as total_quantity,
                   SUM(s.sale_quantity * p.product_unit_price) as daily_revenue
            FROM Sale s 
            JOIN Product p ON s.product_id = p.product_id 
            WHERE s.sale_date BETWEEN ? AND ?
            GROUP BY s.sale_date
            ORDER BY s.sale_date DESC
        """;

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, startDate);
            statement.setDate(2, endDate);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Object[] row = {
                    resultSet.getDate("sale_date"),
                    resultSet.getInt("number_of_sales"),
                    resultSet.getInt("total_quantity"),
                    resultSet.getDouble("daily_revenue")
                };
                dailySales.add(row);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la génération du rapport quotidien : " + e.getMessage());
        }

        return dailySales;
    }
}
