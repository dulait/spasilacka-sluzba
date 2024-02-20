package sistemska_operacija.raspored;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

public class SOUcitajRaspored extends OpstaSO {

    private OpstiDomenskiObjekat raspored;

    public SOUcitajRaspored(OpstiDomenskiObjekat raspored) {
        this.raspored = raspored;
    }

    @Override
    protected void izvrsiSpecificnuOperaciju() {
        raspored = DbBroker.getInstanca().getOpstiDomenskiObjekatPoParametru(raspored);
    }

    public OpstiDomenskiObjekat getRaspored() {
        return raspored;
    }

}
