package models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import models.Client;

public class ClientDAO {
    public void addClient(Client client) {
        String query = "INSERT INTO Sale (client_lastname, client_firstname, client_adress, client_complement, client_zipcode, client_city, client_country, client_phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, client.getClient_lastname());
            statement.setString(2, client.getClient_firstname());
            statement.setString(3, client.getClient_adress());
            statement.setString(4, client.getClient_complement());
            statement.setString(5, client.getClient_zipcode());
            statement.setString(6, client.getClient_city());
            statement.setString(7, client.getClient_country());
            statement.setString(8, client.getClient_phone());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du client : " + e.getMessage());
        }
    }
}
