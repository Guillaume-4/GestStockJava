package models.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.AppUser;
import utils.PasswordUtil;

public class AppUserDAO {
    private AppUser getUserByName(String user_name) {
        String query = "SELECT * FROM AppUser WHERE user_name = ?";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user_name);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String name = resultSet.getString("user_name");
                String password = resultSet.getString("user_password");
                int role = resultSet.getInt("role_id");

                return new AppUser(id, name, password, role);
            } else
                return null;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de l'utilisateur : " + e.getMessage());
            return null;
        }
    }

    public AppUser authenticateUser(String username, String plainPassword) {
        AppUser user = getUserByName(username);

        if (user != null && PasswordUtil.verifyPassword(plainPassword, user.getUserPassword()))
            return user;

        return null;
    }

    public AppUser createUser(String user_name, String user_password, int role_id) {
        String query = "INSERT INTO AppUser (user_name, user_password, role_id) VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user_name);
            preparedStatement.setString(2, user_password);
            preparedStatement.setInt(3, role_id);

            int result = preparedStatement.executeUpdate();

            if (result == 1) {
                return new AppUser(user_name, null, role_id);
            } else
                return null;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création de l'utilisateur : " + e.getMessage());
            return null;
        }
    }

    public boolean checkUser(String user_name) {
        String query = "SELECT TOP 1 1 FROM AppUser WHERE user_name = ?";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user_name);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la vérification de l'utilisateur : " + e.getMessage());
            return false;
        }
    }

    public AppUser getUserById(int user_id) {
        String query = "SELECT * FROM AppUser WHERE user_id = ?";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, user_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String name = resultSet.getString("user_name");
                String password = resultSet.getString("user_password");
                int role = resultSet.getInt("role_id");

                return new AppUser(id, name, password, role);
            } else
                return null;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de l'utilisateur : " + e.getMessage());
            return null;
        }
    }

    public List<AppUser> getAllUsers() {
        List<AppUser> users = new ArrayList<>();
        String query = "SELECT * FROM AppUser";

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String name = resultSet.getString("user_name");
                String password = resultSet.getString("user_password");
                int role = resultSet.getInt("role_id");

                users.add(new AppUser(id, name, password, role));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des utilisateurs : " + e.getMessage());
        }

        return users;
    }

    public void updateUser(AppUser user) {
        String query = "UPDATE AppUser SET user_name=?, user_password=?, role_id=? WHERE user_id=?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getUserPassword());
            statement.setInt(3, user.getUserRole());
            statement.setInt(4, user.getUserId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification de l'utilisateur : " + e.getMessage());
        }
    }

    public void updateUserWithoutPassword(AppUser user) {
        String query = "UPDATE AppUser SET user_name=?, role_id=? WHERE user_id=?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUserName());
            statement.setInt(2, user.getUserRole());
            statement.setInt(3, user.getUserId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification de l'utilisateur : " + e.getMessage());
        }
    }

    public void updateUserPassword(int userId, String hashedPassword) {
        String query = "UPDATE AppUser SET user_password=? WHERE user_id=?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, hashedPassword);
            statement.setInt(2, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification du mot de passe : " + e.getMessage());
        }
    }

    public void deleteUser(int userId) {
        String query = "DELETE FROM AppUser WHERE user_id=?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
        }
    }
}