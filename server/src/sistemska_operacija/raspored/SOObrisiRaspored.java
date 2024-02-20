package sistemska_operacija.raspored;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

public class SOObrisiRaspored extends OpstaSO {

    private final OpstiDomenskiObjekat raspored;
    private boolean uspeh;

    public SOObrisiRaspored(OpstiDomenskiObjekat raspored) {
        this.raspored = raspored;
    }

    @Override
    protected void izvrsiSpecificnuOperaciju() {
        uspeh = DbBroker.getInstanca().deleteOpstiDomenskiObjekat(raspored);
    }

    public boolean isUspeh() {
        return uspeh;
    }

}
