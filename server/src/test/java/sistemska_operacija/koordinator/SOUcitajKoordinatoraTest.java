package sistemska_operacija.koordinator;

import db.DbBroker;
import domen.Koordinator;
import domen.OpstiDomenskiObjekat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SOUcitajKoordinatoraTest {

    private SOUcitajKoordinatora soUcitajKoordinatora;
    private DbBroker mockDbBroker;
    private OpstiDomenskiObjekat mockKoordinator;
    private Koordinator mockRetrievedKoordinator;

    @BeforeEach
    public void setUp() throws Exception {
        mockDbBroker = mock(DbBroker.class);
        mockKoordinator = mock(Koordinator.class);
        mockRetrievedKoordinator = mock(Koordinator.class);

        soUcitajKoordinatora = new SOUcitajKoordinatora(mockKoordinator);

        Field instanceField = DbBroker.class.getDeclaredField("instanca");
        instanceField.setAccessible(true);
        instanceField.set(null, mockDbBroker);
    }

    @Test
    public void testIzvrsiSpecificnuOperacijuSuccess() throws Exception {
        when(mockDbBroker.getOpstiDomenskiObjekatPoParametru(mockKoordinator)).thenReturn(mockRetrievedKoordinator);

        soUcitajKoordinatora.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).getOpstiDomenskiObjekatPoParametru(mockKoordinator);
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertEquals(mockRetrievedKoordinator, soUcitajKoordinatora.getKoordinator());
    }

    @Test
    public void testIzvrsiSpecificnuOperacijuNoMatch() throws Exception {
        when(mockDbBroker.getOpstiDomenskiObjekatPoParametru(mockKoordinator)).thenReturn(null);

        soUcitajKoordinatora.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).getOpstiDomenskiObjekatPoParametru(mockKoordinator);
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertNull(soUcitajKoordinatora.getKoordinator());
    }

}
