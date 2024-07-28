package sistemska_operacija.angazovanje;

import db.DbBroker;
import domen.Angazovanje;
import domen.OpstiDomenskiObjekat;
import java.lang.reflect.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SOObrisiAngazovanjeTest {

    private SOObrisiAngazovanje soObrisiAngazovanje;
    private DbBroker mockDbBroker;
    private OpstiDomenskiObjekat mockAngazovanje;

    @BeforeEach
    public void setUp() throws Exception {
        mockDbBroker = mock(DbBroker.class);
        mockAngazovanje = mock(Angazovanje.class);

        soObrisiAngazovanje = new SOObrisiAngazovanje(mockAngazovanje);

        Field instanceField = DbBroker.class.getDeclaredField("instanca");
        instanceField.setAccessible(true);
        instanceField.set(null, mockDbBroker);
    }

    @Test
    public void testIzvrsiSpecificnuOperaciju() throws Exception {
        when(mockDbBroker.deleteOpstiDomenskiObjekat(mockAngazovanje)).thenReturn(true);

        soObrisiAngazovanje.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).deleteOpstiDomenskiObjekat(mockAngazovanje);
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertTrue(soObrisiAngazovanje.isUspeh());
    }

    @Test
    public void testIzvrsiSpecificnuOperacijuFailure() throws Exception {
        when(mockDbBroker.deleteOpstiDomenskiObjekat(mockAngazovanje)).thenReturn(false);

        soObrisiAngazovanje.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).deleteOpstiDomenskiObjekat(mockAngazovanje);
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertFalse(soObrisiAngazovanje.isUspeh());
    }

    @Test
    public void testIzvrsiSistemskuOperacijuWithException() throws Exception {
        doThrow(new RuntimeException()).when(mockDbBroker).otvoriKonekciju();

        soObrisiAngazovanje.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).rollback();
        verify(mockDbBroker).zatvoriKonekciju();
        verify(mockDbBroker, never()).commit();
    }
}
