package utils;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * @author Raserei
 *         DBHandler simple test
 */
public class DBHandlerTest {
    private static DBHandler dbHandler;

    @Test
    public void DBHandlerCreation() {
        assertNotNull(dbHandler);
    }

    @Test
    public void connectionTest() {
        assertNotNull(dbHandler.getConnection());
    }

    @AfterClass
    public static void finishTest() {
        dbHandler.close();
    }

    @BeforeClass
    public static void startTest()
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        dbHandler = new DBHandler("jdbc:sqlite:../dbref.db", "org.sqlite.JDBC");
    }
}
