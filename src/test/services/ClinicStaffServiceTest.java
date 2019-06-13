package services;

import commonlyUsedStrings.ExceptionMessage;
import dtos.ClinicStaffWithPasswords;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.sql.*;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class ClinicStaffServiceTest {


    @Spy
    private ClinicStaffService clinicStaffService;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;


    @Before
    public void setRules() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(clinicStaffService.receiveConnection())
                .thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.createStatement()).thenReturn(statement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
    }

    @Test
    public void checkIfConnectionIsClosedAfterGetOneById() {
        try {
            when(resultSet.next()).thenReturn(true).thenReturn(false);
            when(resultSet.getInt(anyInt())).thenReturn(0);
            when(resultSet.getString(anyInt())).thenReturn("");
            clinicStaffService.getOneById(1);
            verify(connection).close();
        } catch (SQLException e) {
            fail(ExceptionMessage.TESTING_SQL_EXCEPTION);
        }
    }

    @Test
    public void checkIfConnectionIsClosedAfterGetAll() {
        try {
            clinicStaffService.getAll();
            verify(connection).close();
        } catch (SQLException e) {
            fail(ExceptionMessage.TESTING_SQL_EXCEPTION);
        }
    }

    @Test
    public void checkIfConnectionIsClosedAfterGetAllDoctors() {
        try {
            clinicStaffService.getAllDoctors();
            verify(connection).close();
        } catch (SQLException e) {
            fail(ExceptionMessage.TESTING_SQL_EXCEPTION);
        }
    }

    @Test
    public void checkIfConnectionIsClosedAfterCheckAuthorization() {
        try {
            clinicStaffService.checkAuthorization("email", "password");
            verify(connection).close();
        } catch (SQLException e) {
            fail(ExceptionMessage.TESTING_SQL_EXCEPTION);
        }
    }

    @Test
    public void checkIfConnectionIsClosedAfterAdd() {
        try {
            clinicStaffService.add(new ClinicStaffWithPasswords());
            verify(connection).close();
        } catch (SQLException e) {
            fail(ExceptionMessage.TESTING_SQL_EXCEPTION);
        }
    }
}
