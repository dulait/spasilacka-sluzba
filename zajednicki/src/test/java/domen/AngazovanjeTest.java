package domen;

import java.sql.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.mockito.Mockito.*;

class AngazovanjeTest {

    private Angazovanje angazovanje;
    private Spasilac spasilac;
    private Smena smena;
    private Raspored raspored;

    @BeforeEach
    void setUp() {
        spasilac = new Spasilac(1, "Dusan", "Draskovic", "1234567890123");
        smena = new Smena(1, 8, 16);
        raspored = new Raspored(1, LocalDate.now());
        angazovanje = new Angazovanje(spasilac, smena, raspored);
    }

    @ParameterizedTest
    @CsvSource({
        "1, Dusan, Draskovic, 1234567890123, 1, 8, 16, 1, 2024-08-26",
        "2, Marko, Markovic, 9876543210123, 2, 9, 17, 2, 2024-08-27"
    })
    void testConstructor(int spasilacId, String spasilacIme, String spasilacPrezime, String spasilacJmbg,
            int smenaId, int smenaPocetak, int smenaKraj,
            int rasporedId, String rasporedDatum) {
        Spasilac spasilac = new Spasilac(spasilacId, spasilacIme, spasilacPrezime, spasilacJmbg);
        Smena smena = new Smena(smenaId, smenaPocetak, smenaKraj);
        Raspored raspored = new Raspored(rasporedId, LocalDate.parse(rasporedDatum));

        Angazovanje angazovanje = new Angazovanje(spasilac, smena, raspored);

        assertEquals(spasilac, angazovanje.getSpasilac());
        assertEquals(smena, angazovanje.getSmena());
        assertEquals(raspored, angazovanje.getRaspored());
    }

    @Test
    void testSetSpasilac() {
        Spasilac newSpasilac = new Spasilac(2, "Marko", "Markovic", "9876543210123");
        angazovanje.setSpasilac(newSpasilac);
        assertEquals(newSpasilac, angazovanje.getSpasilac());
    }

    @Test
    void testSetSmena() {
        Smena newSmena = new Smena(2, 9, 17);
        angazovanje.setSmena(newSmena);
        assertEquals(newSmena, angazovanje.getSmena());
    }

    @Test
    void testSetRaspored() {
        Raspored newRaspored = new Raspored(2, LocalDate.now().plusDays(1));
        angazovanje.setRaspored(newRaspored);
        assertEquals(newRaspored, angazovanje.getRaspored());
    }

    @Test
    void testToString() {
        String expectedString = spasilac.toString() + ", " + smena.toString() + ", " + raspored.toString();
        assertEquals(expectedString, angazovanje.toString());
    }

    @Test
    void testGetNazivTabele() {
        assertEquals("angazovanje", angazovanje.getNazivTabele());
    }

    @Test
    void testGetParametre() {
        String expectedParams = String.format("%d, %d, %d", spasilac.getId(), smena.getId(), raspored.getId());
        assertEquals(expectedParams, angazovanje.getParametre());
    }

    @Test
    void testGetNaziveParametara() {
        assertEquals("spasilacId, smenaId, rasporedId", angazovanje.getNaziveParametara());
    }

    @Test
    void testGetSlozeniPrimarniKljuc() {
        String expectedKey = "spasilacId = " + spasilac.getId() + " AND smenaId = " + smena.getId() + " AND rasporedId = " + raspored.getId();
        assertEquals(expectedKey, angazovanje.getSlozeniPrimarniKljuc());
    }

    @Test
    void testKonvertujRSUListu() throws SQLException {
        ResultSet rs = mock(ResultSet.class);

        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("spa.id")).thenReturn(spasilac.getId());
        when(rs.getString("spa.ime")).thenReturn(spasilac.getIme());
        when(rs.getString("spa.prezime")).thenReturn(spasilac.getPrezime());
        when(rs.getString("spa.jmbg")).thenReturn(spasilac.getJmbg());
        when(rs.getInt("sme.id")).thenReturn(smena.getId());
        when(rs.getInt("sme.pocetak")).thenReturn(smena.getPocetak());
        when(rs.getInt("sme.kraj")).thenReturn(smena.getKraj());
        when(rs.getInt("ras.id")).thenReturn(raspored.getId());
        when(rs.getDate("ras.datum")).thenReturn(Date.valueOf(raspored.getDatum()));

        ArrayList<OpstiDomenskiObjekat> result = angazovanje.konvertujRSUListu(rs);

        assertEquals(1, result.size());
        Angazovanje converted = (Angazovanje) result.get(0);
        assertEquals(spasilac, converted.getSpasilac());
        assertEquals(smena, converted.getSmena());
        assertEquals(raspored, converted.getRaspored());
    }

    @Test
    void testGetSelectUpit() {
        String expectedQuery = "SELECT ang.*, spa.*, sme.*, ras.* "
                + "FROM angazovanje ang "
                + "JOIN spasilac spa ON ang.spasilacId = spa.id "
                + "JOIN smena sme ON ang.smenaId = sme.id "
                + "JOIN raspored ras ON ang.rasporedId = ras.id";
        assertEquals(expectedQuery, angazovanje.getSelectUpit());
    }

    @Test
    void testGetSelectUpitPoParametru() {
        String expectedQuery = "SELECT ang.*, spa.*, sme.*, ras.* "
                + "FROM angazovanje ang "
                + "JOIN spasilac spa ON ang.spasilacId = spa.id "
                + "JOIN smena sme ON ang.smenaId = sme.id "
                + "JOIN raspored ras ON ang.rasporedId = ras.id "
                + "WHERE " + angazovanje.getSlozeniPrimarniKljuc();
        assertEquals(expectedQuery, angazovanje.getSelectUpitPoParametru());
    }

    @Test
    void testGetInsertUpit() {
        String expectedQuery = "INSERT INTO " + angazovanje.getNazivTabele() + "(" + angazovanje.getNaziveParametara() + ")"
                + " VALUES (" + angazovanje.getParametre() + ")";
        assertEquals(expectedQuery, angazovanje.getInsertUpit());
    }

    @Test
    void testGetUpdateUpit() {
        String expectedQuery = "UPDATE " + angazovanje.getNazivTabele() + " SET " + angazovanje.getUpdateParametre() + " WHERE "
                + angazovanje.getSlozeniPrimarniKljuc();
        assertEquals(expectedQuery, angazovanje.getUpdateUpit());
    }

    @Test
    void testGetDeleteUpit() {
        String expectedQuery = "DELETE FROM " + angazovanje.getNazivTabele() + " WHERE " + angazovanje.getSlozeniPrimarniKljuc();
        assertEquals(expectedQuery, angazovanje.getDeleteUpit());
    }
}
