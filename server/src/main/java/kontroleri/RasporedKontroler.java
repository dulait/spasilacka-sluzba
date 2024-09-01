package kontroleri;

import domen.OpstiDomenskiObjekat;
import domen.Raspored;
import java.util.List;
import sistemska_operacija.raspored.SOKreirajRaspored;
import sistemska_operacija.raspored.SOObrisiRaspored;
import sistemska_operacija.raspored.SOUcitajListuRasporeda;
import sistemska_operacija.raspored.SOUcitajRaspored;

/**
 * Klasa koja pruža metode za upravljanje Rasporedima.
 *
 * @author dulait
 */
public class RasporedKontroler {

    private static RasporedKontroler instanca;

    private RasporedKontroler() {
    }

    /**
     * Vraća instancu kontrolera.
     *
     * @return singleton instanca {@code RasporedKontroler}
     */
    public static RasporedKontroler getInstanca() {
        if (instanca == null) {
            instanca = new RasporedKontroler();
        }
        return instanca;
    }

    /**
     * Vraća listu svih Rasporeda.
     *
     * @return lista {@code OpstiDomenskiObjekat} koji predstavljaju Raspored
     * objekte
     */
    public List<OpstiDomenskiObjekat> ucitajListuRasporeda() {
        SOUcitajListuRasporeda so = new SOUcitajListuRasporeda();
        so.izvrsiSistemskuOperaciju();
        return so.getRasporedi();
    }

    /**
     * Vraća specifičan Raspored.
     *
     * @param raspored objekat {@code Raspored} koji treba vratiti
     * @return {@code OpstiDomenskiObjekat} koji predstavlja pronađeni Raspored
     */
    public OpstiDomenskiObjekat ucitajRaspored(Raspored raspored) {
        SOUcitajRaspored so = new SOUcitajRaspored(raspored);
        so.izvrsiSistemskuOperaciju();
        return so.getRaspored();
    }

    /**
     * Kreira novi Raspored.
     *
     * @param raspored objekat {@code Raspored} koji treba kreirati
     * @return {@code true} ako je kreiranje uspešno, {@code false} inače
     */
    public boolean kreirajRaspored(Raspored raspored) {
        SOKreirajRaspored so = new SOKreirajRaspored(raspored);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }

    /**
     * Briše specifičan Raspored.
     *
     * @param raspored objekat {@code Raspored} koji treba obrisati
     * @return {@code true} ako je brisanje uspešno, {@code false} inače
     */
    public boolean obrisiRaspored(Raspored raspored) {
        SOObrisiRaspored so = new SOObrisiRaspored(raspored);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }

}
