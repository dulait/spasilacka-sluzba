package kontroler;

import domen.Koordinator;
import java.util.List;
import konstante.Operacija;
import transfer.Odgovor;
import transfer.Zahtev;

/**
 * Handler class that sends and receives server requests and responses for CRUD
 * operations regarding the Koordinator domain model
 *
 * @author dulait
 */
public class KoordinatorKontroler {

    private static KoordinatorKontroler instanca;

    private KoordinatorKontroler() {

    }

    public static KoordinatorKontroler getInstanca() {
        if (instanca == null) {
            instanca = new KoordinatorKontroler();
        }
        return instanca;
    }

    public boolean prijaviKoordinatora(Koordinator koordinator) {
        ServerKontroler.getInstanca().posaljiZahtev(new Zahtev(koordinator, Operacija.PRIJAVI_KOORDINATORA));
        Odgovor odgovor = ServerKontroler.getInstanca().primiOdgovor();

        return odgovor.getUspeh() == Operacija.USPEH;
    }

    public Koordinator ucitajKoordinatora(Koordinator koordinator) {
        ServerKontroler.getInstanca().posaljiZahtev(new Zahtev(koordinator, Operacija.UCITAJ_KOORDINATORA));
        Odgovor odgovor = ServerKontroler.getInstanca().primiOdgovor();

        return (Koordinator) odgovor.getOdgovor();
    }

    public List<Koordinator> ucitajListuKoordinatora(List<Koordinator> koordinatori) {
        ServerKontroler.getInstanca().posaljiZahtev(new Zahtev(koordinatori, Operacija.UCITAJ_LISTU_KOORDINATORA));
        Odgovor odgovor = ServerKontroler.getInstanca().primiOdgovor();

        return (List<Koordinator>) odgovor.getOdgovor();
    }

}
