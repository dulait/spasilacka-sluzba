package kontroleri;

import domen.Koordinator;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import sistemska_operacija.koordinator.SOPrijaviKoordinatora;
import sistemska_operacija.koordinator.SOUcitajKoordinatora;
import sistemska_operacija.koordinator.SOUcitajListuKoordinatora;

/**
 * The {@code KoordinatorKontroler} class provides methods for managing
 * {@code Koordinator} objects.
 * <p>
 * This class serves as a controller for {@code Koordinator} entities,
 * interacting with system operations to perform various operations related to
 * {@code Koordinator} data.
 * </p>
 *
 * <p>
 * It implements the Singleton pattern to ensure that only one instance of the
 * controller is created.
 * </p>
 *
 * @author dulait
 */
public class KoordinatorKontroler {

    private static KoordinatorKontroler instanca;

    private KoordinatorKontroler() {
    }

    /**
     * Returns the singleton instance of the {@code KoordinatorKontroler}.
     * <p>
     * If the instance does not exist, it is created. This method ensures that
     * only one instance of the controller is used.
     * </p>
     *
     * @return the singleton instance of {@code KoordinatorKontroler}
     */
    public static KoordinatorKontroler getInstanca() {
        if (instanca == null) {
            instanca = new KoordinatorKontroler();
        }
        return instanca;
    }

    /**
     * Registers a new {@code Koordinator} object.
     * <p>
     * This method uses the {@code SOPrijaviKoordinatora} system operation to
     * execute the registration process.
     * </p>
     *
     * @param koordinator the {@code Koordinator} object to be registered
     * @return the {@code OpstiDomenskiObjekat} representing the registered
     * {@code Koordinator}
     */
    public OpstiDomenskiObjekat prijaviKoordinatora(Koordinator koordinator) {
        SOPrijaviKoordinatora so = new SOPrijaviKoordinatora(koordinator);
        so.izvrsiSistemskuOperaciju();
        return so.getKoordinator();
    }

    /**
     * Retrieves a specific {@code Koordinator} object by its identifier.
     * <p>
     * This method uses the {@code SOUcitajKoordinatora} system operation to
     * execute the retrieval process.
     * </p>
     *
     * @param koordinator the {@code Koordinator} object to be retrieved
     * @return the {@code OpstiDomenskiObjekat} representing the retrieved
     * {@code Koordinator}
     */
    public OpstiDomenskiObjekat ucitajKoordinatora(Koordinator koordinator) {
        SOUcitajKoordinatora so = new SOUcitajKoordinatora(koordinator);
        so.izvrsiSistemskuOperaciju();
        return so.getKoordinator();
    }

    /**
     * Retrieves a list of all {@code Koordinator} objects.
     * <p>
     * This method uses the {@code SOUcitajListuKoordinatora} system operation
     * to execute the retrieval process.
     * </p>
     *
     * @return a list of {@code OpstiDomenskiObjekat} representing
     * {@code Koordinator} objects
     */
    public List<OpstiDomenskiObjekat> ucitajListuKoordinatora() {
        SOUcitajListuKoordinatora so = new SOUcitajListuKoordinatora();
        so.izvrsiSistemskuOperaciju();
        return so.getKoordinatori();
    }
}
