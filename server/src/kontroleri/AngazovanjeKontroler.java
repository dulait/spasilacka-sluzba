package kontroleri;

import domen.Angazovanje;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import sistemska_operacija.angazovanje.SOKreirajAngazovanje;
import sistemska_operacija.angazovanje.SOObrisiAngazovanje;
import sistemska_operacija.angazovanje.SOUcitajAngazovanje;
import sistemska_operacija.angazovanje.SOUcitajListuAngazovanja;

public class AngazovanjeKontroler {

    private static AngazovanjeKontroler instanca;

    private AngazovanjeKontroler() {
    }

    public static AngazovanjeKontroler getInstanca() {
        if (instanca == null) {
            instanca = new AngazovanjeKontroler();
        }
        return instanca;
    }

    public List<OpstiDomenskiObjekat> ucitajListuAngazovanja() {
        SOUcitajListuAngazovanja so = new SOUcitajListuAngazovanja();
        so.izvrsiSistemskuOperaciju();
        return so.getAngazovanja();
    }

    public OpstiDomenskiObjekat ucitajAngazovanje(Angazovanje angazovanje) {
        SOUcitajAngazovanje so = new SOUcitajAngazovanje(angazovanje);
        so.izvrsiSistemskuOperaciju();
        return so.getAngazovanje();
    }

    public boolean kreirajAngazovanje(Angazovanje angazovanje) {
        SOKreirajAngazovanje so = new SOKreirajAngazovanje(angazovanje);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }

    public boolean obrisiAngazovanje(Angazovanje angazovanje) {
        SOObrisiAngazovanje so = new SOObrisiAngazovanje(angazovanje);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }

}
