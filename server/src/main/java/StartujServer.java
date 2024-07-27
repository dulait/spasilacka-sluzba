
import gui.frame.ServerFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Startup class for initializing the server application.
 * <p>
 * This class sets the look and feel of the user interface to Nimbus and then
 * creates and displays the server frame.
 * </p>
 *
 * @author dulait
 */
public class StartujServer {

    /**
     * Main method to start the server application.
     * <p>
     * Sets the Nimbus look and feel and then initializes the
     * {@link ServerFrame}. The server frame is set to be visible once the GUI
     * is fully initialized.
     * </p>
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.invokeLater(() -> {
                new ServerFrame().setVisible(true);
            });
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            System.out.println("Greska prilikom inicijalizacije serverske forme\n" + e.getMessage());
        }
    }
}
