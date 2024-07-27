package sistemska_operacija.angazovanje;

import db.DbBroker;
import domen.Angazovanje;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import sistemska_operacija.OpstaSO;

/**
 * System operation for retrieving a list of all {@link OpstiDomenskiObjekat}
 * instances of type {@link Angazovanje} from the database.
 * <p>
 * This operation retrieves all instances of the specified domain object from
 * the database. It handles the transaction, ensuring changes are committed if
 * successful or rolled back in case of an error.
 * </p>
 *
 * @see OpstiDomenskiObjekat
 * @see Angazovanje
 * @see DbBroker
 * @author dulait
 */
public class SOUcitajListuAngazovanja extends OpstaSO {

    private List<OpstiDomenskiObjekat> angazovanja;

    /**
     * Returns the list of retrieved domain objects.
     *
     * @return the list of all {@link OpstiDomenskiObjekat} instances of type
     * {@link Angazovanje}
     */
    public List<OpstiDomenskiObjekat> getAngazovanja() {
        return angazovanja;
    }

    /**
     * Executes the specific operation of retrieving all domain objects of type
     * {@link Angazovanje} from the database.
     * <p>
     * This method is invoked within the transaction managed by the
     * {@code izvrsiSistemskuOperaciju} method of the {@link OpstaSO} class. It
     * retrieves all instances of {@link Angazovanje} using the
     * {@link DbBroker#getAllOpstiDomenskiObjekats(OpstiDomenskiObjekat)}
     * method.
     * </p>
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        angazovanja = DbBroker.getInstanca().getAllOpstiDomenskiObjekats(new Angazovanje());
    }
}
