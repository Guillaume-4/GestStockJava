package models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.AppUser;

public class AppUserDAO {
    public AppUser getUser(String user_name, String user_password) {
        String query = "SELECT * FROM AppUser WHERE user_name = ? AND user_password = ?";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user_name);
            preparedStatement.setString(2, user_password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String name = resultSet.getString("user_name");
                String password = resultSet.getString("user_password");
                String role = resultSet.getString("user_role");

                return new AppUser(id, name, password, role);
            } else
                return null;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de l'utilisateur : " + e.getMessage());
            return null;
        }
    }
}