package kontroleri;

import domen.OpstiDomenskiObjekat;
import domen.Smena;
import java.util.List;
import sistemska_operacija.smena.SOAzurirajSmenu;
import sistemska_operacija.smena.SOKreirajSmenu;
import sistemska_operacija.smena.SOUcitajListuSmena;
import sistemska_operacija.smena.SOUcitajSmenu;

public class SmenaKontroler {

    private static SmenaKontroler instanca;

    private SmenaKontroler() {
    }

    public static SmenaKontroler getInstanca() {
        if (instanca == null) {
            instanca = new SmenaKontroler();
        }
        return instanca;
    }

    public List<OpstiDomenskiObjekat> ucitajListuSmena() {
        SOUcitajListuSmena so = new SOUcitajListuSmena();
        so.izvrsiSistemskuOperaciju();
        return so.getSmene();
    }

    public OpstiDomenskiObjekat ucitajSmenu(Smena smena) {
        SOUcitajSmenu so = new SOUcitajSmenu(smena);
        so.izvrsiSistemskuOperaciju();
        return so.getSmena();
    }

    public boolean kreirajSmenu(Smena smena) {
        SOKreirajSmenu so = new SOKreirajSmenu(smena);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }

    public boolean azurirajSmenu(Smena smena) {
        SOAzurirajSmenu so = new SOAzurirajSmenu(smena);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }

}
