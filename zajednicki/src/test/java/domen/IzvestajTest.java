package domen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.mockito.Mockito.*;

public class IzvestajTest {

    private Izvestaj izvestaj;
    private Angazovanje angazovanje;
    private Spasilac spasilac;
    private Smena smena;
    private Raspored raspored;

    @BeforeEach
    public void setUp() {
        spasilac = new Spasilac(1, "Dusan", "Draskovic", "1234567890123");
        smena = new Smena(1, 8, 16);
        raspored = new Raspored(1, LocalDate.now());
        angazovanje = new Angazovanje(spasilac, smena, raspored);
        izvestaj = new Izvestaj(angazovanje, "Test opis");
    }

    @ParameterizedTest
    @CsvSource({
        "1, Dusan, Draskovic, 1234567890123, 1, 8, 16, 1, 'Opis dusan'",
        "2, Marko, Markovic, 9876543210123, 2, 9, 17, 2, 'Opis marko'"
    })
    void testConstructor(int spasilacId, String spasilacIme, String spasilacPrezime, String spasilacJmbg,
            int smenaId, int smenaPocetak, int smenaKraj,
            int rasporedId, String opis) {
        Spasilac spasilac = new Spasilac(spasilacId, spasilacIme, spasilacPrezime, spasilacJmbg);
        Smena smena = new Smena(smenaId, smenaPocetak, smenaKraj);
        Raspored raspored = new Raspored(rasporedId, LocalDate.now());
        Angazovanje angazovanje = new Angazovanje(spasilac, smena, raspored);

        Izvestaj izvestaj = new Izvestaj(angazovanje, opis);

        assertEquals(angazovanje, izvestaj.getAngazovanje());
        assertEquals(opis, izvestaj.getOpis());
    }

    @Test
    void testSetAngazovanje() {
        Angazovanje newAngazovanje = new Angazovanje(new Spasilac(2, "Marko", "Markovic", "9876543210123"), new Smena(2, 9, 17), new Raspored(2, LocalDate.now().plusDays(1)));
        izvestaj.setAngazovanje(newAngazovanje);
        assertEquals(newAngazovanje, izvestaj.getAngazovanje());
    }

    @Test
    void testSetOpis() {
        String newOpis = "Updated description";
        izvestaj.setOpis(newOpis);
        assertEquals(newOpis, izvestaj.getOpis());
    }

    @Test
    public void testGetNazivTabele() {
        assertEquals("izvestaj", izvestaj.getNazivTabele());
    }

    @Test
    public void testGetParametre() {
        String expected = String.format("%d, %d, %d, '%s'",
                spasilac.getId(), smena.getId(), raspored.getId(), "Test opis");
        assertEquals(expected, izvestaj.getParametre());
    }

    @Test
    public void testGetNaziveParametara() {
        assertEquals("spasilacId, smenaId, rasporedId, opis", izvestaj.getNaziveParametara());
    }

    @Test
    public void testGetNazivPrimarnogKljuca() {
        assertNull(izvestaj.getNazivPrimarnogKljuca());
    }

    @Test
    public void testGetVrednostPrimarnogKljuca() {
        assertNull(izvestaj.getVrednostPrimarnogKljuca());
    }

    @Test
    public void testGetSlozeniPrimarniKljuc() {
        String expected = String.format("spasilacId = %d AND smenaId = %d AND rasporedId = %d",
                spasilac.getId(), smena.getId(), raspored.getId());
        assertEquals(expected, izvestaj.getSlozeniPrimarniKljuc());
    }

    @Test
    public void testGetSelectUpit() {
        String expected = "SELECT izv.*, ang.*, spa.*, sme.*, ras.* "
                + "FROM izvestaj izv "
                + "JOIN angazovanje ang ON izv.spasilacId = ang.spasilacId AND izv.smenaId = ang.smenaId AND izv.rasporedId = ang.rasporedId "
                + "JOIN spasilac spa ON ang.spasilacId = spa.id "
                + "JOIN smena sme ON ang.smenaId = sme.id "
                + "JOIN raspored ras ON ang.rasporedId = ras.id";
        assertEquals(expected, izvestaj.getSelectUpit());
    }

    @Test
    public void testGetSelectUpitPoParametru() {
        String expected = "SELECT izv.*, ang.*, spa.*, sme.*, ras.* "
                + "FROM izvestaj izv "
                + "JOIN angazovanje ang ON izv.spasilacId = ang.spasilacId AND izv.smenaId = ang.smenaId AND izv.rasporedId = ang.rasporedId "
                + "JOIN spasilac spa ON ang.spasilacId = spa.id "
                + "JOIN smena sme ON ang.smenaId = sme.id "
                + "JOIN raspored ras ON ang.rasporedId = ras.id "
                + "WHERE " + izvestaj.getSlozeniPrimarniKljuc();
        assertEquals(expected, izvestaj.getSelectUpitPoParametru());
    }

    @Test
    public void testGetInsertUpit() {
        String expected = "INSERT INTO izvestaj(spasilacId, smenaId, rasporedId, opis) VALUES (" + izvestaj.getParametre() + ")";
        assertEquals(expected, izvestaj.getInsertUpit());
    }

    @Test
    public void testGetUpdateUpit() {
        String expected = "UPDATE izvestaj SET spasilacId = " + spasilac.getId() + ", smenaId = " + smena.getId()
                + ", rasporedId = " + raspored.getId() + ", opis = 'Test opis' WHERE " + izvestaj.getSlozeniPrimarniKljuc();
        assertEquals(expected, izvestaj.getUpdateUpit());
    }

    @Test
    public void testGetUpdateParametre() {
        String expected = String.format("spasilacId = %d, smenaId = %d, rasporedId = %d, opis = '%s'",
                spasilac.getId(), smena.getId(), raspored.getId(), "Test opis");
        assertEquals(expected, izvestaj.getUpdateParametre());
    }

    @Test
    public void testGetDeleteUpit() {
        String expected = "DELETE FROM izvestaj WHERE " + izvestaj.getSlozeniPrimarniKljuc();
        assertEquals(expected, izvestaj.getDeleteUpit());
    }

    @Test
    public void testKonvertujRSUListu() throws SQLException {
        ResultSet rs = mock(ResultSet.class);

        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getString("izv.opis")).thenReturn("Test opis");

        when(rs.getInt("spa.id")).thenReturn(1);
        when(rs.getString("spa.ime")).thenReturn("Dusan");
        when(rs.getString("spa.prezime")).thenReturn("Draskovic");
        when(rs.getString("spa.jmbg")).thenReturn("1234567890123");

        when(rs.getInt("sme.id")).thenReturn(1);
        when(rs.getInt("sme.pocetak")).thenReturn(8);
        when(rs.getInt("sme.kraj")).thenReturn(16);

        when(rs.getInt("ras.id")).thenReturn(1);
        when(rs.getDate("ras.datum")).thenReturn(Date.valueOf(LocalDate.now()));

        ArrayList<OpstiDomenskiObjekat> result = izvestaj.konvertujRSUListu(rs);

        assertNotNull(result);
        assertEquals(1, result.size());
        Izvestaj rsIzvestaj = (Izvestaj) result.get(0);
        assertEquals(izvestaj.getOpis(), rsIzvestaj.getOpis());
        assertEquals(izvestaj.getAngazovanje(), rsIzvestaj.getAngazovanje());
    }
}
