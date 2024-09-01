package domen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.mockito.Mockito.*;

public class SpasilacTest {

    private Spasilac spasilac;

    @BeforeEach
    public void setUp() {
        spasilac = new Spasilac(1, "Dusan", "Draskovic", "1234567890123");
    }

    @ParameterizedTest
    @CsvSource({
        "1, 'Dusan', 'Draskovic', '1234567890123'",
        "2, 'Marija', 'Draskovic', '1234567890124'"
    })
    void testConstructor(int id, String ime, String prezime, String jmbg) {
        Spasilac spasilac = new Spasilac(id, ime, prezime, jmbg);

        assertEquals(id, spasilac.getId());
        assertEquals(ime, spasilac.getIme());
        assertEquals(prezime, spasilac.getPrezime());
        assertEquals(jmbg, spasilac.getJmbg());
    }

    @Test
    public void testSetIme() {
        spasilac.setIme("Marija");
        assertEquals("Marija", spasilac.getIme());
    }

    @Test
    public void testSetPrezime() {
        spasilac.setPrezime("Petrovic");
        assertEquals("Petrovic", spasilac.getPrezime());
    }

    @Test
    public void testSetJmbg() {
        spasilac.setJmbg("1234567890124");
        assertEquals("1234567890124", spasilac.getJmbg());
    }

    @Test
    public void testGetNazivTabele() {
        assertEquals("spasilac", spasilac.getNazivTabele());
    }

    @Test
    public void testGetParametre() {
        assertEquals("1, 'Dusan', 'Draskovic', '1234567890123'", spasilac.getParametre());
    }

    @Test
    public void testGetNaziveParametara() {
        assertEquals("id, ime, prezime, jmbg", spasilac.getNaziveParametara());
    }

    @Test
    public void testGetNazivPrimarnogKljuca() {
        assertEquals("id", spasilac.getNazivPrimarnogKljuca());
    }

    @Test
    public void testGetVrednostPrimarnogKljuca() {
        assertEquals(1, spasilac.getVrednostPrimarnogKljuca());
    }

    @Test
    public void testGetSelectUpit() {
        assertEquals("SELECT * FROM spasilac", spasilac.getSelectUpit());
    }

    @Test
    public void testGetSelectUpitPoParametru() {
        assertEquals("SELECT * FROM spasilac WHERE id = 1 OR jmbg = '1234567890123'", spasilac.getSelectUpitPoParametru());
    }

    @Test
    public void testToString() {
        assertEquals("Dusan Draskovic", spasilac.toString());
    }

    @Test
    public void testKonvertujRSUListu() throws SQLException {
        ResultSet rs = mock(ResultSet.class);

        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(1);
        when(rs.getString("ime")).thenReturn("Dusan");
        when(rs.getString("prezime")).thenReturn("Draskovic");
        when(rs.getString("jmbg")).thenReturn("1234567890123");

        List<OpstiDomenskiObjekat> result = spasilac.konvertujRSUListu(rs);

        assertEquals(1, result.size());

        Spasilac resultSpasilac = (Spasilac) result.get(0);
        assertEquals(1, resultSpasilac.getId());
        assertEquals("Dusan", resultSpasilac.getIme());
        assertEquals("Draskovic", resultSpasilac.getPrezime());
        assertEquals("1234567890123", resultSpasilac.getJmbg());

        verify(rs, times(2)).next();
        verify(rs, times(1)).getInt("id");
        verify(rs, times(1)).getString("ime");
        verify(rs, times(1)).getString("prezime");
        verify(rs, times(1)).getString("jmbg");
    }

    @Test
    public void testEqualsAndHashCode() {
        Spasilac spasilac1 = new Spasilac(1, "Dusan", "Draskovic", "1234567890123");
        Spasilac spasilac2 = new Spasilac(1, "Dusan", "Draskovic", "1234567890123");
        Spasilac spasilac3 = new Spasilac(2, "Marija", "Draskovic", "1234567890124");

        assertEquals(spasilac1, spasilac2);
        assertNotEquals(spasilac1, spasilac3);
        assertEquals(spasilac1.hashCode(), spasilac2.hashCode());
        assertNotEquals(spasilac1.hashCode(), spasilac3.hashCode());
    }
}
