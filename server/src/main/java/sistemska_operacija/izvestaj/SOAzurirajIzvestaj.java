package sistemska_operacija.izvestaj;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * System operation for updating an existing {@link OpstiDomenskiObjekat}
 * instance in the database. Specifically, this operation updates the provided
 * {@link OpstiDomenskiObjekat} instance, which is expected to be of type
 * {@link domen.Izvestaj}.
 * <p>
 * This operation handles the entire database transaction, ensuring that changes
 * are committed if successful or rolled back in case of an error.
 * </p>
 *
 * @see OpstiDomenskiObjekat
 * @see DbBroker
 * @author dulait
 */
public class SOAzurirajIzvestaj extends OpstaSO {

    private final OpstiDomenskiObjekat izvestaj;
    private boolean uspeh;

    /**
     * Constructs a new system operation to update the specified
     * {@link OpstiDomenskiObjekat}.
     *
     * @param izvestaj the {@link OpstiDomenskiObjekat} instance to be updated,
     * which is expected to be of type {@link domen.Izvestaj}
     */
    public SOAzurirajIzvestaj(OpstiDomenskiObjekat izvestaj) {
        this.izvestaj = izvestaj;
    }

    /**
     * Returns whether the update operation was successful.
     *
     * @return {@code true} if the update was successful, {@code false}
     * otherwise
     */
    public boolean isUspeh() {
        return uspeh;
    }

    /**
     * Executes the specific operation of updating the
     * {@link OpstiDomenskiObjekat} in the database.
     * <p>
     * This method is invoked within the transaction managed by the
     * {@code izvrsiSistemskuOperaciju} method of the {@link OpstaSO} class. It
     * updates the specified domain object using the
     * {@link DbBroker#updateOpstiDomenskiObjekat(OpstiDomenskiObjekat)} method.
     * </p>
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        uspeh = DbBroker.getInstanca().updateOpstiDomenskiObjekat(izvestaj);
    }
}
