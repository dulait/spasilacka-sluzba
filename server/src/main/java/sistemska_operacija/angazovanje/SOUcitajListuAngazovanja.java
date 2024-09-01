package sistemska_operacija.angazovanje;

import db.DbBroker;
import domen.Angazovanje;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import sistemska_operacija.OpstaSO;

/**
 * Klasa koja predstavlja sistemsku operaciju za preuzimanje liste svih
 * {@link OpstiDomenskiObjekat} instanci tipa {@link Angazovanje} iz baze
 * podataka.
 *
 * @author dulait
 */
public class SOUcitajListuAngazovanja extends OpstaSO {

    private List<OpstiDomenskiObjekat> angazovanja;

    /**
     * Metoda koja vraća listu preuzetih objekata domena.
     *
     * @return lista svih {@link OpstiDomenskiObjekat} instanci tipa
     * {@link Angazovanje}
     */
    public List<OpstiDomenskiObjekat> getAngazovanja() {
        return angazovanja;
    }

    /**
     * Metoda koja izvršava specifičnu operaciju preuzimanja svih objekata
     * domena tipa {@link Angazovanje} iz baze podataka.
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        angazovanja = DbBroker.getInstanca().getAllOpstiDomenskiObjekats(new Angazovanje());
    }
}
