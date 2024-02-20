package sistemska_operacija.smena;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.Smena;
import java.util.List;
import sistemska_operacija.OpstaSO;

public class SOUcitajListuSmena extends OpstaSO {

    private List<OpstiDomenskiObjekat> smene;

    public List<OpstiDomenskiObjekat> getSmene() {
        return smene;
    }

    @Override
    protected void izvrsiSpecificnuOperaciju() {
        smene = DbBroker.getInstanca().getAllOpstiDomenskiObjekats(new Smena());
    }

}
