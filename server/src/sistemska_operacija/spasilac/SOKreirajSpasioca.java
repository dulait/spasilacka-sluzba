package sistemska_operacija.spasilac;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import sistemska_operacija.OpstaSO;

/**
 * System operation for creating a new {@link OpstiDomenskiObjekat} instance
 * representing {@link Spasilac} in the database.
 * <p>
 * This operation performs an insertion of a new {@link OpstiDomenskiObjekat}
 * instance, which represents a {@link Spasilac} object. The success of the
 * creation process can be verified using the {@link #isUspeh()} method.
 * </p>
 *
 * @see OpstiDomenskiObjekat
 * @see Spasilac
 * @see DbBroker
 * @author dulait
 */
public class SOKreirajSpasioca extends OpstaSO {

    private final OpstiDomenskiObjekat spasilac;
    private boolean uspeh = false;

    /**
     * Constructs a new system operation for creating a specific
     * {@link Spasilac} instance.
     *
     * @param spasilac an {@link OpstiDomenskiObjekat} representing the
     * {@link Spasilac} to be created.
     */
    public SOKreirajSpasioca(OpstiDomenskiObjekat spasilac) {
        this.spasilac = spasilac;
    }

    /**
     * Returns {@code true} if the creation operation was successful;
     * {@code false} otherwise.
     *
     * @return {@code true} if the creation operation was successful,
     * {@code false} otherwise.
     */
    public boolean isUspeh() {
        return uspeh;
    }

    /**
     * Executes the specific operation of creating the {@link Spasilac} instance
     * in the database.
     * <p>
     * This method performs the creation operation by calling the appropriate
     * method in {@link DbBroker}, using the {@link OpstiDomenskiObjekat}
     * provided to the constructor. The result of the operation is stored in the
     * {@code uspeh} field.
     * </p>
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        uspeh = DbBroker.getInstanca().saveOpstiDomenskiObjekat(spasilac);
    }

}
