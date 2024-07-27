
import gui.frame.LoginFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import niti.KlijentskaNit;

/**
 * Startup class for initializing the client application.
 * <p>
 * This class sets the look and feel of the user interface to Nimbus and then
 * creates and displays the login frame. It also initializes a client thread
 * (`KlijentskaNit`) that connects to a server on port 9000.
 * </p>
 *
 * @author dulait
 */
public class StartujKlijenta {

    /**
     * Main method to start the client application.
     * <p>
     * Sets the Nimbus look and feel and then initializes the {@link LoginFrame}
     * with a new instance of {@link KlijentskaNit} for connecting to the
     * server. The login frame is set to be visible once the GUI is fully
     * initialized.
     * </p>
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.invokeLater(() -> {
                new LoginFrame(new KlijentskaNit(9000)).setVisible(true);
            });
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            System.out.println("Greska prilikom inicijalizacije login forme\n" + e.getMessage());
        }
    }
}
