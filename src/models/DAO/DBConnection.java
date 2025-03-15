package models.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

import io.github.cdimascio.dotenv.Dotenv;

public class DBConnection {
    private static final Dotenv DOTENV = Dotenv.load();

    private DBConnection() {
    }

    public static Connection getConnection() {
        try {
            String URL = DOTENV.get("DB_URL");
            String USER = DOTENV.get("DB_USER");
            String PASSWORD = DOTENV.get("DB_PASS");

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
            return null;
        }
    }
}