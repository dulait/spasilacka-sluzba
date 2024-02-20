package kontroler;

import domen.Angazovanje;
import java.util.List;
import konstante.Operacija;
import transfer.Odgovor;
import transfer.Zahtev;

public class AngazovanjeKontroler {

    private static AngazovanjeKontroler instanca;

    private AngazovanjeKontroler() {

    }

    public static AngazovanjeKontroler getInstanca() {
        if (instanca == null) {
            instanca = new AngazovanjeKontroler();
        }
        return instanca;
    }

    public boolean kreirajAngazovanje(Angazovanje angazovanje) {
        ServerKontroler.getInstanca().posaljiZahtev(new Zahtev(angazovanje, Operacija.KREIRAJ_ANGAZOVANJE));
        Odgovor odgovor = ServerKontroler.getInstanca().primiOdgovor();

        return odgovor.getUspeh() == Operacija.USPEH;
    }

    public boolean obrisiAngazovanje(Angazovanje angazovanje) {
        ServerKontroler.getInstanca().posaljiZahtev(new Zahtev(angazovanje, Operacija.OBRISI_ANGAZOVANJE));
        Odgovor odgovor = ServerKontroler.getInstanca().primiOdgovor();

        return odgovor.getUspeh() == Operacija.USPEH;
    }

    public Angazovanje ucitajAngazovanje(Angazovanje angazovanje) {
        ServerKontroler.getInstanca().posaljiZahtev(new Zahtev(angazovanje, Operacija.UCITAJ_ANGAZOVANJE));
        Odgovor odgovor = ServerKontroler.getInstanca().primiOdgovor();

        return (Angazovanje) odgovor.getOdgovor();
    }

    public List<Angazovanje> ucitajListuAngazovanja(List<Angazovanje> angazovanja) {
        ServerKontroler.getInstanca().posaljiZahtev(new Zahtev(angazovanja, Operacija.UCITAJ_LISTU_ANGAZOVANJA));
        Odgovor odgovor = ServerKontroler.getInstanca().primiOdgovor();

        return (List<Angazovanje>) odgovor.getOdgovor();
    }

}
