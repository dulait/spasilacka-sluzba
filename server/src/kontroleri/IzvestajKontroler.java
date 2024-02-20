package kontroleri;

import domen.Izvestaj;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import sistemska_operacija.izvestaj.SOAzurirajIzvestaj;
import sistemska_operacija.izvestaj.SOKreirajIzvestaj;
import sistemska_operacija.izvestaj.SOUcitajIzvestaj;
import sistemska_operacija.izvestaj.SOUcitajListuIzvestaja;

public class IzvestajKontroler {

    private static IzvestajKontroler instanca;

    private IzvestajKontroler() {
    }

    public static IzvestajKontroler getInstanca() {
        if (instanca == null) {
            instanca = new IzvestajKontroler();
        }
        return instanca;
    }

    public List<OpstiDomenskiObjekat> ucitajListuIzvestaja() {
        SOUcitajListuIzvestaja so = new SOUcitajListuIzvestaja();
        so.izvrsiSistemskuOperaciju();
        return so.getIzvestaji();
    }

    public OpstiDomenskiObjekat ucitajIzvestaj(Izvestaj izvestaj) {
        SOUcitajIzvestaj so = new SOUcitajIzvestaj(izvestaj);
        so.izvrsiSistemskuOperaciju();
        return so.getIzvestaj();
    }

    public boolean kreirajIzvestaj(Izvestaj izvestaj) {
        SOKreirajIzvestaj so = new SOKreirajIzvestaj(izvestaj);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }

    public boolean azurirajIzvestaj(Izvestaj izvestaj) {
        SOAzurirajIzvestaj so = new SOAzurirajIzvestaj(izvestaj);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }

}
