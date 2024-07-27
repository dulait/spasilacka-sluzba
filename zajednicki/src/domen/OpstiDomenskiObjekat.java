package domen;

import java.io.Serializable;
import java.sql.*;
import java.util.List;

/**
 * An abstract class representing a general domain object. This class provides a
 * contract for all domain objects to interact with the database. Classes
 * extending this abstract class must implement the abstract methods for
 * specific database operations such as select, insert, update, and delete.
 *
 * This class implements {@link Serializable} to allow objects to be serialized.
 *
 * @author dulait
 */
public abstract class OpstiDomenskiObjekat implements Serializable {

    /**
     * Gets the name of the table associated with this domain object.
     *
     * @return the table name as a String.
     */
    public abstract String getNazivTabele();

    /**
     * Gets the parameters for this domain object in the format required for an
     * SQL statement.
     *
     * @return the parameters as a String.
     */
    public abstract String getParametre();

    /**
     * Gets the names of the parameters for this domain object.
     *
     * @return the parameter names as a String.
     */
    public abstract String getNaziveParametara();

    /**
     * Gets the name of the primary key column for this domain object.
     *
     * @return the primary key column name as a String.
     */
    public abstract String getNazivPrimarnogKljuca();

    /**
     * Gets the value of the primary key for this domain object.
     *
     * @return the primary key value as an Integer.
     */
    public abstract Integer getVrednostPrimarnogKljuca();

    /**
     * Gets the composite primary key for this domain object, if applicable.
     *
     * @return the composite primary key as a String, or null if not applicable.
     */
    public abstract String getSlozeniPrimarniKljuc();

    /**
     * Converts a {@link ResultSet} to a list of domain objects.
     *
     * @param rs the {@link ResultSet} to convert.
     * @return a {@link List} of domain objects.
     * @throws SQLException if there is an error accessing the
     * {@link ResultSet}.
     */
    public abstract List<OpstiDomenskiObjekat> konvertujRSUListu(ResultSet rs);

    /**
     * Gets the SQL SELECT query for retrieving all records of this domain
     * object.
     *
     * @return the SQL SELECT query as a String.
     */
    public abstract String getSelectUpit();

    /**
     * Gets the SQL SELECT query for retrieving records of this domain object
     * based on specific parameters.
     *
     * @return the SQL SELECT query with parameters as a String.
     */
    public abstract String getSelectUpitPoParametru();

    /**
     * Gets the SQL INSERT query for inserting a record of this domain object.
     *
     * @return the SQL INSERT query as a String.
     */
    public abstract String getInsertUpit();

    /**
     * Gets the SQL UPDATE query for updating a record of this domain object.
     *
     * @return the SQL UPDATE query as a String.
     */
    public abstract String getUpdateUpit();

    /**
     * Gets the parameters for updating a record of this domain object.
     *
     * @return the update parameters as a String.
     */
    public abstract String getUpdateParametre();

    /**
     * Gets the SQL DELETE query for deleting a record of this domain object.
     *
     * @return the SQL DELETE query as a String.
     */
    public abstract String getDeleteUpit();

}
