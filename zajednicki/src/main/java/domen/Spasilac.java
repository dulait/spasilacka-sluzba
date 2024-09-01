package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a lifeguard with personal information including ID, name, surname,
 * and JMBG. This class extends {@link OpstiDomenskiObjekat} and provides
 * methods for converting between ResultSet and domain objects, and generating
 * SQL queries for CRUD operations.
 *
 * @author dulait
 */
public class Spasilac extends OpstiDomenskiObjekat {

    private int id;
    private String ime;
    private String prezime;
    private String jmbg;

    /**
     * Default constructor.
     */
    public Spasilac() {
    }

    /**
     * Constructs a Spasilac with the specified ID.
     *
     * @param id the ID of the lifeguard.
     */
    public Spasilac(int id) {
        this.id = id;
    }

    /**
     * Sets the ID of the lifeguard.
     *
     * @param id the new ID of the lifeguard.
     * @throws IllegalArgumentException if id is less than or equal to zero.
     */
    public final void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id must be greater than zero.");
        }
        this.id = id;
    }

    /**
     * Sets the name of the lifeguard.
     *
     * @param ime the new name of the lifeguard.
     * @throws IllegalArgumentException if ime is null or empty.
     */
    public final void setIme(String ime) {
        if (ime == null || ime.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.ime = ime;
    }

    /**
     * Sets the surname of the lifeguard.
     *
     * @param prezime the new surname of the lifeguard.
     * @throws IllegalArgumentException if prezime is null or empty.
     */
    public final void setPrezime(String prezime) {
        if (prezime == null || prezime.trim().isEmpty()) {
            throw new IllegalArgumentException("Surname cannot be null or empty.");
        }
        this.prezime = prezime;
    }

    /**
     * Sets the JMBG of the lifeguard.
     *
     * @param jmbg the new JMBG of the lifeguard.
     * @throws IllegalArgumentException if jmbg is null or does not match the
     * required format.
     */
    public final void setJmbg(String jmbg) {
        if (jmbg == null || !jmbg.matches("\\d{13}")) { // Assuming JMBG is a 13-digit number
            throw new IllegalArgumentException("JMBG must be a 13-digit number.");
        }
        this.jmbg = jmbg;
    }

    /**
     * Constructs a Spasilac with the specified ID, name, surname, and JMBG.
     *
     * @param spasilacId the ID of the lifeguard.
     * @param ime the name of the lifeguard.
     * @param prezime the surname of the lifeguard.
     * @param jmbg the JMBG of the lifeguard.
     * @throws IllegalArgumentException if ime or prezime are null or empty, or
     * if jmbg does not match the required format.
     */
    public Spasilac(int spasilacId, String ime, String prezime, String jmbg) {
        setId(spasilacId); // Using the validated setter
        setIme(ime); // Using the validated setter
        setPrezime(prezime); // Using the validated setter
        setJmbg(jmbg); // Using the validated setter
    }

    /**
     * Returns a string representation of the lifeguard.
     *
     * @return the string representation of the lifeguard.
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    /**
     * Gets the ID of the lifeguard.
     *
     * @return the ID of the lifeguard.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of the lifeguard.
     *
     * @return the name of the lifeguard.
     */
    public String getIme() {
        return ime;
    }

    /**
     * Gets the surname of the lifeguard.
     *
     * @return the surname of the lifeguard.
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Gets the JMBG of the lifeguard.
     *
     * @return the JMBG of the lifeguard.
     */
    public String getJmbg() {
        return jmbg;
    }

    /**
     * Gets the name of the table associated with this domain object.
     *
     * @return the table name as a String.
     */
    @Override
    public String getNazivTabele() {
        return "spasilac";
    }

    /**
     * Gets the parameters for this domain object in the format required for an
     * SQL statement.
     *
     * @return the parameters as a String.
     */
    @Override
    public String getParametre() {
        return String.format("%d, '%s', '%s', '%s'", id, ime, prezime, jmbg);
    }

    /**
     * Gets the names of the parameters for this domain object.
     *
     * @return the parameter names as a String.
     */
    @Override
    public String getNaziveParametara() {
        return "id, ime, prezime, jmbg";
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
    public List<OpstiDomenskiObjekat> konvertujRSUListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> spasioci = new ArrayList<>();
        try {
            while (rs.next()) {
                int rsId = rs.getInt("id");
                String rsIme = rs.getString("ime");
                String rsPrezime = rs.getString("prezime");
                String rsJmbg = rs.getString("jmbg");

                spasioci.add(new Spasilac(rsId, rsIme, rsPrezime, rsJmbg));
            }
        } catch (SQLException e) {
            System.out.println("Greska u Spasilac::konvertujRSUListu\n" + e.getMessage());
        }
        return spasioci;
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
        return "SELECT * FROM " + getNazivTabele() + " WHERE id = " + getId() + " OR jmbg = '" + getJmbg() + "'";
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
        return String.format("ime = '%s', prezime = '%s', jmbg = '%s'", ime, prezime, jmbg);
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
        Spasilac spasilac = (Spasilac) o;
        return id == spasilac.id
                && Objects.equals(ime, spasilac.ime)
                && Objects.equals(prezime, spasilac.prezime)
                && Objects.equals(jmbg, spasilac.jmbg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ime, prezime, jmbg);
    }

}
