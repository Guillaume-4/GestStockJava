package models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import models.Category;

public class CategoryDAO {
    public void addSale(Category category) {
        String query = "INSERT INTO Category (category_name) VALUES (?)";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, category.getCategory_name());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la catégorie : " + e.getMessage());
        }
    }
}
