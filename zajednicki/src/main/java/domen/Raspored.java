package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Represents a schedule with a list of engagements. This class extends
 * {@link OpstiDomenskiObjekat} and implements {@link Comparable} for comparing
 * schedules by date. It provides methods for converting between ResultSet and
 * domain objects and generating SQL queries for CRUD operations.
 *
 * @author dulait
 */
public class Raspored extends OpstiDomenskiObjekat implements Comparable<Raspored> {

    private int id;
    private LocalDate datum;
    private List<Angazovanje> angazovanja;

    /**
     * Default constructor.
     */
    public Raspored() {
    }

    /**
     * Constructs a Raspored with the specified ID.
     *
     * @param id the ID of the schedule.
     */
    public Raspored(int id) {
        this.id = id;
    }

    /**
     * Constructs a Raspored with the specified ID and date.
     *
     * @param id the ID of the schedule.
     * @param datum the date of the schedule.
     */
    public Raspored(int id, LocalDate datum) {
        this.id = id;
        this.datum = datum;
        this.angazovanja = new ArrayList<>();
    }

    /**
     * Gets the date of the schedule.
     *
     * @return the date of the schedule.
     */
    public LocalDate getDatum() {
        return datum;
    }

    /**
     * Sets the date of the schedule.
     *
     * @param datum the new date of the schedule.
     */
    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    /**
     * Gets the ID of the schedule.
     *
     * @return the ID of the schedule.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the schedule.
     *
     * @param id the new ID of the schedule.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the list of engagements in the schedule.
     *
     * @return the list of engagements.
     */
    public List<Angazovanje> getAngazovanja() {
        return angazovanja;
    }

    /**
     * Sets the list of engagements in the schedule.
     *
     * @param angazovanja the new list of engagements.
     */
    public void setAngazovanja(List<Angazovanje> angazovanja) {
        this.angazovanja = angazovanja;
    }

    /**
     * Converts the schedule's date to a string representation.
     *
     * @return the string representation of the schedule's date.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy", new Locale.Builder().setLanguage("sr").setScript("Latn").build());
        return formatter.format(datum);
    }

    /**
     * Compares this schedule to another schedule by date.
     *
     * @param o the other schedule to compare to.
     * @return a negative integer, zero, or a positive integer as this schedule
     * is earlier than, equal to, or later than the specified schedule.
     */
    @Override
    public int compareTo(Raspored o) {
        return this.getDatum().compareTo(o.getDatum());
    }

    /**
     * Gets the name of the table associated with this domain object.
     *
     * @return the table name as a String.
     */
    @Override
    public String getNazivTabele() {
        return "raspored";
    }

    /**
     * Gets the parameters for this domain object in the format required for an
     * SQL statement.
     *
     * @return the parameters as a String.
     */
    @Override
    public String getParametre() {
        return String.format("%d, '%s'", id, datum);
    }

    /**
     * Gets the names of the parameters for this domain object.
     *
     * @return the parameter names as a String.
     */
    @Override
    public String getNaziveParametara() {
        return "id, datum";
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
        ArrayList<OpstiDomenskiObjekat> rasporedi = new ArrayList<>();
        try {
            while (rs.next()) {
                int rsId = rs.getInt("id");
                LocalDate rsDatum = rs.getDate("datum").toLocalDate();
                rasporedi.add(new Raspored(rsId, rsDatum));
            }
        } catch (SQLException e) {
            System.out.println("Greska u Raspored::konvertujRSUListu:\n" + e.getMessage());
        }
        return rasporedi;
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
        return "SELECT * FROM " + getNazivTabele() + " WHERE id = " + getId() + " OR datum = '" + getDatum() + "'";
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
        return String.format("datum = '%s'", getDatum());
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
        Raspored raspored = (Raspored) o;
        return id == raspored.id
                && Objects.equals(datum, raspored.datum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, datum);
    }

}
