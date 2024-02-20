package sistemska_operacija.angazovanje;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

public class SOUcitajAngazovanje extends OpstaSO {

    private OpstiDomenskiObjekat angazovanje;

    public SOUcitajAngazovanje(OpstiDomenskiObjekat angazovanje) {
        this.angazovanje = angazovanje;
    }

    @Override
    protected void izvrsiSpecificnuOperaciju() {
        angazovanje = DbBroker.getInstanca().getOpstiDomenskiObjekatPoParametru(angazovanje);
    }

    public OpstiDomenskiObjekat getAngazovanje() {
        return angazovanje;
    }

}
