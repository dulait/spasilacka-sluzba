package sistemska_operacija.koordinator;

import db.DbBroker;
import domen.Koordinator;
import domen.OpstiDomenskiObjekat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class SOUcitajListuKoordinatoraTest {

    private SOUcitajListuKoordinatora soUcitajListuKoordinatora;
    private DbBroker mockDbBroker;
    private List<OpstiDomenskiObjekat> mockKoordinatori;

    @BeforeEach
    public void setUp() throws Exception {
        mockDbBroker = mock(DbBroker.class);
        mockKoordinatori = new ArrayList<>();
        mockKoordinatori.add(mock(Koordinator.class));
        mockKoordinatori.add(mock(Koordinator.class));

        soUcitajListuKoordinatora = new SOUcitajListuKoordinatora();

        Field instanceField = DbBroker.class.getDeclaredField("instanca");
        instanceField.setAccessible(true);
        instanceField.set(null, mockDbBroker);
    }

    @Test
    public void testIzvrsiSpecificnuOperacijuSuccess() throws Exception {
        when(mockDbBroker.getAllOpstiDomenskiObjekats(any(Koordinator.class))).thenReturn(mockKoordinatori);

        soUcitajListuKoordinatora.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).getAllOpstiDomenskiObjekats(any(Koordinator.class));
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertEquals(mockKoordinatori, soUcitajListuKoordinatora.getKoordinatori());
    }

    @Test
    public void testIzvrsiSpecificnuOperacijuNoKoordinatori() throws Exception {
        when(mockDbBroker.getAllOpstiDomenskiObjekats(any(Koordinator.class))).thenReturn(new ArrayList<>());

        soUcitajListuKoordinatora.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).getAllOpstiDomenskiObjekats(any(Koordinator.class));
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertEquals(0, soUcitajListuKoordinatora.getKoordinatori().size());
    }

    @Test
    public void testIzvrsiSistemskuOperacijuWithException() throws Exception {
        doThrow(new RuntimeException()).when(mockDbBroker).otvoriKonekciju();

        soUcitajListuKoordinatora.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).rollback();
        verify(mockDbBroker).zatvoriKonekciju();
        verify(mockDbBroker, never()).commit();
        assertNull(soUcitajListuKoordinatora.getKoordinatori());
    }
}
