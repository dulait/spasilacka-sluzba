package sistemska_operacija.angazovanje;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * System operation for retrieving a specific {@link OpstiDomenskiObjekat}
 * instance from the database based on provided parameters.
 * <p>
 * This operation retrieves a domain object from the database using specified
 * criteria. It ensures the transaction is handled appropriately, committing the
 * changes if successful or rolling back if an error occurs during the process.
 * </p>
 *
 * @see OpstiDomenskiObjekat
 * @see DbBroker
 * @author dulait
 */
public class SOUcitajAngazovanje extends OpstaSO {

    private OpstiDomenskiObjekat angazovanje;

    /**
     * Constructs a new {@code SOUcitajAngazovanje} instance with the specified
     * domain object parameter for retrieval.
     *
     * @param angazovanje the domain object containing parameters for the
     * retrieval query
     */
    public SOUcitajAngazovanje(OpstiDomenskiObjekat angazovanje) {
        this.angazovanje = angazovanje;
    }

    /**
     * Executes the specific operation of retrieving the domain object from the
     * database based on the provided parameters.
     * <p>
     * This method is invoked within the transaction managed by the
     * {@code izvrsiSistemskuOperaciju} method of the {@link OpstaSO} class. It
     * retrieves the domain object using the
     * {@link DbBroker#getOpstiDomenskiObjekatPoParametru(OpstiDomenskiObjekat)}
     * method.
     * </p>
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        angazovanje = DbBroker.getInstanca().getOpstiDomenskiObjekatPoParametru(angazovanje);
    }

    /**
     * Returns the retrieved domain object.
     *
     * @return the domain object retrieved from the database
     */
    public OpstiDomenskiObjekat getAngazovanje() {
        return angazovanje;
    }
}
