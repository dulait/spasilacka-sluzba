package sistemska_operacija.spasilac;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * Klasa koja predstavlja sistemsku operaciju za ažuriranje
 * {@link OpstiDomenskiObjekat} instance koja predstavlja {@link Spasilac} u
 * bazi podataka.
 *
 * @author dulait
 */
public class SOAzurirajSpasioca extends OpstaSO {

    private final OpstiDomenskiObjekat spasilac;
    private boolean uspeh;

    /**
     * Konstruktor za kreiranje nove sistemske operacije za ažuriranje
     * specifične {@link Spasilac} instance.
     *
     * @param spasilac {@link OpstiDomenskiObjekat} koji predstavlja
     * {@link Spasilac} koji treba da bude ažuriran.
     */
    public SOAzurirajSpasioca(OpstiDomenskiObjekat spasilac) {
        this.spasilac = spasilac;
    }

    /**
     * Vraća {@code true} ako je operacija ažuriranja bila uspešna;
     * {@code false} u suprotnom.
     *
     * @return {@code true} ako je operacija ažuriranja bila uspešna,
     * {@code false} u suprotnom.
     */
    public boolean isUspeh() {
        return uspeh;
    }

    /**
     * Izvršava specifičnu operaciju ažuriranja {@link Spasilac} instance u bazi
     * podataka.
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        uspeh = DbBroker.getInstanca().updateOpstiDomenskiObjekat(spasilac);
    }

}
