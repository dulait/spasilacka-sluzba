package sistemska_operacija.smena;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * Klasa koja predstavlja sistemsku operaciju za ažuriranje postojeće
 * {@link OpstiDomenskiObjekat} instance koja predstavlja {@link Smena} u bazi.
 *
 * @author dulait
 */
public class SOAzurirajSmenu extends OpstaSO {

    private final OpstiDomenskiObjekat smena;
    private boolean uspeh;

    /**
     * Konstruktor za sistemsku operaciju ažuriranja specifične {@link Smena}.
     *
     * @param smena {@link OpstiDomenskiObjekat} instanca koja predstavlja
     * {@link Smena} koja treba biti ažurirana.
     */
    public SOAzurirajSmenu(OpstiDomenskiObjekat smena) {
        this.smena = smena;
    }

    /**
     * Vraća da li je operacija ažuriranja bila uspešna.
     *
     * @return {@code true} ako je ažuriranje bilo uspešno, {@code false} inače.
     */
    public boolean isUspeh() {
        return uspeh;
    }

    /**
     * Izvršava specifičnu operaciju ažuriranja {@link Smena} instance u bazi.
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        uspeh = DbBroker.getInstanca().updateOpstiDomenskiObjekat(smena);
    }

}
