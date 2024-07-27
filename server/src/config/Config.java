package config;

import java.io.InputStream;
import java.util.Properties;

/**
 * The {@code Config} class is responsible for loading and providing
 * configuration properties for the application.
 * <p>
 * This class reads configuration settings from a properties file named
 * {@code prop.properties}. It provides static methods to retrieve specific
 * configuration values such as database connection details.
 * </p>
 *
 * @author dulait
 */
public class Config {

    private static final Properties properties = new Properties();

    static {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("prop.properties")) {
            if (in != null) {
                properties.load(in);
            } else {
                System.out.println("Property file not found.");
            }
        } catch (Exception ex) {
            System.out.println("Error loading property file: " + ex.getMessage());
        }
    }

    /**
     * Retrieves the username property from the configuration file.
     *
     * @return the username configured in {@code prop.properties}, or
     * {@code null} if not set
     */
    public static String getUsername() {
        return properties.getProperty("username");
    }

    /**
     * Retrieves the password property from the configuration file.
     *
     * @return the password configured in {@code prop.properties}, or
     * {@code null} if not set
     */
    public static String getPassword() {
        return properties.getProperty("pass");
    }

    /**
     * Retrieves the database URL property from the configuration file.
     *
     * @return the database URL configured in {@code prop.properties}, or
     * {@code null} if not set
     */
    public static String getDatabaseUrl() {
        return properties.getProperty("url");
    }

    /**
     * Retrieves the port number property from the configuration file.
     *
     * @return the port number configured in {@code prop.properties}, or
     * {@code 0} if not set or if an error occurs
     */
    public static int getPort() {
        String portString = properties.getProperty("port");
        try {
            return Integer.parseInt(portString);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing port number: " + e.getMessage());
            return 0;
        }
    }
}
