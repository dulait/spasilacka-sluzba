package sistemska_operacija.koordinator;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

public class SOUcitajKoordinatora extends OpstaSO {

    private OpstiDomenskiObjekat koordinator;

    public SOUcitajKoordinatora(OpstiDomenskiObjekat koordinator) {
        this.koordinator = koordinator;
    }

    public OpstiDomenskiObjekat getKoordinator() {
        return koordinator;
    }

    @Override
    protected void izvrsiSpecificnuOperaciju() {
        koordinator = DbBroker.getInstanca().getOpstiDomenskiObjekatPoParametru(koordinator);
    }

}
