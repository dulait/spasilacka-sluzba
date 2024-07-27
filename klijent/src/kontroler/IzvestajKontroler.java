package kontroler;

import domen.Izvestaj;
import java.util.List;
import konstante.Operacija;
import transfer.Odgovor;
import transfer.Zahtev;

/**
 * Handler class that sends and receives server requests and responses for CRUD
 * operations regarding the Izvestaj domain model
 *
 * @author dulait
 */
public class IzvestajKontroler {

    private static IzvestajKontroler instanca;

    private IzvestajKontroler() {

    }

    public static IzvestajKontroler getInstanca() {
        if (instanca == null) {
            instanca = new IzvestajKontroler();
        }
        return instanca;
    }

    public boolean kreirajIzvestaj(Izvestaj izvestaj) {
        ServerKontroler.getInstanca().posaljiZahtev(new Zahtev(izvestaj, Operacija.KREIRAJ_IZVESTAJ));
        Odgovor odgovor = ServerKontroler.getInstanca().primiOdgovor();

        return odgovor.getUspeh() == Operacija.USPEH;
    }

    public boolean azurirajIzvestaj(Izvestaj izvestaj) {
        ServerKontroler.getInstanca().posaljiZahtev(new Zahtev(izvestaj, Operacija.AZURIRAJ_IZVESTAJ));
        Odgovor odgovor = ServerKontroler.getInstanca().primiOdgovor();

        return odgovor.getUspeh() == Operacija.USPEH;
    }

    public Izvestaj ucitajIzvestaj(Izvestaj izvestaj) {
        ServerKontroler.getInstanca().posaljiZahtev(new Zahtev(izvestaj, Operacija.UCITAJ_IZVESTAJ));
        Odgovor odgovor = ServerKontroler.getInstanca().primiOdgovor();

        return (Izvestaj) odgovor.getOdgovor();
    }

    public List<Izvestaj> ucitajListuIzvestaja(List<Izvestaj> izvestaji) {
        ServerKontroler.getInstanca().posaljiZahtev(new Zahtev(izvestaji, Operacija.UCITAJ_LISTU_IZVESTAJA));
        Odgovor odgovor = ServerKontroler.getInstanca().primiOdgovor();

        return (List<Izvestaj>) odgovor.getOdgovor();
    }

}
