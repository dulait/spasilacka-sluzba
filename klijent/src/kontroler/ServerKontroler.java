package kontroler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.Odgovor;
import transfer.Zahtev;

public class ServerKontroler {

    private Socket socket;
    private static ServerKontroler instanca;

    private ServerKontroler() {
    }

    public static ServerKontroler getInstanca() {
        if (instanca == null) {
            instanca = new ServerKontroler();
        }
        return instanca;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void posaljiZahtev(Zahtev z) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(z);
        } catch (IOException ex) {
            System.exit(0);
        }
    }

    public Odgovor primiOdgovor() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            return (Odgovor) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.exit(0);
        }
        return null;
    }

}
