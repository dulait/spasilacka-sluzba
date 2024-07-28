package sistemska_operacija.smena;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.Smena;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class SOKreirajSmenuTest {

    private SOKreirajSmenu soKreirajSmenu;
    private DbBroker mockDbBroker;
    private OpstiDomenskiObjekat mockSmena;

    @BeforeEach
    public void setUp() throws Exception {
        mockDbBroker = mock(DbBroker.class);
        mockSmena = mock(Smena.class);

        soKreirajSmenu = new SOKreirajSmenu(mockSmena);

        Field instanceField = DbBroker.class.getDeclaredField("instanca");
        instanceField.setAccessible(true);
        instanceField.set(null, mockDbBroker);
    }

    @Test
    public void testIzvrsiSpecificnuOperacijuSuccess() throws Exception {
        when(mockDbBroker.saveOpstiDomenskiObjekat(mockSmena)).thenReturn(true);

        soKreirajSmenu.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).saveOpstiDomenskiObjekat(mockSmena);
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertTrue(soKreirajSmenu.isUspeh());
    }

    @Test
    public void testIzvrsiSpecificnuOperacijuFailure() throws Exception {
        when(mockDbBroker.saveOpstiDomenskiObjekat(mockSmena)).thenReturn(false);

        soKreirajSmenu.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).saveOpstiDomenskiObjekat(mockSmena);
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertFalse(soKreirajSmenu.isUspeh());
    }

    @Test
    public void testIzvrsiSistemskuOperacijuWithException() throws Exception {
        doThrow(new RuntimeException()).when(mockDbBroker).otvoriKonekciju();

        soKreirajSmenu.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).rollback();
        verify(mockDbBroker).zatvoriKonekciju();
        verify(mockDbBroker, never()).saveOpstiDomenskiObjekat(mockSmena);
        verify(mockDbBroker, never()).commit();
        assertFalse(soKreirajSmenu.isUspeh());
    }
}
