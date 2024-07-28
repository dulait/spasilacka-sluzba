package kontroleri;

import sistemska_operacija.export.SOExportToJson;

/**
 * The {@code ExportKontroler} class provides methods for manipulating JSON
 * files.
 * <p>
 * This class serves as a controller that interacts with system operations
 * related to all entities. It uses system operation classes to perform JSON
 * exporting of data related to all entities.
 * </p>
 *
 * <p>
 * It implements the Singleton pattern to ensure that only one instance of the
 * controller exists.
 * </p>
 *
 * @author dulait
 */
public class ExportKontroler {

    private static ExportKontroler instanca;

    private ExportKontroler() {
    }

    /**
     * Returns the singleton instance of the {@code ExportKontroler}.
     * <p>
     * If the instance does not exist, it is created. This method ensures that
     * only one instance of the controller is used.
     * </p>
     *
     * @return the singleton instance of {@code ExportKontroler}
     */
    public static ExportKontroler getInstanca() {
        if (instanca == null) {
            instanca = new ExportKontroler();
        }
        return instanca;
    }

    public String exportToJson() {
        SOExportToJson so = new SOExportToJson();
        so.izvrsiSistemskuOperaciju();
        if (so.isUspeh()) {
            return so.getJsonFilePath();
        } else {
            return null;
        }
    }

}
