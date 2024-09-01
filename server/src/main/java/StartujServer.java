
import gui.frame.ServerFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Glavna klasa za serversku aplikaciju
 *
 * @author dulait
 */
public class StartujServer {

    /**
     * Metoda koja predstavlja ulaz u serversku aplikaciju.
     *
     * @param args command-line argumenti (nekorišćeni).
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
