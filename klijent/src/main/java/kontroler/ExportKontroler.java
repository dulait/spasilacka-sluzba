package kontroler;

import konstante.Operacija;
import transfer.Odgovor;
import transfer.Zahtev;

/**
 * Handler class that sends and receives server requests and responses for
 * exporting to JSON.
 *
 * @author dulait
 */
public class ExportKontroler {

    private static ExportKontroler instanca;

    private ExportKontroler() {

    }

    public static ExportKontroler getInstanca() {
        if (instanca == null) {
            instanca = new ExportKontroler();
        }
        return instanca;
    }

    public boolean exportToJson() {
        ServerKontroler.getInstanca().posaljiZahtev(new Zahtev(null, Operacija.EXPORT_TO_JSON));
        Odgovor odgovor = ServerKontroler.getInstanca().primiOdgovor();

        return odgovor.getUspeh() == Operacija.USPEH;
    }

}
