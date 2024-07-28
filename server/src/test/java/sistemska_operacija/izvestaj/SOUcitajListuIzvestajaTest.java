package sistemska_operacija.izvestaj;

import db.DbBroker;
import domen.Izvestaj;
import domen.OpstiDomenskiObjekat;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SOUcitajListuIzvestajaTest {

    private SOUcitajListuIzvestaja soUcitajListuIzvestaja;
    private DbBroker mockDbBroker;
    private List<OpstiDomenskiObjekat> mockIzvestaji;

    @BeforeEach
    public void setUp() throws Exception {
        mockDbBroker = mock(DbBroker.class);
        mockIzvestaji = new ArrayList<>();
        mockIzvestaji.add(mock(Izvestaj.class));
        mockIzvestaji.add(mock(Izvestaj.class));

        soUcitajListuIzvestaja = new SOUcitajListuIzvestaja();

        Field instanceField = DbBroker.class.getDeclaredField("instanca");
        instanceField.setAccessible(true);
        instanceField.set(null, mockDbBroker);
    }

    @Test
    public void testIzvrsiSpecificnuOperaciju() throws Exception {
        when(mockDbBroker.getAllOpstiDomenskiObjekats(any(Izvestaj.class))).thenReturn(mockIzvestaji);

        soUcitajListuIzvestaja.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).getAllOpstiDomenskiObjekats(any(Izvestaj.class));
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertEquals(mockIzvestaji, soUcitajListuIzvestaja.getIzvestaji());
    }

    @Test
    public void testIzvrsiSistemskuOperacijuWithException() throws Exception {
        doThrow(new RuntimeException()).when(mockDbBroker).otvoriKonekciju();

        soUcitajListuIzvestaja.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).rollback();
        verify(mockDbBroker).zatvoriKonekciju();
        verify(mockDbBroker, never()).commit();
        assertNull(soUcitajListuIzvestaja.getIzvestaji());
    }
}
