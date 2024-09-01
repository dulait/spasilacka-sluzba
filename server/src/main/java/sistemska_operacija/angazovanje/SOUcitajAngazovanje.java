package sistemska_operacija.angazovanje;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * Klasa koja predstavlja sistemsku operaciju za preuzimanje određene
 * {@link OpstiDomenskiObjekat} instance iz baze podataka na osnovu pruženih
 * parametara.
 *
 * @author dulait
 */
public class SOUcitajAngazovanje extends OpstaSO {

    private OpstiDomenskiObjekat angazovanje;

    /**
     * Konstruktor za novu {@code SOUcitajAngazovanje} instancu sa
     * specificiranim objektom domena koji sadrži parametre za preuzimanje.
     *
     * @param angazovanje objekat domena koji sadrži parametre za upit
     * preuzimanja
     */
    public SOUcitajAngazovanje(OpstiDomenskiObjekat angazovanje) {
        this.angazovanje = angazovanje;
    }

    /**
     * Metoda koja izvršava specifičnu operaciju preuzimanja objekta domena iz
     * baze podataka na osnovu pruženih parametara.
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        angazovanje = DbBroker.getInstanca().getOpstiDomenskiObjekatPoParametru(angazovanje);
    }

    /**
     * Metoda koja vraća preuzeti objekat domena.
     *
     * @return objekat domena preuzet iz baze podataka
     */
    public OpstiDomenskiObjekat getAngazovanje() {
        return angazovanje;
    }
}
