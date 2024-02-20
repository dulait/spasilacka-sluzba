package sistemska_operacija.angazovanje;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

public class SOObrisiAngazovanje extends OpstaSO {

    private final OpstiDomenskiObjekat angazovanje;
    private boolean uspeh;

    public SOObrisiAngazovanje(OpstiDomenskiObjekat angazovanje) {
        this.angazovanje = angazovanje;
    }

    @Override
    protected void izvrsiSpecificnuOperaciju() {
        uspeh = DbBroker.getInstanca().deleteOpstiDomenskiObjekat(angazovanje);
    }

    public boolean isUspeh() {
        return uspeh;
    }

}
