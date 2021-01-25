package utils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/cinema";
    private static final String USERNAME = "cherina";
    private static final String PASSWORD = "1111";

    public static Connection getConnection() {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

            return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't connect to DB");
        }
    }
}
