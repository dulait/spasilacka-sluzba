package kontroler;

import domen.Smena;
import java.util.List;
import konstante.Operacija;
import transfer.Odgovor;
import transfer.Zahtev;

/**
 * Klasa koja predstavlja mediator za CRUD operacije nad smenama
 *
 * @author dulait
 */
public class SmenaKontroler {

    private static SmenaKontroler instanca;

    private SmenaKontroler() {

    }

    public static SmenaKontroler getInstanca() {
        if (instanca == null) {
            instanca = new SmenaKontroler();
        }
        return instanca;
    }

    public boolean kreirajSmenu(Smena smena) {
        ServerKontroler.getInstanca().posaljiZahtev(new Zahtev(smena, Operacija.KREIRAJ_SMENU));
        Odgovor odgovor = ServerKontroler.getInstanca().primiOdgovor();

        return odgovor.getUspeh() == Operacija.USPEH;
    }

    public boolean azurirajSmenu(Smena smena) {
        ServerKontroler.getInstanca().posaljiZahtev(new Zahtev(smena, Operacija.AZURIRAJ_SMENU));
        Odgovor odgovor = ServerKontroler.getInstanca().primiOdgovor();

        return odgovor.getUspeh() == Operacija.USPEH;
    }

    public Smena ucitajSmenu(Smena smena) {
        ServerKontroler.getInstanca().posaljiZahtev(new Zahtev(smena, Operacija.UCITAJ_SMENU));
        Odgovor odgovor = ServerKontroler.getInstanca().primiOdgovor();

        return (Smena) odgovor.getOdgovor();
    }

    public List<Smena> ucitajListuSmena(List<Smena> smene) {
        ServerKontroler.getInstanca().posaljiZahtev(new Zahtev(smene, Operacija.UCITAJ_LISTU_SMENA));
        Odgovor odgovor = ServerKontroler.getInstanca().primiOdgovor();

        return (List<Smena>) odgovor.getOdgovor();
    }

}
