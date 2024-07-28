package db;

import domen.OpstiDomenskiObjekat;
import java.lang.reflect.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DbBrokerTest {

    private DbBroker dbBroker;
    private Connection mockConnection;
    private Statement mockStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() throws Exception {
        dbBroker = DbBroker.getInstanca();

        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);
        mockResultSet = mock(ResultSet.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        Field field = DbBroker.class.getDeclaredField("konekcija");
        field.setAccessible(true);
        field.set(dbBroker, mockConnection);
    }

    @Test
    public void testOtvoriKonekciju() throws Exception {
        try (MockedStatic<DriverManager> mockedDriverManager = mockStatic(DriverManager.class)) {
            mockedDriverManager.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString())).thenReturn(mockConnection);

            dbBroker.otvoriKonekciju();

            verify(mockConnection).setAutoCommit(false);
            verify(mockConnection, never()).close();
        }
    }

    @Test
    public void testZatvoriKonekciju() throws Exception {
        dbBroker.zatvoriKonekciju();

        verify(mockConnection).close();
    }

    @Test
    public void testCommit() throws Exception {
        dbBroker.commit();

        verify(mockConnection).commit();
    }

    @Test
    public void testRollback() throws Exception {
        dbBroker.rollback();

        verify(mockConnection).rollback();
    }

    @Test
    public void testGetAllOpstiDomenskiObjekats() throws Exception {
        OpstiDomenskiObjekat mockObjekat = mock(OpstiDomenskiObjekat.class);
        when(mockObjekat.getSelectUpit()).thenReturn("SELECT * FROM table");
        when(mockObjekat.konvertujRSUListu(mockResultSet)).thenReturn(List.of(mockObjekat));

        List<OpstiDomenskiObjekat> result = dbBroker.getAllOpstiDomenskiObjekats(mockObjekat);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(mockStatement).executeQuery("SELECT * FROM table");
    }

    @Test
    public void testGetOpstiDomenskiObjekatPoParametru() throws Exception {
        OpstiDomenskiObjekat mockObjekat = mock(OpstiDomenskiObjekat.class);
        when(mockObjekat.getSelectUpitPoParametru()).thenReturn("SELECT * FROM table WHERE id = 1");
        when(mockObjekat.konvertujRSUListu(mockResultSet)).thenReturn(List.of(mockObjekat));

        OpstiDomenskiObjekat result = dbBroker.getOpstiDomenskiObjekatPoParametru(mockObjekat);

        assertNotNull(result);
        verify(mockStatement).executeQuery("SELECT * FROM table WHERE id = 1");
    }

    @Test
    public void testSaveOpstiDomenskiObjekat() throws Exception {
        OpstiDomenskiObjekat mockObjekat = mock(OpstiDomenskiObjekat.class);
        when(mockObjekat.getInsertUpit()).thenReturn("INSERT INTO table (col1, col2) VALUES (val1, val2)");

        boolean result = dbBroker.saveOpstiDomenskiObjekat(mockObjekat);

        assertTrue(result);
        verify(mockStatement).executeUpdate("INSERT INTO table (col1, col2) VALUES (val1, val2)");
    }

    @Test
    public void testUpdateOpstiDomenskiObjekat() throws Exception {
        OpstiDomenskiObjekat mockObjekat = mock(OpstiDomenskiObjekat.class);
        when(mockObjekat.getUpdateUpit()).thenReturn("UPDATE table SET col1 = val1 WHERE id = 1");

        boolean result = dbBroker.updateOpstiDomenskiObjekat(mockObjekat);

        assertTrue(result);
        verify(mockStatement).executeUpdate("UPDATE table SET col1 = val1 WHERE id = 1");
    }

    @Test
    public void testDeleteOpstiDomenskiObjekat() throws Exception {
        OpstiDomenskiObjekat mockObjekat = mock(OpstiDomenskiObjekat.class);
        when(mockObjekat.getDeleteUpit()).thenReturn("DELETE FROM table WHERE id = 1");

        boolean result = dbBroker.deleteOpstiDomenskiObjekat(mockObjekat);

        assertTrue(result);
        verify(mockStatement).executeUpdate("DELETE FROM table WHERE id = 1");
    }
}
