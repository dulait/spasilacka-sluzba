package sistemska_operacija.spasilac;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.Spasilac;
import java.util.List;
import sistemska_operacija.OpstaSO;

/**
 * System operation for retrieving a list of all {@link Spasilac} instances from
 * the database.
 * <p>
 * This operation fetches all {@link Spasilac} records from the database and
 * stores them in the {@link #getSpasioci()} method for further processing.
 * </p>
 *
 * @see OpstiDomenskiObjekat
 * @see Spasilac
 * @see DbBroker
 * @author drask
 */
public class SOUcitajListuSpasioca extends OpstaSO {

    private List<OpstiDomenskiObjekat> spasioci;

    /**
     * Returns the list of all {@link OpstiDomenskiObjekat} instances
     * representing the retrieved {@link Spasilac} records.
     *
     * @return a list of all {@link OpstiDomenskiObjekat} instances representing
     * the retrieved {@link Spasilac} records.
     */
    public List<OpstiDomenskiObjekat> getSpasioci() {
        return spasioci;
    }

    /**
     * Executes the specific operation of retrieving all {@link Spasilac}
     * instances from the database.
     * <p>
     * This method retrieves all records of type {@link Spasilac} from the
     * database and stores them in the {@code spasioci} field.
     * </p>
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        spasioci = DbBroker.getInstanca().getAllOpstiDomenskiObjekats(new Spasilac());
    }

}
