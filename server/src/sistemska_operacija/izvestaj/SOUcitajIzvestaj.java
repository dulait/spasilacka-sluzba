package sistemska_operacija.izvestaj;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

public class SOUcitajIzvestaj extends OpstaSO {

    private OpstiDomenskiObjekat izvestaj;

    public SOUcitajIzvestaj(OpstiDomenskiObjekat izvestaj) {
        this.izvestaj = izvestaj;
    }

    public OpstiDomenskiObjekat getIzvestaj() {
        return izvestaj;
    }

    @Override
    protected void izvrsiSpecificnuOperaciju() {
        izvestaj = DbBroker.getInstanca().getOpstiDomenskiObjekatPoParametru(izvestaj);
    }

}
