package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a report associated with an engagement of a lifeguard during a
 * shift within a schedule. This class provides methods for database interaction
 * and entity manipulation. It extends the {@link OpstiDomenskiObjekat} class.
 *
 * @author dulait
 */
public class Izvestaj extends OpstiDomenskiObjekat {

    private Angazovanje angazovanje;
    private String opis;

    /**
     * Default constructor.
     */
    public Izvestaj() {
    }

    /**
     * Constructs an Izvestaj object with the specified engagement and
     * description.
     *
     * @param angazovanje the engagement associated with the report.
     * @param opis the description of the report.
     * @throws IllegalArgumentException if angazovanje is null or if opis is
     * null or empty.
     */
    public Izvestaj(Angazovanje angazovanje, String opis) {
        setAngazovanje(angazovanje);
        setOpis(opis);
    }

    /**
     * Gets the engagement associated with the report.
     *
     * @return the engagement.
     */
    public Angazovanje getAngazovanje() {
        return angazovanje;
    }

    /**
     * Sets the engagement associated with the report.
     *
     * @param angazovanje the engagement to set.
     * @throws IllegalArgumentException if angazovanje is null.
     */
    public final void setAngazovanje(Angazovanje angazovanje) {
        if (angazovanje == null) {
            throw new IllegalArgumentException("Angazovanje cannot be null.");
        }
        this.angazovanje = angazovanje;
    }

    /**
     * Gets the description of the report.
     *
     * @return the description.
     */
    public String getOpis() {
        return opis;
    }

    /**
     * Sets the description of the report.
     *
     * @param opis the description to set.
     * @throws IllegalArgumentException if opis is null or empty.
     */
    public final void setOpis(String opis) {
        if (opis == null || opis.isEmpty()) {
            throw new IllegalArgumentException("Opis cannot be null or empty.");
        }
        this.opis = opis;
    }

    /**
     * Gets the name of the table associated with this entity.
     *
     * @return the table name.
     */
    @Override
    public String getNazivTabele() {
        return "izvestaj";
    }

    /**
     * Gets the parameters for this entity in the format required for an SQL
     * statement.
     *
     * @return the parameters string.
     */
    @Override
    public String getParametre() {
        return String.format("%d, %d, %d, '%s'",
                angazovanje.getSpasilac().getId(), angazovanje.getSmena().getId(), angazovanje.getRaspored().getId(), opis);
    }

    /**
     * Gets the names of the parameters for this entity.
     *
     * @return the parameter names string.
     */
    @Override
    public String getNaziveParametara() {
        return "spasilacId, smenaId, rasporedId, opis";
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
        return "spasilacId = " + angazovanje.getSpasilac().getId() + " AND smenaId = " + angazovanje.getSmena().getId()
                + " AND rasporedId = " + angazovanje.getRaspored().getId();
    }

    /**
     * Converts a ResultSet to a list of Izvestaj objects.
     *
     * @param rs the ResultSet to convert.
     * @return a list of Izvestaj objects.
     */
    @Override
    public ArrayList<OpstiDomenskiObjekat> konvertujRSUListu(ResultSet rs) {
        ArrayList<OpstiDomenskiObjekat> izvestaji = new ArrayList<>();
        try {
            while (rs.next()) {
                String rsOpis = rs.getString("izv.opis");

                int spasilacTableId = rs.getInt("spa.id");
                String spasilacIme = rs.getString("spa.ime");
                String spasilacPrezime = rs.getString("spa.prezime");
                String spasilacJmbg = rs.getString("spa.jmbg");
                Spasilac rsSpasilac = new Spasilac(spasilacTableId, spasilacIme, spasilacPrezime, spasilacJmbg);

                int smenaTableId = rs.getInt("sme.id");
                int smenaPocetak = rs.getInt("sme.pocetak");
                int smenaKraj = rs.getInt("sme.kraj");
                Smena rsSmena = new Smena(smenaTableId, smenaPocetak, smenaKraj);

                int rasporedTableId = rs.getInt("ras.id");
                LocalDate rasporedDatum = rs.getDate("ras.datum").toLocalDate();
                Raspored rsRaspored = new Raspored(rasporedTableId, rasporedDatum);

                Angazovanje rsAngazovanje = new Angazovanje(rsSpasilac, rsSmena, rsRaspored);

                rsAngazovanje.setSpasilac(rsSpasilac);
                rsAngazovanje.setSmena(rsSmena);
                rsAngazovanje.setRaspored(rsRaspored);

                Izvestaj izvestaj = new Izvestaj(rsAngazovanje, rsOpis);
                izvestaji.add(izvestaj);
            }
        } catch (SQLException e) {
            System.out.println("Greska u Izvestaj::konvertujRSUListu\n" + e.getMessage());
        }
        return izvestaji;
    }

    /**
     * Gets the SQL SELECT query for retrieving all Izvestaj records.
     *
     * @return the SQL SELECT query string.
     */
    @Override
    public String getSelectUpit() {
        return "SELECT izv.*, ang.*, spa.*, sme.*, ras.* "
                + "FROM izvestaj izv "
                + "JOIN angazovanje ang ON izv.spasilacId = ang.spasilacId AND izv.smenaId = ang.smenaId AND izv.rasporedId = ang.rasporedId "
                + "JOIN spasilac spa ON ang.spasilacId = spa.id "
                + "JOIN smena sme ON ang.smenaId = sme.id "
                + "JOIN raspored ras ON ang.rasporedId = ras.id";
    }

    /**
     * Gets the SQL SELECT query for retrieving Izvestaj records based on
     * specific parameters.
     *
     * @return the SQL SELECT query string with parameters.
     */
    @Override
    public String getSelectUpitPoParametru() {
        return "SELECT izv.*, ang.*, spa.*, sme.*, ras.* "
                + "FROM izvestaj izv "
                + "JOIN angazovanje ang ON izv.spasilacId = ang.spasilacId AND izv.smenaId = ang.smenaId AND izv.rasporedId = ang.rasporedId "
                + "JOIN spasilac spa ON ang.spasilacId = spa.id "
                + "JOIN smena sme ON ang.smenaId = sme.id "
                + "JOIN raspored ras ON ang.rasporedId = ras.id "
                + "WHERE " + getSlozeniPrimarniKljuc();
    }

    /**
     * Gets the SQL INSERT query for inserting an Izvestaj record.
     *
     * @return the SQL INSERT query string.
     */
    @Override
    public String getInsertUpit() {
        return "INSERT INTO " + getNazivTabele() + "(" + getNaziveParametara() + ")" + " VALUES (" + getParametre() + ")";
    }

    /**
     * Gets the SQL UPDATE query for updating an Izvestaj record.
     *
     * @return the SQL UPDATE query string.
     */
    @Override
    public String getUpdateUpit() {
        return "UPDATE " + getNazivTabele() + " SET " + getUpdateParametre() + " WHERE " + getSlozeniPrimarniKljuc();
    }

    /**
     * Gets the parameters for updating an Izvestaj record.
     *
     * @return the update parameters string.
     */
    @Override
    public String getUpdateParametre() {
        return String.format("spasilacId = %d, smenaId = %d, rasporedId = %d, opis = '%s'",
                angazovanje.getSpasilac().getId(), angazovanje.getSmena().getId(), angazovanje.getRaspored().getId(), opis);
    }

    /**
     * Gets the SQL DELETE query for deleting an Izvestaj record.
     *
     * @return the SQL DELETE query string.
     */
    @Override
    public String getDeleteUpit() {
        return "DELETE FROM " + getNazivTabele() + " WHERE " + getSlozeniPrimarniKljuc();
    }

}
