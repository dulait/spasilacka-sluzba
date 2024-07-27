package sistemska_operacija.spasilac;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * System operation for retrieving a specific {@link OpstiDomenskiObjekat}
 * instance representing a {@link domen.Spasilac} from the database.
 * <p>
 * This operation fetches a single record from the database based on the
 * parameters provided in the constructor and stores the result in the
 * {@link #getSpasilac()} method for further processing.
 * </p>
 *
 * @see OpstiDomenskiObjekat
 * @see db.DbBroker
 * @author drask
 */
public class SOUcitajSpasioca extends OpstaSO {

    private OpstiDomenskiObjekat spasilac;

    /**
     * Constructs a new {@code SOUcitajSpasioca} with the specified
     * {@link OpstiDomenskiObjekat} to retrieve.
     *
     * @param spasilac the {@link OpstiDomenskiObjekat} representing the
     * {@link domen.Spasilac} to be retrieved.
     */
    public SOUcitajSpasioca(OpstiDomenskiObjekat spasilac) {
        this.spasilac = spasilac;
    }

    /**
     * Executes the specific operation of retrieving the
     * {@link OpstiDomenskiObjekat} instance from the database.
     * <p>
     * This method retrieves the {@link OpstiDomenskiObjekat} based on the
     * parameters provided and stores the result in the {@code spasilac} field.
     * </p>
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        spasilac = DbBroker.getInstanca().getOpstiDomenskiObjekatPoParametru(spasilac);
    }

    /**
     * Returns the retrieved {@link OpstiDomenskiObjekat} instance representing
     * the {@link domen.Spasilac}.
     *
     * @return the {@link OpstiDomenskiObjekat} instance representing the
     * retrieved {@link domen.Spasilac}.
     */
    public OpstiDomenskiObjekat getSpasilac() {
        return spasilac;
    }

}
