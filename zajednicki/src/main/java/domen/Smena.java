package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Predstavlja smenu sa vremenima početka i kraja.
 *
 * @author dulait
 */
public class Smena extends OpstiDomenskiObjekat {

    private int id;
    private int pocetak;
    private int kraj;

    /**
     * Podrazumevani konstruktor.
     */
    public Smena() {
    }

    /**
     * Konstruše Smenu sa datim id-jem.
     *
     * @param smenaId id smene.
     */
    public Smena(int smenaId) {
        this.id = smenaId;
    }

    /**
     * Postavlja id smene.
     *
     * @param id novi id smene.
     * @throws IllegalArgumentException ako je id manji ili jednak nuli.
     */
    public final void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id mora biti veći od nule.");
        }
        this.id = id;
    }

    /**
     * Postavlja vreme početka smene.
     *
     * @param pocetak novo vreme početka smene.
     * @throws IllegalArgumentException ako je pocetak manji od 0 ili veći od
     * 23.
     */
    public final void setPocetak(int pocetak) {
        if (pocetak < 0 || pocetak > 23) {
            throw new IllegalArgumentException("Vreme početka mora biti između 0 i 23.");
        }
        this.pocetak = pocetak;
    }

    /**
     * Postavlja vreme kraja smene.
     *
     * @param kraj novo vreme kraja smene.
     * @throws IllegalArgumentException ako je kraj manji od 0 ili veći od 23.
     */
    public final void setKraj(int kraj) {
        if (kraj < 0 || kraj > 23) {
            throw new IllegalArgumentException("Vreme kraja mora biti između 0 i 23.");
        }
        this.kraj = kraj;
    }

    /**
     * Konstruše Smenu sa datim id-jem, vremenom početka i vremenom kraja.
     *
     * @param smenaId id smene.
     * @param pocetak vreme početka smene.
     * @param kraj vreme kraja smene.
     * @throws IllegalArgumentException ako je pocetak manji od 0, veći od 23,
     * ili ako je kraj manji od 0, veći od 23, ili ako je kraj manji od pocetak.
     */
    public Smena(int smenaId, int pocetak, int kraj) {
        setId(smenaId);
        setPocetak(pocetak);
        setKraj(kraj);

        if (kraj < pocetak) {
            throw new IllegalArgumentException("Vreme kraja mora biti veće ili jednako vremenu početka.");
        }
    }

    /**
     * Vraća id smene.
     *
     * @return id smene.
     */
    public int getId() {
        return id;
    }

    /**
     * Vraća vreme početka smene.
     *
     * @return vreme početka smene.
     */
    public int getPocetak() {
        return pocetak;
    }

    /**
     * Vraća vreme kraja smene.
     *
     * @return vreme kraja smene.
     */
    public int getKraj() {
        return kraj;
    }

    /**
     * Konvertuje vreme početka i kraja smene u string reprezentaciju.
     *
     * @return string reprezentacija vremena početka i kraja smene.
     */
    @Override
    public String toString() {
        return "" + pocetak + ":00h - " + kraj + ":00h";
    }

    /**
     * Vraća naziv tabele povezan sa ovim domen objektom.
     *
     * @return naziv tabele kao String.
     */
    @Override
    public String getNazivTabele() {
        return "smena";
    }

    /**
     * Vraća parametre za ovaj domen objekat u formatu potrebnom za SQL izjavu.
     *
     * @return parametri kao String.
     */
    @Override
    public String getParametre() {
        return String.format("%s, %s, %s", id, pocetak, kraj);
    }

    /**
     * Vraća nazive parametara za ovaj domen objekat.
     *
     * @return nazivi parametara kao String.
     */
    @Override
    public String getNaziveParametara() {
        return "id, pocetak, kraj";
    }

    /**
     * Vraća naziv kolone primarnog ključa za ovaj domen objekat.
     *
     * @return naziv kolone primarnog ključa kao String.
     */
    @Override
    public String getNazivPrimarnogKljuca() {
        return "id";
    }

    /**
     * Vraća vrednost primarnog ključa za ovaj domen objekat.
     *
     * @return vrednost primarnog ključa kao Integer.
     */
    @Override
    public Integer getVrednostPrimarnogKljuca() {
        return id;
    }

    /**
     * Vraća složeni primarni ključ za ovaj domen objekat, ako je primenljivo.
     *
     * @return složeni primarni ključ kao String, ili null ako nije primenljivo.
     */
    @Override
    public String getSlozeniPrimarniKljuc() {
        return null;
    }

    /**
     * Konvertuje {@link ResultSet} u listu domen objekata.
     *
     * @param rs {@link ResultSet} koji treba konvertovati.
     * @return {@link List} domen objekata.
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
            System.out.println("Greška u Smena::konvertujRSUListu:\n" + e.getMessage());
        }
        return smene;
    }

    /**
     * Vraća SQL SELECT upit za dobijanje svih zapisa ovog domen objekta.
     *
     * @return SQL SELECT upit kao String.
     */
    @Override
    public String getSelectUpit() {
        return "SELECT * FROM " + getNazivTabele();
    }

    /**
     * Vraća SQL SELECT upit za dobijanje zapisa ovog domen objekta na osnovu
     * specifičnih parametara.
     *
     * @return SQL SELECT upit sa parametrima kao String.
     */
    @Override
    public String getSelectUpitPoParametru() {
        return "SELECT * FROM " + getNazivTabele() + " WHERE id = " + getId() + " OR (pocetak = " + getPocetak() + " AND kraj = " + getKraj() + ")";
    }

    /**
     * Vraća SQL INSERT upit za umetanje zapisa ovog domen objekta.
     *
     * @return SQL INSERT upit kao String.
     */
    @Override
    public String getInsertUpit() {
        return "INSERT INTO " + getNazivTabele() + "(" + getNaziveParametara() + ")" + " VALUES (" + getParametre() + ")";
    }

    /**
     * Vraća SQL UPDATE upit za ažuriranje zapisa ovog domen objekta.
     *
     * @return SQL UPDATE upit kao String.
     */
    @Override
    public String getUpdateUpit() {
        return "UPDATE " + getNazivTabele() + " SET " + getUpdateParametre() + " WHERE " + getNazivPrimarnogKljuca() + " = " + getVrednostPrimarnogKljuca();
    }

    /**
     * Vraća parametre za ažuriranje zapisa ovog domen objekta.
     *
     * @return parametri za ažuriranje kao String.
     */
    @Override
    public String getUpdateParametre() {
        return String.format("pocetak = %s, kraj = %s", pocetak, kraj);
    }

    /**
     * Vraća SQL DELETE upit za brisanje zapisa ovog domen objekta.
     *
     * @return SQL DELETE upit kao String.
     */
    @Override
    public String getDeleteUpit() {
        return "DELETE FROM " + getNazivTabele() + " WHERE " + getNazivPrimarnogKljuca() + " = " + getVrednostPrimarnogKljuca();
    }

    /**
     * Upoređuje ovaj objekat sa datim objektom radi utvrđivanja jednakosti.
     *
     * @param o objekat sa kojim se upoređuje.
     * @return true ako su objekti jednaki, inače false.
     */
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

    /**
     * Vraća hash kod ovog objekta.
     *
     * @return hash kod kao int.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, pocetak, kraj);
    }

}
