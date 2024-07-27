package sistemska_operacija.raspored;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.Raspored;
import java.util.List;
import sistemska_operacija.OpstaSO;

/**
 * System operation for retrieving a list of all {@link Raspored} instances from
 * the database.
 * <p>
 * This operation fetches all {@link OpstiDomenskiObjekat} instances
 * representing {@link Raspored} from the database. The retrieved list of
 * {@link OpstiDomenskiObjekat} instances can be accessed using the
 * {@link #getRasporedi()} method.
 * </p>
 *
 * @see OpstiDomenskiObjekat
 * @see Raspored
 * @see DbBroker
 * @author dulait
 */
public class SOUcitajListuRasporeda extends OpstaSO {

    private List<OpstiDomenskiObjekat> rasporedi;

    /**
     * Returns the list of {@link OpstiDomenskiObjekat} instances representing
     * {@link Raspored} retrieved from the database.
     *
     * @return the list of {@link OpstiDomenskiObjekat} instances
     */
    public List<OpstiDomenskiObjekat> getRasporedi() {
        return rasporedi;
    }

    /**
     * Executes the specific operation of retrieving a list of all
     * {@link Raspored} instances from the database.
     * <p>
     * This method fetches all {@link OpstiDomenskiObjekat} instances
     * representing {@link Raspored} from the database and stores them in the
     * {@code rasporedi} field.
     * </p>
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        rasporedi = DbBroker.getInstanca().getAllOpstiDomenskiObjekats(new Raspored());
    }

}
