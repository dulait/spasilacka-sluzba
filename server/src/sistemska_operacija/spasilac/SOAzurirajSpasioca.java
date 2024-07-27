package sistemska_operacija.spasilac;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * System operation for updating an {@link OpstiDomenskiObjekat} instance
 * representing {@link Spasilac} in the database.
 * <p>
 * This operation performs an update on a specific {@link OpstiDomenskiObjekat}
 * instance, which represents a {@link Spasilac} object. The update is executed
 * using the provided {@link OpstiDomenskiObjekat} instance, and the success of
 * the operation can be checked using the {@link #isUspeh()} method.
 * </p>
 *
 * @see OpstiDomenskiObjekat
 * @see Spasilac
 * @see DbBroker
 * @author dulait
 */
public class SOAzurirajSpasioca extends OpstaSO {

    private final OpstiDomenskiObjekat spasilac;
    private boolean uspeh;

    /**
     * Constructs a new system operation for updating a specific
     * {@link Spasilac} instance.
     *
     * @param spasilac an {@link OpstiDomenskiObjekat} representing the
     * {@link Spasilac} to be updated.
     */
    public SOAzurirajSpasioca(OpstiDomenskiObjekat spasilac) {
        this.spasilac = spasilac;
    }

    /**
     * Returns {@code true} if the update operation was successful;
     * {@code false} otherwise.
     *
     * @return {@code true} if the update operation was successful,
     * {@code false} otherwise.
     */
    public boolean isUspeh() {
        return uspeh;
    }

    /**
     * Executes the specific operation of updating the {@link Spasilac} instance
     * in the database.
     * <p>
     * This method performs the update operation by calling the appropriate
     * method in {@link DbBroker}, using the {@link OpstiDomenskiObjekat}
     * provided to the constructor. The result of the operation is stored in the
     * {@code uspeh} field.
     * </p>
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        uspeh = DbBroker.getInstanca().updateOpstiDomenskiObjekat(spasilac);
    }

}
