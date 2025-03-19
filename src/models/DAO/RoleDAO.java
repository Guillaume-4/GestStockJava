package models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Role;

public class RoleDAO {
    public Role getRoleByID(int role_id) {
        String query = "SELECT * FROM Role WHERE role_id = ?";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, role_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("role_id");
                String name = resultSet.getString("role_name");

                return new Role(id, name);
            } else
                return null;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du rôle : " + e.getMessage());
            return null;
        }
    }

    public Role getRoleByName(String role_name) {
        String query = "SELECT * FROM Role WHERE role_name = ?";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, role_name);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("role_id");
                String name = resultSet.getString("role_name");

                return new Role(id, name);
            } else
                return null;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du rôle : " + e.getMessage());
            return null;
        }
    }

    public List<Role> getAllRoles() {
        String query = "SELECT * FROM Role";
        List<Role> roles = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("role_id");
                String name = resultSet.getString("role_name");

                roles.add(new Role(id, name));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des rôles : " + e.getMessage());
        }

        return roles;
    }
}
