package sistemska_operacija.smena;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.Smena;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SOUcitajSmenuTest {

    private SOUcitajSmenu soUcitajSmenu;
    private DbBroker mockDbBroker;
    private OpstiDomenskiObjekat mockSmena;

    @BeforeEach
    public void setUp() throws Exception {
        mockDbBroker = mock(DbBroker.class);
        mockSmena = mock(Smena.class);

        soUcitajSmenu = new SOUcitajSmenu(mockSmena);

        Field instanceField = DbBroker.class.getDeclaredField("instanca");
        instanceField.setAccessible(true);
        instanceField.set(null, mockDbBroker);
    }

    @Test
    public void testIzvrsiSpecificnuOperaciju() throws Exception {
        when(mockDbBroker.getOpstiDomenskiObjekatPoParametru(mockSmena)).thenReturn(mockSmena);

        soUcitajSmenu.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).getOpstiDomenskiObjekatPoParametru(mockSmena);
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertEquals(mockSmena, soUcitajSmenu.getSmena());
    }

}
