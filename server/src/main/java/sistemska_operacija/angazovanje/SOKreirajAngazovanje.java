package sistemska_operacija.angazovanje;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * System operation for creating a new {@link OpstiDomenskiObjekat} instance in
 * the database.
 * <p>
 * This operation involves saving a specific domain object into the database.
 * The operation is performed within a transaction, ensuring that the changes
 * are committed if successful, or rolled back in case of an error.
 * </p>
 *
 * @see OpstiDomenskiObjekat
 * @see DbBroker
 *
 * @author dulait
 */
public class SOKreirajAngazovanje extends OpstaSO {

    private final OpstiDomenskiObjekat angazovanje;
    private boolean uspeh;

    /**
     * Constructs a new {@code SOKreirajAngazovanje} instance with the specified
     * domain object.
     *
     * @param angazovanje the domain object to be created in the database
     */
    public SOKreirajAngazovanje(OpstiDomenskiObjekat angazovanje) {
        this.angazovanje = angazovanje;
    }

    /**
     * Executes the specific operation of saving the domain object into the
     * database.
     * <p>
     * This method is called within the transaction managed by the
     * {@code izvrsiSistemskuOperaciju} method of the {@link OpstaSO} class. The
     * success of the operation is determined by the return value of the
     * {@link DbBroker#saveOpstiDomenskiObjekat(OpstiDomenskiObjekat)} method.
     * </p>
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        uspeh = DbBroker.getInstanca().saveOpstiDomenskiObjekat(angazovanje);
    }

    /**
     * Returns whether the operation was successful.
     *
     * @return {@code true} if the domain object was successfully saved,
     * {@code false} otherwise
     */
    public boolean isUspeh() {
        return uspeh;
    }
}
