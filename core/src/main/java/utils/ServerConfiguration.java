package utils;

import java.io.IOException;

/**
 * Class is reponsible for server configuration properties from the specified {@link java.util.Properties} file.
 * @see #getDbClassPath() Class path for class loader inside {@link DBHandler}
 * @see #getDbConnectionURL() JDBC connection string for {@link DBHandler}
 * @see #getServerPort() Port server listens
 */
public class ServerConfiguration extends Configuration {
    private static final String DBDRIVER_PROP_NAME = "dbDriver";
    private static final String DBCONNECTIONSTRING_PROP_NAME = "dbConnectionString";
    private static final String SERVERPORT_PROP_NAME = "port";
    private static final String MYSQL_CLASS_PATH = "com.mysql.jdbc.Driver";
    private static final String SQLITE_CLASS_PATH = "org.sqlite.JDBC";
    private String dbClassPath;
    private String dbConnectionURL;
    private int serverPort;

    /**
     * Parses specified file and assigns values
     *
     * @param configurationPath - path to the *.properties file
     * @throws IOException in case file is not found or unable to read
     */
    public ServerConfiguration(String configurationPath) throws IOException {
        super(configurationPath);
    }

    /**
     * Parses file and assign values to the config properties.
     * Translate database type to the JDBC class
     */
    @Override
    protected void load() {
        dbDriver driver = dbDriver.valueOf(property.getProperty(DBDRIVER_PROP_NAME));
        switch (driver) {
            case mySQL:
                dbClassPath = MYSQL_CLASS_PATH;
                break;
            case SQLite:
                dbClassPath = SQLITE_CLASS_PATH;
                break;
            default:
                throw new RuntimeException("No suitable driver for chosen database type: " + driver.toString());
        }
        dbConnectionURL = property.getProperty(DBCONNECTIONSTRING_PROP_NAME);
        serverPort = Integer.parseInt(property.getProperty(SERVERPORT_PROP_NAME));
    }

    /**
     * @return JDBC driver path for the ClassLoader
     */
    public String getDbClassPath() {
        return dbClassPath;
    }

    /**
     * @return Database connection URL
     */
    public String getDbConnectionURL() {
        return dbConnectionURL;
    }

    /**
     * @return server port
     */
    public int getServerPort() {
        return serverPort;
    }

    private enum dbDriver {SQLite, mySQL}
}
