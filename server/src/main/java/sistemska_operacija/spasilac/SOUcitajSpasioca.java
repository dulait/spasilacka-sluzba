package sistemska_operacija.spasilac;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * Klasa koja predstavlja sistemsku operaciju za preuzimanje specifične
 * {@link OpstiDomenskiObjekat} instance koja predstavlja {@link domen.Spasilac}
 * iz baze podataka.
 *
 * @author drask
 */
public class SOUcitajSpasioca extends OpstaSO {

    private OpstiDomenskiObjekat spasilac;

    /**
     * Konstruira novu {@code SOUcitajSpasioca} sa specificiranom
     * {@link OpstiDomenskiObjekat} instancom koja treba da se preuzme.
     *
     * @param spasilac {@link OpstiDomenskiObjekat} koji predstavlja
     * {@link domen.Spasilac} koji treba da se preuzme.
     */
    public SOUcitajSpasioca(OpstiDomenskiObjekat spasilac) {
        this.spasilac = spasilac;
    }

    /**
     * Izvršava specifičnu operaciju preuzimanja {@link OpstiDomenskiObjekat}
     * instance iz baze podataka.
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        spasilac = DbBroker.getInstanca().getOpstiDomenskiObjekatPoParametru(spasilac);
    }

    /**
     * Vraća preuzetu {@link OpstiDomenskiObjekat} instancu koja predstavlja
     * {@link domen.Spasilac}.
     *
     * @return {@link OpstiDomenskiObjekat} instanca koja predstavlja preuzetu
     * {@link domen.Spasilac}.
     */
    public OpstiDomenskiObjekat getSpasilac() {
        return spasilac;
    }

}
