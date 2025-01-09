package models.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import models.Sale;

public class SaleDAO {
    public void addSale(Sale sale) {
        String query = "INSERT INTO Sale (sale_quantity, sale_date, product_id) VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, sale.getSale_quantity());
            statement.setDate(2, Date.valueOf(sale.getSale_date().toString()));
            statement.setInt(3, sale.getProduct().getProduct_id());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la vente : " + e.getMessage());
        }
    }
}
