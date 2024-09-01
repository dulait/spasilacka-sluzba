package sistemska_operacija.raspored;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.Raspored;
import java.util.List;
import sistemska_operacija.OpstaSO;

/**
 * Klasa koja predstavlja sistemsku operaciju za preuzimanje liste svih
 * {@link Raspored} instanci iz baze podataka.
 *
 * @author dulait
 */
public class SOUcitajListuRasporeda extends OpstaSO {

    private List<OpstiDomenskiObjekat> rasporedi;

    /**
     * Vraća listu {@link OpstiDomenskiObjekat} instanci koje predstavljaju
     * {@link Raspored} preuzete iz baze podataka.
     *
     * @return lista {@link OpstiDomenskiObjekat} instanci
     */
    public List<OpstiDomenskiObjekat> getRasporedi() {
        return rasporedi;
    }

    /**
     * Izvršava specifičnu operaciju preuzimanja liste svih {@link Raspored}
     * instanci iz baze podataka.
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        rasporedi = DbBroker.getInstanca().getAllOpstiDomenskiObjekats(new Raspored());
    }

}
