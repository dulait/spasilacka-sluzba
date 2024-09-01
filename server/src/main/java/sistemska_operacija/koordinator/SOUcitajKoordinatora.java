package sistemska_operacija.koordinator;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * Klasa koja predstavlja sistemsku operaciju za preuzimanje specifične
 * {@link OpstiDomenskiObjekat} instance tipa {@link Koordinator} iz baze.
 *
 * @see OpstiDomenskiObjekat
 * @see Koordinator
 * @see DbBroker
 * @author dulait
 */
public class SOUcitajKoordinatora extends OpstaSO {

    private OpstiDomenskiObjekat koordinator;

    /**
     * Metoda koja kreira novu instancu {@code SOUcitajKoordinatora} sa datim
     * {@link OpstiDomenskiObjekat} koji predstavlja {@link Koordinator} koji
     * treba da bude preuzet.
     *
     * @param koordinator {@link OpstiDomenskiObjekat} koji predstavlja
     * {@link Koordinator} sa parametrima koji se koriste za preuzimanje
     * instance iz baze
     */
    public SOUcitajKoordinatora(OpstiDomenskiObjekat koordinator) {
        this.koordinator = koordinator;
    }

    /**
     * Metoda koja vraća {@link OpstiDomenskiObjekat} preuzet iz baze.
     *
     * @return preuzeti {@link OpstiDomenskiObjekat} koji predstavlja
     * {@link Koordinator}
     */
    public OpstiDomenskiObjekat getKoordinator() {
        return koordinator;
    }

    /**
     * Metoda koja izvršava specifičnu operaciju preuzimanja {@link Koordinator}
     * iz baze.
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        koordinator = DbBroker.getInstanca().getOpstiDomenskiObjekatPoParametru(koordinator);
    }

}
