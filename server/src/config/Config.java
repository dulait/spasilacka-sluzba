package config;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Properties properties = new Properties();

    static {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("prop.properties")) {
            properties.load(in);
        } catch (Exception ex) {
            System.out.println("Greska prilikom ucitavanja Property fajla");
        }
    }

    public static String getUsername() {
        return properties.getProperty("username");
    }

    public static String getPassword() {
        return properties.getProperty("pass");
    }

    public static String getDatabaseUrl() {
        return properties.getProperty("url");
    }

    public static int getPort() {
        return Integer.parseInt(properties.getProperty("port"));
    }
}
