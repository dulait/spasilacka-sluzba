package sistemska_operacija.koordinator;

import db.DbBroker;
import domen.Koordinator;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import sistemska_operacija.OpstaSO;

/**
 * Klasa koja predstavlja sistemsku operaciju za preuzimanje liste svih
 * {@link Koordinator} instanci iz baze.
 *
 * @author dulait
 */
public class SOUcitajListuKoordinatora extends OpstaSO {

    private List<OpstiDomenskiObjekat> koordinatori;

    /**
     * Metoda koja vraća listu {@link OpstiDomenskiObjekat} instanci koje
     * predstavljaju sve preuzete {@link Koordinator} entitete.
     *
     * @return lista {@link OpstiDomenskiObjekat} instanci koje predstavljaju
     * preuzete {@link Koordinator} instance
     */
    public List<OpstiDomenskiObjekat> getKoordinatori() {
        return koordinatori;
    }

    /**
     * Metoda koja izvršava specifičnu operaciju preuzimanja svih
     * {@link Koordinator} instanci iz baze.
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        koordinatori = DbBroker.getInstanca().getAllOpstiDomenskiObjekats(new Koordinator());
    }

}
