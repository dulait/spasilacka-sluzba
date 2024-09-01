package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja spasioca sa ličnim informacijama uključujući ID, ime, prezime i
 * JMBG.
 *
 * @author dulait
 */
public class Spasilac extends OpstiDomenskiObjekat {

    private int id;
    private String ime;
    private String prezime;
    private String jmbg;

    /**
     * Podrazumevajući konstruktor.
     */
    public Spasilac() {
    }

    /**
     * Konstruira Spasioca sa specifičnim ID-jem.
     *
     * @param id id spasioca.
     */
    public Spasilac(int id) {
        this.id = id;
    }

    /**
     * Postavlja id spasioca.
     *
     * @param id novi id spasioca.
     * @throws IllegalArgumentException ako je id manji ili jednak nuli.
     */
    public final void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id mora biti veći od nule.");
        }
        this.id = id;
    }

    /**
     * Postavlja ime spasioca.
     *
     * @param ime novo ime spasioca.
     * @throws IllegalArgumentException ako je ime null ili prazno.
     */
    public final void setIme(String ime) {
        if (ime == null || ime.trim().isEmpty()) {
            throw new IllegalArgumentException("Ime ne može biti null ili prazno.");
        }
        this.ime = ime;
    }

    /**
     * Postavlja prezime spasioca.
     *
     * @param prezime novo prezime spasioca.
     * @throws IllegalArgumentException ako je prezime null ili prazno.
     */
    public final void setPrezime(String prezime) {
        if (prezime == null || prezime.trim().isEmpty()) {
            throw new IllegalArgumentException("Prezime ne može biti null ili prazno.");
        }
        this.prezime = prezime;
    }

    /**
     * Postavlja JMBG spasioca.
     *
     * @param jmbg novi JMBG spasioca.
     * @throws IllegalArgumentException ako je jmbg null ili ne odgovara
     * zahtevanom formatu.
     */
    public final void setJmbg(String jmbg) {
        if (jmbg == null || !jmbg.matches("\\d{13}")) { // Pretpostavlja se da je JMBG 13-cifren broj
            throw new IllegalArgumentException("JMBG mora biti 13-cifren broj.");
        }
        this.jmbg = jmbg;
    }

    /**
     * Konstruira Spasioca sa specifičnim id-jem, imenom, prezimenom i JMBG-om.
     *
     * @param spasilacId ID spasioca.
     * @param ime ime spasioca.
     * @param prezime prezime spasioca.
     * @param jmbg JMBG spasioca.
     * @throws IllegalArgumentException ako su ime ili prezime null ili prazni,
     * ili ako jmbg ne odgovara zahtevanom formatu.
     */
    public Spasilac(int spasilacId, String ime, String prezime, String jmbg) {
        setId(spasilacId); // Koristeći validirani setter
        setIme(ime); // Koristeći validirani setter
        setPrezime(prezime); // Koristeći validirani setter
        setJmbg(jmbg); // Koristeći validirani setter
    }

    /**
     * Vraća string reprezentaciju spasioca.
     *
     * @return string reprezentacija spasioca.
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    /**
     * Vraća id spasioca.
     *
     * @return id spasioca.
     */
    public int getId() {
        return id;
    }

    /**
     * Vraća ime spasioca.
     *
     * @return ime spasioca.
     */
    public String getIme() {
        return ime;
    }

    /**
     * Vraća prezime spasioca.
     *
     * @return prezime spasioca.
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Vraća JMBG spasioca.
     *
     * @return JMBG spasioca.
     */
    public String getJmbg() {
        return jmbg;
    }

    /**
     * Vraća naziv tabele povezan sa ovim domena objektom.
     *
     * @return naziv tabele kao String.
     */
    @Override
    public String getNazivTabele() {
        return "spasilac";
    }

    /**
     * Vraća parametre za ovaj domena objekat u formatu potrebnom za SQL izjavu.
     *
     * @return parametri kao String.
     */
    @Override
    public String getParametre() {
        return String.format("%d, '%s', '%s', '%s'", id, ime, prezime, jmbg);
    }

    /**
     * Vraća nazive parametara za ovaj domena objekat.
     *
     * @return nazivi parametara kao String.
     */
    @Override
    public String getNaziveParametara() {
        return "id, ime, prezime, jmbg";
    }

    /**
     * Vraća naziv primarnog ključa za ovaj domena objekat.
     *
     * @return naziv primarnog ključa kao String.
     */
    @Override
    public String getNazivPrimarnogKljuca() {
        return "id";
    }

    /**
     * Vraća vrednost primarnog ključa za ovaj domena objekat.
     *
     * @return vrednost primarnog ključa kao Integer.
     */
    @Override
    public Integer getVrednostPrimarnogKljuca() {
        return id;
    }

    /**
     * Vraća složeni primarni ključ za ovaj domena objekat, ako je primenljivo.
     *
     * @return složeni primarni ključ kao String, ili null ako nije primenljivo.
     */
    @Override
    public String getSlozeniPrimarniKljuc() {
        return null;
    }

    /**
     * Konvertuje {@link ResultSet} u listu domena objekata.
     *
     * @param rs {@link ResultSet} koji treba konvertovati.
     * @return {@link List} domena objekata.
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
            System.out.println("Greška u Spasilac::konvertujRSUListu\n" + e.getMessage());
        }
        return spasioci;
    }

    /**
     * Vraća SQL SELECT upit za Vraćanje svih zapisa ovog domena objekta.
     *
     * @return SQL SELECT upit kao String.
     */
    @Override
    public String getSelectUpit() {
        return "SELECT * FROM " + getNazivTabele();
    }

    /**
     * Vraća SQL SELECT upit za Vraćanje zapisa ovog domena objekta na osnovu
     * specifičnih parametara.
     *
     * @return SQL SELECT upit sa parametrima kao String.
     */
    @Override
    public String getSelectUpitPoParametru() {
        return "SELECT * FROM " + getNazivTabele() + " WHERE id = " + getId() + " OR jmbg = '" + getJmbg() + "'";
    }

    /**
     * Vraća SQL INSERT upit za umetanje zapisa ovog domena objekta.
     *
     * @return SQL INSERT upit kao String.
     */
    @Override
    public String getInsertUpit() {
        return "INSERT INTO " + getNazivTabele() + "(" + getNaziveParametara() + ")" + " VALUES (" + getParametre() + ")";
    }

    /**
     * Vraća SQL UPDATE upit za ažuriranje zapisa ovog domena objekta.
     *
     * @return SQL UPDATE upit kao String.
     */
    @Override
    public String getUpdateUpit() {
        return "UPDATE " + getNazivTabele() + " SET " + getUpdateParametre() + " WHERE " + getNazivPrimarnogKljuca() + " = " + getVrednostPrimarnogKljuca();
    }

    /**
     * Vraća parametre za ažuriranje zapisa ovog domena objekta.
     *
     * @return parametri za ažuriranje kao String.
     */
    @Override
    public String getUpdateParametre() {
        return String.format("ime = '%s', prezime = '%s', jmbg = '%s'", ime, prezime, jmbg);
    }

    /**
     * Vraća SQL DELETE upit za brisanje zapisa ovog domena objekta.
     *
     * @return SQL DELETE upit kao String.
     */
    @Override
    public String getDeleteUpit() {
        return "DELETE FROM " + getNazivTabele() + " WHERE " + getNazivPrimarnogKljuca() + " = " + getVrednostPrimarnogKljuca();
    }

    /**
     * Upoređuje ovaj objekat sa datim objektom radi jednakosti.
     *
     * @param o objekat koji se upoređuje sa ovim.
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
        Spasilac spasilac = (Spasilac) o;
        return id == spasilac.id
                && Objects.equals(ime, spasilac.ime)
                && Objects.equals(prezime, spasilac.prezime)
                && Objects.equals(jmbg, spasilac.jmbg);
    }

    /**
     * Vraća hash kod za ovaj objekat.
     *
     * @return hash kod objekta kao int.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, ime, prezime, jmbg);
    }
}
