package models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Furnisher;

public class FurnisherDAO {
    public static void addFurnisher(Furnisher furnisher) {
        String query = "INSERT INTO Furnisher (furnisher_name, furnisher_address, furnisher_complement, furnisher_zipcode, furnisher_city, furnisher_country, furnisher_phone) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, furnisher.getFurnisherName());
            statement.setString(2, furnisher.getFurnisherAdress());
            statement.setString(3, furnisher.getFurnisherComplement());
            statement.setString(4, furnisher.getFurnisherZipcode());
            statement.setString(5, furnisher.getFurnisherCity());
            statement.setString(6, furnisher.getFurnisherCountry());
            statement.setString(7, furnisher.getFurnisherPhone());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du fournisseur : " + e.getMessage());
        }
    }

    public Furnisher getFurnisherByID(int furnisher_id) {
        String query = "SELECT * FROM Furnisher WHERE furnisher_id = ?";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, furnisher_id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("furnisher_id");
                String name = resultSet.getString("furnisher_name");
                String adress = resultSet.getString("furnisher_address");
                String complement = resultSet.getString("furnisher_complement");
                String zipcode = resultSet.getString("furnisher_zipcode");
                String city = resultSet.getString("furnisher_city");
                String country = resultSet.getString("furnisher_country");
                String phone = resultSet.getString("furnisher_phone");

                return new Furnisher(id, name, adress, complement, zipcode, city, country, phone);
            } else
                return null;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du fournisseur : " + e.getMessage());
            return null;
        }
    }

    public Furnisher getFurnisherByName(String furnisher_name) {
        String query = "SELECT * FROM Furnisher WHERE furnisher_name = ?";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, furnisher_name);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("furnisher_id");
                String name = resultSet.getString("furnisher_name");
                String adress = resultSet.getString("furnisher_address");
                String complement = resultSet.getString("furnisher_complement");
                String zipcode = resultSet.getString("furnisher_zipcode");
                String city = resultSet.getString("furnisher_city");
                String country = resultSet.getString("furnisher_country");
                String phone = resultSet.getString("furnisher_phone");

                return new Furnisher(id, name, adress, complement, zipcode, city, country, phone);
            } else
                return null;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du fournisseur : " + e.getMessage());
            return null;
        }
    }

    public static void updateFurnisher(Furnisher furnisher) {
        String query = "UPDATE Furnisher SET furnisher_name = ?, furnisher_address = ?, furnisher_complement = ?, furnisher_zipcode = ?, furnisher_city = ?, furnisher_country = ?, furnisher_phone = ? WHERE furnisher_id=?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, furnisher.getFurnisherName());
            statement.setString(2, furnisher.getFurnisherAdress());
            statement.setString(3, furnisher.getFurnisherComplement());
            statement.setString(4, furnisher.getFurnisherZipcode());
            statement.setString(5, furnisher.getFurnisherCity());
            statement.setString(6, furnisher.getFurnisherCountry());
            statement.setString(7, furnisher.getFurnisherPhone());
            statement.setInt(8, furnisher.getFurnisherId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification du fournisseur : " + e.getMessage());
        }
    }


    public List<Furnisher> getFurnishers() {
        String query = "SELECT * FROM Furnisher";
        List<Furnisher> furnishers = new ArrayList<Furnisher>();

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("furnisher_id");
                String name = resultSet.getString("furnisher_name");
                String adress = resultSet.getString("furnisher_address");
                String complement = resultSet.getString("furnisher_complement");
                String zipcode = resultSet.getString("furnisher_zipcode");
                String city = resultSet.getString("furnisher_city");
                String country = resultSet.getString("furnisher_country");
                String phone = resultSet.getString("furnisher_phone");

                furnishers.add(new Furnisher(id, name, adress, complement, zipcode, city, country, phone));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la catégorie : " + e.getMessage());
        }

        return furnishers;
    }

    public void deleteFurnisher(int furnisherId) {
        String query = "DELETE FROM furnisher WHERE furnisher_id=?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, furnisherId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du Fournisseur : " + e.getMessage());
        }
    }
}
