package sistemska_operacija.smena;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * Klasa koja predstavlja sistemsku operaciju za kreiranje nove
 * {@link OpstiDomenskiObjekat} instance koja predstavlja {@link Smena} u bazi.
 *
 * @author dulait
 */
public class SOKreirajSmenu extends OpstaSO {

    private final OpstiDomenskiObjekat smena;
    private boolean uspeh;

    /**
     * Konstruktor za sistemsku operaciju koja kreira specifičnu {@link Smena}.
     *
     * @param smena {@link OpstiDomenskiObjekat} instanca koja predstavlja
     * {@link Smena} koja treba biti kreirana.
     */
    public SOKreirajSmenu(OpstiDomenskiObjekat smena) {
        this.smena = smena;
    }

    /**
     * Vraća da li je operacija kreiranja bila uspešna.
     *
     * @return {@code true} ako je kreiranje bilo uspešno, {@code false} inače.
     */
    public boolean isUspeh() {
        return uspeh;
    }

    /**
     * Izvršava specifičnu operaciju kreiranja {@link Smena} instance u bazi.
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        uspeh = DbBroker.getInstanca().saveOpstiDomenskiObjekat(smena);
    }

}
