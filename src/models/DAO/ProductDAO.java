package models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import models.Product;

public class ProductDAO {
    public void addProduct(Product product) {
        String query = "INSERT INTO product (product_name, product_quantity, product_unit_price, category_id, furnisher_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getProduct_name());
            statement.setInt(2, product.getProduct_quantity());
            statement.setDouble(3, product.getProduct_unit_price());
            statement.setDouble(4, product.getCategory().getCategory_id());
            statement.setDouble(5, product.getFurnisher().getFurnisher_id());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du produit : " + e.getMessage());
        }
    }
}