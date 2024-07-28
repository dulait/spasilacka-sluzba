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

public class SOUcitajAngazovanjeTest {

    private SOUcitajAngazovanje soUcitajAngazovanje;
    private DbBroker mockDbBroker;
    private OpstiDomenskiObjekat mockAngazovanje;

    @BeforeEach
    public void setUp() throws Exception {
        mockDbBroker = mock(DbBroker.class);
        mockAngazovanje = mock(Angazovanje.class);

        soUcitajAngazovanje = new SOUcitajAngazovanje(mockAngazovanje);

        Field instanceField = DbBroker.class.getDeclaredField("instanca");
        instanceField.setAccessible(true);
        instanceField.set(null, mockDbBroker);
    }

    @Test
    public void testIzvrsiSpecificnuOperaciju() throws Exception {
        OpstiDomenskiObjekat retrievedAngazovanje = mock(Angazovanje.class);
        when(mockDbBroker.getOpstiDomenskiObjekatPoParametru(mockAngazovanje)).thenReturn(retrievedAngazovanje);

        soUcitajAngazovanje.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).getOpstiDomenskiObjekatPoParametru(mockAngazovanje);
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertEquals(retrievedAngazovanje, soUcitajAngazovanje.getAngazovanje());
    }

    @Test
    public void testIzvrsiSistemskuOperacijuWithException() throws Exception {
        doThrow(new RuntimeException()).when(mockDbBroker).otvoriKonekciju();

        soUcitajAngazovanje.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).rollback();
        verify(mockDbBroker).zatvoriKonekciju();
        verify(mockDbBroker, never()).commit();
    }
}
