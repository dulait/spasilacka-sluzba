package sistemska_operacija.raspored;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.Raspored;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class SOObrisiRasporedTest {

    private SOObrisiRaspored soObrisiRaspored;
    private DbBroker mockDbBroker;
    private OpstiDomenskiObjekat mockRaspored;

    @BeforeEach
    public void setUp() throws Exception {
        mockDbBroker = mock(DbBroker.class);
        mockRaspored = mock(Raspored.class);

        soObrisiRaspored = new SOObrisiRaspored(mockRaspored);

        Field instanceField = DbBroker.class.getDeclaredField("instanca");
        instanceField.setAccessible(true);
        instanceField.set(null, mockDbBroker);
    }

    @Test
    public void testIzvrsiSpecificnuOperacijuSuccess() throws Exception {
        when(mockDbBroker.deleteOpstiDomenskiObjekat(mockRaspored)).thenReturn(true);

        soObrisiRaspored.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).deleteOpstiDomenskiObjekat(mockRaspored);
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertTrue(soObrisiRaspored.isUspeh());
    }

    @Test
    public void testIzvrsiSpecificnuOperacijuFailure() throws Exception {
        when(mockDbBroker.deleteOpstiDomenskiObjekat(mockRaspored)).thenReturn(false);

        soObrisiRaspored.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).deleteOpstiDomenskiObjekat(mockRaspored);
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertFalse(soObrisiRaspored.isUspeh());
    }

    @Test
    public void testIzvrsiSistemskuOperacijuWithException() throws Exception {
        doThrow(new RuntimeException()).when(mockDbBroker).otvoriKonekciju();

        soObrisiRaspored.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).rollback();
        verify(mockDbBroker).zatvoriKonekciju();
        verify(mockDbBroker, never()).deleteOpstiDomenskiObjekat(mockRaspored);
        verify(mockDbBroker, never()).commit();
        assertFalse(soObrisiRaspored.isUspeh());
    }
}
