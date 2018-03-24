package utils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Raserei
 *         Class handles database connection.
 * @see #DBHandler(String, String) establishes a connection
 * @see #close() closes connection and MUST be used while server shutting down
 */
public class DBHandler {
    private Connection connection;

    /**
     * @param connectionUrl - Full connection string consumed by JDBC
     * @param jdbcClassName - Name of JDBC Driver (mySQL or SQLite)
     * @throws ClassNotFoundException if JDBC driver is incorrect
     * @throws SQLException           if connection cannot be created
     * @throws IllegalAccessException if the same exception was thrown while JDBC registering
     * @throws InstantiationException if JDBC driver cannot be instantiated
     */
    public DBHandler(String connectionUrl, String jdbcClassName)
            throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        try {
            DriverManager.registerDriver((Driver) Class.forName(jdbcClassName).newInstance());
            connection = DriverManager.getConnection(connectionUrl);
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            try {
                connection.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            } finally {
                throw e;
            }
        }
    }

    /**
     * Closes connection to database. This method MUST be used while server shutting down
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns link to established connection.
     *
     * @return {@link Connection}
     */
    public Connection getConnection() {
        return connection;
    }
}
