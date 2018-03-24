package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Abstract class for working with *.properties configurations
 *
 * @author Raserei
 */
public abstract class Configuration{
    protected FileInputStream fileInputStream;
    protected Properties property = new Properties();

    public Configuration(String configurationPath) throws IOException {
        fileInputStream = new FileInputStream(configurationPath);
        property.load(fileInputStream);
        load();
        fileInputStream.close();
    }

    protected abstract void load();
}
