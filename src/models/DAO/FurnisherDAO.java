package models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import models.Furnisher;

public class FurnisherDAO {
    public void addSale(Furnisher furnisher) {
        String query = "INSERT INTO Furnisher (furnisher_name, furnisher_adress, furnisher_complement, furnisher_zipcode, furnisher_city, furnisher_country, furnisher_phone) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, furnisher.getFurnisher_name());
            statement.setString(2, furnisher.getFurnisher_adress());
            statement.setString(3, furnisher.getFurnisher_complement());
            statement.setString(4, furnisher.getFurnisher_zipcode());
            statement.setString(5, furnisher.getFurnisher_city());
            statement.setString(6, furnisher.getFurnisher_country());
            statement.setString(7, furnisher.getFurnisher_phone());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du fournisseur : " + e.getMessage());
        }
    }
}
