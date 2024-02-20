package sistemska_operacija.smena;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

public class SOUcitajSmenu extends OpstaSO {

    private OpstiDomenskiObjekat smena;

    public SOUcitajSmenu(OpstiDomenskiObjekat smena) {
        this.smena = smena;
    }

    @Override
    protected void izvrsiSpecificnuOperaciju() {
        smena = DbBroker.getInstanca().getOpstiDomenskiObjekatPoParametru(smena);
    }

    public OpstiDomenskiObjekat getSmena() {
        return smena;
    }

}
