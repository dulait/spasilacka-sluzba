package sistemska_operacija.koordinator;

import db.DbBroker;
import domen.Koordinator;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import sistemska_operacija.OpstaSO;

/**
 * Klasa koja predstavlja sistemsku operaciju za autentifikaciju
 * {@link Koordinator} u bazi podataka.
 *
 * @author dulait
 */
public class SOPrijaviKoordinatora extends OpstaSO {

    private final OpstiDomenskiObjekat koordinator;
    private OpstiDomenskiObjekat prijavljeni;

    /**
     * Metoda koja kreira novu instancu {@code SOPrijaviKoordinatora} sa datim
     * {@link OpstiDomenskiObjekat} koji predstavlja {@link Koordinator} koji
     * treba da bude autentifikovan.
     *
     * @param koordinator {@link OpstiDomenskiObjekat} koji predstavlja
     * {@link Koordinator} čije kredencijale treba verifikovati
     */
    public SOPrijaviKoordinatora(OpstiDomenskiObjekat koordinator) {
        this.koordinator = koordinator;
        this.prijavljeni = null;
    }

    /**
     * Metoda koja izvršava specifičnu operaciju autentifikacije
     * {@link Koordinator}.
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        List<OpstiDomenskiObjekat> koordinatori = DbBroker.getInstanca().getAllOpstiDomenskiObjekats(koordinator);
        Koordinator uneti = (Koordinator) koordinator;

        for (OpstiDomenskiObjekat odo : koordinatori) {
            Koordinator k = (Koordinator) odo;
            if (k.getKorisnickoIme().equals(uneti.getKorisnickoIme()) && k.getLozinka().equals(uneti.getLozinka())) {
                this.prijavljeni = k;
            }
        }
    }

    /**
     * Metoda koja vraća autentifikovanog {@link Koordinator} ako je
     * autentifikacija bila uspešna, ili {@code null} ako nije pronađen
     * odgovarajući {@link Koordinator}.
     *
     * @return autentifikovani {@link Koordinator} ili {@code null} ako
     * autentifikacija nije uspela
     */
    public OpstiDomenskiObjekat getKoordinator() {
        return prijavljeni;
    }
}
