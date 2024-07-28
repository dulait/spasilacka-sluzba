package sistemska_operacija.raspored;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.Raspored;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class SOUcitajListuRasporedaTest {

    private SOUcitajListuRasporeda soUcitajListuRasporeda;
    private DbBroker mockDbBroker;

    @BeforeEach
    public void setUp() throws Exception {
        mockDbBroker = mock(DbBroker.class);
        soUcitajListuRasporeda = new SOUcitajListuRasporeda();

        Field instanceField = DbBroker.class.getDeclaredField("instanca");
        instanceField.setAccessible(true);
        instanceField.set(null, mockDbBroker);
    }

    @Test
    public void testIzvrsiSpecificnuOperacijuSuccess() throws Exception {
        List<OpstiDomenskiObjekat> mockRasporedi = new ArrayList<>();
        mockRasporedi.add(mock(Raspored.class));
        mockRasporedi.add(mock(Raspored.class));

        when(mockDbBroker.getAllOpstiDomenskiObjekats(any(Raspored.class))).thenReturn(mockRasporedi);

        soUcitajListuRasporeda.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).getAllOpstiDomenskiObjekats(any(Raspored.class));
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertEquals(mockRasporedi, soUcitajListuRasporeda.getRasporedi());
    }

    @Test
    public void testIzvrsiSpecificnuOperacijuNoData() throws Exception {
        when(mockDbBroker.getAllOpstiDomenskiObjekats(any(Raspored.class))).thenReturn(new ArrayList<>());

        soUcitajListuRasporeda.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).getAllOpstiDomenskiObjekats(any(Raspored.class));
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertEquals(0, soUcitajListuRasporeda.getRasporedi().size());
    }

    @Test
    public void testIzvrsiSistemskuOperacijuWithException() throws Exception {
        doThrow(new RuntimeException()).when(mockDbBroker).otvoriKonekciju();

        soUcitajListuRasporeda.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).rollback();
        verify(mockDbBroker).zatvoriKonekciju();
        verify(mockDbBroker, never()).getAllOpstiDomenskiObjekats(any(Raspored.class));
        verify(mockDbBroker, never()).commit();
        assertNull(soUcitajListuRasporeda.getRasporedi());
    }
}
