package utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private ConnectionPool() {
        //private constructor
    }

    private static ConnectionPool connectionPool = null;

    public static ConnectionPool getInstance() {
        if (connectionPool == null)
            connectionPool = new ConnectionPool();
        return connectionPool;
    }

    public Connection getConnection() {
        Context ctx;
        Connection connection = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cinema");
            connection = ds.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}