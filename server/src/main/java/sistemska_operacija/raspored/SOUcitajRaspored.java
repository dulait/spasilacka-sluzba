package sistemska_operacija.raspored;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * Klasa koja predstavlja sistemsku operaciju za preuzimanje specifične
 * {@link OpstiDomenskiObjekat} instance koja predstavlja {@link Raspored} iz
 * baze podataka.
 *
 * @author dulait
 */
public class SOUcitajRaspored extends OpstaSO {

    private OpstiDomenskiObjekat raspored;

    /**
     * Konstruktor za sistemsku operaciju preuzimanja specifičnog
     * {@link Raspored}.
     *
     * @param raspored {@link OpstiDomenskiObjekat} instanca koja predstavlja
     * {@link Raspored} koji treba preuzeti.
     */
    public SOUcitajRaspored(OpstiDomenskiObjekat raspored) {
        this.raspored = raspored;
    }

    /**
     * Izvršava specifičnu operaciju preuzimanja {@link Raspored} instance iz
     * baze podataka.
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        raspored = DbBroker.getInstanca().getOpstiDomenskiObjekatPoParametru(raspored);
    }

    /**
     * Vraća {@link OpstiDomenskiObjekat} instancu koja predstavlja
     * {@link Raspored} preuzetu iz baze podataka.
     *
     * @return {@link OpstiDomenskiObjekat} instanca koja predstavlja
     * {@link Raspored}
     */
    public OpstiDomenskiObjekat getRaspored() {
        return raspored;
    }

}
