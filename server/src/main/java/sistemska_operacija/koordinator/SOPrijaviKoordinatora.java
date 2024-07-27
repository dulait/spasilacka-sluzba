package sistemska_operacija.koordinator;

import db.DbBroker;
import domen.Koordinator;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import sistemska_operacija.OpstaSO;

/**
 * System operation for authenticating a {@link Koordinator} against the
 * database.
 * <p>
 * This operation retrieves all {@link OpstiDomenskiObjekat} instances of type
 * {@link Koordinator} from the database and checks if any of them match the
 * provided credentials (username and password). If a match is found, the
 * authenticated {@link Koordinator} is stored for further use.
 * </p>
 *
 * @see OpstiDomenskiObjekat
 * @see Koordinator
 * @see DbBroker
 * @author dulait
 */
public class SOPrijaviKoordinatora extends OpstaSO {

    private final OpstiDomenskiObjekat koordinator;
    private OpstiDomenskiObjekat prijavljeni;

    /**
     * Constructs a new {@code SOPrijaviKoordinatora} with the specified
     * {@link OpstiDomenskiObjekat} representing the {@link Koordinator} to be
     * authenticated.
     *
     * @param koordinator the {@link OpstiDomenskiObjekat} representing the
     * {@link Koordinator} whose credentials are to be verified
     */
    public SOPrijaviKoordinatora(OpstiDomenskiObjekat koordinator) {
        this.koordinator = koordinator;
        this.prijavljeni = null;
    }

    /**
     * Executes the specific operation of authenticating the
     * {@link Koordinator}.
     * <p>
     * This method retrieves all {@link OpstiDomenskiObjekat} instances from the
     * database that are of type {@link Koordinator}. It then compares the
     * username and password of each retrieved instance with those of the
     * provided {@link Koordinator}. If a match is found, the authenticated
     * {@link Koordinator} is stored in the {@code prijavljeni} field.
     * </p>
     */
    @Override
    protected void izvrsiSpecificnuOperaciju() {
        List<OpstiDomenskiObjekat> koordinatori = DbBroker.getInstanca().getAllOpstiDomenskiObjekats(koordinator);
        Koordinator uneti = (Koordinator) koordinator;

        for (OpstiDomenskiObjekat odo : koordinatori) {
            Koordinator k = (Koordinator) odo;
            if (k.getKorisnickoIme().equals(uneti.getKorisnickoIme()) && k.getLozinka().equals(uneti.getLozinka())) {
                this.prijavljeni = k;
            }
        }
    }

    /**
     * Returns the authenticated {@link Koordinator} if authentication was
     * successful, or {@code null} if no matching {@link Koordinator} was found.
     *
     * @return the authenticated {@link Koordinator} or {@code null} if
     * authentication failed
     */
    public OpstiDomenskiObjekat getKoordinator() {
        return prijavljeni;
    }
}
