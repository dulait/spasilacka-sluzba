package kontroleri;

import domen.OpstiDomenskiObjekat;
import domen.Smena;
import java.util.List;
import sistemska_operacija.smena.SOAzurirajSmenu;
import sistemska_operacija.smena.SOKreirajSmenu;
import sistemska_operacija.smena.SOUcitajListuSmena;
import sistemska_operacija.smena.SOUcitajSmenu;

/**
 * The {@code SmenaKontroler} class provides methods for managing {@code Smena}
 * objects.
 * <p>
 * This class serves as a controller for {@code Smena} entities, interacting
 * with system operations to perform various operations related to {@code Smena}
 * data.
 * </p>
 *
 * <p>
 * It implements the Singleton pattern to ensure that only one instance of the
 * controller is created.
 * </p>
 *
 * @author dulait
 */
public class SmenaKontroler {

    private static SmenaKontroler instanca;

    private SmenaKontroler() {
    }

    /**
     * Returns the singleton instance of the {@code SmenaKontroler}.
     * <p>
     * If the instance does not exist, it is created. This method ensures that
     * only one instance of the controller is used.
     * </p>
     *
     * @return the singleton instance of {@code SmenaKontroler}
     */
    public static SmenaKontroler getInstanca() {
        if (instanca == null) {
            instanca = new SmenaKontroler();
        }
        return instanca;
    }

    /**
     * Retrieves a list of all {@code Smena} objects.
     * <p>
     * This method uses the {@code SOUcitajListuSmena} system operation to
     * execute the retrieval process.
     * </p>
     *
     * @return a list of {@code OpstiDomenskiObjekat} representing {@code Smena}
     * objects
     */
    public List<OpstiDomenskiObjekat> ucitajListuSmena() {
        SOUcitajListuSmena so = new SOUcitajListuSmena();
        so.izvrsiSistemskuOperaciju();
        return so.getSmene();
    }

    /**
     * Retrieves a specific {@code Smena} object by its identifier.
     * <p>
     * This method uses the {@code SOUcitajSmenu} system operation to execute
     * the retrieval process.
     * </p>
     *
     * @param smena the {@code Smena} object to be retrieved
     * @return the {@code OpstiDomenskiObjekat} representing the retrieved
     * {@code Smena}
     */
    public OpstiDomenskiObjekat ucitajSmenu(Smena smena) {
        SOUcitajSmenu so = new SOUcitajSmenu(smena);
        so.izvrsiSistemskuOperaciju();
        return so.getSmena();
    }

    /**
     * Creates a new {@code Smena} object.
     * <p>
     * This method uses the {@code SOKreirajSmenu} system operation to execute
     * the creation process.
     * </p>
     *
     * @param smena the {@code Smena} object to be created
     * @return {@code true} if the creation is successful, {@code false}
     * otherwise
     */
    public boolean kreirajSmenu(Smena smena) {
        SOKreirajSmenu so = new SOKreirajSmenu(smena);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }

    /**
     * Updates an existing {@code Smena} object.
     * <p>
     * This method uses the {@code SOAzurirajSmenu} system operation to execute
     * the update process.
     * </p>
     *
     * @param smena the {@code Smena} object to be updated
     * @return {@code true} if the update is successful, {@code false} otherwise
     */
    public boolean azurirajSmenu(Smena smena) {
        SOAzurirajSmenu so = new SOAzurirajSmenu(smena);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }

}
