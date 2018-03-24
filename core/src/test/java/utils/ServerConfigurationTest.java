package utils;

import org.junit.BeforeClass;
import org.junit.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * Test for {@link ServerConfiguration}
 */
public class ServerConfigurationTest {
    static  Properties props;
    static ServerConfiguration serverConfiguration;

    @BeforeClass
    public static void init(){
        try {
            FileInputStream fis = new FileInputStream("../server.properties");
            props = new Properties();
            props.load(fis);
            fis.close();
            serverConfiguration = new ServerConfiguration("../server.properties");
        } catch (java.io.IOException e) {
            fail("Smth wrong with this files");
            e.printStackTrace();
        }
    }

    @Test
    public void getDbClassPathTest(){
        String dbType = "";
        switch (props.getProperty("dbDriver")){
            case "mySQL":
                dbType = "com.mysql.jdbc.Driver";
                break;
            case "SQLite":
                dbType = "org.sqlite.JDBC";
                break;
            default:
                fail("Unrecognized database driver"); //if we received smth else from this method instead of RTE, it's fail
                break;
        }
        assertEquals(dbType,serverConfiguration.getDbClassPath());
    }

    @Test
    public void getDbConnectionURLTest(){
        assertEquals(props.getProperty("dbConnectionString"),serverConfiguration.getDbConnectionURL());
    }

    @Test
    public void getServerPortTest(){
        assertEquals(Integer.parseInt(props.getProperty("port")),serverConfiguration.getServerPort());
    }

    @Test(expected = IOException.class)
    public void checkRTEFileIsLost() throws IOException {
        ServerConfiguration servConf = new ServerConfiguration("../wrongFile.properties");
    }
}
