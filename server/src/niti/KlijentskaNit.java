package niti;

import domen.Angazovanje;
import domen.Izvestaj;
import domen.Koordinator;
import domen.OpstiDomenskiObjekat;
import domen.Raspored;
import domen.Smena;
import domen.Spasilac;
import gui.frame.ServerFrame;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import konstante.Operacija;
import kontroleri.AngazovanjeKontroler;
import kontroleri.IzvestajKontroler;
import kontroleri.KoordinatorKontroler;
import kontroleri.RasporedKontroler;
import kontroleri.SmenaKontroler;
import kontroleri.SpasilacKontroler;
import transfer.Odgovor;
import transfer.Zahtev;

public class KlijentskaNit extends Thread {

    private Socket klijentskiSocket;
    private boolean signal;
    private final ServerFrame frame;

    public KlijentskaNit(Socket klijentskiSocket, ServerFrame frame) {
        this.klijentskiSocket = klijentskiSocket;
        this.frame = frame;
    }

    public Socket getKlijentskiSocket() {
        return klijentskiSocket;
    }

    public void setKlijentskiSocket(Socket klijentskiSocket) {
        this.klijentskiSocket = klijentskiSocket;
    }

    @Override
    public void run() {

        signal = true;

        while (signal && !isInterrupted()) {
            Zahtev z = primiZahtev();

            if (z == null) {
                break;
            }

            Odgovor o = new Odgovor();
            boolean uspeh;

            OpstiDomenskiObjekat koordinator;
            OpstiDomenskiObjekat spasilac;
            OpstiDomenskiObjekat smena;
            OpstiDomenskiObjekat raspored;
            OpstiDomenskiObjekat angazovanje;
            OpstiDomenskiObjekat izvestaj;
            List<OpstiDomenskiObjekat> lista;

            switch (z.getOperacija()) {

                case Operacija.PRIJAVI_KOORDINATORA -> {
                    koordinator = KoordinatorKontroler.getInstanca().prijaviKoordinatora((Koordinator) z.getParametar());
                    if (koordinator != null) {
                        frame.dodajKoordinatora((Koordinator) koordinator, getKlijentskiSocket());
                        o.setOdgovor(koordinator);
                        o.setUspeh(Operacija.USPEH);
                    } else {
                        o.setOdgovor(null);
                        o.setUspeh(Operacija.NEUSPEH);
                    }
                }
                case Operacija.UCITAJ_KOORDINATORA -> {
                    koordinator = KoordinatorKontroler.getInstanca().ucitajKoordinatora((Koordinator) z.getParametar());
                    o.setOdgovor(koordinator);
                }

                case Operacija.UCITAJ_SPASIOCA -> {
                    spasilac = SpasilacKontroler.getInstanca().ucitajSpasioca((Spasilac) z.getParametar());
                    o.setOdgovor(spasilac);
                }

                case Operacija.UCITAJ_SMENU -> {
                    smena = SmenaKontroler.getInstanca().ucitajSmenu((Smena) z.getParametar());
                    o.setOdgovor(smena);
                }

                case Operacija.UCITAJ_ANGAZOVANJE -> {
                    angazovanje = AngazovanjeKontroler.getInstanca().ucitajAngazovanje((Angazovanje) z.getParametar());
                    o.setOdgovor(angazovanje);
                }

                case Operacija.UCITAJ_RASPORED -> {
                    raspored = RasporedKontroler.getInstanca().ucitajRaspored((Raspored) z.getParametar());
                    o.setOdgovor(raspored);
                }

                case Operacija.UCITAJ_IZVESTAJ -> {
                    izvestaj = IzvestajKontroler.getInstanca().ucitajIzvestaj((Izvestaj) z.getParametar());
                    o.setOdgovor(izvestaj);
                }

                case Operacija.UCITAJ_LISTU_SPASIOCA -> {
                    lista = SpasilacKontroler.getInstanca().ucitajListuSpasioca();
                    o.setOdgovor(lista);
                }

                case Operacija.UCITAJ_LISTU_SMENA -> {
                    lista = SmenaKontroler.getInstanca().ucitajListuSmena();
                    o.setOdgovor(lista);
                }

                case Operacija.UCITAJ_LISTU_RASPOREDA -> {
                    lista = RasporedKontroler.getInstanca().ucitajListuRasporeda();
                    o.setOdgovor(lista);
                }

                case Operacija.UCITAJ_LISTU_ANGAZOVANJA -> {
                    lista = AngazovanjeKontroler.getInstanca().ucitajListuAngazovanja();
                    o.setOdgovor(lista);
                }

                case Operacija.UCITAJ_LISTU_IZVESTAJA -> {
                    lista = IzvestajKontroler.getInstanca().ucitajListuIzvestaja();
                    o.setOdgovor(lista);
                }

                case Operacija.KREIRAJ_SPASIOCA -> {
                    uspeh = SpasilacKontroler.getInstanca().kreirajSpasioca((Spasilac) z.getParametar());
                    if (uspeh) {
                        o.setPoruka("Sistem je kreirao spasioca");
                        o.setUspeh(Operacija.USPEH);
                    } else {
                        o.setPoruka("Sistem ne moze da kreira spasioca");
                        o.setUspeh(Operacija.NEUSPEH);
                    }
                }

                case Operacija.KREIRAJ_SMENU -> {
                    uspeh = SmenaKontroler.getInstanca().kreirajSmenu((Smena) z.getParametar());
                    if (uspeh) {
                        o.setPoruka("Sistem je kreirao smenu");
                        o.setUspeh(Operacija.USPEH);
                    } else {
                        o.setPoruka("Sistem ne moze da kreira smenu");
                        o.setUspeh(Operacija.NEUSPEH);
                    }
                }

                case Operacija.KREIRAJ_RASPORED -> {
                    uspeh = RasporedKontroler.getInstanca().kreirajRaspored((Raspored) z.getParametar());
                    if (uspeh) {
                        o.setPoruka("Sistem je kreirao raspored");
                        o.setUspeh(Operacija.USPEH);
                    } else {
                        o.setPoruka("Sistem ne moze da kreira raspored");
                        o.setUspeh(Operacija.NEUSPEH);
                    }
                }

                case Operacija.KREIRAJ_ANGAZOVANJE -> {
                    uspeh = AngazovanjeKontroler.getInstanca().kreirajAngazovanje((Angazovanje) z.getParametar());
                    if (uspeh) {
                        o.setPoruka("Sistem je kreirao angazovanje");
                        o.setUspeh(Operacija.USPEH);
                    } else {
                        o.setPoruka("Sistem ne moze da kreira angazovanje");
                        o.setUspeh(Operacija.NEUSPEH);
                    }
                }

                case Operacija.KREIRAJ_IZVESTAJ -> {
                    uspeh = IzvestajKontroler.getInstanca().kreirajIzvestaj((Izvestaj) z.getParametar());
                    if (uspeh) {
                        o.setPoruka("Sistem je kreirao izvestaj");
                        o.setUspeh(Operacija.USPEH);
                    } else {
                        o.setPoruka("Sistem ne moze da kreira izvestaj");
                        o.setUspeh(Operacija.NEUSPEH);
                    }
                }

                case Operacija.AZURIRAJ_SPASIOCA -> {
                    uspeh = SpasilacKontroler.getInstanca().azurirajSpasioca((Spasilac) z.getParametar());
                    if (uspeh) {
                        o.setPoruka("Sistem je azurirao spasioca");
                        o.setUspeh(Operacija.USPEH);
                    } else {
                        o.setPoruka("Sistem ne moze da azurira spasioca");
                        o.setUspeh(Operacija.NEUSPEH);
                    }
                }

                case Operacija.AZURIRAJ_SMENU -> {
                    uspeh = SmenaKontroler.getInstanca().azurirajSmenu((Smena) z.getParametar());
                    if (uspeh) {
                        o.setPoruka("Sistem je azurirao smenu");
                        o.setUspeh(Operacija.USPEH);
                    } else {
                        o.setPoruka("Sistem ne moze da azurira smenu");
                        o.setUspeh(Operacija.NEUSPEH);
                    }
                }

                case Operacija.AZURIRAJ_IZVESTAJ -> {
                    uspeh = IzvestajKontroler.getInstanca().azurirajIzvestaj((Izvestaj) z.getParametar());
                    if (uspeh) {
                        o.setPoruka("Sistem je azurirao izvestaj");
                        o.setUspeh(Operacija.USPEH);
                    } else {
                        o.setPoruka("Sistem ne moze da azurira izvestaj");
                        o.setUspeh(Operacija.NEUSPEH);
                    }
                }

                case Operacija.OBRISI_RASPORED -> {
                    uspeh = RasporedKontroler.getInstanca().obrisiRaspored((Raspored) z.getParametar());
                    if (uspeh) {
                        o.setPoruka("Sistem je obrisao raspored");
                        o.setUspeh(Operacija.USPEH);
                    } else {
                        o.setPoruka("Sistem ne moze da obrise raspored");
                        o.setUspeh(Operacija.NEUSPEH);
                    }
                }

                case Operacija.OBRISI_ANGAZOVANJE -> {
                    uspeh = AngazovanjeKontroler.getInstanca().obrisiAngazovanje((Angazovanje) z.getParametar());
                    if (uspeh) {
                        o.setPoruka("Sistem je obrisao angazovanje");
                        o.setUspeh(Operacija.USPEH);
                    } else {
                        o.setPoruka("Sistem ne moze da obrise angazovanje");
                        o.setUspeh(Operacija.NEUSPEH);
                    }
                }

                case Operacija.PRETRAZI_SPASIOCE -> {
                    lista = SpasilacKontroler.getInstanca().pretraziSpasioce((String) z.getParametar());
                    o.setOdgovor(lista);

                    if (lista.isEmpty()) {
                        o.setPoruka("Sistem ne moze da nadje spasioca po zadatoj vrednosti");
                    }
                }

                case Operacija.ZATVORI_KONEKCIJU -> {
                    try {
                        System.out.println("Klijentski program zatvoren: " + getKlijentskiSocket());
                        frame.ukloniKoordinatora(getKlijentskiSocket());

                        o.setPoruka("KLIJENT_ZATVOREN");
                        posaljiOdgovor(o);

                        this.interrupt();
                        getKlijentskiSocket().close();
                        break;
                    } catch (IOException ex) {
                        System.err.println("ZATVORI_KONEKCIJU: " + ex.getMessage());
                    }
                }

            }
            if (!getKlijentskiSocket().isClosed()) {
                posaljiOdgovor(o);
            }
        }
    }

    public Zahtev primiZahtev() {
        try {
            ObjectInputStream ois = new ObjectInputStream(klijentskiSocket.getInputStream());
            return (Zahtev) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Klijentski socket je zatvoren. Nije moguce primati zahteve.");
        }
        return null;
    }

    public void posaljiOdgovor(Odgovor o) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(klijentskiSocket.getOutputStream());
            oos.writeObject(o);
        } catch (IOException ex) {
            System.err.println("Klijentski socket je zatvoren. Nije moguce slati odgovore.");
        }
    }

}
