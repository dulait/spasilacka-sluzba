package kontroleri;

import sistemska_operacija.export.SOExportToJson;

/**
 * Klasa koja pruža metode za manipulaciju JSON fajlovima.
 *
 * @author dulait
 */
public class ExportKontroler {

    private static ExportKontroler instanca;

    private ExportKontroler() {
    }

    /**
     * Vraća instancu kontrolera.
     *
     * @return singleton instanca {@code ExportKontroler}
     */
    public static ExportKontroler getInstanca() {
        if (instanca == null) {
            instanca = new ExportKontroler();
        }
        return instanca;
    }

    /**
     * Izvodi eksport podataka u JSON format.
     *
     * @return putanja do JSON fajla ako je uspešno, {@code null} inače
     */
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
