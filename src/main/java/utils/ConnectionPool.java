package utils;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * The type Connection pool.
 */
public class ConnectionPool {
    private static final Logger LOG = LoggerFactory.getLogger(ConnectionPool.class);

    private ConnectionPool() {
        //private constructor
    }

    private static ConnectionPool connectionPool = null;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ConnectionPool getInstance() {
        if (connectionPool == null)
            connectionPool = new ConnectionPool();
        return connectionPool;
    }

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        Context ctx;
        Connection connection = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cinema");
            connection = ds.getConnection();
        } catch (NamingException | SQLException e) {
            LOG.error("NamingException | SQLException in getConnection method of ConnectionPool class", e);
        }
        return connection;
    }
}