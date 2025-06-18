package models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Client;

public class ClientDAO {
    public static void addclient(Client client) {
        String query = "INSERT INTO client (client_name, client_address, client_complement, client_zipcode, client_city, client_country, client_phone) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, client.getClient_name());
            statement.setString(2, client.getClient_address());
            statement.setString(3, client.getClient_complement());
            statement.setString(4, client.getClient_zipcode());
            statement.setString(5, client.getClient_city());
            statement.setString(6, client.getClient_country());
            statement.setString(7, client.getClient_phone());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du Client : " + e.getMessage());
        }
    }

    public Client getclientByID(int client_id) {
        String query = "SELECT * FROM client WHERE client_id = ?";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, client_id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("client_id");
                String name = resultSet.getString("client_name");
                String adress = resultSet.getString("client_address");
                String complement = resultSet.getString("client_complement");
                String zipcode = resultSet.getString("client_zipcode");
                String city = resultSet.getString("client_city");
                String country = resultSet.getString("client_country");
                String phone = resultSet.getString("client_phone");

                return new Client(name, adress, complement, zipcode, city, country, phone);
            } else
                return null;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du Client : " + e.getMessage());
            return null;
        }
    }

    public Client getclientByName(String client_name) {
        String query = "SELECT * FROM client WHERE client_name = ?";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, client_name);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("client_name");
                String adress = resultSet.getString("client_address");
                String complement = resultSet.getString("client_complement");
                String zipcode = resultSet.getString("client_zipcode");
                String city = resultSet.getString("client_city");
                String country = resultSet.getString("client_country");
                String phone = resultSet.getString("client_phone");

                return new Client(name, adress, complement, zipcode, city, country, phone);
            } else
                return null;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du Client : " + e.getMessage());
            return null;
        }
    }

    public static void updateclient(Client client) {
        String query = "UPDATE Client SET client_name = ?, client_address = ?, client_complement = ?, client_zipcode = ?, client_city = ?, client_country = ?, client_phone = ? WHERE client_id=?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, client.getClient_name());
            statement.setString(2, client.getClient_address());
            statement.setString(3, client.getClient_complement());
            statement.setString(4, client.getClient_zipcode());
            statement.setString(5, client.getClient_city());
            statement.setString(6, client.getClient_country());
            statement.setString(7, client.getClient_phone());
            statement.setInt(8, client.getClient_id());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification du Client : " + e.getMessage());
        }
    }


    public List<Client> getclients() {
        String query = "SELECT * FROM client";
        List<Client> clients = new ArrayList<Client>();

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("client_id");
                String name = resultSet.getString("client_name");
                String adress = resultSet.getString("client_address");
                String complement = resultSet.getString("client_complement");
                String zipcode = resultSet.getString("client_zipcode");
                String city = resultSet.getString("client_city");
                String country = resultSet.getString("client_country");
                String phone = resultSet.getString("client_phone");

                clients.add(new Client(name, adress, complement, zipcode, city, country, phone));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la catégorie : " + e.getMessage());
        }

        return clients;
    }
}
