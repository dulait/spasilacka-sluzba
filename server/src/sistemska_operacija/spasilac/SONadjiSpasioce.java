package sistemska_operacija.spasilac;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.Spasilac;
import java.util.ArrayList;
import java.util.List;
import sistemska_operacija.OpstaSO;

/**
 * System operation for finding {@link Spasilac} instances based on a search
 * criterion.
 * <p>
 * This operation retrieves all {@link Spasilac} instances from the database and
 * filters them based on the provided search criterion. The results are stored
 * in the {@link #getRezultat()} method.
 * </p>
 *
 * @see OpstiDomenskiObjekat
 * @see Spasilac
 * @see DbBroker
 * @author dulait
 */
public class SONadjiSpasioce extends OpstaSO {

    private List<OpstiDomenskiObjekat> rezultat;
    private final String kriterijum;

    /**
     * Constructs a new system operation for finding {@link Spasilac} instances
     * based on a given criterion.
     *
     * @param kriterijum the search criterion to filter {@link Spasilac}
     * instances.
     */
    public SONadjiSpasioce(String kriterijum) {
        this.kriterijum = kriterijum;
    }

    /**
     * Executes the specific operation of finding {@link Spasilac} instances
     * based on the search criterion.
     * <p>
     * This method retrieves all {@link Spasilac} instances from the database,
     * filters them according to the provided {@code kriterijum}, and stores the
     * results in the {@code rezultat} field.
     * </p>
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
     * Returns the list of {@link OpstiDomenskiObjekat} instances that match the
     * search criterion.
     *
     * @return a list of {@link OpstiDomenskiObjekat} instances that match the
     * search criterion.
     */
    public List<OpstiDomenskiObjekat> getRezultat() {
        return rezultat;
    }

    /**
     * Checks if the given {@link Spasilac} instance contains the search
     * criterion in its fields.
     *
     * @param s the {@link Spasilac} instance to be checked.
     * @return {@code true} if the {@link Spasilac} instance contains the search
     * criterion in its fields, {@code false} otherwise.
     */
    private boolean sadrziKriterijum(Spasilac s) {
        return s.getIme().toLowerCase().contains(kriterijum.toLowerCase())
                || s.getPrezime().toLowerCase().contains(kriterijum.toLowerCase());
    }

}
