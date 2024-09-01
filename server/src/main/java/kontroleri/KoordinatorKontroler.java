package kontroleri;

import domen.Koordinator;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import sistemska_operacija.koordinator.SOPrijaviKoordinatora;
import sistemska_operacija.koordinator.SOUcitajKoordinatora;
import sistemska_operacija.koordinator.SOUcitajListuKoordinatora;

/**
 * Klasa koja pruža metode za upravljanje Koordinatorima.
 *
 * @author dulait
 */
public class KoordinatorKontroler {

    private static KoordinatorKontroler instanca;

    private KoordinatorKontroler() {
    }

    /**
     * Vraća instancu kontrolera.
     *
     * @return singleton instanca {@code KoordinatorKontroler}
     */
    public static KoordinatorKontroler getInstanca() {
        if (instanca == null) {
            instanca = new KoordinatorKontroler();
        }
        return instanca;
    }

    /**
     * Vrši prijavu Koordinatora na sistem (login).
     *
     * @param koordinator objekat {@code Koordinator} koji se prijavljuje
     * @return {@code OpstiDomenskiObjekat} koji predstavlja prijavljenog
     * Koordinatora
     */
    public OpstiDomenskiObjekat prijaviKoordinatora(Koordinator koordinator) {
        SOPrijaviKoordinatora so = new SOPrijaviKoordinatora(koordinator);
        so.izvrsiSistemskuOperaciju();
        return so.getKoordinator();
    }

    /**
     * Vraća specifičnog Koordinatora.
     *
     * @param koordinator objekat {@code Koordinator} koji treba vratiti
     * @return {@code OpstiDomenskiObjekat} koji predstavlja pronađenog
     * Koordinatora
     */
    public OpstiDomenskiObjekat ucitajKoordinatora(Koordinator koordinator) {
        SOUcitajKoordinatora so = new SOUcitajKoordinatora(koordinator);
        so.izvrsiSistemskuOperaciju();
        return so.getKoordinator();
    }

    /**
     * Vraća listu svih Koordinatora.
     *
     * @return lista {@code OpstiDomenskiObjekat} koji predstavljaju Koordinator
     * objekte
     */
    public List<OpstiDomenskiObjekat> ucitajListuKoordinatora() {
        SOUcitajListuKoordinatora so = new SOUcitajListuKoordinatora();
        so.izvrsiSistemskuOperaciju();
        return so.getKoordinatori();
    }
}
