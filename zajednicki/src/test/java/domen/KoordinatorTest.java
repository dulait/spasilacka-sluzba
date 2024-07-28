package domen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class KoordinatorTest {

    private Koordinator koordinator;

    @BeforeEach
    public void setUp() {
        koordinator = new Koordinator(1, "user1", "password1", "Dusan", "Draskovic");
    }

    @Test
    public void testGetNazivTabele() {
        assertEquals("koordinator", koordinator.getNazivTabele());
    }

    @Test
    public void testGetParametre() {
        assertEquals("1, 'user1', 'password1', 'Dusan', 'Draskovic'", koordinator.getParametre());
    }

    @Test
    public void testGetNaziveParametara() {
        assertEquals("id, korisnickoIme, lozinka, ime, prezime", koordinator.getNaziveParametara());
    }

    @Test
    public void testGetNazivPrimarnogKljuca() {
        assertEquals("id", koordinator.getNazivPrimarnogKljuca());
    }

    @Test
    public void testGetVrednostPrimarnogKljuca() {
        assertEquals(1, koordinator.getVrednostPrimarnogKljuca());
    }

    @Test
    public void testGetSelectUpit() {
        assertEquals("SELECT * FROM koordinator", koordinator.getSelectUpit());
    }

    @Test
    public void testGetSelectUpitPoParametru() {
        assertEquals("SELECT * FROM koordinator WHERE id = 1", koordinator.getSelectUpitPoParametru());
    }

    @Test
    public void testGetUpdateUpit() {
        assertEquals("id = 1, korisnickoIme = 'user1', lozinka = 'password1', ime = 'Dusan', prezime = 'Draskovic'", koordinator.getUpdateUpit());
    }

    @Test
    public void testToString() {
        assertEquals("Dusan Draskovic", koordinator.toString());
    }

    @Test
    public void testKonvertujRSUListu() throws SQLException {
        ResultSet rs = mock(ResultSet.class);

        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(1);
        when(rs.getString("korisnickoIme")).thenReturn("user1");
        when(rs.getString("lozinka")).thenReturn("password1");
        when(rs.getString("ime")).thenReturn("Dusan");
        when(rs.getString("prezime")).thenReturn("Draskovic");

        ArrayList<OpstiDomenskiObjekat> result = koordinator.konvertujRSUListu(rs);

        assertEquals(1, result.size());

        Koordinator resultKoordinator = (Koordinator) result.get(0);
        assertEquals(1, resultKoordinator.getId());
        assertEquals("user1", resultKoordinator.getKorisnickoIme());
        assertEquals("password1", resultKoordinator.getLozinka());
        assertEquals("Dusan", resultKoordinator.getIme());
        assertEquals("Draskovic", resultKoordinator.getPrezime());

        verify(rs, times(2)).next();  // Adjusting the verification to account for the loop
        verify(rs, times(1)).getInt("id");
        verify(rs, times(1)).getString("korisnickoIme");
        verify(rs, times(1)).getString("lozinka");
        verify(rs, times(1)).getString("ime");
        verify(rs, times(1)).getString("prezime");
    }

}
