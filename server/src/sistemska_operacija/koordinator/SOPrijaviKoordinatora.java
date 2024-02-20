package sistemska_operacija.koordinator;

import db.DbBroker;
import domen.Koordinator;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import sistemska_operacija.OpstaSO;

public class SOPrijaviKoordinatora extends OpstaSO {

    private OpstiDomenskiObjekat koordinator;
    private OpstiDomenskiObjekat prijavljeni;

    public SOPrijaviKoordinatora(OpstiDomenskiObjekat koordinator) {
        this.koordinator = koordinator;
        prijavljeni = null;
    }

    @Override
    protected void izvrsiSpecificnuOperaciju() {
        List<OpstiDomenskiObjekat> koordinatori = DbBroker.getInstanca().getAllOpstiDomenskiObjekats(koordinator);
        Koordinator uneti = (Koordinator) koordinator;

        for (OpstiDomenskiObjekat odo : koordinatori) {
            Koordinator k = (Koordinator) odo;
            if (k.getKorisnickoIme().equals(uneti.getKorisnickoIme()) && k.getLozinka().equals(uneti.getLozinka())) {
                this.prijavljeni = k;
            }
        }
    }

    public OpstiDomenskiObjekat getKoordinator() {
        return prijavljeni;
    }

}
