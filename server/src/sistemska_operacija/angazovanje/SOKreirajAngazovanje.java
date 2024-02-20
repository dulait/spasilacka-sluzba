package sistemska_operacija.angazovanje;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

public class SOKreirajAngazovanje extends OpstaSO {

    private final OpstiDomenskiObjekat angazovanje;
    private boolean uspeh;

    public SOKreirajAngazovanje(OpstiDomenskiObjekat angazovanje) {
        this.angazovanje = angazovanje;
    }

    @Override
    protected void izvrsiSpecificnuOperaciju() {
        uspeh = DbBroker.getInstanca().saveOpstiDomenskiObjekat(angazovanje);
    }

    public boolean isUspeh() {
        return uspeh;
    }

}
