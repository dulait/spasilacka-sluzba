package sistemska_operacija.angazovanje;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * Klasa koja predstavlja sistemsku operaciju za kreiranje Angažovanja.
 *
 * @author dulait
 */
public class SOKreirajAngazovanje extends OpstaSO {

    private final OpstiDomenskiObjekat angazovanje;
    private boolean uspeh;

    /**
     * Metoda koja kreira instancu sistemske operacije.
     *
     * @param angazovanje Angažovanje koje se koristi za sistemsku operaciju.
     */
    public SOKreirajAngazovanje(OpstiDomenskiObjekat angazovanje) {
        this.angazovanje = angazovanje;
    }

    /**
     * Metoda koja izvržava sistemsku operaciju.
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        uspeh = DbBroker.getInstanca().saveOpstiDomenskiObjekat(angazovanje);
    }

    /**
     * Metoda koja vraća ishod sistemske operacije
     *
     * @return {@code true} ako je sistemska operacija uspešno izvršena, inače
     * {@code false}
     */
    public boolean isUspeh() {
        return uspeh;
    }
}
