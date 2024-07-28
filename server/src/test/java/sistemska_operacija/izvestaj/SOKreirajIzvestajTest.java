package sistemska_operacija.izvestaj;

import db.DbBroker;
import domen.Izvestaj;
import domen.OpstiDomenskiObjekat;
import java.lang.reflect.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class SOKreirajIzvestajTest {

    private SOKreirajIzvestaj soKreirajIzvestaj;
    private DbBroker mockDbBroker;
    private OpstiDomenskiObjekat mockIzvestaj;

    @BeforeEach
    public void setUp() throws Exception {
        mockDbBroker = mock(DbBroker.class);
        mockIzvestaj = mock(Izvestaj.class);
        soKreirajIzvestaj = new SOKreirajIzvestaj(mockIzvestaj);

        Field instanceField = DbBroker.class.getDeclaredField("instanca");
        instanceField.setAccessible(true);
        instanceField.set(null, mockDbBroker);
    }

    @Test
    public void testIzvrsiSpecificnuOperacijuSuccess() throws Exception {
        when(mockDbBroker.saveOpstiDomenskiObjekat(mockIzvestaj)).thenReturn(true);

        soKreirajIzvestaj.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).saveOpstiDomenskiObjekat(mockIzvestaj);
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertTrue(soKreirajIzvestaj.isUspeh());
    }

    @Test
    public void testIzvrsiSpecificnuOperacijuFailure() throws Exception {
        when(mockDbBroker.saveOpstiDomenskiObjekat(mockIzvestaj)).thenReturn(false);

        soKreirajIzvestaj.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).saveOpstiDomenskiObjekat(mockIzvestaj);
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertFalse(soKreirajIzvestaj.isUspeh());
    }

    @Test
    public void testIzvrsiSistemskuOperacijuWithException() throws Exception {
        doThrow(new RuntimeException()).when(mockDbBroker).otvoriKonekciju();

        soKreirajIzvestaj.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).rollback();
        verify(mockDbBroker).zatvoriKonekciju();
        verify(mockDbBroker, never()).commit();
        assertFalse(soKreirajIzvestaj.isUspeh());
    }
}
