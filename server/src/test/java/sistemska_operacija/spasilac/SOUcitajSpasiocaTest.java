package sistemska_operacija.spasilac;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.Spasilac;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SOUcitajSpasiocaTest {

    private SOUcitajSpasioca soUcitajSpasioca;
    private DbBroker mockDbBroker;
    private Spasilac mockSpasilac;

    @BeforeEach
    public void setUp() throws Exception {
        mockDbBroker = mock(DbBroker.class);
        mockSpasilac = new Spasilac();
        soUcitajSpasioca = new SOUcitajSpasioca(mockSpasilac);

        Field instanceField = DbBroker.class.getDeclaredField("instanca");
        instanceField.setAccessible(true);
        instanceField.set(null, mockDbBroker);
    }

    @Test
    public void testIzvrsiSpecificnuOperaciju() throws Exception {
        Spasilac retrievedSpasilac = new Spasilac();
        when(mockDbBroker.getOpstiDomenskiObjekatPoParametru(mockSpasilac)).thenReturn(retrievedSpasilac);

        soUcitajSpasioca.izvrsiSistemskuOperaciju();

        OpstiDomenskiObjekat spasilac = soUcitajSpasioca.getSpasilac();
        assertNotNull(spasilac);
        assertEquals(retrievedSpasilac, spasilac);
    }
}
