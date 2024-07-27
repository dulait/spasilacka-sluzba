package sistemska_operacija.smena;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.Smena;
import java.util.List;
import sistemska_operacija.OpstaSO;

/**
 * System operation for retrieving a list of all {@link Smena} instances from
 * the database.
 * <p>
 * This operation fetches all {@link OpstiDomenskiObjekat} instances
 * representing {@link Smena} from the database. The retrieved list can be
 * accessed using the {@link #getSmene()} method.
 * </p>
 *
 * @see OpstiDomenskiObjekat
 * @see Smena
 * @see DbBroker
 * @author dulait
 */
public class SOUcitajListuSmena extends OpstaSO {

    private List<OpstiDomenskiObjekat> smene;

    /**
     * Returns the list of {@link OpstiDomenskiObjekat} instances representing
     * {@link Smena}.
     *
     * @return a {@link List} of {@link OpstiDomenskiObjekat} instances
     * representing all {@link Smena} objects in the database.
     */
    public List<OpstiDomenskiObjekat> getSmene() {
        return smene;
    }

    /**
     * Executes the specific operation of retrieving all {@link Smena} instances
     * from the database.
     * <p>
     * This method performs the retrieval operation by calling the appropriate
     * method in {@link DbBroker}. The list of retrieved {@link Smena} instances
     * is stored in the {@code smene} field.
     * </p>
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        smene = DbBroker.getInstanca().getAllOpstiDomenskiObjekats(new Smena());
    }

}
