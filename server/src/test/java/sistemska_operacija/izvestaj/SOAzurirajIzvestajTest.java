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

public class SOAzurirajIzvestajTest {

    private SOAzurirajIzvestaj soAzurirajIzvestaj;
    private DbBroker mockDbBroker;
    private OpstiDomenskiObjekat mockIzvestaj;

    @BeforeEach
    public void setUp() throws Exception {
        mockDbBroker = mock(DbBroker.class);
        mockIzvestaj = mock(Izvestaj.class);
        soAzurirajIzvestaj = new SOAzurirajIzvestaj(mockIzvestaj);

        Field instanceField = DbBroker.class.getDeclaredField("instanca");
        instanceField.setAccessible(true);
        instanceField.set(null, mockDbBroker);
    }

    @Test
    public void testIzvrsiSpecificnuOperacijuSuccess() throws Exception {
        when(mockDbBroker.updateOpstiDomenskiObjekat(mockIzvestaj)).thenReturn(true);

        soAzurirajIzvestaj.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).updateOpstiDomenskiObjekat(mockIzvestaj);
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertTrue(soAzurirajIzvestaj.isUspeh());
    }

    @Test
    public void testIzvrsiSpecificnuOperacijuFailure() throws Exception {
        when(mockDbBroker.updateOpstiDomenskiObjekat(mockIzvestaj)).thenReturn(false);

        soAzurirajIzvestaj.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).updateOpstiDomenskiObjekat(mockIzvestaj);
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertFalse(soAzurirajIzvestaj.isUspeh());
    }

    @Test
    public void testIzvrsiSistemskuOperacijuWithException() throws Exception {
        doThrow(new RuntimeException()).when(mockDbBroker).otvoriKonekciju();

        soAzurirajIzvestaj.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).rollback();
        verify(mockDbBroker).zatvoriKonekciju();
        verify(mockDbBroker, never()).commit();
        assertFalse(soAzurirajIzvestaj.isUspeh());
    }
}
