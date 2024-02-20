package sistemska_operacija.koordinator;

import db.DbBroker;
import domen.Koordinator;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import sistemska_operacija.OpstaSO;

public class SOUcitajListuKoordinatora extends OpstaSO {

    private List<OpstiDomenskiObjekat> koordinatori;

    public List<OpstiDomenskiObjekat> getKoordinatori() {
        return koordinatori;
    }

    @Override
    protected void izvrsiSpecificnuOperaciju() {
        koordinatori = DbBroker.getInstanca().getAllOpstiDomenskiObjekats(new Koordinator());
    }

}
