package sistemska_operacija.raspored;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * Klasa koja predstavlja sistemsku operaciju za brisanje
 * {@link OpstiDomenskiObjekat} instance koja predstavlja {@link Raspored} iz
 * baze podataka.
 *
 * @author dulait
 */
public class SOObrisiRaspored extends OpstaSO {

    private final OpstiDomenskiObjekat raspored;
    private boolean uspeh;

    /**
     * Konstruktor koji kreira novu {@code SOObrisiRaspored} instancu sa
     * specifikovanom {@link OpstiDomenskiObjekat} koja predstavlja
     * {@link Raspored}.
     *
     * @param raspored instanca {@link OpstiDomenskiObjekat} koja predstavlja
     * {@link Raspored} koji treba da se obriše
     */
    public SOObrisiRaspored(OpstiDomenskiObjekat raspored) {
        this.raspored = raspored;
    }

    /**
     * Vraća da li je brisanje {@link Raspored} bilo uspešno.
     *
     * @return {@code true} ako je brisanje bilo uspešno, {@code false} inače
     */
    public boolean isUspeh() {
        return uspeh;
    }

    /**
     * Izvršava specifičnu operaciju brisanja {@link OpstiDomenskiObjekat} koja
     * predstavlja {@link Raspored} iz baze podataka.
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        uspeh = DbBroker.getInstanca().deleteOpstiDomenskiObjekat(raspored);
    }

}
