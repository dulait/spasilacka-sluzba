package sistemska_operacija.smena;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * System operation for updating an existing {@link OpstiDomenskiObjekat}
 * instance representing a {@link Smena} in the database.
 * <p>
 * This operation updates an existing {@link OpstiDomenskiObjekat} instance
 * representing a {@link Smena} in the database. The success of the operation
 * can be determined using the {@link #isUspeh()} method.
 * </p>
 *
 * @see OpstiDomenskiObjekat
 * @see Smena
 * @see DbBroker
 * @author dulait
 */
public class SOAzurirajSmenu extends OpstaSO {

    private final OpstiDomenskiObjekat smena;
    private boolean uspeh;

    /**
     * Constructs a system operation for updating a specific {@link Smena}.
     *
     * @param smena the {@link OpstiDomenskiObjekat} instance representing the
     * {@link Smena} to be updated.
     */
    public SOAzurirajSmenu(OpstiDomenskiObjekat smena) {
        this.smena = smena;
    }

    /**
     * Returns whether the update operation was successful.
     *
     * @return {@code true} if the update was successful, {@code false}
     * otherwise.
     */
    public boolean isUspeh() {
        return uspeh;
    }

    /**
     * Executes the specific operation of updating the {@link Smena} instance in
     * the database.
     * <p>
     * This method performs the update operation by calling the appropriate
     * method in {@link DbBroker}. The success of the update operation is stored
     * in the {@code uspeh} field.
     * </p>
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        uspeh = DbBroker.getInstanca().updateOpstiDomenskiObjekat(smena);
    }

}
