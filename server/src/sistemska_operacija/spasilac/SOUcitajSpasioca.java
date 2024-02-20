package sistemska_operacija.spasilac;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

public class SOUcitajSpasioca extends OpstaSO {

    private OpstiDomenskiObjekat spasilac;

    public SOUcitajSpasioca(OpstiDomenskiObjekat spasilac) {
        this.spasilac = spasilac;
    }

    @Override
    protected void izvrsiSpecificnuOperaciju() {
        spasilac = DbBroker.getInstanca().getOpstiDomenskiObjekatPoParametru(spasilac);
    }

    public OpstiDomenskiObjekat getSpasilac() {
        return spasilac;
    }

}
