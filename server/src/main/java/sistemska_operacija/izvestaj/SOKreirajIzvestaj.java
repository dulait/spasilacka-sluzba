package sistemska_operacija.izvestaj;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * Klasa koja predstavlja sistemsku operaciju za kreiranje nove
 * {@link OpstiDomenskiObjekat} instance u bazi podataka.
 *
 * @author dulait
 */
public class SOKreirajIzvestaj extends OpstaSO {

    private final OpstiDomenskiObjekat izvestaj;
    private boolean uspeh;

    /**
     * Metoda koja kreira novu sistemsku operaciju za kreiranje specificirane
     * {@link OpstiDomenskiObjekat}.
     *
     * @param izvestaj {@link OpstiDomenskiObjekat} instanca koja treba da bude
     * kreirana, koja se očekuje da bude tipa {@link domen.Izvestaj}
     */
    public SOKreirajIzvestaj(OpstiDomenskiObjekat izvestaj) {
        this.izvestaj = izvestaj;
    }

    /**
     * Metoda koja vraća da li je operacija kreiranja bila uspešna.
     *
     * @return {@code true} ako je kreiranje bilo uspešno, {@code false} inače
     */
    public boolean isUspeh() {
        return uspeh;
    }

    /**
     * Metoda koja izvršava specifičnu operaciju kreiranja nove
     * {@link OpstiDomenskiObjekat} u bazi podataka.
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        uspeh = DbBroker.getInstanca().saveOpstiDomenskiObjekat(izvestaj);
    }
}
