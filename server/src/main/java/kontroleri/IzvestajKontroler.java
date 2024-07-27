package kontroleri;

import domen.Izvestaj;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import sistemska_operacija.izvestaj.SOAzurirajIzvestaj;
import sistemska_operacija.izvestaj.SOKreirajIzvestaj;
import sistemska_operacija.izvestaj.SOUcitajIzvestaj;
import sistemska_operacija.izvestaj.SOUcitajListuIzvestaja;

/**
 * The {@code IzvestajKontroler} class provides methods for managing
 * {@code Izvestaj} objects.
 * <p>
 * This class serves as a controller for {@code Izvestaj} entities, interacting
 * with system operations to perform CRUD (Create, Read, Update, Delete)
 * operations on {@code Izvestaj} data.
 * </p>
 *
 * <p>
 * It implements the Singleton pattern to ensure that only one instance of the
 * controller is created.
 * </p>
 *
 * @author dulait
 */
public class IzvestajKontroler {

    private static IzvestajKontroler instanca;

    private IzvestajKontroler() {
    }

    /**
     * Returns the singleton instance of the {@code IzvestajKontroler}.
     * <p>
     * If the instance does not exist, it is created. This method ensures that
     * only one instance of the controller is used.
     * </p>
     *
     * @return the singleton instance of {@code IzvestajKontroler}
     */
    public static IzvestajKontroler getInstanca() {
        if (instanca == null) {
            instanca = new IzvestajKontroler();
        }
        return instanca;
    }

    /**
     * Retrieves a list of all {@code Izvestaj} objects.
     * <p>
     * This method uses the {@code SOUcitajListuIzvestaja} system operation to
     * execute the retrieval process.
     * </p>
     *
     * @return a list of {@code OpstiDomenskiObjekat} representing
     * {@code Izvestaj} objects
     */
    public List<OpstiDomenskiObjekat> ucitajListuIzvestaja() {
        SOUcitajListuIzvestaja so = new SOUcitajListuIzvestaja();
        so.izvrsiSistemskuOperaciju();
        return so.getIzvestaji();
    }

    /**
     * Retrieves a specific {@code Izvestaj} object by its identifier.
     * <p>
     * This method uses the {@code SOUcitajIzvestaj} system operation to execute
     * the retrieval process.
     * </p>
     *
     * @param izvestaj the {@code Izvestaj} object to be retrieved
     * @return the {@code OpstiDomenskiObjekat} representing the retrieved
     * {@code Izvestaj}
     */
    public OpstiDomenskiObjekat ucitajIzvestaj(Izvestaj izvestaj) {
        SOUcitajIzvestaj so = new SOUcitajIzvestaj(izvestaj);
        so.izvrsiSistemskuOperaciju();
        return so.getIzvestaj();
    }

    /**
     * Creates a new {@code Izvestaj} object.
     * <p>
     * This method uses the {@code SOKreirajIzvestaj} system operation to
     * execute the creation process.
     * </p>
     *
     * @param izvestaj the {@code Izvestaj} object to be created
     * @return {@code true} if the creation was successful, {@code false}
     * otherwise
     */
    public boolean kreirajIzvestaj(Izvestaj izvestaj) {
        SOKreirajIzvestaj so = new SOKreirajIzvestaj(izvestaj);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }

    /**
     * Updates a specific {@code Izvestaj} object.
     * <p>
     * This method uses the {@code SOAzurirajIzvestaj} system operation to
     * execute the update process.
     * </p>
     *
     * @param izvestaj the {@code Izvestaj} object to be updated
     * @return {@code true} if the update was successful, {@code false}
     * otherwise
     */
    public boolean azurirajIzvestaj(Izvestaj izvestaj) {
        SOAzurirajIzvestaj so = new SOAzurirajIzvestaj(izvestaj);
        so.izvrsiSistemskuOperaciju();
        return so.isUspeh();
    }

}
