package sistemska_operacija.angazovanje;

import db.DbBroker;
import domen.Angazovanje;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import sistemska_operacija.OpstaSO;

public class SOUcitajListuAngazovanja extends OpstaSO {

    private List<OpstiDomenskiObjekat> angazovanja;

    public List<OpstiDomenskiObjekat> getAngazovanja() {
        return angazovanja;
    }

    @Override
    protected void izvrsiSpecificnuOperaciju() {
        angazovanja = DbBroker.getInstanca().getAllOpstiDomenskiObjekats(new Angazovanje());
    }

}
