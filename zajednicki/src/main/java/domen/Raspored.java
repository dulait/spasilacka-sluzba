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
 * Predstavlja raspored sa listom angažovanja.
 *
 * @author dulait
 */
public class Raspored extends OpstiDomenskiObjekat implements Comparable<Raspored> {

    private int id;
    private LocalDate datum;
    private List<Angazovanje> angazovanja;

    /**
     * Podrazumevajući konstruktor.
     */
    public Raspored() {
    }

    /**
     * Konstruktor koji kreira Raspored sa zadatim id-jem.
     *
     * @param id ID rasporeda.
     */
    public Raspored(int id) {
        this.id = id;
    }

    /**
     * Postavlja datum rasporeda.
     *
     * @param datum novi datum rasporeda.
     * @throws IllegalArgumentException ako je datum null.
     */
    public final void setDatum(LocalDate datum) {
        if (datum == null) {
            throw new IllegalArgumentException("Datum ne može biti null.");
        }
        this.datum = datum;
    }

    /**
     * Postavlja id rasporeda.
     *
     * @param id novi id rasporeda.
     * @throws IllegalArgumentException ako je id manji ili jednak nuli.
     */
    public final void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id mora biti veći od nule.");
        }
        this.id = id;
    }

    /**
     * Konstruktor koji kreira Raspored sa specifičnim ID-em i datumom.
     *
     * @param id id rasporeda.
     * @param datum datum rasporeda.
     * @throws IllegalArgumentException ako je datum null ili id manji ili
     * jednako nuli.
     */
    public Raspored(int id, LocalDate datum) {
        setId(id);
        setDatum(datum);
        this.angazovanja = new ArrayList<>();
    }

    /**
     * Vraća datum rasporeda.
     *
     * @return datum rasporeda.
     */
    public LocalDate getDatum() {
        return datum;
    }

    /**
     * Vraća id rasporeda.
     *
     * @return id rasporeda.
     */
    public int getId() {
        return id;
    }

    /**
     * Vraća listu angažovanja u rasporedu.
     *
     * @return lista angažovanja.
     */
    public List<Angazovanje> getAngazovanja() {
        return angazovanja;
    }

    /**
     * Postavlja listu angažovanja u rasporedu.
     *
     * @param angazovanja nova lista angažovanja.
     * @throws IllegalArgumentException ako je angazovanja null.
     */
    public void setAngazovanja(List<Angazovanje> angazovanja) {
        if (angazovanja == null) {
            throw new IllegalArgumentException("Angazovanja ne mogu biti null.");
        }
        this.angazovanja = angazovanja;
    }

    /**
     * Konvertuje datum rasporeda u string reprezentaciju.
     *
     * @return string reprezentacija datuma rasporeda.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy", new Locale.Builder().setLanguage("sr").setScript("Latn").build());
        return formatter.format(datum);
    }

    /**
     * Upoređuje ovaj raspored sa drugim rasporedom po datumu.
     *
     * @param o drugi raspored za upoređivanje.
     * @return negativan ceo broj, nula ili pozitivan ceo broj kao ovaj raspored
     * je raniji, jednak ili kasniji od specificiranog rasporeda.
     */
    @Override
    public int compareTo(Raspored o) {
        return this.getDatum().compareTo(o.getDatum());
    }

    /**
     * Vraća ime tabele povezane sa ovim domenski objektom.
     *
     * @return ime tabele kao String.
     */
    @Override
    public String getNazivTabele() {
        return "raspored";
    }

    /**
     * Vraća parametre za ovaj domenski objekat u formatu koji je potreban za
     * SQL iskaz.
     *
     * @return parametri kao String.
     */
    @Override
    public String getParametre() {
        return String.format("%d, '%s'", id, datum);
    }

    /**
     * Vraća imena parametara za ovaj domenski objekat.
     *
     * @return imena parametara kao String.
     */
    @Override
    public String getNaziveParametara() {
        return "id, datum";
    }

    /**
     * Vraća ime kolone primarnog ključa za ovaj domenski objekat.
     *
     * @return ime kolone primarnog ključa kao String.
     */
    @Override
    public String getNazivPrimarnogKljuca() {
        return "id";
    }

    /**
     * Vraća vrednost primarnog ključa za ovaj domenski objekat.
     *
     * @return vrednost primarnog ključa kao Integer.
     */
    @Override
    public Integer getVrednostPrimarnogKljuca() {
        return id;
    }

    /**
     * Vraća složeni primarni ključ za ovaj domenski objekat, ako je
     * primenljivo.
     *
     * @return složeni primarni ključ kao String, ili null ako nije primenljivo.
     */
    @Override
    public String getSlozeniPrimarniKljuc() {
        return null;
    }

    /**
     * Konvertuje {@link ResultSet} u listu domenskih objekata.
     *
     * @param rs {@link ResultSet} koji se konvertuje.
     * @return {@link List} domenskih objekata.
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
            System.out.println("Greška u Raspored::konvertujRSUListu:\n" + e.getMessage());
        }
        return rasporedi;
    }

    /**
     * Vraća SQL SELECT upit za dobijanje svih zapisa ovog domenskog objekta.
     *
     * @return SQL SELECT upit kao String.
     */
    @Override
    public String getSelectUpit() {
        return "SELECT * FROM " + getNazivTabele();
    }

    /**
     * Vraća SQL SELECT upit za dobijanje zapisa ovog domenskog objekta na
     * osnovu specifičnih parametara.
     *
     * @return SQL SELECT upit sa parametrima kao String.
     */
    @Override
    public String getSelectUpitPoParametru() {
        return "SELECT * FROM " + getNazivTabele() + " WHERE id = " + getId() + " OR datum = '" + getDatum() + "'";
    }

    /**
     * Vraća SQL INSERT upit za umetanje zapisa ovog domenskog objekta.
     *
     * @return SQL INSERT upit kao String.
     */
    @Override
    public String getInsertUpit() {
        return "INSERT INTO " + getNazivTabele() + "(" + getNaziveParametara() + ")" + " VALUES (" + getParametre() + ")";
    }

    /**
     * Vraća SQL UPDATE upit za ažuriranje zapisa ovog domenskog objekta.
     *
     * @return SQL UPDATE upit kao String.
     */
    @Override
    public String getUpdateUpit() {
        return "UPDATE " + getNazivTabele() + " SET " + getUpdateParametre() + " WHERE " + getNazivPrimarnogKljuca() + " = " + getVrednostPrimarnogKljuca();
    }

    /**
     * Vraća parametre za ažuriranje zapisa ovog domenskog objekta.
     *
     * @return parametri za ažuriranje kao String.
     */
    @Override
    public String getUpdateParametre() {
        return String.format("datum = '%s'", getDatum());
    }

    /**
     * Vraća SQL DELETE upit za brisanje zapisa ovog domenskog objekta.
     *
     * @return SQL DELETE upit kao String.
     */
    @Override
    public String getDeleteUpit() {
        return "DELETE FROM " + getNazivTabele() + " WHERE " + getNazivPrimarnogKljuca() + " = " + getVrednostPrimarnogKljuca();
    }

    /**
     * Upoređuje ovaj objekat sa drugim objektom radi utvrđivanja da li su
     * jednaki.
     *
     * @param o objekat sa kojim se upoređuje.
     * @return {@code true} ako su objekti jednaki, inače {@code false}.
     */
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

    /**
     * Vraća hash kod za ovaj objekat. Hash kod se generiše koristeći id i datum
     * rasporeda.
     *
     * @return hash kod kao {@code int}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, datum);
    }

}
