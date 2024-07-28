package sistemska_operacija.angazovanje;

import db.DbBroker;
import domen.Angazovanje;
import domen.OpstiDomenskiObjekat;
import java.lang.reflect.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SOKreirajAngazovanjeTest {

    private SOKreirajAngazovanje soKreirajAngazovanje;
    private DbBroker mockDbBroker;
    private OpstiDomenskiObjekat mockAngazovanje;

    @BeforeEach
    public void setUp() throws Exception {
        mockDbBroker = mock(DbBroker.class);
        mockAngazovanje = mock(Angazovanje.class);

        soKreirajAngazovanje = new SOKreirajAngazovanje(mockAngazovanje);

        Field instanceField = DbBroker.class.getDeclaredField("instanca");
        instanceField.setAccessible(true);
        instanceField.set(null, mockDbBroker);
    }

    @Test
    public void testIzvrsiSpecificnuOperaciju() throws Exception {
        when(mockDbBroker.saveOpstiDomenskiObjekat(mockAngazovanje)).thenReturn(true);

        soKreirajAngazovanje.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).saveOpstiDomenskiObjekat(mockAngazovanje);
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertTrue(soKreirajAngazovanje.isUspeh());
    }

    @Test
    public void testIzvrsiSpecificnuOperacijuFailure() throws Exception {
        when(mockDbBroker.saveOpstiDomenskiObjekat(mockAngazovanje)).thenReturn(false);

        soKreirajAngazovanje.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).saveOpstiDomenskiObjekat(mockAngazovanje);
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertFalse(soKreirajAngazovanje.isUspeh());
    }

    @Test
    public void testIzvrsiSistemskuOperacijuWithException() throws Exception {
        doThrow(new RuntimeException()).when(mockDbBroker).otvoriKonekciju();

        soKreirajAngazovanje.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).rollback();
        verify(mockDbBroker).zatvoriKonekciju();
        verify(mockDbBroker, never()).commit();
    }
}
