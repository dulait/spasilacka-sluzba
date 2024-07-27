package kontroleri;

import domen.Angazovanje;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import sistemska_operacija.angazovanje.SOKreirajAngazovanje;
import sistemska_operacija.angazovanje.SOObrisiAngazovanje;
import sistemska_operacija.angazovanje.SOUcitajAngazovanje;
import sistemska_operacija.angazovanje.SOUcitajListuAngazovanja;

/**
 * The {@code AngazovanjeKontroler} class provides methods for managing
 * {@code Angazovanje} objects.
 * <p>
 * This class serves as a controller that interacts with system operations
 * related to {@code Angazovanje} entities. It uses system operation classes to
 * perform CRUD (Create, Read, Update, Delete) operations and manage
 * {@code Angazovanje} data.
 * </p>
 *
 * <p>
 * It implements the Singleton pattern to ensure that only one instance of the
 * controller exists.
 * </p>
 *
 * @author dulait
 */
public class AngazovanjeKontroler {

    private static AngazovanjeKontroler instanca;

    private AngazovanjeKontroler() {
    }

    /**
     * Returns the singleton instance of the {@code AngazovanjeKontroler}.
     * <p>
     * If the instance does not exist, it is created. This method ensures that
     * only one instance of the controller is used.
     * </p>
     *
     * @return the singleton instance of {@code AngazovanjeKontroler}
     */
    public static AngazovanjeKontroler getInstanca() {
        if (instanca == null) {
            instanca = new AngazovanjeKontroler();
        }
        return instanca;
    }

    /**
     * Retrieves a list of all {@code Angazovanje} objects.
     * <p>
     * This method uses the {@code SOUcitajListuAngazovanja} system operation to
     * execute the retrieval process.
     * </p>
     *
     * @return a list of {@code OpstiDomenskiObjekat} representing
     * {@code Angazovanje} objects
     */
    public List<OpstiDomenskiObjekat> ucitajListuAngazovanja() {
        SOUcitajListuAngazovanja so = new SOUcitajListuAngazovanja();
        so.izvrsiSistemskuOperaciju();
        return so.getAngazovanja();
    }

    /**
     * Retrieves a specific {@code Angazovanje} object by its identifier.
     * <p>
     * This method uses the {@code SOUcitajAngazovanje} system operation to
     * execute the retrieval process.
     * </p>
     *
     * @param angazovanje the {@code Angazovanje} object to be retrieved
     * @return the {@code OpstiDomenskiObjekat} representing the retrieved
     * {@code Angazovanje}
     */
    public OpstiDomenskiObjekat ucitajAngazovanje(Angazovanje angazovanje) {
        SOUcitajAngazovanje so = new SOUcitajAngazovanje(angazovanje);
        so.izvrsiSistemskuOperaciju();
        return so.getAngazovanje();
    }

    /**
     * Creates a new {@code Angazovanje} object.
     * <p>
     * This method uses the {@code SOKreirajAngazovanje} system operation to
     * execute the creation process.
     * </p>
     *
     * @param angazovanje the {@code Angazovanje} object to be created
     * @return {@code true} if the creation was successful, {@code false}
     * otherwise
     */
    public boolean kreirajAngazovanje(Angazovanje angazovanje) {
        SOKreirajAngazovanje so = new SOKreirajAngazovanje(angazovanje);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }

    /**
     * Deletes a specific {@code Angazovanje} object.
     * <p>
     * This method uses the {@code SOObrisiAngazovanje} system operation to
     * execute the deletion process.
     * </p>
     *
     * @param angazovanje the {@code Angazovanje} object to be deleted
     * @return {@code true} if the deletion was successful, {@code false}
     * otherwise
     */
    public boolean obrisiAngazovanje(Angazovanje angazovanje) {
        SOObrisiAngazovanje so = new SOObrisiAngazovanje(angazovanje);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }
}
