package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a coordinator in the system. This class provides methods for
 * database interaction and entity manipulation. It extends the
 * {@link OpstiDomenskiObjekat} class.
 *
 * @author dulait
 */
public class Koordinator extends OpstiDomenskiObjekat {

    private int id;
    private String korisnickoIme;
    private String lozinka;
    private String ime;
    private String prezime;

    /**
     * Default constructor.
     */
    public Koordinator() {
    }

    /**
     * Constructs a Koordinator object with the specified id.
     *
     * @param id the id of the coordinator.
     */
    public Koordinator(int id) {
        this.id = id;
    }

    /**
     * Sets the username of the coordinator.
     *
     * @param korisnickoIme the username to set.
     * @throws IllegalArgumentException if korisnickoIme is null or empty.
     */
    public final void setKorisnickoIme(String korisnickoIme) {
        if (korisnickoIme == null || korisnickoIme.isEmpty()) {
            throw new IllegalArgumentException("Korisnicko ime cannot be null or empty.");
        }
        this.korisnickoIme = korisnickoIme;
    }

    /**
     * Sets the password of the coordinator.
     *
     * @param lozinka the password to set.
     * @throws IllegalArgumentException if lozinka is null or empty.
     */
    public final void setLozinka(String lozinka) {
        if (lozinka == null || lozinka.isEmpty()) {
            throw new IllegalArgumentException("Lozinka cannot be null or empty.");
        }
        this.lozinka = lozinka;
    }

    /**
     * Sets the first name of the coordinator.
     *
     * @param ime the first name to set.
     * @throws IllegalArgumentException if ime is null or empty.
     */
    public final void setIme(String ime) {
        if (ime == null || ime.isEmpty()) {
            throw new IllegalArgumentException("Ime cannot be null or empty.");
        }
        this.ime = ime;
    }

    /**
     * Sets the last name of the coordinator.
     *
     * @param prezime the last name to set.
     * @throws IllegalArgumentException if prezime is null or empty.
     */
    public final void setPrezime(String prezime) {
        if (prezime == null || prezime.isEmpty()) {
            throw new IllegalArgumentException("Prezime cannot be null or empty.");
        }
        this.prezime = prezime;
    }

    /**
     * Constructs a Koordinator object with the specified attributes.
     *
     * @param id the id of the coordinator.
     * @param korisnickoIme the username of the coordinator.
     * @param lozinka the password of the coordinator.
     * @param ime the first name of the coordinator.
     * @param prezime the last name of the coordinator.
     * @throws IllegalArgumentException if korisnickoIme, lozinka, ime, or
     * prezime is null or empty.
     */
    public Koordinator(int id, String korisnickoIme, String lozinka, String ime, String prezime) {
        setId(id);
        setKorisnickoIme(korisnickoIme);
        setLozinka(lozinka);
        setIme(ime);
        setPrezime(prezime);
    }

