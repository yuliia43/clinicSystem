package repositories;

import commonlyUsedStrings.CommonlyUsedStrings;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClinicStaffRepositoryTest {
    private ClinicStaffRepository repository =
            ClinicStaffRepository.getClinicStaffRepository();
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void checkIfConnectionIsClosedAfterGetAllDoctors() {
        try {
            when(connection.createStatement()).thenReturn(statement);
            when(statement.executeQuery(any(String.class))).thenReturn(resultSet);
            repository.getAllDoctors(connection);
            verify(connection).close();
        } catch (SQLException e) {
            fail(CommonlyUsedStrings.TESTING_SQL_EXCEPTION);
        } catch (NullPointerException e) {
            fail(CommonlyUsedStrings.TESTING_NPE);
        }
    }

    @Test
    public void checkIfConnectionIsClosedAfterCheckAuthorization() {
        try {
            when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            repository.checkAuthorization(any(String.class), "password", connection);
            verify(connection).close();
        } catch (SQLException e) {
            fail(CommonlyUsedStrings.TESTING_SQL_EXCEPTION);
        } catch (NullPointerException e) {
            fail(CommonlyUsedStrings.TESTING_NPE);
        }
    }

    @Test
    public void checkIfConnectionIsClosedAfterCheckIfEmailExists() {
        try {
            when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            repository.checkIfEmailExists("email", connection);
            verify(connection).close();
        } catch (SQLException e) {
            fail(CommonlyUsedStrings.TESTING_SQL_EXCEPTION);
        } catch (NullPointerException e) {
            fail(CommonlyUsedStrings.TESTING_NPE);
        }
    }

}
