package sistemska_operacija.spasilac;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.Spasilac;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SONadjiSpasioceTest {

    private SONadjiSpasioce soNadjiSpasioce;
    private DbBroker mockDbBroker;
    private final String KRITERIJUM = "test";

    @BeforeEach
    public void setUp() throws Exception {
        mockDbBroker = mock(DbBroker.class);

        soNadjiSpasioce = new SONadjiSpasioce(KRITERIJUM);

        Field instanceField = DbBroker.class.getDeclaredField("instanca");
        instanceField.setAccessible(true);
        instanceField.set(null, mockDbBroker);
    }

    @Test
    public void testIzvrsiSpecificnuOperacijuWithMatchingResults() throws Exception {
        List<OpstiDomenskiObjekat> mockSpasioci = new ArrayList<>();
        Spasilac s1 = new Spasilac();
        s1.setIme("TestIme1");
        s1.setPrezime("Prezime1");
        Spasilac s2 = new Spasilac();
        s2.setIme("Ime2");
        s2.setPrezime("TestPrezime2");
        mockSpasioci.add(s1);
        mockSpasioci.add(s2);

        when(mockDbBroker.getAllOpstiDomenskiObjekats(any(Spasilac.class))).thenReturn(mockSpasioci);

        soNadjiSpasioce.izvrsiSistemskuOperaciju();

        Field rezultatField = SONadjiSpasioce.class.getDeclaredField("rezultat");
        rezultatField.setAccessible(true);
        List<OpstiDomenskiObjekat> rezultat = (List<OpstiDomenskiObjekat>) rezultatField.get(soNadjiSpasioce);

        assertNotNull(rezultat);
        assertEquals(2, rezultat.size());
        assertTrue(rezultat.contains(s1));
        assertTrue(rezultat.contains(s2));
    }

    @Test
    public void testIzvrsiSpecificnuOperacijuWithNoMatchingResults() throws Exception {
        List<OpstiDomenskiObjekat> mockSpasioci = new ArrayList<>();
        Spasilac s1 = new Spasilac();
        s1.setIme("Ime1");
        s1.setPrezime("Prezime1");
        Spasilac s2 = new Spasilac();
        s2.setIme("Ime2");
        s2.setPrezime("Prezime2");
        mockSpasioci.add(s1);
        mockSpasioci.add(s2);

        when(mockDbBroker.getAllOpstiDomenskiObjekats(any(Spasilac.class))).thenReturn(mockSpasioci);

        soNadjiSpasioce.izvrsiSistemskuOperaciju();

        Field rezultatField = SONadjiSpasioce.class.getDeclaredField("rezultat");
        rezultatField.setAccessible(true);
        List<OpstiDomenskiObjekat> rezultat = (List<OpstiDomenskiObjekat>) rezultatField.get(soNadjiSpasioce);

        assertNotNull(rezultat);
        assertTrue(rezultat.isEmpty());
    }

    @Test
    public void testSadrziKriterijumMethod() throws Exception {
        Spasilac s1 = new Spasilac();
        s1.setIme("TestIme1");
        s1.setPrezime("Prezime1");
        Spasilac s2 = new Spasilac();
        s2.setIme("Ime2");
        s2.setPrezime("TestPrezime2");

        Method sadrziKriterijumMethod = SONadjiSpasioce.class.getDeclaredMethod("sadrziKriterijum", Spasilac.class);
        sadrziKriterijumMethod.setAccessible(true);

        assertTrue((boolean) sadrziKriterijumMethod.invoke(soNadjiSpasioce, s1));
        assertTrue((boolean) sadrziKriterijumMethod.invoke(soNadjiSpasioce, s2));

        Spasilac s3 = new Spasilac();
        s3.setIme("Ime3");
        s3.setPrezime("Prezime3");

        assertFalse((boolean) sadrziKriterijumMethod.invoke(soNadjiSpasioce, s3));
    }

    @Test
    public void testIzvrsiSpecificnuOperacijuWithException() throws Exception {
        when(mockDbBroker.getAllOpstiDomenskiObjekats(any(Spasilac.class))).thenThrow(new RuntimeException());

        soNadjiSpasioce.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).rollback();
        verify(mockDbBroker).zatvoriKonekciju();
    }

    @Test
    public void testGetRezultat() throws Exception {
        List<OpstiDomenskiObjekat> mockRezultat = new ArrayList<>();
        Spasilac s1 = new Spasilac();
        s1.setIme("TestIme1");
        s1.setPrezime("Prezime1");
        mockRezultat.add(s1);

        Field rezultatField = SONadjiSpasioce.class.getDeclaredField("rezultat");
        rezultatField.setAccessible(true);
        rezultatField.set(soNadjiSpasioce, mockRezultat);

        List<OpstiDomenskiObjekat> rezultat = soNadjiSpasioce.getRezultat();

        assertNotNull(rezultat);
        assertEquals(1, rezultat.size());
        assertTrue(rezultat.contains(s1));
    }
}
