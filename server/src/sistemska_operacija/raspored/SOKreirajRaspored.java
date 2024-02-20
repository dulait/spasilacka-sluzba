package sistemska_operacija.raspored;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

public class SOKreirajRaspored extends OpstaSO {

    private final OpstiDomenskiObjekat raspored;
    private boolean uspeh;

    public SOKreirajRaspored(OpstiDomenskiObjekat raspored) {
        this.raspored = raspored;
    }

    public boolean isUspeh() {
        return uspeh;
    }

    @Override
    protected void izvrsiSpecificnuOperaciju() {
        uspeh = DbBroker.getInstanca().saveOpstiDomenskiObjekat(raspored);
    }

}
