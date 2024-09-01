package sistemska_operacija.izvestaj;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * Klasa koja predstavlja sistemsku operaciju za preuzimanje specificirane
 * {@link OpstiDomenskiObjekat} instance iz baze podataka.
 *
 * @author dulait
 */
public class SOUcitajIzvestaj extends OpstaSO {

    private OpstiDomenskiObjekat izvestaj;

    /**
     * Metoda koja kreira novu sistemsku operaciju za preuzimanje specificirane
     * {@link OpstiDomenskiObjekat}.
     *
     * @param izvestaj {@link OpstiDomenskiObjekat} instanca čiji se detalji
     * preuzimaju, koja se očekuje da bude tipa {@link domen.Izvestaj}
     */
    public SOUcitajIzvestaj(OpstiDomenskiObjekat izvestaj) {
        this.izvestaj = izvestaj;
    }

    /**
     * Metoda koja vraća preuzetu {@link OpstiDomenskiObjekat} instancu.
     *
     * @return {@link OpstiDomenskiObjekat} instanca sa detaljima popunjenim iz
     * baze podataka
     */
    public OpstiDomenskiObjekat getIzvestaj() {
        return izvestaj;
    }

    /**
     * Metoda koja izvršava specifičnu operaciju preuzimanja
     * {@link OpstiDomenskiObjekat} iz baze podataka.
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        izvestaj = DbBroker.getInstanca().getOpstiDomenskiObjekatPoParametru(izvestaj);
    }
}
