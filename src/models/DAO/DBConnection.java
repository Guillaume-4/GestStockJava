package models.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static String URL = "jdbc:sqlserver://163.5.143.146:1433;databaseName=GestStockJava;encrypt=false";
    private static String USER = "connect";
    private static String PASSWORD = "?5Q7_53RV3R?*";
    private static Connection connection;

    private DBConnection() {}

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (Exception e) {
                System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
            }
        }
        return connection;
    }
}
