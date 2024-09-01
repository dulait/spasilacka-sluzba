package config;

import java.io.InputStream;
import java.util.Properties;

/**
 * Klasa {@code Config} je odgovorna za konfiguraciju aplikacije.
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
                System.out.println("Datoteka sa svojstvima nije pronađena.");
            }
        } catch (Exception ex) {
            System.out.println("Greška prilikom učitavanja datoteke sa svojstvima: " + ex.getMessage());
        }
    }

    /**
     * Vraća korisničko ime za pristup bazi.
     *
     * @return korisničko ime podešeno u {@code prop.properties}, ili
     * {@code null} ako nije postavljeno
     */
    public static String getUsername() {
        return properties.getProperty("username");
    }

    /**
     * Vraća lozinku za pristup bazi.
     *
     * @return lozinka podešena u {@code prop.properties}, ili {@code null} ako
     * nije postavljena
     */
    public static String getPassword() {
        return properties.getProperty("pass");
    }

    /**
     * Vraća URL baze (connection string).
     *
     * @return URL baze podataka podešen u {@code prop.properties}, ili
     * {@code null} ako nije postavljen
     */
    public static String getDatabaseUrl() {
        return properties.getProperty("url");
    }

    /**
     * Vraća broj porta preko kojeg se vrši socket komunikacija.
     *
     * @return broj porta podešen u {@code prop.properties}, ili {@code 0} ako
     * nije postavljen ili ako dođe do greške.
     */
    public static int getPort() {
        String portString = properties.getProperty("port");
        try {
            return Integer.parseInt(portString);
        } catch (NumberFormatException e) {
            System.out.println("Greška prilikom parsiranja broja porta: " + e.getMessage());
            return 0;
        }
    }
}
