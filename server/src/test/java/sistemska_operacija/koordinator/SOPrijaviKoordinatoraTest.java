package sistemska_operacija.koordinator;

import db.DbBroker;
import domen.Koordinator;
import domen.OpstiDomenskiObjekat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SOPrijaviKoordinatoraTest {

    private SOPrijaviKoordinatora soPrijaviKoordinatora;
    private DbBroker mockDbBroker;
    private Koordinator mockKoordinator;
    private Koordinator matchingKoordinator;

    @BeforeEach
    public void setUp() throws Exception {
        mockDbBroker = mock(DbBroker.class);
        mockKoordinator = mock(Koordinator.class);
        matchingKoordinator = mock(Koordinator.class);

        when(mockKoordinator.getKorisnickoIme()).thenReturn("testUser");
        when(mockKoordinator.getLozinka()).thenReturn("testPass");

        when(matchingKoordinator.getKorisnickoIme()).thenReturn("testUser");
        when(matchingKoordinator.getLozinka()).thenReturn("testPass");

        soPrijaviKoordinatora = new SOPrijaviKoordinatora(mockKoordinator);

        Field instanceField = DbBroker.class.getDeclaredField("instanca");
        instanceField.setAccessible(true);
        instanceField.set(null, mockDbBroker);
    }

    @Test
    public void testIzvrsiSpecificnuOperacijuSuccess() throws Exception {
        List<OpstiDomenskiObjekat> koordinatori = new ArrayList<>();
        koordinatori.add(matchingKoordinator);
        when(mockDbBroker.getAllOpstiDomenskiObjekats(mockKoordinator)).thenReturn(koordinatori);

        soPrijaviKoordinatora.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).getAllOpstiDomenskiObjekats(mockKoordinator);
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertEquals(matchingKoordinator, soPrijaviKoordinatora.getKoordinator());
    }

    @Test
    public void testIzvrsiSpecificnuOperacijuNoMatch() throws Exception {
        List<OpstiDomenskiObjekat> koordinatori = new ArrayList<>();
        when(mockDbBroker.getAllOpstiDomenskiObjekats(mockKoordinator)).thenReturn(koordinatori);

        soPrijaviKoordinatora.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).getAllOpstiDomenskiObjekats(mockKoordinator);
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertNull(soPrijaviKoordinatora.getKoordinator());
    }

    @Test
    public void testIzvrsiSistemskuOperacijuWithException() throws Exception {
        doThrow(new RuntimeException()).when(mockDbBroker).otvoriKonekciju();

        soPrijaviKoordinatora.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).rollback();
        verify(mockDbBroker).zatvoriKonekciju();
        verify(mockDbBroker, never()).commit();
        assertNull(soPrijaviKoordinatora.getKoordinator());
    }
}
