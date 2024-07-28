package sistemska_operacija.spasilac;

import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.Spasilac;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SOUcitajListuSpasiocaTest {

    private SOUcitajListuSpasioca soUcitajListuSpasioca;
    private DbBroker mockDbBroker;

    @BeforeEach
    public void setUp() throws Exception {
        mockDbBroker = mock(DbBroker.class);
        soUcitajListuSpasioca = new SOUcitajListuSpasioca();

        Field instanceField = DbBroker.class.getDeclaredField("instanca");
        instanceField.setAccessible(true);
        instanceField.set(null, mockDbBroker);
    }

    @Test
    public void testIzvrsiSpecificnuOperaciju() throws Exception {
        List<OpstiDomenskiObjekat> mockSpasioci = new ArrayList<>();
        Spasilac s1 = new Spasilac();
        Spasilac s2 = new Spasilac();
        mockSpasioci.add(s1);
        mockSpasioci.add(s2);

        when(mockDbBroker.getAllOpstiDomenskiObjekats(any(Spasilac.class))).thenReturn(mockSpasioci);

        soUcitajListuSpasioca.izvrsiSistemskuOperaciju();

        List<OpstiDomenskiObjekat> spasioci = soUcitajListuSpasioca.getSpasioci();
        assertNotNull(spasioci);
        assertEquals(2, spasioci.size());
        assertTrue(spasioci.contains(s1));
        assertTrue(spasioci.contains(s2));
    }
}
