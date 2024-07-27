package kontroleri;

import domen.OpstiDomenskiObjekat;
import domen.Spasilac;
import java.util.List;
import sistemska_operacija.spasilac.SOAzurirajSpasioca;
import sistemska_operacija.spasilac.SOKreirajSpasioca;
import sistemska_operacija.spasilac.SONadjiSpasioce;
import sistemska_operacija.spasilac.SOUcitajListuSpasioca;
import sistemska_operacija.spasilac.SOUcitajSpasioca;

/**
 * The {@code SpasilacKontroler} class provides methods for managing
 * {@code Spasilac} objects.
 * <p>
 * This class serves as a controller for {@code Spasilac} entities, interacting
 * with system operations to perform various operations related to
 * {@code Spasilac} data.
 * </p>
 *
 * <p>
 * It implements the Singleton pattern to ensure that only one instance of the
 * controller is created.
 * </p>
 *
 * @author dulait
 */
public class SpasilacKontroler {

    private static SpasilacKontroler instanca;

    private SpasilacKontroler() {
    }

    /**
     * Returns the singleton instance of the {@code SpasilacKontroler}.
     * <p>
     * If the instance does not exist, it is created. This method ensures that
     * only one instance of the controller is used.
     * </p>
     *
     * @return the singleton instance of {@code SpasilacKontroler}
     */
    public static SpasilacKontroler getInstanca() {
        if (instanca == null) {
            instanca = new SpasilacKontroler();
        }
        return instanca;
    }

    /**
     * Retrieves a list of all {@code Spasilac} objects.
     * <p>
     * This method uses the {@code SOUcitajListuSpasioca} system operation to
     * execute the retrieval process.
     * </p>
     *
     * @return a list of {@code OpstiDomenskiObjekat} representing
     * {@code Spasilac} objects
     */
    public List<OpstiDomenskiObjekat> ucitajListuSpasioca() {
        SOUcitajListuSpasioca so = new SOUcitajListuSpasioca();
        so.izvrsiSistemskuOperaciju();
        return so.getSpasioci();
    }

    /**
     * Retrieves a specific {@code Spasilac} object by its identifier.
     * <p>
     * This method uses the {@code SOUcitajSpasioca} system operation to execute
     * the retrieval process.
     * </p>
     *
     * @param spasilac the {@code Spasilac} object to be retrieved
     * @return the {@code OpstiDomenskiObjekat} representing the retrieved
     * {@code Spasilac}
     */
    public OpstiDomenskiObjekat ucitajSpasioca(Spasilac spasilac) {
        SOUcitajSpasioca so = new SOUcitajSpasioca(spasilac);
        so.izvrsiSistemskuOperaciju();
        return so.getSpasilac();
    }

    /**
     * Creates a new {@code Spasilac} object.
     * <p>
     * This method uses the {@code SOKreirajSpasioca} system operation to
     * execute the creation process.
     * </p>
     *
     * @param spasilac the {@code Spasilac} object to be created
     * @return {@code true} if the creation is successful, {@code false}
     * otherwise
     */
    public boolean kreirajSpasioca(Spasilac spasilac) {
        SOKreirajSpasioca so = new SOKreirajSpasioca(spasilac);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }

    /**
     * Updates an existing {@code Spasilac} object.
     * <p>
     * This method uses the {@code SOAzurirajSpasioca} system operation to
     * execute the update process.
     * </p>
     *
     * @param spasilac the {@code Spasilac} object to be updated
     * @return {@code true} if the update is successful, {@code false} otherwise
     */
    public boolean azurirajSpasioca(Spasilac spasilac) {
        SOAzurirajSpasioca so = new SOAzurirajSpasioca(spasilac);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }

    /**
     * Searches for {@code Spasilac} objects based on a search criterion.
     * <p>
     * This method uses the {@code SONadjiSpasioce} system operation to execute
     * the search process.
     * </p>
     *
     * @param kriterijum the search criterion used to find {@code Spasilac}
     * objects
     * @return a list of {@code OpstiDomenskiObjekat} representing the search
     * results
     */
    public List<OpstiDomenskiObjekat> pretraziSpasioce(String kriterijum) {
        SONadjiSpasioce so = new SONadjiSpasioce(kriterijum);
        so.izvrsiSistemskuOperaciju();
        return so.getRezultat();
    }

}
