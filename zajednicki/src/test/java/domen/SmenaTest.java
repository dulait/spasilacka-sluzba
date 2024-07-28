package domen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SmenaTest {

    private Smena smena;

    @BeforeEach
    public void setUp() {
        smena = new Smena(1, 8, 16);
    }

    @Test
    public void testGetNazivTabele() {
        assertEquals("smena", smena.getNazivTabele());
    }

    @Test
    public void testGetParametre() {
        assertEquals("1, 8, 16", smena.getParametre());
    }

    @Test
    public void testGetNaziveParametara() {
        assertEquals("id, pocetak, kraj", smena.getNaziveParametara());
    }

    @Test
    public void testGetNazivPrimarnogKljuca() {
        assertEquals("id", smena.getNazivPrimarnogKljuca());
    }

    @Test
    public void testGetVrednostPrimarnogKljuca() {
        assertEquals(1, smena.getVrednostPrimarnogKljuca());
    }

    @Test
    public void testGetSelectUpit() {
        assertEquals("SELECT * FROM smena", smena.getSelectUpit());
    }

    @Test
    public void testGetSelectUpitPoParametru() {
        assertEquals("SELECT * FROM smena WHERE id = 1 OR (pocetak = 8 AND kraj = 16)", smena.getSelectUpitPoParametru());
    }

    @Test
    public void testToString() {
        assertEquals("8:00h - 16:00h", smena.toString());
    }

    @Test
    public void testKonvertujRSUListu() throws SQLException {
        ResultSet rs = mock(ResultSet.class);

        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(1);
        when(rs.getInt("pocetak")).thenReturn(8);
        when(rs.getInt("kraj")).thenReturn(16);

        ArrayList<OpstiDomenskiObjekat> result = smena.konvertujRSUListu(rs);

        assertEquals(1, result.size());

        Smena resultSmena = (Smena) result.get(0);
        assertEquals(1, resultSmena.getId());
        assertEquals(8, resultSmena.getPocetak());
        assertEquals(16, resultSmena.getKraj());

        verify(rs, times(2)).next();
        verify(rs, times(1)).getInt("id");
        verify(rs, times(1)).getInt("pocetak");
        verify(rs, times(1)).getInt("kraj");
    }

    @Test
    public void testEqualsAndHashCode() {
        Smena smena1 = new Smena(1, 8, 16);
        Smena smena2 = new Smena(1, 8, 16);
        Smena smena3 = new Smena(2, 9, 17);

        assertEquals(smena1, smena2);
        assertNotEquals(smena1, smena3);
        assertEquals(smena1.hashCode(), smena2.hashCode());
        assertNotEquals(smena1.hashCode(), smena3.hashCode());
    }
}
