package sistemska_operacija.spasilac;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.Spasilac;
import java.util.ArrayList;
import java.util.List;
import sistemska_operacija.OpstaSO;

public class SONadjiSpasioce extends OpstaSO {

    private List<OpstiDomenskiObjekat> rezultat;
    private final String kriterijum;

    public SONadjiSpasioce(String kriterijum) {
        this.kriterijum = kriterijum;
    }

    @Override
    protected void izvrsiSpecificnuOperaciju() {
        List<OpstiDomenskiObjekat> sviSpasioci = DbBroker.getInstanca().getAllOpstiDomenskiObjekats(new Spasilac());
        rezultat = new ArrayList<>();

        for (OpstiDomenskiObjekat odo : sviSpasioci) {
            Spasilac s = (Spasilac) odo;
            if (sadrziKriterijum(s)) {
                rezultat.add(s);
            }
        }
    }

    public List<OpstiDomenskiObjekat> getRezultat() {
        return rezultat;
    }

    private boolean sadrziKriterijum(Spasilac s) {
        return s.getIme().toLowerCase().contains(kriterijum.toLowerCase())
                || s.getPrezime().toLowerCase().contains(kriterijum.toLowerCase());
    }

}
