package sistemska_operacija.smena;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * Klasa koja predstavlja sistemsku operaciju za preuzimanje jedne
 * {@link OpstiDomenskiObjekat} instance koja predstavlja {@link Smena} iz baze
 * podataka na osnovu prosleđenih parametara.
 *
 * @author dulait
 */
public class SOUcitajSmenu extends OpstaSO {

    private OpstiDomenskiObjekat smena;

    /**
     * Konstruktor za kreiranje nove sistemske operacije za preuzimanje
     * specifične {@link Smena} instance.
     *
     * @param smena {@link OpstiDomenskiObjekat} koji predstavlja parametre za
     * identifikaciju {@link Smena} koja treba da bude preuzeta.
     */
    public SOUcitajSmenu(OpstiDomenskiObjekat smena) {
        this.smena = smena;
    }

    /**
     * Izvršava specifičnu operaciju preuzimanja jedne {@link Smena} instance iz
     * baze podataka.
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        smena = DbBroker.getInstanca().getOpstiDomenskiObjekatPoParametru(smena);
    }

    /**
     * Vraća preuzetu {@link OpstiDomenskiObjekat} instancu koja predstavlja
     * {@link Smena}.
     *
     * @return {@link OpstiDomenskiObjekat} koji predstavlja preuzetu
     * {@link Smena} objekat.
     */
    public OpstiDomenskiObjekat getSmena() {
        return smena;
    }

}
