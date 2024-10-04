package utils;

import exception.LoadPropertiesException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Manipulation {

    private Manipulation() {}

    public static Properties getProp() {
        Properties props = new Properties();

        try (FileInputStream file = new FileInputStream("src/main/resources/application-config.properties")){
            props.load(file);
        } catch (IOException e) {
            throw new LoadPropertiesException("Error loading properties file", e);
        }

        return props;
    }
}
