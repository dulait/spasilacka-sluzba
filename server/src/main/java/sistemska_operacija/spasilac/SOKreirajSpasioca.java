package sistemska_operacija.spasilac;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * Klasa koja predstavlja sistemsku operaciju za kreiranje nove
 * {@link OpstiDomenskiObjekat} instance koja predstavlja {@link Spasilac} u
 * bazi.
 *
 * @author dulait
 */
public class SOKreirajSpasioca extends OpstaSO {

    private final OpstiDomenskiObjekat spasilac;
    private boolean uspeh = false;

    /**
     * Konstruktor za kreiranje nove sistemske operacije za kreiranje specifične
     * {@link Spasilac} instance.
     *
     * @param spasilac {@link OpstiDomenskiObjekat} koji predstavlja
     * {@link Spasilac} koji treba da bude kreiran.
     */
    public SOKreirajSpasioca(OpstiDomenskiObjekat spasilac) {
        this.spasilac = spasilac;
    }

    /**
     * Vraća {@code true} ako je operacija kreiranja bila uspešna; {@code false}
     * u suprotnom.
     *
     * @return {@code true} ako je operacija kreiranja bila uspešna,
     * {@code false} u suprotnom.
     */
    public boolean isUspeh() {
        return uspeh;
    }

    /**
     * Izvršava specifičnu operaciju kreiranja {@link Spasilac} instance u bazi.
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        uspeh = DbBroker.getInstanca().saveOpstiDomenskiObjekat(spasilac);
    }

}
