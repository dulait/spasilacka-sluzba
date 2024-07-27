package sistemska_operacija.smena;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * System operation for creating a new {@link OpstiDomenskiObjekat} instance
 * representing a {@link Smena} in the database.
 * <p>
 * This operation creates a new {@link OpstiDomenskiObjekat} instance
 * representing a {@link Smena} in the database. The success of the operation
 * can be determined using the {@link #isUspeh()} method.
 * </p>
 *
 * @see OpstiDomenskiObjekat
 * @see Smena
 * @see DbBroker
 * @author dulait
 */
public class SOKreirajSmenu extends OpstaSO {

    private final OpstiDomenskiObjekat smena;
    private boolean uspeh;

    /**
     * Constructs a system operation for creating a specific {@link Smena}.
     *
     * @param smena the {@link OpstiDomenskiObjekat} instance representing the
     * {@link Smena} to be created.
     */
    public SOKreirajSmenu(OpstiDomenskiObjekat smena) {
        this.smena = smena;
    }

    /**
     * Returns whether the creation operation was successful.
     *
     * @return {@code true} if the creation was successful, {@code false}
     * otherwise.
     */
    public boolean isUspeh() {
        return uspeh;
    }

    /**
     * Executes the specific operation of creating the {@link Smena} instance in
     * the database.
     * <p>
     * This method performs the creation operation by calling the appropriate
     * method in {@link DbBroker}. The success of the creation operation is
     * stored in the {@code uspeh} field.
     * </p>
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        uspeh = DbBroker.getInstanca().saveOpstiDomenskiObjekat(smena);
    }

}
