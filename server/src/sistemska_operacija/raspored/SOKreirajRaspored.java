package sistemska_operacija.raspored;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * System operation for creating a new {@link OpstiDomenskiObjekat} instance
 * representing a {@link Raspored} in the database.
 * <p>
 * This operation saves a new instance of {@link OpstiDomenskiObjekat}, which
 * represents a {@link Raspored}, to the database. The success of the operation
 * can be determined by calling the {@link #isUspeh()} method.
 * </p>
 *
 * @see OpstiDomenskiObjekat
 * @see Raspored
 * @see DbBroker
 * @author dulait
 */
public class SOKreirajRaspored extends OpstaSO {

    private final OpstiDomenskiObjekat raspored;
    private boolean uspeh;

    /**
     * Constructs a new {@code SOKreirajRaspored} instance with the specified
     * {@link OpstiDomenskiObjekat} representing the {@link Raspored}.
     *
     * @param raspored the {@link OpstiDomenskiObjekat} instance representing
     * the {@link Raspored} to be created
     */
    public SOKreirajRaspored(OpstiDomenskiObjekat raspored) {
        this.raspored = raspored;
    }

    /**
     * Returns whether the creation of the {@link Raspored} was successful.
     *
     * @return {@code true} if the creation was successful, {@code false}
     * otherwise
     */
    public boolean isUspeh() {
        return uspeh;
    }

    /**
     * Executes the specific operation of saving the
     * {@link OpstiDomenskiObjekat} representing a {@link Raspored} to the
     * database.
     * <p>
     * This method attempts to save the provided {@link OpstiDomenskiObjekat} to
     * the database. The success of the operation is stored in the {@code uspeh}
     * field.
     * </p>
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        uspeh = DbBroker.getInstanca().saveOpstiDomenskiObjekat(raspored);
    }

}
