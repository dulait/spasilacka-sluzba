package niti;

import config.Config;
import gui.frame.ServerFrame;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa za podizanje konekcije na serverskoj strani
 *
 * @author dulait
 */
public class ServerskaNit extends Thread {

    private ServerSocket serverskiSocket;
    private boolean signal;
    private List<KlijentskaNit> klijenti;
    private ServerFrame frame;

    /**
     * Konstruktor koji inicijalizuje socket i frame koji prikazuje serverska
     * strana aplikacije.
     *
     * @param frame frame koji se prikazuje
     */
    public ServerskaNit(ServerFrame frame) {
        try {
            serverskiSocket = new ServerSocket(Config.getPort());
            signal = true;
            klijenti = new ArrayList<>();
            this.frame = frame;
            System.out.println("Server pokrenut");
        } catch (IOException ex) {
            System.err.println("ServerskaNit::ServerskaNit " + ex.getMessage());
        }
    }

    /**
     * Vraća serverski socket koji se koristi za konekciju sa klijentom
     *
     * @return serverski socket
     */
    public ServerSocket getServerskiSocket() {
        return serverskiSocket;
    }

    /**
     * Proverava da li je serverska nit pokrenuta
     *
     * @return {@code true} ako je pokrenuta, inače {@code false}
     */
    public boolean isSignal() {
        return signal;
    }

    /**
     * Metoda koja pokreće serversku nit
     */
    @Override
    public void run() {
        while (signal && !isInterrupted()) {
            try {
                Socket klijentskiSocket = serverskiSocket.accept();
                KlijentskaNit kn = new KlijentskaNit(klijentskiSocket, frame);
                kn.start();
                klijenti.add(kn);
                System.out.println("Klijent povezan");
            } catch (IOException ex) {
                System.err.println("ServerskaNit::run " + ex.getMessage());
                break;
            }
        }
    }

    /**
     * Zaustavlja server kao i sve konekcije
     */
    public void zaustaviServer() {
        for (KlijentskaNit nk : klijenti) {
            zatvoriKlijenta(nk);
        }
        try {
            serverskiSocket.close();
        } catch (IOException ex) {
            System.err.println("Server zaustavljen: " + serverskiSocket.getLocalPort());
        }
    }

    /**
     * Zatvara konekciju sa klijentom
     *
     * @param nk nit koja se zatvara
     */
    private void zatvoriKlijenta(KlijentskaNit nk) {
        try {
            nk.interrupt();
            nk.getKlijentskiSocket().close();
            System.err.println("Klijent zatvoren: " + nk.getName());
        } catch (IOException ex) {
            System.err.println("Klijent ne moze da se zatvori: " + nk.getName());
        }
    }
}
