package kontroleri;

import domen.OpstiDomenskiObjekat;
import domen.Spasilac;
import java.util.List;
import sistemska_operacija.spasilac.SOAzurirajSpasioca;
import sistemska_operacija.spasilac.SOKreirajSpasioca;
import sistemska_operacija.spasilac.SONadjiSpasioce;
import sistemska_operacija.spasilac.SOUcitajListuSpasioca;
import sistemska_operacija.spasilac.SOUcitajSpasioca;

public class SpasilacKontroler {

    private static SpasilacKontroler instanca;

    private SpasilacKontroler() {
    }

    public static SpasilacKontroler getInstanca() {
        if (instanca == null) {
            instanca = new SpasilacKontroler();
        }
        return instanca;
    }

    public List<OpstiDomenskiObjekat> ucitajListuSpasioca() {
        SOUcitajListuSpasioca so = new SOUcitajListuSpasioca();
        so.izvrsiSistemskuOperaciju();
        return so.getSpasioci();
    }

    public OpstiDomenskiObjekat ucitajSpasioca(Spasilac spasilac) {
        SOUcitajSpasioca so = new SOUcitajSpasioca(spasilac);
        so.izvrsiSistemskuOperaciju();
        return so.getSpasilac();
    }

    public boolean kreirajSpasioca(Spasilac spasilac) {
        SOKreirajSpasioca so = new SOKreirajSpasioca(spasilac);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }

    public boolean azurirajSpasioca(Spasilac spasilac) {
        SOAzurirajSpasioca so = new SOAzurirajSpasioca(spasilac);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }

    public List<OpstiDomenskiObjekat> pretraziSpasioce(String kriterijum) {
        SONadjiSpasioce so = new SONadjiSpasioce(kriterijum);
        so.izvrsiSistemskuOperaciju();
        return so.getRezultat();
    }

}
