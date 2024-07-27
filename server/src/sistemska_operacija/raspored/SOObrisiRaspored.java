package sistemska_operacija.raspored;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * System operation for deleting an {@link OpstiDomenskiObjekat} instance
 * representing a {@link Raspored} from the database.
 * <p>
 * This operation deletes the specified {@link OpstiDomenskiObjekat}, which
 * represents a {@link Raspored}, from the database. The success of the deletion
 * can be determined by calling the {@link #isUspeh()} method.
 * </p>
 *
 * @see OpstiDomenskiObjekat
 * @see Raspored
 * @see DbBroker
 * @author dulait
 */
public class SOObrisiRaspored extends OpstaSO {

    private final OpstiDomenskiObjekat raspored;
    private boolean uspeh;

    /**
     * Constructs a new {@code SOObrisiRaspored} instance with the specified
     * {@link OpstiDomenskiObjekat} representing the {@link Raspored}.
     *
     * @param raspored the {@link OpstiDomenskiObjekat} instance representing
     * the {@link Raspored} to be deleted
     */
    public SOObrisiRaspored(OpstiDomenskiObjekat raspored) {
        this.raspored = raspored;
    }

    /**
     * Returns whether the deletion of the {@link Raspored} was successful.
     *
     * @return {@code true} if the deletion was successful, {@code false}
     * otherwise
     */
    public boolean isUspeh() {
        return uspeh;
    }

    /**
     * Executes the specific operation of deleting the
     * {@link OpstiDomenskiObjekat} representing a {@link Raspored} from the
     * database.
     * <p>
     * This method attempts to delete the provided {@link OpstiDomenskiObjekat}
     * from the database. The success of the operation is stored in the
     * {@code uspeh} field.
     * </p>
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        uspeh = DbBroker.getInstanca().deleteOpstiDomenskiObjekat(raspored);
    }

}
