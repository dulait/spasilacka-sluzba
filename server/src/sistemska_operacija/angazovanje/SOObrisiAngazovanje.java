package sistemska_operacija.angazovanje;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * System operation for deleting a specific {@link OpstiDomenskiObjekat}
 * instance from the database.
 * <p>
 * This operation involves removing a specific domain object from the database.
 * It is executed within a transaction to ensure that changes are committed if
 * successful or rolled back if an error occurs.
 * </p>
 *
 * @see OpstiDomenskiObjekat
 * @see DbBroker
 * @author dulait
 */
public class SOObrisiAngazovanje extends OpstaSO {

    private final OpstiDomenskiObjekat angazovanje;
    private boolean uspeh;

    /**
     * Constructs a new {@code SOObrisiAngazovanje} instance with the specified
     * domain object to delete.
     *
     * @param angazovanje the domain object to be deleted from the database
     */
    public SOObrisiAngazovanje(OpstiDomenskiObjekat angazovanje) {
        this.angazovanje = angazovanje;
    }

    /**
     * Executes the specific operation of deleting the domain object from the
     * database.
     * <p>
     * This method is invoked within the transaction managed by the
     * {@code izvrsiSistemskuOperaciju} method of the {@link OpstaSO} class. The
     * success of the operation is determined by the return value of the
     * {@link DbBroker#deleteOpstiDomenskiObjekat(OpstiDomenskiObjekat)} method.
     * </p>
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        uspeh = DbBroker.getInstanca().deleteOpstiDomenskiObjekat(angazovanje);
    }

    /**
     * Returns whether the delete operation was successful.
     *
     * @return {@code true} if the domain object was successfully deleted,
     * {@code false} otherwise
     */
    public boolean isUspeh() {
        return uspeh;
    }
}
