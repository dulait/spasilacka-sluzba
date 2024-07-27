package sistemska_operacija.smena;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * System operation for retrieving a single {@link OpstiDomenskiObjekat}
 * instance representing {@link Smena} from the database based on the provided
 * parameters.
 * <p>
 * This operation fetches a specific {@link OpstiDomenskiObjekat} instance from
 * the database, which represents a {@link Smena} object, using the parameters
 * provided during initialization. The retrieved instance can be accessed using
 * the {@link #getSmena()} method.
 * </p>
 *
 * @see OpstiDomenskiObjekat
 * @see Smena
 * @see DbBroker
 * @author dulait
 */
public class SOUcitajSmenu extends OpstaSO {

    private OpstiDomenskiObjekat smena;

    /**
     * Constructs a new system operation for retrieving a specific {@link Smena}
     * instance.
     *
     * @param smena an {@link OpstiDomenskiObjekat} representing the parameters
     * to identify the {@link Smena} to be retrieved.
     */
    public SOUcitajSmenu(OpstiDomenskiObjekat smena) {
        this.smena = smena;
    }

    /**
     * Executes the specific operation of retrieving a single {@link Smena}
     * instance from the database.
     * <p>
     * This method performs the retrieval operation by calling the appropriate
     * method in {@link DbBroker}, using the parameters provided to the
     * constructor. The retrieved {@link Smena} instance is stored in the
     * {@code smena} field.
     * </p>
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        smena = DbBroker.getInstanca().getOpstiDomenskiObjekatPoParametru(smena);
    }

    /**
     * Returns the retrieved {@link OpstiDomenskiObjekat} instance representing
     * {@link Smena}.
     *
     * @return an {@link OpstiDomenskiObjekat} representing the retrieved
     * {@link Smena} object.
     */
    public OpstiDomenskiObjekat getSmena() {
        return smena;
    }

}
