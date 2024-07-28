package sistemska_operacija.raspored;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.Raspored;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class SOUcitajRasporedTest {

    private SOUcitajRaspored soUcitajRaspored;
    private DbBroker mockDbBroker;
    private OpstiDomenskiObjekat mockRaspored;
    private Raspored mockRetrievedRaspored;

    @BeforeEach
    public void setUp() throws Exception {
        mockDbBroker = mock(DbBroker.class);
        mockRaspored = mock(Raspored.class);
        mockRetrievedRaspored = mock(Raspored.class);

        soUcitajRaspored = new SOUcitajRaspored(mockRaspored);

        Field instanceField = DbBroker.class.getDeclaredField("instanca");
        instanceField.setAccessible(true);
        instanceField.set(null, mockDbBroker);
    }

    @Test
    public void testIzvrsiSpecificnuOperacijuSuccess() throws Exception {
        when(mockDbBroker.getOpstiDomenskiObjekatPoParametru(mockRaspored)).thenReturn(mockRetrievedRaspored);

        soUcitajRaspored.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).getOpstiDomenskiObjekatPoParametru(mockRaspored);
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertEquals(mockRetrievedRaspored, soUcitajRaspored.getRaspored());
    }

    @Test
    public void testIzvrsiSpecificnuOperacijuNoMatch() throws Exception {
        when(mockDbBroker.getOpstiDomenskiObjekatPoParametru(mockRaspored)).thenReturn(null);

        soUcitajRaspored.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).getOpstiDomenskiObjekatPoParametru(mockRaspored);
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertNull(soUcitajRaspored.getRaspored());
    }

}
