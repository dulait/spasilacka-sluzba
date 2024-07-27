package sistemska_operacija.koordinator;

import db.DbBroker;
import domen.Koordinator;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import sistemska_operacija.OpstaSO;

/**
 * System operation for retrieving a list of all {@link Koordinator} instances
 * from the database.
 * <p>
 * This operation retrieves all instances of {@link Koordinator} from the
 * database and stores them in a list. The list can then be accessed via the
 * {@link #getKoordinatori()} method.
 * </p>
 *
 * @see OpstiDomenskiObjekat
 * @see Koordinator
 * @see DbBroker
 * @author dulait
 */
public class SOUcitajListuKoordinatora extends OpstaSO {

    private List<OpstiDomenskiObjekat> koordinatori;

    /**
     * Returns the list of {@link OpstiDomenskiObjekat} instances representing
     * all retrieved {@link Koordinator} entities.
     *
     * @return a list of {@link OpstiDomenskiObjekat} instances representing the
     * retrieved {@link Koordinator} entities
     */
    public List<OpstiDomenskiObjekat> getKoordinatori() {
        return koordinatori;
    }

    /**
     * Executes the specific operation of retrieving all {@link Koordinator}
     * instances from the database.
     * <p>
     * This method queries the database for all entries of {@link Koordinator}
     * and stores the result in the {@code koordinatori} field.
     * </p>
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        koordinatori = DbBroker.getInstanca().getAllOpstiDomenskiObjekats(new Koordinator());
    }

}
