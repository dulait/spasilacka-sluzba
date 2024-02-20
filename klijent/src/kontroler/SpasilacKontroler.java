package kontroler;

import domen.Spasilac;
import java.util.List;
import konstante.Operacija;
import transfer.Odgovor;
import transfer.Zahtev;

public class SpasilacKontroler {

    private static SpasilacKontroler instanca;

    private SpasilacKontroler() {

    }

    public static SpasilacKontroler getInstanca() {
        if (instanca == null) {
            instanca = new SpasilacKontroler();
        }
        return instanca;
    }

    public boolean kreirajSpasioca(Spasilac s) {
        ServerKontroler.getInstanca().posaljiZahtev(new Zahtev(s, Operacija.KREIRAJ_SPASIOCA));
        Odgovor o = ServerKontroler.getInstanca().primiOdgovor();

        return o.getUspeh() == Operacija.USPEH;

    }

    public boolean azurirajSpasioca(Spasilac s) {
        ServerKontroler.getInstanca().posaljiZahtev(new Zahtev(s, Operacija.AZURIRAJ_SPASIOCA));
        Odgovor o = ServerKontroler.getInstanca().primiOdgovor();

        return o.getUspeh() == Operacija.USPEH;
    }

    public Spasilac ucitajSpasioca(Spasilac s) {
        ServerKontroler.getInstanca().posaljiZahtev(new Zahtev(s, Operacija.UCITAJ_SPASIOCA));
        Odgovor o = ServerKontroler.getInstanca().primiOdgovor();

        return (Spasilac) o.getOdgovor();
    }

    public List<Spasilac> ucitajListuSpasioca(List<Spasilac> spasioci) {
        ServerKontroler.getInstanca().posaljiZahtev(new Zahtev(spasioci, Operacija.UCITAJ_LISTU_SPASIOCA));
        Odgovor o = ServerKontroler.getInstanca().primiOdgovor();

        return (List<Spasilac>) o.getOdgovor();
    }

    public List<Spasilac> pretraziSpasioce(String kriterijum) {
        ServerKontroler.getInstanca().posaljiZahtev(new Zahtev(kriterijum, Operacija.PRETRAZI_SPASIOCE));
        Odgovor o = ServerKontroler.getInstanca().primiOdgovor();

        return (List<Spasilac>) o.getOdgovor();
    }

}
