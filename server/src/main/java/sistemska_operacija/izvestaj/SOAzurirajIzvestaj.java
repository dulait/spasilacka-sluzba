package sistemska_operacija.izvestaj;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * Klasa koja predstavlja sistemsku operaciju za ažuriranje postojeće
 * {@link OpstiDomenskiObjekat} instance u bazi podataka.
 *
 * @see OpstiDomenskiObjekat
 * @see DbBroker
 * @author dulait
 */
public class SOAzurirajIzvestaj extends OpstaSO {

    private final OpstiDomenskiObjekat izvestaj;
    private boolean uspeh;

    /**
     * Metoda koja kreira novu sistemsku operaciju za ažuriranje specificirane
     * {@link OpstiDomenskiObjekat}.
     *
     * @param izvestaj {@link OpstiDomenskiObjekat} instanca koja treba da bude
     * ažurirana, koja se očekuje da bude tipa {@link domen.Izvestaj}
     */
    public SOAzurirajIzvestaj(OpstiDomenskiObjekat izvestaj) {
        this.izvestaj = izvestaj;
    }

    /**
     * Metoda koja vraća da li je operacija ažuriranja bila uspešna.
     *
     * @return {@code true} ako je ažuriranje bilo uspešno, {@code false} inače
     */
    public boolean isUspeh() {
        return uspeh;
    }

    /**
     * Metoda koja izvršava specifičnu operaciju ažuriranja
     * {@link OpstiDomenskiObjekat} u bazi podataka.
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        uspeh = DbBroker.getInstanca().updateOpstiDomenskiObjekat(izvestaj);
    }
}
