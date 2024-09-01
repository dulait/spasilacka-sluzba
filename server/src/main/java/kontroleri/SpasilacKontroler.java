package kontroleri;

import domen.OpstiDomenskiObjekat;
import domen.Spasilac;
import java.util.List;
import sistemska_operacija.spasilac.SOAzurirajSpasioca;
import sistemska_operacija.spasilac.SOKreirajSpasioca;
import sistemska_operacija.spasilac.SONadjiSpasioce;
import sistemska_operacija.spasilac.SOUcitajListuSpasioca;
import sistemska_operacija.spasilac.SOUcitajSpasioca;

/**
 * Klasa koja pruža metode za upravljanje Spasiocima.
 *
 * @author dulait
 */
public class SpasilacKontroler {

    private static SpasilacKontroler instanca;

    private SpasilacKontroler() {
    }

    /**
     * Vraća instancu kontrolera.
     *
     * @return singleton instanca {@code SpasilacKontroler}
     */
    public static SpasilacKontroler getInstanca() {
        if (instanca == null) {
            instanca = new SpasilacKontroler();
        }
        return instanca;
    }

    /**
     * Vraća listu svih Spasilaca.
     *
     * @return lista {@code OpstiDomenskiObjekat} koji predstavljaju Spasilac
     * objekte
     */
    public List<OpstiDomenskiObjekat> ucitajListuSpasioca() {
        SOUcitajListuSpasioca so = new SOUcitajListuSpasioca();
        so.izvrsiSistemskuOperaciju();
        return so.getSpasioci();
    }

    /**
     * Vraća specifičnog Spasioca.
     *
     * @param spasilac objekat {@code Spasilac} koji treba vratiti
     * @return {@code OpstiDomenskiObjekat} koji predstavlja pronađenog Spasioca
     */
    public OpstiDomenskiObjekat ucitajSpasioca(Spasilac spasilac) {
        SOUcitajSpasioca so = new SOUcitajSpasioca(spasilac);
        so.izvrsiSistemskuOperaciju();
        return so.getSpasilac();
    }

    /**
     * Kreira novog Spasioca.
     *
     * @param spasilac objekat {@code Spasilac} koji treba kreirati
     * @return {@code true} ako je kreiranje uspešno, {@code false} inače
     */
    public boolean kreirajSpasioca(Spasilac spasilac) {
        SOKreirajSpasioca so = new SOKreirajSpasioca(spasilac);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }

    /**
     * Ažurira postojećeg Spasioca.
     *
     * @param spasilac objekat {@code Spasilac} koji treba ažurirati
     * @return {@code true} ako je ažuriranje uspešno, {@code false} inače
     */
    public boolean azurirajSpasioca(Spasilac spasilac) {
        SOAzurirajSpasioca so = new SOAzurirajSpasioca(spasilac);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }

    /**
     * Pretražuje Spasioce na osnovu kriterijuma.
     *
     * @param kriterijum kriterijum pretrage za Spasilac objekte
     * @return lista {@code OpstiDomenskiObjekat} koja predstavlja rezultate
     * pretrage
     */
    public List<OpstiDomenskiObjekat> pretraziSpasioce(String kriterijum) {
        SONadjiSpasioce so = new SONadjiSpasioce(kriterijum);
        so.izvrsiSistemskuOperaciju();
        return so.getRezultat();
    }

}
