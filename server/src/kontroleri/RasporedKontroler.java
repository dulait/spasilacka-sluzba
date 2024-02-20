package kontroleri;

import domen.OpstiDomenskiObjekat;
import domen.Raspored;
import java.util.List;
import sistemska_operacija.raspored.SOKreirajRaspored;
import sistemska_operacija.raspored.SOObrisiRaspored;
import sistemska_operacija.raspored.SOUcitajListuRasporeda;
import sistemska_operacija.raspored.SOUcitajRaspored;

public class RasporedKontroler {

    private static RasporedKontroler instanca;

    private RasporedKontroler() {
    }

    public static RasporedKontroler getInstanca() {
        if (instanca == null) {
            instanca = new RasporedKontroler();
        }
        return instanca;
    }

    public List<OpstiDomenskiObjekat> ucitajListuRasporeda() {
        SOUcitajListuRasporeda so = new SOUcitajListuRasporeda();
        so.izvrsiSistemskuOperaciju();
        return so.getRasporedi();
    }

    public OpstiDomenskiObjekat ucitajRaspored(Raspored raspored) {
        SOUcitajRaspored so = new SOUcitajRaspored(raspored);
        so.izvrsiSistemskuOperaciju();
        return so.getRaspored();
    }

    public boolean kreirajRaspored(Raspored raspored) {
        SOKreirajRaspored so = new SOKreirajRaspored(raspored);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }

    public boolean obrisiRaspored(Raspored raspored) {
        SOObrisiRaspored so = new SOObrisiRaspored(raspored);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }

}