    /**
     * Gets the last name of the coordinator.
     *
     * @return the last name.
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Gets the id of the coordinator.
     *
     * @return the id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the coordinator.
     *
     * @param id the id to set.
     * @throws IllegalArgumentException if id is less than or equal to zero.
     */
    public final void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id must be greater than zero.");
        }
        this.id = id;
    }

    /**
     * Gets the username of the coordinator.
     *
     * @return the username.
     */
    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    /**
     * Gets the password of the coordinator.
     *
     * @return the password.
     */
    public String getLozinka() {
        return lozinka;
    }

    /**
     * Gets the first name of the coordinator.
     *
     * @return the first name.
     */
    public String getIme() {
        return ime;
    }

    /**
     * Returns a string representation of the coordinator.
     *
     * @return a string representing the coordinator's first and last name.
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    /**
     * Gets the name of the table associated with this entity.
     *
     * @return the table name.
     */
    @Override
    public String getNazivTabele() {
        return "koordinator";
    }

    /**
     * Gets the parameters for this entity in the format required for an SQL
     * statement.
     *
     * @return the parameters string.
     */
    @Override
    public String getParametre() {
        return String.format("%d, '%s', '%s', '%s', '%s'", id, korisnickoIme, lozinka, ime, prezime);
    }

    /**
     * Gets the names of the parameters for this entity.
     *
     * @return the parameter names string.
     */
    @Override
    public String getNaziveParametara() {
        return "id, korisnickoIme, lozinka, ime, prezime";
    }

    /**
     * Gets the name of the primary key for this entity.
     *
     * @return the primary key name.
     */
    @Override
    public String getNazivPrimarnogKljuca() {
        return "id";
    }

    /**
     * Gets the value of the primary key for this entity.
     *
     * @return the primary key value.
     */
    @Override
    public Integer getVrednostPrimarnogKljuca() {
        return id;
    }

    /**
     * Gets the composite primary key for this entity.
     *
     * @return the composite primary key string, which is null for this entity.
     */
    @Override
    public String getSlozeniPrimarniKljuc() {
        return null;
    }

    /**
     * Converts a ResultSet to a list of Koordinator objects.
     *
     * @param rs the ResultSet to convert.
     * @return a list of Koordinator objects.
     */
    @Override
    public ArrayList<OpstiDomenskiObjekat> konvertujRSUListu(ResultSet rs) {
        ArrayList<OpstiDomenskiObjekat> koordinatori = new ArrayList<>();
        try {
            while (rs.next()) {
                int rsId = rs.getInt("id");
                String rsKorisnickoIme = rs.getString("korisnickoIme");
                String rsLozinka = rs.getString("lozinka");
                String rsIme = rs.getString("ime");
                String rePrezime = rs.getString("prezime");

                koordinatori.add(new Koordinator(rsId, rsKorisnickoIme, rsLozinka, rsIme, rePrezime));
            }
        } catch (SQLException e) {
            System.out.println("Greska u Koordinator::konvertujRSUListu\n" + e.getMessage());
        }
        return koordinatori;
    }

    /**
     * Gets the SQL SELECT query for retrieving all Koordinator records.
     *
     * @return the SQL SELECT query string.
     */
    @Override
    public String getSelectUpit() {
        return "SELECT * FROM " + getNazivTabele();
    }

    /**
     * Gets the SQL SELECT query for retrieving Koordinator records based on
     * specific parameters.
     *
     * @return the SQL SELECT query string with parameters.
     */
    @Override
    public String getSelectUpitPoParametru() {
        return "SELECT * FROM " + getNazivTabele() + " WHERE id = " + getVrednostPrimarnogKljuca();
    }

    /**
     * Gets the SQL INSERT query for inserting a Koordinator record.
     *
     * @return the SQL INSERT query string.
     */
    @Override
    public String getInsertUpit() {
        return null;
    }

    /**
     * Gets the parameters for updating a Koordinator record.
     *
     * @return the update parameters string.
     */
    @Override
    public String getUpdateParametre() {
        return null;
    }

    /**
     * Gets the SQL DELETE query for deleting a Koordinator record.
     *
     * @return the SQL DELETE query string.
     */
    @Override
    public String getDeleteUpit() {
        return null;
    }

    /**
     * Gets the SQL UPDATE query for updating a Koordinator record.
     *
     * @return the SQL UPDATE query string.
     */
    @Override
    public String getUpdateUpit() {
        return String.format("id = %d, korisnickoIme = '%s', lozinka = '%s', ime = '%s', prezime = '%s'",
                id, korisnickoIme, lozinka, ime, prezime);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Koordinator that = (Koordinator) obj;
        return id == that.id
                && Objects.equals(korisnickoIme, that.korisnickoIme)
                && Objects.equals(lozinka, that.lozinka)
                && Objects.equals(ime, that.ime)
                && Objects.equals(prezime, that.prezime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, korisnickoIme, lozinka, ime, prezime);
    }

}
