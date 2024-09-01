package kontroleri;

import domen.Angazovanje;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import sistemska_operacija.angazovanje.SOKreirajAngazovanje;
import sistemska_operacija.angazovanje.SOObrisiAngazovanje;
import sistemska_operacija.angazovanje.SOUcitajAngazovanje;
import sistemska_operacija.angazovanje.SOUcitajListuAngazovanja;

/**
 * Klasa koja pruža metode za upravljanje Angažovanjima.
 *
 * @author dulait
 */
public class AngazovanjeKontroler {

    private static AngazovanjeKontroler instanca;

    private AngazovanjeKontroler() {
    }

    /**
     * Vraća instancu kontrolera.
     *
     * @return singleton instanca {@code AngazovanjeKontroler}
     */
    public static AngazovanjeKontroler getInstanca() {
        if (instanca == null) {
            instanca = new AngazovanjeKontroler();
        }
        return instanca;
    }

    /**
     * Vraća listu svih Angažovanja.
     *
     * @return lista {@code OpstiDomenskiObjekat} koji predstavljaju
     * {@code Angazovanje} objekte
     */
    public List<OpstiDomenskiObjekat> ucitajListuAngazovanja() {
        SOUcitajListuAngazovanja so = new SOUcitajListuAngazovanja();
        so.izvrsiSistemskuOperaciju();
        return so.getAngazovanja();
    }

    /**
     * Vraća Angažovanje.
     *
     * @param angazovanje objekat {@code Angazovanje} koji treba vratiti
     * @return {@code OpstiDomenskiObjekat} koji predstavlja pronađeno
     * Angažovanje
     */
    public OpstiDomenskiObjekat ucitajAngazovanje(Angazovanje angazovanje) {
        SOUcitajAngazovanje so = new SOUcitajAngazovanje(angazovanje);
        so.izvrsiSistemskuOperaciju();
        return so.getAngazovanje();
    }

    /**
     * Kreira novo Angažovanje.
     *
     * @param angazovanje objekat {@code Angazovanje} koji treba kreirati
     * @return {@code true} ako je kreiranje uspešno, {@code false} inače
     */
    public boolean kreirajAngazovanje(Angazovanje angazovanje) {
        SOKreirajAngazovanje so = new SOKreirajAngazovanje(angazovanje);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }

    /**
     * Briše specifično Angažovanje.
     *
     * @param angazovanje Angažovanje koji treba obrisati
     * @return {@code true} ako je brisanje uspešno, {@code false} inače
     */
    public boolean obrisiAngazovanje(Angazovanje angazovanje) {
        SOObrisiAngazovanje so = new SOObrisiAngazovanje(angazovanje);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }
}
