package sistemska_operacija.raspored;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * System operation for retrieving a specific {@link OpstiDomenskiObjekat}
 * instance representing a {@link Raspored} from the database.
 * <p>
 * This operation fetches a single {@link OpstiDomenskiObjekat} instance
 * representing a {@link Raspored} based on the parameters provided. The
 * retrieved {@link OpstiDomenskiObjekat} instance can be accessed using the
 * {@link #getRaspored()} method.
 * </p>
 *
 * @see OpstiDomenskiObjekat
 * @see Raspored
 * @see DbBroker
 * @author dulait
 */
public class SOUcitajRaspored extends OpstaSO {

    private OpstiDomenskiObjekat raspored;

    /**
     * Constructs a system operation for retrieving a specific {@link Raspored}.
     *
     * @param raspored the {@link OpstiDomenskiObjekat} instance representing
     * the {@link Raspored} to be retrieved.
     */
    public SOUcitajRaspored(OpstiDomenskiObjekat raspored) {
        this.raspored = raspored;
    }

    /**
     * Executes the specific operation of retrieving a {@link Raspored} instance
     * from the database.
     * <p>
     * This method fetches a single {@link OpstiDomenskiObjekat} instance
     * representing a {@link Raspored} based on the parameters provided in the
     * constructor and stores it in the {@code raspored} field.
     * </p>
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        raspored = DbBroker.getInstanca().getOpstiDomenskiObjekatPoParametru(raspored);
    }

    /**
     * Returns the {@link OpstiDomenskiObjekat} instance representing the
     * {@link Raspored} retrieved from the database.
     *
     * @return the {@link OpstiDomenskiObjekat} instance representing the
     * {@link Raspored}
     */
    public OpstiDomenskiObjekat getRaspored() {
        return raspored;
    }

}
