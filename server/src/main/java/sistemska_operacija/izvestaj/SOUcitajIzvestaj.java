package sistemska_operacija.izvestaj;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * System operation for retrieving a specific {@link OpstiDomenskiObjekat}
 * instance from the database. Specifically, this operation reads the details of
 * the provided {@link OpstiDomenskiObjekat} instance, which is expected to be
 * of type {@link domen.Izvestaj}, based on its parameters.
 * <p>
 * This operation handles the database transaction, ensuring that the database
 * connection is managed correctly throughout the read operation.
 * </p>
 *
 * @see OpstiDomenskiObjekat
 * @see DbBroker
 * @author dulait
 */
public class SOUcitajIzvestaj extends OpstaSO {

    private OpstiDomenskiObjekat izvestaj;

    /**
     * Constructs a new system operation to retrieve the specified
     * {@link OpstiDomenskiObjekat}.
     *
     * @param izvestaj the {@link OpstiDomenskiObjekat} instance whose details
     * are to be retrieved, which is expected to be of type
     * {@link domen.Izvestaj}
     */
    public SOUcitajIzvestaj(OpstiDomenskiObjekat izvestaj) {
        this.izvestaj = izvestaj;
    }

    /**
     * Returns the retrieved {@link OpstiDomenskiObjekat} instance.
     *
     * @return the {@link OpstiDomenskiObjekat} instance with its details filled
     * in from the database
     */
    public OpstiDomenskiObjekat getIzvestaj() {
        return izvestaj;
    }

    /**
     * Executes the specific operation of retrieving a
     * {@link OpstiDomenskiObjekat} from the database.
     * <p>
     * This method is invoked within the transaction managed by the
     * {@code izvrsiSistemskuOperaciju} method of the {@link OpstaSO} class. It
     * retrieves the details of the specified domain object using the
     * {@link DbBroker#getOpstiDomenskiObjekatPoParametru(OpstiDomenskiObjekat)}
     * method.
     * </p>
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        izvestaj = DbBroker.getInstanca().getOpstiDomenskiObjekatPoParametru(izvestaj);
    }
}
