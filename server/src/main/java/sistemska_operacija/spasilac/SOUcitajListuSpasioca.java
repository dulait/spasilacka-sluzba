package sistemska_operacija.spasilac;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.Spasilac;
import java.util.List;
import sistemska_operacija.OpstaSO;

/**
 * Klasa koja predstavlja sistemsku operaciju za preuzimanje liste svih
 * {@link Spasilac} instanci iz baze podataka.
 *
 * @author dulait
 */
public class SOUcitajListuSpasioca extends OpstaSO {

    private List<OpstiDomenskiObjekat> spasioci;

    /**
     * Vraća listu svih {@link OpstiDomenskiObjekat} instanci koje predstavljaju
     * preuzete {@link Spasilac} zapise.
     *
     * @return lista svih {@link OpstiDomenskiObjekat} instanci koje
     * predstavljaju preuzete {@link Spasilac} zapise.
     */
    public List<OpstiDomenskiObjekat> getSpasioci() {
        return spasioci;
    }

    /**
     * Izvršava specifičnu operaciju preuzimanja svih {@link Spasilac} instanci
     * iz baze podataka.
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        spasioci = DbBroker.getInstanca().getAllOpstiDomenskiObjekats(new Spasilac());
    }

}
