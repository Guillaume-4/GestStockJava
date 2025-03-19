package models.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:sqlserver://163.5.143.146:1433;databaseName=GestStockJava;encrypt=false";
    private static final String USER = "connect";
    private static final String PASSWORD = "?5Q7_53RV3R?**";

    private DBConnection() {}

    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
            return null;
        }
    }
}