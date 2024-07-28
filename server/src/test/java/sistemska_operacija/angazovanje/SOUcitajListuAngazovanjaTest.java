package sistemska_operacija.angazovanje;

import db.DbBroker;
import domen.Angazovanje;
import domen.OpstiDomenskiObjekat;
import java.lang.reflect.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SOUcitajListuAngazovanjaTest {

    private SOUcitajListuAngazovanja soUcitajListuAngazovanja;
    private DbBroker mockDbBroker;

    @BeforeEach
    public void setUp() throws Exception {
        mockDbBroker = mock(DbBroker.class);
        soUcitajListuAngazovanja = new SOUcitajListuAngazovanja();

        Field instanceField = DbBroker.class.getDeclaredField("instanca");
        instanceField.setAccessible(true);
        instanceField.set(null, mockDbBroker);
    }

    @Test
    public void testIzvrsiSpecificnuOperaciju() throws Exception {
        List<OpstiDomenskiObjekat> mockAngazovanjaList = new ArrayList<>();
        mockAngazovanjaList.add(mock(Angazovanje.class));
        when(mockDbBroker.getAllOpstiDomenskiObjekats(any(Angazovanje.class))).thenReturn(mockAngazovanjaList);

        soUcitajListuAngazovanja.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).getAllOpstiDomenskiObjekats(any(Angazovanje.class));
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertEquals(mockAngazovanjaList, soUcitajListuAngazovanja.getAngazovanja());
    }

    @Test
    public void testIzvrsiSistemskuOperacijuWithException() throws Exception {
        doThrow(new RuntimeException()).when(mockDbBroker).otvoriKonekciju();

        soUcitajListuAngazovanja.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).rollback();
        verify(mockDbBroker).zatvoriKonekciju();
        verify(mockDbBroker, never()).commit();
    }
}
