package sistemska_operacija.angazovanje;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * Klasa koja predstavlja sistemsku operaciju za brisanje određene
 * {@link OpstiDomenskiObjekat} instance iz baze podataka.
 *
 * @author dulait
 */
public class SOObrisiAngazovanje extends OpstaSO {

    private final OpstiDomenskiObjekat angazovanje;
    private boolean uspeh;

    /**
     * Konstruktor za novu {@code SOObrisiAngazovanje} instancu sa
     * specificiranim objektom domena koji treba obrisati.
     *
     * @param angazovanje objekat domena koji se briše iz baze podataka
     */
    public SOObrisiAngazovanje(OpstiDomenskiObjekat angazovanje) {
        this.angazovanje = angazovanje;
    }

    /**
     * Metoda koja izvršava specifičnu operaciju brisanja objekta domena iz baze
     * podataka.
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        uspeh = DbBroker.getInstanca().deleteOpstiDomenskiObjekat(angazovanje);
    }

    /**
     * Metoda koja vraća da li je operacija brisanja bila uspešna.
     *
     * @return {@code true} ako je objekat domena uspešno obrisan, {@code false}
     * inače
     */
    public boolean isUspeh() {
        return uspeh;
    }
}
