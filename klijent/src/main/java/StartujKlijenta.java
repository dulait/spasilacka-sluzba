
import gui.frame.LoginFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import niti.KlijentskaNit;

/**
 * Glavna klasa za klijentsku aplikaciju.
 *
 * @author dulait
 */
public class StartujKlijenta {

    /**
     * Metoda koja predstavlja ulaz za klijentsku aplikaciju.
     *
     * @param args command-line argumenti (nekorišćeni).
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
