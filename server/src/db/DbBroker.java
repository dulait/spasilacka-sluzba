package db;

import config.Config;
import domen.OpstiDomenskiObjekat;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The {@code DbBroker} class is a singleton that provides a way for interacting
 * with the database.
 * <p>
 * This class manages database connections and provides methods for executing
 * SQL queries, including operations for retrieving, inserting, updating, and
 * deleting domain objects. It handles transactions using commit and rollback
 * operations to ensure data consistency.
 * </p>
 *
 * <p>
 * The database connection is established using credentials and configuration
 * specified in the {@code Config} class. All database operations are performed
 * within the context of a transaction.
 * </p>
 *
 * @author dulait
 */
public class DbBroker {

    private static DbBroker instanca;
    private Connection konekcija;

    /**
     * Private constructor to prevent direct instantiation.
     */
    private DbBroker() {
    }

    /**
     * Retrieves the singleton instance of {@code DbBroker}.
     * <p>
     * If the instance does not exist, it is created and initialized.
     * </p>
     *
     * @return the singleton instance of {@code DbBroker}
     */
    public static DbBroker getInstanca() {
        if (instanca == null) {
            instanca = new DbBroker();
        }
        return instanca;
    }

    /**
     * Opens a connection to the database using credentials from the
     * {@code Config} class.
     * <p>
     * The connection is set to manual commit mode. Any database operations
     * should be enclosed in transactions that are explicitly committed or
     * rolled back.
     * </p>
     */
    public void otvoriKonekciju() {
        try {
            konekcija = DriverManager.getConnection(
                    Config.getDatabaseUrl(),
                    Config.getUsername(),
                    Config.getPassword()
            );
            konekcija.setAutoCommit(false);
        } catch (SQLException ex) {
            System.err.println("Failed to connect to the database: " + ex.getMessage());
        }
    }

    /**
     * Closes the current database connection.
     * <p>
     * Any active transactions should be committed or rolled back before closing
     * the connection.
     * </p>
     */
    public void zatvoriKonekciju() {
        try {
            if (konekcija != null && !konekcija.isClosed()) {
                konekcija.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, "Error closing the database connection", ex);
        }
    }

    /**
     * Commits the current transaction.
     * <p>
     * This method should be called to persist changes made during the current
     * transaction.
     * </p>
     */
    public void commit() {
        try {
            if (konekcija != null && !konekcija.isClosed()) {
                konekcija.commit();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, "Error committing the transaction", ex);
        }
    }

    /**
     * Rolls back the current transaction.
     * <p>
     * This method should be called to discard changes made during the current
     * transaction in case of an error.
     * </p>
     */
    public void rollback() {
        try {
            if (konekcija != null && !konekcija.isClosed()) {
                konekcija.rollback();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbBroker.class.getName()).log(Level.SEVERE, "Error rolling back the transaction", ex);
        }
    }

    /**
     * Retrieves a list of domain objects from the database based on the
     * provided template object.
     * <p>
     * Executes a SELECT query using the template object’s query and converts
     * the result set into a list of domain objects.
     * </p>
     *
     * @param o the domain object template used to generate the SELECT query
     * @return a list of domain objects retrieved from the database, or
     * {@code null} if an error occurs
     */
    public synchronized List<OpstiDomenskiObjekat> getAllOpstiDomenskiObjekats(OpstiDomenskiObjekat o) {
        try {
            String upit = o.getSelectUpit();
            List<OpstiDomenskiObjekat> lista;
            try (Statement s = konekcija.createStatement()) {
                ResultSet rs = s.executeQuery(upit);
                lista = o.konvertujRSUListu(rs);
            }
            return lista;
        } catch (SQLException ex) {
            System.err.println("Error retrieving domain objects from table: " + o.getNazivTabele());
        }
        return null;
    }

    /**
     * Retrieves a single domain object from the database based on the provided
     * template object’s parameters.
     * <p>
     * Executes a SELECT query using the template object’s query by parameter
     * and returns the first result, or {@code null} if no matching object is
     * found.
     * </p>
     *
     * @param o the domain object template used to generate the SELECT query by
     * parameter
     * @return the domain object retrieved from the database, or {@code null} if
     * no object is found or an error occurs
     */
    public synchronized OpstiDomenskiObjekat getOpstiDomenskiObjekatPoParametru(OpstiDomenskiObjekat o) {
        try {
            String upit = o.getSelectUpitPoParametru();
            List<OpstiDomenskiObjekat> lista;
            try (Statement s = konekcija.createStatement()) {
                ResultSet rs = s.executeQuery(upit);
                lista = o.konvertujRSUListu(rs);
            }
            return lista.isEmpty() ? null : lista.get(0);
        } catch (SQLException ex) {
            System.err.println("Error retrieving domain object from table: " + o.getNazivTabele());
        }
        return null;
    }

    /**
     * Inserts a domain object into the database.
     * <p>
     * Executes an INSERT query using the provided domain object’s query and
     * parameters.
     * </p>
     *
     * @param o the domain object to be inserted into the database
     * @return {@code true} if the insertion is successful, {@code false}
     * otherwise
     */
    public synchronized boolean saveOpstiDomenskiObjekat(OpstiDomenskiObjekat o) {
        try {
            String upit = o.getInsertUpit();
            try (Statement s = konekcija.createStatement()) {
                s.executeUpdate(upit);
            }
            return true;
        } catch (SQLException ex) {
            System.err.println("Error saving new domain object to table: " + o.getNazivTabele() + " Error: " + ex.getMessage());
        }
        return false;
    }

    /**
     * Updates an existing domain object in the database.
     * <p>
     * Executes an UPDATE query using the provided domain object’s query and
     * parameters.
     * </p>
     *
     * @param o the domain object to be updated in the database
     * @return {@code true} if the update is successful, {@code false} otherwise
     */
    public synchronized boolean updateOpstiDomenskiObjekat(OpstiDomenskiObjekat o) {
        try {
            String upit = o.getUpdateUpit();
            try (Statement s = konekcija.createStatement()) {
                s.executeUpdate(upit);
            }
            return true;
        } catch (SQLException ex) {
            System.err.println("Error updating object in table: " + o.getNazivTabele() + " Error: " + ex.getMessage());
        }
        return false;
    }

    /**
     * Deletes a domain object from the database.
     * <p>
     * Executes a DELETE query using the provided domain object’s query and
     * parameters.
     * </p>
     *
     * @param o the domain object to be deleted from the database
     * @return {@code true} if the deletion is successful, {@code false}
     * otherwise
     */
    public synchronized boolean deleteOpstiDomenskiObjekat(OpstiDomenskiObjekat o) {
        try {
            String upit = o.getDeleteUpit();
            try (Statement s = konekcija.createStatement()) {
                s.executeUpdate(upit);
            }
            return true;
        } catch (SQLException ex) {
            System.err.println("Error deleting object from table: " + o.getNazivTabele() + " Error: " + ex.getMessage());
        }
        return false;
    }
}
