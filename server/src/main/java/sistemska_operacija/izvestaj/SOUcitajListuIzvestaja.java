package sistemska_operacija.izvestaj;

import db.DbBroker;
import domen.Izvestaj;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import sistemska_operacija.OpstaSO;

/**
 * System operation for retrieving a list of {@link OpstiDomenskiObjekat}
 * instances from the database. Specifically, this operation retrieves all
 * instances of {@link domen.Izvestaj} from the database.
 * <p>
 * This operation handles the database transaction, ensuring that the database
 * connection is managed correctly throughout the retrieval process.
 * </p>
 *
 * @see OpstiDomenskiObjekat
 * @see DbBroker
 * @author dulait
 */
public class SOUcitajListuIzvestaja extends OpstaSO {

    private List<OpstiDomenskiObjekat> izvestaji;

    /**
     * Returns the list of retrieved {@link OpstiDomenskiObjekat} instances.
     *
     * @return a list of {@link OpstiDomenskiObjekat} instances, specifically
     * instances of {@link domen.Izvestaj}, retrieved from the database
     */
    public List<OpstiDomenskiObjekat> getIzvestaji() {
        return izvestaji;
    }

    /**
     * Executes the specific operation of retrieving all
     * {@link OpstiDomenskiObjekat} instances from the database.
     * <p>
     * This method is invoked within the transaction managed by the
     * {@code izvrsiSistemskuOperaciju} method of the {@link OpstaSO} class. It
     * retrieves all instances of {@link domen.Izvestaj} using the
     * {@link DbBroker#getAllOpstiDomenskiObjekats(OpstiDomenskiObjekat)}
     * method.
     * </p>
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        izvestaji = DbBroker.getInstanca().getAllOpstiDomenskiObjekats(new Izvestaj());
    }
}
