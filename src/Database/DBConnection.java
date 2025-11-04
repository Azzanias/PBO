package database;

import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/Laundry";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "password";
    
    private static Connection connection;
    
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Koneksi database berhasil!");
            }
        } catch (Exception e) {
            System.out.println("Koneksi database gagal: " + e.getMessage());
        }
        return connection;
    }
}