package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a work shift with start and end times. This class extends
 * {@link OpstiDomenskiObjekat} and provides methods for converting between
 * ResultSet and domain objects, and generating SQL queries for CRUD operations.
 *
 * @author dulait
 */
public class Smena extends OpstiDomenskiObjekat {

    private int id;
    private int pocetak;
    private int kraj;

    /**
     * Default constructor.
     */
    public Smena() {
    }

    /**
     * Constructs a Smena with the specified ID.
     *
     * @param smenaId the ID of the shift.
     */
    public Smena(int smenaId) {
        this.id = smenaId;
    }

    /**
     * Constructs a Smena with the specified ID, start time, and end time.
     *
     * @param smenaId the ID of the shift.
     * @param pocetak the start time of the shift.
     * @param kraj the end time of the shift.
     */
    public Smena(int smenaId, int pocetak, int kraj) {
        this.id = smenaId;
        this.pocetak = pocetak;
        this.kraj = kraj;
    }

    /**
     * Gets the ID of the shift.
     *
     * @return the ID of the shift.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the shift.
     *
     * @param id the new ID of the shift.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the start time of the shift.
     *
     * @return the start time of the shift.
     */
    public int getPocetak() {
        return pocetak;
    }

    /**
     * Sets the start time of the shift.
     *
     * @param pocetak the new start time of the shift.
     */
    public void setPocetak(int pocetak) {
        this.pocetak = pocetak;
    }

    /**
     * Gets the end time of the shift.
     *
     * @return the end time of the shift.
     */
    public int getKraj() {
        return kraj;
    }

    /**
     * Sets the end time of the shift.
     *
     * @param kraj the new end time of the shift.
     */
    public void setKraj(int kraj) {
        this.kraj = kraj;
    }

    /**
     * Converts the shift's start and end times to a string representation.
     *
     * @return the string representation of the shift's start and end times.
     */
    @Override
    public String toString() {
        return "" + pocetak + ":00h - " + kraj + ":00h";
    }

    /**
     * Gets the name of the table associated with this domain object.
     *
     * @return the table name as a String.
     */
    @Override
    public String getNazivTabele() {
        return "smena";
    }

    /**
     * Gets the parameters for this domain object in the format required for an
     * SQL statement.
     *
     * @return the parameters as a String.
     */
    @Override
    public String getParametre() {
        return String.format("%s, %s, %s", id, pocetak, kraj);
    }

    /**
     * Gets the names of the parameters for this domain object.
     *
     * @return the parameter names as a String.
     */
    @Override
    public String getNaziveParametara() {
        return "id, pocetak, kraj";
    }

    /**
     * Gets the name of the primary key column for this domain object.
     *
     * @return the primary key column name as a String.
     */
    @Override
    public String getNazivPrimarnogKljuca() {
        return "id";
    }

    /**
     * Gets the value of the primary key for this domain object.
     *
     * @return the primary key value as an Integer.
     */
    @Override
    public Integer getVrednostPrimarnogKljuca() {
        return id;
    }

    /**
     * Gets the composite primary key for this domain object, if applicable.
     *
     * @return the composite primary key as a String, or null if not applicable.
     */
    @Override
    public String getSlozeniPrimarniKljuc() {
        return null;
    }

    /**
     * Converts a {@link ResultSet} to a list of domain objects.
     *
     * @param rs the {@link ResultSet} to convert.
     * @return a {@link List} of domain objects.
     */
    @Override
    public ArrayList<OpstiDomenskiObjekat> konvertujRSUListu(ResultSet rs) {
        ArrayList<OpstiDomenskiObjekat> smene = new ArrayList<>();
        try {
            while (rs.next()) {
                int rsId = rs.getInt("id");
                int rsPocetak = rs.getInt("pocetak");
                int rsKraj = rs.getInt("kraj");

                smene.add(new Smena(rsId, rsPocetak, rsKraj));
            }
        } catch (SQLException e) {
            System.out.println("Greska u Smena::konvertujRSUListu:\n" + e.getMessage());
        }
        return smene;
    }

    /**
     * Gets the SQL SELECT query for retrieving all records of this domain
     * object.
     *
     * @return the SQL SELECT query as a String.
     */
    @Override
    public String getSelectUpit() {
        return "SELECT * FROM " + getNazivTabele();
    }

    /**
     * Gets the SQL SELECT query for retrieving records of this domain object
     * based on specific parameters.
     *
     * @return the SQL SELECT query with parameters as a String.
     */
    @Override
    public String getSelectUpitPoParametru() {
        return "SELECT * FROM " + getNazivTabele() + " WHERE id = " + getId() + " OR (pocetak = " + getPocetak() + " AND kraj = " + getKraj() + ")";
    }

    /**
     * Gets the SQL INSERT query for inserting a record of this domain object.
     *
     * @return the SQL INSERT query as a String.
     */
    @Override
    public String getInsertUpit() {
        return "INSERT INTO " + getNazivTabele() + "(" + getNaziveParametara() + ")" + " VALUES (" + getParametre() + ")";
    }

    /**
     * Gets the SQL UPDATE query for updating a record of this domain object.
     *
     * @return the SQL UPDATE query as a String.
     */
    @Override
    public String getUpdateUpit() {
        return "UPDATE " + getNazivTabele() + " SET " + getUpdateParametre() + " WHERE " + getNazivPrimarnogKljuca() + " = " + getVrednostPrimarnogKljuca();
    }

    /**
     * Gets the parameters for updating a record of this domain object.
     *
     * @return the update parameters as a String.
     */
    @Override
    public String getUpdateParametre() {
        return String.format("pocetak = %s, kraj = %s", pocetak, kraj);
    }

    /**
     * Gets the SQL DELETE query for deleting a record of this domain object.
     *
     * @return the SQL DELETE query as a String.
     */
    @Override
    public String getDeleteUpit() {
        return "DELETE FROM " + getNazivTabele() + " WHERE " + getNazivPrimarnogKljuca() + " = " + getVrednostPrimarnogKljuca();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Smena smena = (Smena) o;
        return id == smena.id
                && pocetak == smena.pocetak
                && kraj == smena.kraj;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pocetak, kraj);
    }

}
