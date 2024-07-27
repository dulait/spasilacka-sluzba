package niti;

import java.awt.Frame;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import javax.swing.JOptionPane;
import konstante.Operacija;
import kontroler.ServerKontroler;
import transfer.Odgovor;
import transfer.Zahtev;

/**
 * Thread that connects the Client and the Server using a predefined Socket port
 *
 * @author dulait
 */
public class KlijentskaNit extends Thread {

    private final int port;
    private Socket socket;

    public KlijentskaNit(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            socket = new Socket("localhost", port);
            ServerKontroler.getInstanca().setSocket(socket);
        } catch (SocketException ex) {
            System.err.println("Konekcija zatvorena: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Server nije pokrenut", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        } catch (IOException ex) {
            System.out.println("Greska: " + ex.getMessage());
        }
    }

    public void zatvoriKonekciju(Frame frame) {
        int odgovor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da želite da zatvorite program?",
                "Potvrda zatvaranja", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (odgovor == JOptionPane.YES_OPTION) {
            ServerKontroler.getInstanca().posaljiZahtev(new Zahtev(null, Operacija.ZATVORI_KONEKCIJU));
            Odgovor o = ServerKontroler.getInstanca().primiOdgovor();

            if (o.getUspeh() == Operacija.USPEH) {
                frame.dispose();
                this.interrupt();
            } else {
                JOptionPane.showMessageDialog(frame, o.getPoruka(), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
