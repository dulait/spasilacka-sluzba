package sistemska_operacija.smena;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.Smena;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SOUcitajListuSmenaTest {

    private SOUcitajListuSmena soUcitajListuSmena;
    private DbBroker mockDbBroker;

    @BeforeEach
    public void setUp() throws Exception {
        mockDbBroker = mock(DbBroker.class);
        soUcitajListuSmena = new SOUcitajListuSmena();

        Field instanceField = DbBroker.class.getDeclaredField("instanca");
        instanceField.setAccessible(true);
        instanceField.set(null, mockDbBroker);
    }

    @Test
    public void testIzvrsiSpecificnuOperaciju() throws Exception {
        List<OpstiDomenskiObjekat> mockList = new ArrayList<>();
        mockList.add(mock(Smena.class));
        when(mockDbBroker.getAllOpstiDomenskiObjekats(any(Smena.class))).thenReturn(mockList);

        soUcitajListuSmena.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).getAllOpstiDomenskiObjekats(any(Smena.class));
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertEquals(mockList, soUcitajListuSmena.getSmene());
    }

    @Test
    public void testIzvrsiSistemskuOperacijuWithException() throws Exception {
        doThrow(new RuntimeException()).when(mockDbBroker).otvoriKonekciju();

        soUcitajListuSmena.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).rollback();
        verify(mockDbBroker).zatvoriKonekciju();
        verify(mockDbBroker, never()).getAllOpstiDomenskiObjekats(any(Smena.class));
        verify(mockDbBroker, never()).commit();
        assertEquals(null, soUcitajListuSmena.getSmene());
    }
}
