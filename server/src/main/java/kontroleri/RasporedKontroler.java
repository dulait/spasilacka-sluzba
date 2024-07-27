package kontroleri;

import domen.OpstiDomenskiObjekat;
import domen.Raspored;
import java.util.List;
import sistemska_operacija.raspored.SOKreirajRaspored;
import sistemska_operacija.raspored.SOObrisiRaspored;
import sistemska_operacija.raspored.SOUcitajListuRasporeda;
import sistemska_operacija.raspored.SOUcitajRaspored;

/**
 * The {@code RasporedKontroler} class provides methods for managing
 * {@code Raspored} objects.
 * <p>
 * This class serves as a controller for {@code Raspored} entities, interacting
 * with system operations to perform various operations related to
 * {@code Raspored} data.
 * </p>
 *
 * <p>
 * It implements the Singleton pattern to ensure that only one instance of the
 * controller is created.
 * </p>
 *
 * @author dulait
 */
public class RasporedKontroler {

    private static RasporedKontroler instanca;

    private RasporedKontroler() {
    }

    /**
     * Returns the singleton instance of the {@code RasporedKontroler}.
     * <p>
     * If the instance does not exist, it is created. This method ensures that
     * only one instance of the controller is used.
     * </p>
     *
     * @return the singleton instance of {@code RasporedKontroler}
     */
    public static RasporedKontroler getInstanca() {
        if (instanca == null) {
            instanca = new RasporedKontroler();
        }
        return instanca;
    }

    /**
     * Retrieves a list of all {@code Raspored} objects.
     * <p>
     * This method uses the {@code SOUcitajListuRasporeda} system operation to
     * execute the retrieval process.
     * </p>
     *
     * @return a list of {@code OpstiDomenskiObjekat} representing
     * {@code Raspored} objects
     */
    public List<OpstiDomenskiObjekat> ucitajListuRasporeda() {
        SOUcitajListuRasporeda so = new SOUcitajListuRasporeda();
        so.izvrsiSistemskuOperaciju();
        return so.getRasporedi();
    }

    /**
     * Retrieves a specific {@code Raspored} object by its identifier.
     * <p>
     * This method uses the {@code SOUcitajRaspored} system operation to execute
     * the retrieval process.
     * </p>
     *
     * @param raspored the {@code Raspored} object to be retrieved
     * @return the {@code OpstiDomenskiObjekat} representing the retrieved
     * {@code Raspored}
     */
    public OpstiDomenskiObjekat ucitajRaspored(Raspored raspored) {
        SOUcitajRaspored so = new SOUcitajRaspored(raspored);
        so.izvrsiSistemskuOperaciju();
        return so.getRaspored();
    }

    /**
     * Creates a new {@code Raspored} object.
     * <p>
     * This method uses the {@code SOKreirajRaspored} system operation to
     * execute the creation process.
     * </p>
     *
     * @param raspored the {@code Raspored} object to be created
     * @return {@code true} if the creation is successful, {@code false}
     * otherwise
     */
    public boolean kreirajRaspored(Raspored raspored) {
        SOKreirajRaspored so = new SOKreirajRaspored(raspored);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }

    /**
     * Deletes a specific {@code Raspored} object.
     * <p>
     * This method uses the {@code SOObrisiRaspored} system operation to execute
     * the deletion process.
     * </p>
     *
     * @param raspored the {@code Raspored} object to be deleted
     * @return {@code true} if the deletion is successful, {@code false}
     * otherwise
     */
    public boolean obrisiRaspored(Raspored raspored) {
        SOObrisiRaspored so = new SOObrisiRaspored(raspored);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }

}
