package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Predstavlja angažovanje spasioca tokom smene u rasporedu.
 *
 * @author dulait
 */
public class Angazovanje extends OpstiDomenskiObjekat {

    private Spasilac spasilac;
    private Smena smena;
    private Raspored raspored;

    /**
     * Podrazumevajući konstruktor.
     */
    public Angazovanje() {
    }

    /**
     * Konstruktor koji kreira objekat Angazovanje sa datim spasiocem, smenom i
     * rasporedom.
     *
     * @param spasilac spasilac uključen u angažovanje.
     * @param smena smena tokom koje se angažovanje dešava.
     * @param raspored raspored unutar kojeg je angažovanje definisano.
     */
    public Angazovanje(Spasilac spasilac, Smena smena, Raspored raspored) {
        setSpasilac(spasilac);
        setSmena(smena);
        setRaspored(raspored);
    }

    /**
     * Vraća spasioca uključenog u angažovanje.
     *
     * @return spasilac.
     */
    public Spasilac getSpasilac() {
        return spasilac;
    }

    /**
     * Postavlja spasioca uključenog u angažovanje.
     *
     * @param spasilac spasilac za postavljanje.
     * @throws IllegalArgumentException ako je spasilac null.
     */
    public final void setSpasilac(Spasilac spasilac) {
        if (spasilac == null) {
            throw new IllegalArgumentException("Spasilac ne može biti null.");
        }
        this.spasilac = spasilac;
    }

    /**
     * Vraća smenu tokom koje se angažovanje dešava.
     *
     * @return smena.
     */
    public Smena getSmena() {
        return smena;
    }

    /**
     * Postavlja smenu tokom koje se angažovanje dešava.
     *
     * @param smena smena za postavljanje.
     * @throws IllegalArgumentException ako je smena null.
     */
    public final void setSmena(Smena smena) {
        if (smena == null) {
            throw new IllegalArgumentException("Smena ne može biti null.");
        }
        this.smena = smena;
    }

    /**
     * Vraća raspored unutar kojeg je angažovanje definisano.
     *
     * @return raspored.
     */
    public Raspored getRaspored() {
        return raspored;
    }

    /**
     * Postavlja raspored unutar kojeg je angažovanje definisano.
     *
     * @param raspored raspored za postavljanje.
     * @throws IllegalArgumentException ako je raspored null.
     */
    public final void setRaspored(Raspored raspored) {
        if (raspored == null) {
            throw new IllegalArgumentException("Raspored ne može biti null.");
        }
        this.raspored = raspored;
    }

    /**
     * Vraća string reprezentaciju objekta Angazovanje.
     *
     * @return string reprezentacija objekta Angazovanje.
     */
    @Override
    public String toString() {
        return spasilac.toString() + ", " + smena.toString() + ", " + raspored.toString();
    }

    /**
     * Vraća naziv tabele povezane sa ovim entitetom.
     *
     * @return naziv tabele.
     */
    @Override
    public String getNazivTabele() {
        return "angazovanje";
    }

    /**
     * Vraća parametre za ovaj entitet u formatu potrebnom za SQL izjavu.
     *
     * @return string parametara.
     */
    @Override
    public String getParametre() {
        return String.format("%d, %d, %d", spasilac.getId(), smena.getId(), raspored.getId());
    }

    /**
     * Vraća nazive parametara za ovaj entitet.
     *
     * @return string sa nazivima parametara.
     */
    @Override
    public String getNaziveParametara() {
        return "spasilacId, smenaId, rasporedId";
    }

    /**
     * Vraća naziv primarnog ključa za ovaj entitet.
     *
     * @return naziv primarnog ključa, koji je null jer ovaj entitet ima složeni
     * ključ.
     */
    @Override
    public String getNazivPrimarnogKljuca() {
        return null;
    }

    /**
     * Vraća vrednost primarnog ključa za ovaj entitet.
     *
     * @return vrednost primarnog ključa, koja je null jer ovaj entitet ima
     * složeni ključ.
     */
    @Override
    public Integer getVrednostPrimarnogKljuca() {
        return null;
    }

    /**
     * Vraća složeni primarni ključ za ovaj entitet.
     *
     * @return string sa složenim primarnim ključem.
     */
    @Override
    public String getSlozeniPrimarniKljuc() {
        return "spasilacId = " + spasilac.getId() + " AND smenaId = " + smena.getId() + " AND rasporedId = " + raspored.getId();
    }

    /**
     * Konvertuje ResultSet u listu objekata Angazovanje.
     *
     * @param rs ResultSet za konverziju.
     * @return lista objekata Angazovanje.
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
            System.out.println("Greška u Angazovanje::konvertujRSUListu\n" + e.getMessage());
        }
        return angazovanja;
    }

    /**
     * Vraća SQL SELECT upit za dobijanje svih Angazovanje zapisa.
     *
     * @return SQL SELECT upit kao string.
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
     * Vraća SQL SELECT upit za dobijanje Angazovanje zapisa na osnovu
     * specifičnih parametara.
     *
     * @return SQL SELECT upit kao string sa parametrima.
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
     * Vraća SQL INSERT upit za umetanje Angazovanje zapisa.
     *
     * @return SQL INSERT upit kao string.
     */
    @Override
    public String getInsertUpit() {
        return "INSERT INTO " + getNazivTabele() + "(" + getNaziveParametara() + ")" + " VALUES (" + getParametre() + ")";
    }

    /**
     * Vraća SQL UPDATE upit za ažuriranje Angazovanje zapisa.
     *
     * @return SQL UPDATE upit kao string.
     */
    @Override
    public String getUpdateUpit() {
        return "UPDATE " + getNazivTabele() + " SET " + getUpdateParametre() + " WHERE " + getSlozeniPrimarniKljuc();
    }

    /**
     * Vraća parametre za ažuriranje Angazovanje zapisa.
     *
     * @return string sa parametrima za ažuriranje.
     */
    @Override
    public String getUpdateParametre() {
        return String.format("spasilacId = %d, smenaId = %d, rasporedId = %d",
                getSpasilac().getId(), getSmena().getId(), getRaspored().getId());
    }

    /**
     * Vraća SQL DELETE upit za brisanje Angazovanje zapisa.
     *
     * @return SQL DELETE upit kao string.
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
