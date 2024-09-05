package domen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.mockito.Mockito.*;

public class KoordinatorTest {

    private Koordinator koordinator;

    @BeforeEach
    public void setUp() {
        koordinator = new Koordinator(1, "user1", "password1", "Dusan", "Draskovic");
    }

    @ParameterizedTest
    @CsvSource({
        "1, user1, password1, Dusan, Draskovic",
        "2, user2, password2, Marko, Markovic"
    })
    void testConstructor(int id, String korisnickoIme, String lozinka, String ime, String prezime) {
        Koordinator koordinator = new Koordinator(id, korisnickoIme, lozinka, ime, prezime);

        assertEquals(id, koordinator.getId());
        assertEquals(korisnickoIme, koordinator.getKorisnickoIme());
        assertEquals(lozinka, koordinator.getLozinka());
        assertEquals(ime, koordinator.getIme());
        assertEquals(prezime, koordinator.getPrezime());
    }

    @Test
    public void testSetKorisnickoIme() {
        koordinator.setKorisnickoIme("username");
        assertEquals("username", koordinator.getKorisnickoIme());
    }

    @Test
    public void testSetLozinka() {
        koordinator.setLozinka("newPassword");
        assertEquals("newPassword", koordinator.getLozinka());
    }

    @Test
    public void testSetIme() {
        koordinator.setIme("NewIme");
        assertEquals("NewIme", koordinator.getIme());
    }

    @Test
    public void testSetPrezime() {
        koordinator.setPrezime("Prezimenovic");
        assertEquals("Prezimenovic", koordinator.getPrezime());
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
