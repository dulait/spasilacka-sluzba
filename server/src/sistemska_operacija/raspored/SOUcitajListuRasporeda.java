package sistemska_operacija.raspored;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.Raspored;
import java.util.List;
import sistemska_operacija.OpstaSO;

public class SOUcitajListuRasporeda extends OpstaSO {

    private List<OpstiDomenskiObjekat> rasporedi;

    public List<OpstiDomenskiObjekat> getRasporedi() {
        return rasporedi;
    }

    @Override
    protected void izvrsiSpecificnuOperaciju() {
        rasporedi = DbBroker.getInstanca().getAllOpstiDomenskiObjekats(new Raspored());
    }

}
