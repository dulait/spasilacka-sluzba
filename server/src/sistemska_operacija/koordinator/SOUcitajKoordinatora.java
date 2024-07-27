package sistemska_operacija.koordinator;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * System operation for retrieving a specific {@link OpstiDomenskiObjekat}
 * instance of type {@link Koordinator} from the database.
 * <p>
 * This operation retrieves a single {@link OpstiDomenskiObjekat} based on the
 * parameters provided in the {@link Koordinator} instance passed to the
 * constructor. The retrieved instance is then stored in the {@code koordinator}
 * field.
 * </p>
 *
 * @see OpstiDomenskiObjekat
 * @see Koordinator
 * @see DbBroker
 * @author dulait
 */
public class SOUcitajKoordinatora extends OpstaSO {

    private OpstiDomenskiObjekat koordinator;

    /**
     * Constructs a new {@code SOUcitajKoordinatora} with the specified
     * {@link OpstiDomenskiObjekat} representing the {@link Koordinator} to be
     * retrieved.
     *
     * @param koordinator the {@link OpstiDomenskiObjekat} representing the
     * {@link Koordinator} with the parameters used for retrieving the instance
     * from the database
     */
    public SOUcitajKoordinatora(OpstiDomenskiObjekat koordinator) {
        this.koordinator = koordinator;
    }

    /**
     * Returns the {@link OpstiDomenskiObjekat} retrieved from the database.
     *
     * @return the retrieved {@link OpstiDomenskiObjekat} representing the
     * {@link Koordinator}
     */
    public OpstiDomenskiObjekat getKoordinator() {
        return koordinator;
    }

    /**
     * Executes the specific operation of retrieving the {@link Koordinator}
     * from the database.
     * <p>
     * This method uses the parameters in the provided
     * {@link OpstiDomenskiObjekat} to query the database and retrieve the
     * corresponding {@link Koordinator} instance. The retrieved instance is
     * then stored in the {@code koordinator} field.
     * </p>
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        koordinator = DbBroker.getInstanca().getOpstiDomenskiObjekatPoParametru(koordinator);
    }

}
