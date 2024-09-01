package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Predstavlja koordinatora u sistemu.
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
     * Podrazumevani konstruktor.
     */
    public Koordinator() {
    }

    /**
     * Konstruira objekat Koordinator sa zadatim id-jem.
     *
     * @param id id koordinatora.
     */
    public Koordinator(int id) {
        this.id = id;
    }

    /**
     * Postavlja korisničko ime koordinatora.
     *
     * @param korisnickoIme korisničko ime za postavljanje.
     * @throws IllegalArgumentException ako je korisnickoIme null ili prazno.
     */
    public final void setKorisnickoIme(String korisnickoIme) {
        if (korisnickoIme == null || korisnickoIme.isEmpty()) {
            throw new IllegalArgumentException("Korisnicko ime ne može biti null ili prazno.");
        }
        this.korisnickoIme = korisnickoIme;
    }

    /**
     * Postavlja lozinku koordinatora.
     *
     * @param lozinka lozinka za postavljanje.
     * @throws IllegalArgumentException ako je lozinka null ili prazna.
     */
    public final void setLozinka(String lozinka) {
        if (lozinka == null || lozinka.isEmpty()) {
            throw new IllegalArgumentException("Lozinka ne može biti null ili prazna.");
        }
        this.lozinka = lozinka;
    }

    /**
     * Postavlja ime koordinatora.
     *
     * @param ime ime za postavljanje.
     * @throws IllegalArgumentException ako je ime null ili prazno.
     */
    public final void setIme(String ime) {
        if (ime == null || ime.isEmpty()) {
            throw new IllegalArgumentException("Ime ne može biti null ili prazno.");
        }
        this.ime = ime;
    }

    /**
     * Postavlja prezime koordinatora.
     *
     * @param prezime prezime za postavljanje.
     * @throws IllegalArgumentException ako je prezime null ili prazno.
     */
    public final void setPrezime(String prezime) {
        if (prezime == null || prezime.isEmpty()) {
            throw new IllegalArgumentException("Prezime ne može biti null ili prazno.");
        }
        this.prezime = prezime;
    }

    /**
     * Konstruira objekat Koordinator sa zadatim atributima.
     *
     * @param id id koordinatora.
     * @param korisnickoIme korisničko ime koordinatora.
     * @param lozinka lozinka koordinatora.
     * @param ime ime koordinatora.
     * @param prezime prezime koordinatora.
     * @throws IllegalArgumentException ako je korisnickoIme, lozinka, ime ili
     * prezime null ili prazno.
     */
    public Koordinator(int id, String korisnickoIme, String lozinka, String ime, String prezime) {
        setId(id);
        setKorisnickoIme(korisnickoIme);
        setLozinka(lozinka);
        setIme(ime);
        setPrezime(prezime);
    }

    /**
     * Vraća prezime koordinatora.
     *
     * @return prezime.
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Vraća id koordinatora.
     *
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja id koordinatora.
     *
     * @param id id za postavljanje.
     * @throws IllegalArgumentException ako je id manji ili jednak nuli.
     */
    public final void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id mora biti veći od nule.");
        }
        this.id = id;
    }

    /**
     * Vraća korisničko ime koordinatora.
     *
     * @return korisničko ime.
     */
    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    /**
     * Vraća lozinku koordinatora.
     *
     * @return lozinka.
     */
    public String getLozinka() {
        return lozinka;
    }

    /**
     * Vraća ime koordinatora.
     *
     * @return ime.
     */
    public String getIme() {
        return ime;
    }

    /**
     * Vraća string reprezentaciju koordinatora.
     *
     * @return string koji predstavlja ime i prezime koordinatora.
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    /**
     * Vraća ime tabele povezane sa ovim entitetom.
     *
     * @return ime tabele.
     */
    @Override
    public String getNazivTabele() {
        return "koordinator";
    }

    /**
     * Vraća parametre za ovaj entitet u formatu koji je potreban za SQL izjavu.
     *
     * @return string sa parametrima.
     */
    @Override
    public String getParametre() {
        return String.format("%d, '%s', '%s', '%s', '%s'", id, korisnickoIme, lozinka, ime, prezime);
    }

    /**
     * Vraća imena parametara za ovaj entitet.
     *
     * @return string sa imenima parametara.
     */
    @Override
    public String getNaziveParametara() {
        return "id, korisnickoIme, lozinka, ime, prezime";
    }

    /**
     * Vraća ime primarnog ključa za ovaj entitet.
     *
     * @return ime primarnog ključa.
     */
    @Override
    public String getNazivPrimarnogKljuca() {
        return "id";
    }

    /**
     * Vraća vrednost primarnog ključa za ovaj entitet.
     *
     * @return vrednost primarnog ključa.
     */
    @Override
    public Integer getVrednostPrimarnogKljuca() {
        return id;
    }

    /**
     * Vraća složeni primarni ključ za ovaj entitet.
     *
     * @return string sa složenim primarnim ključem, koji je null za ovaj
     * entitet.
     */
    @Override
    public String getSlozeniPrimarniKljuc() {
        return null;
    }

    /**
     * Konvertuje ResultSet u listu objekata Koordinator.
     *
     * @param rs ResultSet za konvertovanje.
     * @return lista objekata Koordinator.
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
                String rsPrezime = rs.getString("prezime");

                koordinatori.add(new Koordinator(rsId, rsKorisnickoIme, rsLozinka, rsIme, rsPrezime));
            }
        } catch (SQLException e) {
            System.out.println("Greška u Koordinator::konvertujRSUListu\n" + e.getMessage());
        }
        return koordinatori;
    }

    /**
     * Vraća SQL SELECT upit za preuzimanje svih Koordinator zapisa.
     *
     * @return SQL SELECT upit.
     */
    @Override
    public String getSelectUpit() {
        return "SELECT * FROM " + getNazivTabele();
    }

    /**
     * Vraća SQL SELECT upit za preuzimanje Koordinator zapisa na osnovu
     * specifičnih parametara.
     *
     * @return SQL SELECT upit sa parametrima.
     */
    @Override
    public String getSelectUpitPoParametru() {
        return "SELECT * FROM " + getNazivTabele() + " WHERE id = " + getVrednostPrimarnogKljuca();
    }

    /**
     * Vraća SQL INSERT upit za ubacivanje zapisa Koordinator.
     *
     * @return SQL INSERT upit.
     */
    @Override
    public String getInsertUpit() {
        return null;
    }

    /**
     * Vraća parametre za ažuriranje zapisa Koordinator.
     *
     * @return string sa parametrima za ažuriranje.
     */
    @Override
    public String getUpdateParametre() {
        return String.format("korisnickoIme='%s', lozinka='%s', ime='%s', prezime='%s' WHERE id=%d",
                korisnickoIme, lozinka, ime, prezime, id);
    }

    /**
     * Vraća SQL DELETE upit za brisanje zapisa Koordinator.
     *
     * @return SQL DELETE upit.
     */
    @Override
    public String getDeleteUpit() {
        return "DELETE FROM " + getNazivTabele() + " WHERE id=" + getVrednostPrimarnogKljuca();
    }

    /**
     * Vraća SQL UPDATE upit za ažuriranje zapisa Koordinator.
     *
     * @return SQL UPDATE upit.
     */
    @Override
    public String getUpdateUpit() {
        return "UPDATE " + getNazivTabele() + " SET " + getUpdateParametre();
    }

    /**
     * Upoređuje objekat sa trenutnim koordinatorom.
     *
     * @param obj objekat koji se upoređuje sa trenutnim koordinatorom.
     * @return true ako su objekti jednaki, false inače.
     */
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

    /**
     * Vraća hash kod za trenutni objekat koordinatora.
     *
     * @return hash kod.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, korisnickoIme, lozinka, ime, prezime);
    }

}
