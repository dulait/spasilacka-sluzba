package sistemska_operacija.izvestaj;

import db.DbBroker;
import domen.Izvestaj;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import sistemska_operacija.OpstaSO;

public class SOUcitajListuIzvestaja extends OpstaSO {

    private List<OpstiDomenskiObjekat> izvestaji;

    public List<OpstiDomenskiObjekat> getIzvestaji() {
        return izvestaji;
    }

    @Override
    protected void izvrsiSpecificnuOperaciju() {
        izvestaji = DbBroker.getInstanca().getAllOpstiDomenskiObjekats(new Izvestaj());
    }

}
