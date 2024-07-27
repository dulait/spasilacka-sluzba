package kontroler;

import domen.Raspored;
import java.util.List;
import konstante.Operacija;
import transfer.Odgovor;
import transfer.Zahtev;

/**
 * Handler class that sends and receives server requests and responses for CRUD
 * operations regarding the Raspored domain model
 *
 * @author dulait
 */
public class RasporedKontroler {

    private static RasporedKontroler instanca;

    private RasporedKontroler() {

    }

    public static RasporedKontroler getInstanca() {
        if (instanca == null) {
            instanca = new RasporedKontroler();
        }
        return instanca;
    }

    public boolean kreirajRaspored(Raspored raspored) {
        ServerKontroler.getInstanca().posaljiZahtev(new Zahtev(raspored, Operacija.KREIRAJ_RASPORED));
        Odgovor odgovor = ServerKontroler.getInstanca().primiOdgovor();

        return odgovor.getUspeh() == Operacija.USPEH;
    }

    public boolean obrisiRaspored(Raspored raspored) {
        ServerKontroler.getInstanca().posaljiZahtev(new Zahtev(raspored, Operacija.OBRISI_RASPORED));
        Odgovor odgovor = ServerKontroler.getInstanca().primiOdgovor();

        return odgovor.getUspeh() == Operacija.USPEH;
    }

    public Raspored ucitajRaspored(Raspored raspored) {
        ServerKontroler.getInstanca().posaljiZahtev(new Zahtev(raspored, Operacija.UCITAJ_RASPORED));
        Odgovor odgovor = ServerKontroler.getInstanca().primiOdgovor();

        return (Raspored) odgovor.getOdgovor();
    }

    public List<Raspored> ucitajListuRasporeda(List<Raspored> rasporedi) {
        ServerKontroler.getInstanca().posaljiZahtev(new Zahtev(rasporedi, Operacija.UCITAJ_LISTU_RASPOREDA));
        Odgovor odgovor = ServerKontroler.getInstanca().primiOdgovor();

        return (List<Raspored>) odgovor.getOdgovor();
    }

}
