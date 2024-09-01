package sistemska_operacija.spasilac;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.Spasilac;
import java.util.ArrayList;
import java.util.List;
import sistemska_operacija.OpstaSO;

/**
 * Klasa koja predstavlja sistemsku operaciju za pronalaženje {@link Spasilac}
 * instanci na osnovu određenih kriterijuma pretrage.
 *
 * @author dulait
 */
public class SONadjiSpasioce extends OpstaSO {

    private List<OpstiDomenskiObjekat> rezultat;
    private final String kriterijum;

    /**
     * Konstruktor za novu sistemsku operaciju koja pronalazi {@link Spasilac}
     * instance na osnovu datog kriterijuma.
     *
     * @param kriterijum kriterijum pretrage za filtriranje {@link Spasilac}
     * instanci.
     */
    public SONadjiSpasioce(String kriterijum) {
        this.kriterijum = kriterijum;
    }

    /**
     * Izvršava specifičnu operaciju pronalaženja {@link Spasilac} instanci na
     * osnovu kriterijuma pretrage.
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        List<OpstiDomenskiObjekat> sviSpasioci = DbBroker.getInstanca().getAllOpstiDomenskiObjekats(new Spasilac());
        rezultat = new ArrayList<>();

        for (OpstiDomenskiObjekat odo : sviSpasioci) {
            Spasilac s = (Spasilac) odo;
            if (sadrziKriterijum(s)) {
                rezultat.add(s);
            }
        }
    }

    /**
     * Vraća listu {@link OpstiDomenskiObjekat} instanci koje odgovaraju
     * kriterijumu pretrage.
     *
     * @return lista {@link OpstiDomenskiObjekat} instanci koje odgovaraju
     * kriterijumu pretrage.
     */
    public List<OpstiDomenskiObjekat> getRezultat() {
        return rezultat;
    }

    /**
     * Proverava da li dat {@link Spasilac} instance sadrži kriterijum pretrage
     * u svojim poljima.
     *
     * @param s {@link Spasilac} instanca koja se proverava.
     * @return {@code true} ako {@link Spasilac} instanca sadrži kriterijum
     * pretrage u svojim poljima, {@code false} u suprotnom.
     */
    private boolean sadrziKriterijum(Spasilac s) {
        return s.getIme().toLowerCase().contains(kriterijum.toLowerCase())
                || s.getPrezime().toLowerCase().contains(kriterijum.toLowerCase());
    }

}
