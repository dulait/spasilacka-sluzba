package sistemska_operacija.spasilac;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.Spasilac;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class SOKreirajSpasiocaTest {

    private SOKreirajSpasioca soKreirajSpasioca;
    private DbBroker mockDbBroker;
    private OpstiDomenskiObjekat mockSpasilac;

    @BeforeEach
    public void setUp() throws Exception {
        mockDbBroker = mock(DbBroker.class);
        mockSpasilac = mock(Spasilac.class);

        soKreirajSpasioca = new SOKreirajSpasioca(mockSpasilac);

        Field instanceField = DbBroker.class.getDeclaredField("instanca");
        instanceField.setAccessible(true);
        instanceField.set(null, mockDbBroker);
    }

    @Test
    public void testIzvrsiSpecificnuOperacijuSuccess() throws Exception {
        when(mockDbBroker.saveOpstiDomenskiObjekat(mockSpasilac)).thenReturn(true);

        soKreirajSpasioca.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).saveOpstiDomenskiObjekat(mockSpasilac);
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertTrue(soKreirajSpasioca.isUspeh());
    }

    @Test
    public void testIzvrsiSpecificnuOperacijuFailure() throws Exception {
        when(mockDbBroker.saveOpstiDomenskiObjekat(mockSpasilac)).thenReturn(false);

        soKreirajSpasioca.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).saveOpstiDomenskiObjekat(mockSpasilac);
        verify(mockDbBroker).commit();
        verify(mockDbBroker).zatvoriKonekciju();
        assertFalse(soKreirajSpasioca.isUspeh());
    }

    @Test
    public void testIzvrsiSistemskuOperacijuWithException() throws Exception {
        doThrow(new RuntimeException()).when(mockDbBroker).otvoriKonekciju();

        soKreirajSpasioca.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).rollback();
        verify(mockDbBroker).zatvoriKonekciju();
        verify(mockDbBroker, never()).saveOpstiDomenskiObjekat(mockSpasilac);
        verify(mockDbBroker, never()).commit();
        assertFalse(soKreirajSpasioca.isUspeh());
    }
}
