package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Predstavlja izveštaj za angažovanje.
 *
 * @author dulait
 */
public class Izvestaj extends OpstiDomenskiObjekat {

    private Angazovanje angazovanje;
    private String opis;

    /**
     * Podrazumevajući konstruktor.
     */
    public Izvestaj() {
    }

    /**
     * Konstruktor koji kreira objekat Izvestaj sa datim angažovanjem i opisom.
     *
     * @param angazovanje angažovanje povezano sa izveštajem.
     * @param opis opis izveštaja.
     * @throws IllegalArgumentException ako je angazovanje null ili ako je opis
     * null ili prazan.
     */
    public Izvestaj(Angazovanje angazovanje, String opis) {
        setAngazovanje(angazovanje);
        setOpis(opis);
    }

    /**
     * Vraća angažovanje povezano sa izveštajem.
     *
     * @return angažovanje.
     */
    public Angazovanje getAngazovanje() {
        return angazovanje;
    }

    /**
     * Postavlja angažovanje povezano sa izveštajem.
     *
     * @param angazovanje angažovanje za postavljanje.
     * @throws IllegalArgumentException ako je angazovanje null.
     */
    public final void setAngazovanje(Angazovanje angazovanje) {
        if (angazovanje == null) {
            throw new IllegalArgumentException("Angazovanje ne može biti null.");
        }
        this.angazovanje = angazovanje;
    }

    /**
     * Vraća opis izveštaja.
     *
     * @return opis.
     */
    public String getOpis() {
        return opis;
    }

    /**
     * Postavlja opis izveštaja.
     *
     * @param opis opis za postavljanje.
     * @throws IllegalArgumentException ako je opis null ili prazan.
     */
    public final void setOpis(String opis) {
        if (opis == null || opis.isEmpty()) {
            throw new IllegalArgumentException("Opis ne može biti null ili prazan.");
        }
        this.opis = opis;
    }

    /**
     * Vraća ime tabele povezane sa ovim entitetom.
     *
     * @return ime tabele.
     */
    @Override
    public String getNazivTabele() {
        return "izvestaj";
    }

    /**
     * Vraća parametre za ovaj entitet u formatu koji je potreban za SQL izjavu.
     *
     * @return string sa parametrima.
     */
    @Override
    public String getParametre() {
        return String.format("%d, %d, %d, '%s'",
                angazovanje.getSpasilac().getId(), angazovanje.getSmena().getId(), angazovanje.getRaspored().getId(), opis);
    }

    /**
     * Vraća imena parametara za ovaj entitet.
     *
     * @return string sa imenima parametara.
     */
    @Override
    public String getNaziveParametara() {
        return "spasilacId, smenaId, rasporedId, opis";
    }

    /**
     * Vraća ime primarnog ključa za ovaj entitet.
     *
     * @return ime primarnog ključa, koje je null jer ovaj entitet ima složeni
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
        return "spasilacId = " + angazovanje.getSpasilac().getId() + " AND smenaId = " + angazovanje.getSmena().getId()
                + " AND rasporedId = " + angazovanje.getRaspored().getId();
    }

    /**
     * Konvertuje ResultSet u listu Izvestaj objekata.
     *
     * @param rs ResultSet koji treba konvertovati.
     * @return lista Izvestaj objekata.
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
            System.out.println("Greška u Izvestaj::konvertujRSUListu\n" + e.getMessage());
        }
        return izvestaji;
    }

    /**
     * Vraća SQL SELECT upit za preuzimanje svih Izvestaj zapisa.
     *
     * @return SQL SELECT upit kao string.
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
     * Vraća SQL SELECT upit za preuzimanje Izvestaj zapisa na osnovu
     * specifičnih parametara.
     *
     * @return SQL SELECT upit kao string sa parametrima.
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
     * Vraća SQL INSERT upit za umetanje Izvestaj zapisa.
     *
     * @return SQL INSERT upit kao string.
     */
    @Override
    public String getInsertUpit() {
        return "INSERT INTO " + getNazivTabele() + "(" + getNaziveParametara() + ")" + " VALUES (" + getParametre() + ")";
    }

    /**
     * Vraća SQL UPDATE upit za ažuriranje Izvestaj zapisa.
     *
     * @return SQL UPDATE upit kao string.
     */
    @Override
    public String getUpdateUpit() {
        return "UPDATE " + getNazivTabele() + " SET " + getUpdateParametre() + " WHERE " + getSlozeniPrimarniKljuc();
    }

    /**
     * Vraća parametre za ažuriranje Izvestaj zapisa.
     *
     * @return string sa parametrima za ažuriranje.
     */
    @Override
    public String getUpdateParametre() {
        return String.format("spasilacId = %d, smenaId = %d, rasporedId = %d, opis = '%s'",
                angazovanje.getSpasilac().getId(), angazovanje.getSmena().getId(), angazovanje.getRaspored().getId(), opis);
    }

    /**
     * Vraća SQL DELETE upit za brisanje Izvestaj zapisa.
     *
     * @return SQL DELETE upit kao string.
     */
    @Override
    public String getDeleteUpit() {
        return "DELETE FROM " + getNazivTabele() + " WHERE " + getSlozeniPrimarniKljuc();
    }
}
