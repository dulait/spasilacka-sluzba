package kontroleri;

import domen.Koordinator;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import sistemska_operacija.koordinator.SOPrijaviKoordinatora;
import sistemska_operacija.koordinator.SOUcitajKoordinatora;
import sistemska_operacija.koordinator.SOUcitajListuKoordinatora;

public class KoordinatorKontroler {

    private static KoordinatorKontroler instanca;

    private KoordinatorKontroler() {
    }

    public static KoordinatorKontroler getInstanca() {
        if (instanca == null) {
            instanca = new KoordinatorKontroler();
        }
        return instanca;
    }

    public OpstiDomenskiObjekat prijaviKoordinatora(Koordinator koordinator) {
        SOPrijaviKoordinatora so = new SOPrijaviKoordinatora(koordinator);
        so.izvrsiSistemskuOperaciju();
        return so.getKoordinator();
    }

    public OpstiDomenskiObjekat ucitajKoordinatora(Koordinator koordinator) {
        SOUcitajKoordinatora so = new SOUcitajKoordinatora(koordinator);
        so.izvrsiSistemskuOperaciju();
        return so.getKoordinator();
    }

    public List<OpstiDomenskiObjekat> ucitajListuKoordinatora() {
        SOUcitajListuKoordinatora so = new SOUcitajListuKoordinatora();
        so.izvrsiSistemskuOperaciju();
        return so.getKoordinatori();
    }
}
