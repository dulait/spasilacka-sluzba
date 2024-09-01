package sistemska_operacija.smena;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.Smena;
import java.util.List;
import sistemska_operacija.OpstaSO;

/**
 * Klasa koja predstavlja sistemsku operaciju za preuzimanje liste svih
 * {@link Smena} instanci iz baze.
 *
 * @author dulait
 */
public class SOUcitajListuSmena extends OpstaSO {

    private List<OpstiDomenskiObjekat> smene;

    /**
     * Vraća listu {@link OpstiDomenskiObjekat} instanci koje predstavljaju
     * {@link Smena}.
     *
     * @return {@link List} {@link OpstiDomenskiObjekat} instanci koje
     * predstavljaju sve {@link Smena} objekte u bazi.
     */
    public List<OpstiDomenskiObjekat> getSmene() {
        return smene;
    }

    /**
     * Izvršava specifičnu operaciju preuzimanja svih {@link Smena} instanci iz
     * baze.
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        smene = DbBroker.getInstanca().getAllOpstiDomenskiObjekats(new Smena());
    }

}
