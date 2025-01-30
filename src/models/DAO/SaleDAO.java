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
        String query = "INSERT INTO Sale (sale_quantity, sale_date, product_id) VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, sale.getSaleQuantity());
            statement.setDate(2, Date.valueOf(sale.getSaleDate().toLocalDate()));
            statement.setInt(3, sale.getProduct().getProductId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la vente : " + e.getMessage());
        }
    }

    public List<Sale> getSalesByProductAndDateRange(int productId, Date startDate, Date endDate) {
        List<Sale> sales = new ArrayList<>();

        String query = "SELECT * FROM Sales WHERE product_id = ? AND sale_date BETWEEN ? AND ?";

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
}
