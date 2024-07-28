package sistemska_operacija.izvestaj;

import db.DbBroker;
import domen.Izvestaj;
import domen.OpstiDomenskiObjekat;
import java.lang.reflect.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SOUcitajIzvestajTest {

    private SOUcitajIzvestaj soUcitajIzvestaj;
    private DbBroker mockDbBroker;
    private OpstiDomenskiObjekat mockIzvestaj;

    @BeforeEach
    public void setUp() throws Exception {
        mockDbBroker = mock(DbBroker.class);
        mockIzvestaj = mock(Izvestaj.class);

        soUcitajIzvestaj = new SOUcitajIzvestaj(mockIzvestaj);

        Field instanceField = DbBroker.class.getDeclaredField("instanca");
        instanceField.setAccessible(true);
        instanceField.set(null, mockDbBroker);
    }

    @Test
    public void testIzvrsiSpecificnuOperaciju() throws Exception {
        OpstiDomenskiObjekat retrievedIzvestaj = mock(Izvestaj.class);
        when(mockDbBroker.getOpstiDomenskiObjekatPoParametru(mockIzvestaj)).thenReturn(retrievedIzvestaj);

        soUcitajIzvestaj.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).getOpstiDomenskiObjekatPoParametru(mockIzvestaj);
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertEquals(retrievedIzvestaj, soUcitajIzvestaj.getIzvestaj());
    }

}
