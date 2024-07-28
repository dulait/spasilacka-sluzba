package sistemska_operacija;

import db.DbBroker;
import java.lang.reflect.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;

public class OpstaSOTest {

    private OpstaSO opstaSO;
    private DbBroker mockDbBroker;

    @BeforeEach
    public void setUp() throws Exception {
        mockDbBroker = mock(DbBroker.class);

        opstaSO = new OpstaSO() {
            @Override
            protected void izvrsiSpecificnuOperaciju() {
            }
        };

        Field instanceField = DbBroker.class.getDeclaredField("instanca");
        instanceField.setAccessible(true);
        instanceField.set(null, mockDbBroker);
    }

    @Test
    public void testIzvrsiSistemskuOperaciju() throws Exception {
        try (MockedStatic<DbBroker> mockedDbBrokerStatic = mockStatic(DbBroker.class)) {
            mockedDbBrokerStatic.when(DbBroker::getInstanca).thenReturn(mockDbBroker);

            opstaSO.izvrsiSistemskuOperaciju();

            verify(mockDbBroker).otvoriKonekciju();
            verify(mockDbBroker).commit();
            verify(mockDbBroker).zatvoriKonekciju();
            verify(mockDbBroker, never()).rollback();
        }
    }

    @Test
    public void testIzvrsiSistemskuOperacijuWithException() throws Exception {
        doThrow(new RuntimeException()).when(mockDbBroker).otvoriKonekciju();

        opstaSO.izvrsiSistemskuOperaciju();

        verify(mockDbBroker).otvoriKonekciju();
        verify(mockDbBroker).rollback();
        verify(mockDbBroker).zatvoriKonekciju();
        verify(mockDbBroker, never()).commit();
    }
}
