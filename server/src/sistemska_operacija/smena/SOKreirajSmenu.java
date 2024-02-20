package sistemska_operacija.smena;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

public class SOKreirajSmenu extends OpstaSO {

    private OpstiDomenskiObjekat smena;
    private boolean uspeh;

    public SOKreirajSmenu(OpstiDomenskiObjekat smena) {
        this.smena = smena;
    }

    public boolean isUspeh() {
        return uspeh;
    }

    @Override
    protected void izvrsiSpecificnuOperaciju() {
        uspeh = DbBroker.getInstanca().saveOpstiDomenskiObjekat(smena);
    }

}
