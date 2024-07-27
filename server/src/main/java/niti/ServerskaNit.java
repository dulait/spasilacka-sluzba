package niti;

import config.Config;
import gui.frame.ServerFrame;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code ServerskaNit} class represents a server thread that handles
 * incoming client connections and manages client threads.
 * <p>
 * This class extends {@code Thread} and is responsible for listening for
 * incoming client connections, accepting them, and creating and starting
 * {@code KlijentskaNit} threads to handle communication with each client.
 * </p>
 *
 * <p>
 * The server thread maintains a list of active client threads and provides
 * methods to stop the server and close client connections.
 * </p>
 *
 * @author dulait
 */
public class ServerskaNit extends Thread {

    private ServerSocket serverskiSocket;
    private boolean signal;
    private List<KlijentskaNit> klijenti;
    private ServerFrame frame;

    /**
     * Constructs a {@code ServerskaNit} with the specified server frame.
     * <p>
     * Initializes the server socket to listen on the port specified in the
     * configuration.
     * </p>
     *
     * @param frame the server frame used to manage the server's state
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
     * Gets the server socket used to accept incoming client connections.
     *
     * @return the server socket
     */
    public ServerSocket getServerskiSocket() {
        return serverskiSocket;
    }

    /**
     * Checks if the server is active.
     *
     * @return {@code true} if the server is active, {@code false} otherwise
     */
    public boolean isSignal() {
        return signal;
    }

    /**
     * Executes the thread's main logic to accept and handle client connections.
     * <p>
     * This method continuously listens for incoming client connections. When a
     * client connects, it creates a new {@code KlijentskaNit} instance to
     * handle communication with the client and adds it to the list of active
     * clients.
     * </p>
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
     * Stops the server and closes all active client connections.
     * <p>
     * This method interrupts all active client threads, closes their sockets,
     * and then closes the server socket.
     * </p>
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
     * Closes a specific client connection.
     * <p>
     * This method interrupts the specified client thread and closes its socket.
     * </p>
     *
     * @param nk the client thread to be closed
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
