package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents an engagement of a lifeguard during a shift within a schedule.
 * This class provides methods for database interaction and entity manipulation.
 * It extends the {@link OpstiDomenskiObjekat} class.
 *
 * @author dulait
 */
public class Angazovanje extends OpstiDomenskiObjekat {

    private Spasilac spasilac;
    private Smena smena;
    private Raspored raspored;

    /**
     * Default constructor.
     */
    public Angazovanje() {
    }

    /**
     * Constructs an Angazovanje object with specified lifeguard, shift, and
     * schedule.
     *
     * @param spasilac the lifeguard involved in the engagement.
     * @param smena the shift during which the engagement occurs.
     * @param raspored the schedule within which the engagement is defined.
     */
    public Angazovanje(Spasilac spasilac, Smena smena, Raspored raspored) {
        this.spasilac = spasilac;
        this.smena = smena;
        this.raspored = raspored;
    }

    /**
     * Gets the lifeguard involved in the engagement.
     *
     * @return the lifeguard.
     */
    public Spasilac getSpasilac() {
        return spasilac;
    }

    /**
     * Sets the lifeguard involved in the engagement.
     *
     * @param spasilac the lifeguard to set.
     */
    public void setSpasilac(Spasilac spasilac) {
        this.spasilac = spasilac;
    }

    /**
     * Gets the shift during which the engagement occurs.
     *
     * @return the shift.
     */
    public Smena getSmena() {
        return smena;
    }

    /**
     * Sets the shift during which the engagement occurs.
     *
     * @param smena the shift to set.
     */
    public void setSmena(Smena smena) {
        this.smena = smena;
    }

    /**
     * Gets the schedule within which the engagement is defined.
     *
     * @return the schedule.
     */
    public Raspored getRaspored() {
        return raspored;
    }

    /**
     * Sets the schedule within which the engagement is defined.
     *
     * @param raspored the schedule to set.
     */
    public void setRaspored(Raspored raspored) {
        this.raspored = raspored;
    }

    /**
     * Returns a string representation of the Angazovanje object.
     *
     * @return a string representation of the Angazovanje object.
     */
    @Override
    public String toString() {
        return spasilac.toString() + ", " + smena.toString() + ", " + raspored.toString();
    }

    /**
     * Gets the name of the table associated with this entity.
     *
     * @return the table name.
     */
    @Override
    public String getNazivTabele() {
        return "angazovanje";
    }

    /**
     * Gets the parameters for this entity in the format required for an SQL
     * statement.
     *
     * @return the parameters string.
     */
    @Override
    public String getParametre() {
        return String.format("%d, %d, %d", spasilac.getId(), smena.getId(), raspored.getId());
    }

    /**
     * Gets the names of the parameters for this entity.
     *
     * @return the parameter names string.
     */
    @Override
    public String getNaziveParametara() {
        return "spasilacId, smenaId, rasporedId";
    }

    /**
     * Gets the name of the primary key for this entity.
     *
     * @return the primary key name, which is null since this entity has a
     * composite key.
     */
    @Override
    public String getNazivPrimarnogKljuca() {
        return null;
    }

    /**
     * Gets the value of the primary key for this entity.
     *
     * @return the primary key value, which is null since this entity has a
     * composite key.
     */
    @Override
    public Integer getVrednostPrimarnogKljuca() {
        return null;
    }

    /**
     * Gets the composite primary key for this entity.
     *
     * @return the composite primary key string.
     */
    @Override
    public String getSlozeniPrimarniKljuc() {
        return "spasilacId = " + spasilac.getId() + " AND smenaId = " + smena.getId() + " AND rasporedId = " + raspored.getId();
    }

    /**
     * Converts a ResultSet to a list of Angazovanje objects.
     *
     * @param rs the ResultSet to convert.
     * @return a list of Angazovanje objects.
     */
    @Override
    public ArrayList<OpstiDomenskiObjekat> konvertujRSUListu(ResultSet rs) {
        ArrayList<OpstiDomenskiObjekat> angazovanja = new ArrayList<>();
        try {
            while (rs.next()) {
                Spasilac rsSpasilac = new Spasilac(
                        rs.getInt("spa.id"),
                        rs.getString("spa.ime"),
                        rs.getString("spa.prezime"),
                        rs.getString("spa.jmbg")
                );
                Smena rsSmena = new Smena(
                        rs.getInt("sme.id"),
                        rs.getInt("sme.pocetak"),
                        rs.getInt("sme.kraj")
                );
                Raspored rsRaspored = new Raspored(
                        rs.getInt("ras.id"),
                        rs.getDate("ras.datum").toLocalDate()
                );

                angazovanja.add(new Angazovanje(rsSpasilac, rsSmena, rsRaspored));
            }
        } catch (SQLException e) {
            System.out.println("Greska u Angazovanje::konvertujRSUListu\n" + e.getMessage());
        }
        return angazovanja;
    }

    /**
     * Gets the SQL SELECT query for retrieving all Angazovanje records.
     *
     * @return the SQL SELECT query string.
     */
    @Override
    public String getSelectUpit() {
        return "SELECT ang.*, spa.*, sme.*, ras.* "
                + "FROM angazovanje ang "
                + "JOIN spasilac spa ON ang.spasilacId = spa.id "
                + "JOIN smena sme ON ang.smenaId = sme.id "
                + "JOIN raspored ras ON ang.rasporedId = ras.id";
    }

    /**
     * Gets the SQL SELECT query for retrieving Angazovanje records based on
     * specific parameters.
     *
     * @return the SQL SELECT query string with parameters.
     */
    @Override
    public String getSelectUpitPoParametru() {
        return "SELECT ang.*, spa.*, sme.*, ras.* "
                + "FROM angazovanje ang "
                + "JOIN spasilac spa ON ang.spasilacId = spa.id "
                + "JOIN smena sme ON ang.smenaId = sme.id "
                + "JOIN raspored ras ON ang.rasporedId = ras.id "
                + "WHERE " + getSlozeniPrimarniKljuc();
    }

    /**
     * Gets the SQL INSERT query for inserting an Angazovanje record.
     *
     * @return the SQL INSERT query string.
     */
    @Override
    public String getInsertUpit() {
        return "INSERT INTO " + getNazivTabele() + "(" + getNaziveParametara() + ")" + " VALUES (" + getParametre() + ")";
    }

    /**
     * Gets the SQL UPDATE query for updating an Angazovanje record.
     *
     * @return the SQL UPDATE query string.
     */
    @Override
    public String getUpdateUpit() {
        return "UPDATE " + getNazivTabele() + " SET " + getUpdateParametre() + " WHERE " + getSlozeniPrimarniKljuc();
    }

    /**
     * Gets the parameters for updating an Angazovanje record.
     *
     * @return the update parameters string.
     */
    @Override
    public String getUpdateParametre() {
        return String.format("spasilacId = %d, smenaId = %d, rasporedId = %d",
                getSpasilac().getId(), getSmena().getId(), getRaspored().getId());
    }

    /**
     * Gets the SQL DELETE query for deleting an Angazovanje record.
     *
     * @return the SQL DELETE query string.
     */
    @Override
    public String getDeleteUpit() {
        return "DELETE FROM " + getNazivTabele() + " WHERE " + getSlozeniPrimarniKljuc();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Angazovanje that = (Angazovanje) o;
        return Objects.equals(spasilac, that.spasilac)
                && Objects.equals(smena, that.smena)
                && Objects.equals(raspored, that.raspored);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spasilac, smena, raspored);
    }
}
