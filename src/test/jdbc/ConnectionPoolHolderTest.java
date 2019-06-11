package jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class ConnectionPoolHolderTest {

    @Test
    public void receivedConnectionShouldNotBeNull(){
        try {
            Connection connection = ConnectionPoolHolder.getConnection();
            assertNotNull("Connection was not received", connection);
            connection.close();
        } catch (SQLException e) {
            fail("SQL EXCEPTION OCCURED!");
        }
    }

}
