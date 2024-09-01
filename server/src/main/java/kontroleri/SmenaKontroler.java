package kontroleri;

import domen.OpstiDomenskiObjekat;
import domen.Smena;
import java.util.List;
import sistemska_operacija.smena.SOAzurirajSmenu;
import sistemska_operacija.smena.SOKreirajSmenu;
import sistemska_operacija.smena.SOUcitajListuSmena;
import sistemska_operacija.smena.SOUcitajSmenu;

/**
 * Klasa koja pruža metode za upravljanje Smenama.
 *
 * @author dulait
 */
public class SmenaKontroler {

    private static SmenaKontroler instanca;

    private SmenaKontroler() {
    }

    /**
     * Vraća instancu kontrolera.
     *
     * @return singleton instanca {@code SmenaKontroler}
     */
    public static SmenaKontroler getInstanca() {
        if (instanca == null) {
            instanca = new SmenaKontroler();
        }
        return instanca;
    }

    /**
     * Vraća listu svih Smena.
     *
     * @return lista {@code OpstiDomenskiObjekat} koji predstavljaju Smena
     * objekte
     */
    public List<OpstiDomenskiObjekat> ucitajListuSmena() {
        SOUcitajListuSmena so = new SOUcitajListuSmena();
        so.izvrsiSistemskuOperaciju();
        return so.getSmene();
    }

    /**
     * Vraća specifičnu Smenu.
     *
     * @param smena objekat {@code Smena} koji treba vratiti
     * @return {@code OpstiDomenskiObjekat} koji predstavlja pronađenu Smenu
     */
    public OpstiDomenskiObjekat ucitajSmenu(Smena smena) {
        SOUcitajSmenu so = new SOUcitajSmenu(smena);
        so.izvrsiSistemskuOperaciju();
        return so.getSmena();
    }

    /**
     * Kreira novu Smenu.
     *
     * @param smena objekat {@code Smena} koji treba kreirati
     * @return {@code true} ako je kreiranje uspešno, {@code false} inače
     */
    public boolean kreirajSmenu(Smena smena) {
        SOKreirajSmenu so = new SOKreirajSmenu(smena);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }

    /**
     * Ažurira postojeću Smenu.
     *
     * @param smena objekat {@code Smena} koji treba ažurirati
     * @return {@code true} ako je ažuriranje uspešno, {@code false} inače
     */
    public boolean azurirajSmenu(Smena smena) {
        SOAzurirajSmenu so = new SOAzurirajSmenu(smena);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }

}
