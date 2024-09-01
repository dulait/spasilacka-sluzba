package kontroleri;

import domen.Izvestaj;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import sistemska_operacija.izvestaj.SOAzurirajIzvestaj;
import sistemska_operacija.izvestaj.SOKreirajIzvestaj;
import sistemska_operacija.izvestaj.SOUcitajIzvestaj;
import sistemska_operacija.izvestaj.SOUcitajListuIzvestaja;

/**
 * Klasa koja pruža metode za upravljanje Izveštajima.
 *
 * @author dulait
 */
public class IzvestajKontroler {

    private static IzvestajKontroler instanca;

    private IzvestajKontroler() {
    }

    /**
     * Vraća instancu kontrolera.
     *
     * @return singleton instanca {@code IzvestajKontroler}
     */
    public static IzvestajKontroler getInstanca() {
        if (instanca == null) {
            instanca = new IzvestajKontroler();
        }
        return instanca;
    }

    /**
     * Vraća listu svih Izveštaja.
     *
     * @return lista {@code OpstiDomenskiObjekat} koji predstavljaju
     * {@code Izvestaj} objekte
     */
    public List<OpstiDomenskiObjekat> ucitajListuIzvestaja() {
        SOUcitajListuIzvestaja so = new SOUcitajListuIzvestaja();
        so.izvrsiSistemskuOperaciju();
        return so.getIzvestaji();
    }

    /**
     * Vraća specifičan Izveštaj.
     *
     * @param izvestaj objekat {@code Izvestaj} koji treba vratiti
     * @return {@code OpstiDomenskiObjekat} koji predstavlja pronađeni Izveštaj
     */
    public OpstiDomenskiObjekat ucitajIzvestaj(Izvestaj izvestaj) {
        SOUcitajIzvestaj so = new SOUcitajIzvestaj(izvestaj);
        so.izvrsiSistemskuOperaciju();
        return so.getIzvestaj();
    }

    /**
     * Kreira novi Izveštaj.
     *
     * @param izvestaj objekat {@code Izvestaj} koji treba kreirati
     * @return {@code true} ako je kreiranje uspešno, {@code false} inače
     */
    public boolean kreirajIzvestaj(Izvestaj izvestaj) {
        SOKreirajIzvestaj so = new SOKreirajIzvestaj(izvestaj);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }

    /**
     * Ažurira specifičan Izveštaj.
     *
     * @param izvestaj objekat {@code Izvestaj} koji treba ažurirati
     * @return {@code true} ako je ažuriranje uspešno, {@code false} inače
     */
    public boolean azurirajIzvestaj(Izvestaj izvestaj) {
        SOAzurirajIzvestaj so = new SOAzurirajIzvestaj(izvestaj);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }
}
