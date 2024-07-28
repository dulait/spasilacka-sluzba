package niti;

import domen.*;
import gui.frame.ServerFrame;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import konstante.Operacija;
import kontroleri.*;
import transfer.Odgovor;
import transfer.Zahtev;

/**
 * The {@code KlijentskaNit} class represents a client thread that handles
 * communication between the server and a client.
 * <p>
 * This class extends {@code Thread} and is responsible for processing client
 * requests and sending responses back to the client. Each client connection is
 * managed by a separate instance of {@code KlijentskaNit}.
 * </p>
 *
 * <p>
 * The class listens for incoming requests from the client, processes these
 * requests using appropriate controller classes, and sends back the results or
 * status messages to the client. It also manages the client connection's
 * lifecycle.
 * </p>
 *
 * @author dulait
 */
public class KlijentskaNit extends Thread {

    private Socket klijentskiSocket;
    private boolean signal;
    private final ServerFrame frame;

    /**
     * Constructs a {@code KlijentskaNit} with the specified client socket and
     * server frame.
     *
     * @param klijentskiSocket the socket used to communicate with the client
     * @param frame the server frame used to update the server's state
     */
    public KlijentskaNit(Socket klijentskiSocket, ServerFrame frame) {
        this.klijentskiSocket = klijentskiSocket;
        this.frame = frame;
    }

    /**
     * Gets the client socket used for communication with the client.
     *
     * @return the client socket
     */
    public Socket getKlijentskiSocket() {
        return klijentskiSocket;
    }

    /**
     * Sets the client socket used for communication with the client.
     *
     * @param klijentskiSocket the new client socket
     */
    public void setKlijentskiSocket(Socket klijentskiSocket) {
        this.klijentskiSocket = klijentskiSocket;
    }

    /**
     * Executes the thread's main logic to handle client requests and send
     * responses.
     * <p>
     * This method continuously listens for incoming requests from the client
     * and processes them based on the requested operation. The operations are
     * handled using appropriate controller classes, and responses are sent back
     * to the client.
     * </p>
     */
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

                case Operacija.EXPORT_TO_JSON -> {
                    String jsonFilePath = ExportKontroler.getInstanca().exportToJson();
                    if (jsonFilePath != null) {
                        o.setPoruka("Sistem je uspešno izvezao podatke u JSON datoteku: " + jsonFilePath);
                        o.setUspeh(Operacija.USPEH);
                    } else {
                        o.setPoruka("Sistem ne može da izveze podatke.");
                        o.setUspeh(Operacija.NEUSPEH);
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

    /**
     * Receives a request from the client.
     *
     * @return the received request, or {@code null} if an error occurs or the
     * client connection is closed
     */
    public Zahtev primiZahtev() {
        try {
            ObjectInputStream ois = new ObjectInputStream(klijentskiSocket.getInputStream());
            return (Zahtev) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Klijentski socket je zatvoren. Nije moguce primati zahteve.");
        }
        return null;
    }

    /**
     * Sends a response to the client.
     *
     * @param o the response to send
     */
    public void posaljiOdgovor(Odgovor o) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(klijentskiSocket.getOutputStream());
            oos.writeObject(o);
        } catch (IOException ex) {
            System.err.println("Klijentski socket je zatvoren. Nije moguce slati odgovore.");
        }
    }
}
