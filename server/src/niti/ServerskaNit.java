package niti;

import config.Config;
import gui.frame.ServerFrame;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerskaNit extends Thread {

    private ServerSocket serverskiSocket;
    private boolean signal;
    private List<KlijentskaNit> klijenti;
    private ServerFrame frame;

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

    public ServerSocket getServerskiSocket() {
        return serverskiSocket;
    }

    public boolean isSignal() {
        return signal;
    }

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
