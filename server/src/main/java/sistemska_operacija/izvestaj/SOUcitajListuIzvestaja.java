package sistemska_operacija.izvestaj;

import db.DbBroker;
import domen.Izvestaj;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import sistemska_operacija.OpstaSO;

/**
 * Klasa koja predstavlja sistemsku operaciju za preuzimanje liste
 * {@link OpstiDomenskiObjekat} instanci iz baze podataka.
 *
 * @see OpstiDomenskiObjekat
 * @see DbBroker
 * @author dulait
 */
public class SOUcitajListuIzvestaja extends OpstaSO {

    private List<OpstiDomenskiObjekat> izvestaji;

    /**
     * Metoda koja vraća listu preuzetih {@link OpstiDomenskiObjekat} instanci.
     *
     * @return lista {@link OpstiDomenskiObjekat} instanci, specifično instanci
     * {@link domen.Izvestaj}, preuzetih iz baze podataka
     */
    public List<OpstiDomenskiObjekat> getIzvestaji() {
        return izvestaji;
    }

    /**
     * Metoda koja izvršava specifičnu operaciju preuzimanja svih
     * {@link OpstiDomenskiObjekat} instanci iz baze podataka.
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        izvestaji = DbBroker.getInstanca().getAllOpstiDomenskiObjekats(new Izvestaj());
    }
}
