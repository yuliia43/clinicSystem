package repositories;

import commonlyUsedStrings.ExceptionMessage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AppointingScheduleRepositoryTest {

    private AppointingScheduleRepository repository =
            AppointingScheduleRepository.getAppointingScheduleRepository();
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void checkIfSearchScheduleForTodayWorksCorrectly() {
        try {
            when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            repository.searchScheduleForToday(0, "", connection);
        }catch (SQLException e) {
            fail(ExceptionMessage.TESTING_SQL_EXCEPTION);
        } catch (NullPointerException e) {
            fail(ExceptionMessage.TESTING_NPE);
        }
    }

    @Test
    public void checkIfDoAppointmentWorksCorrectly() {
        try {
            when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
            repository.doAppointment(anyInt(), connection);
        }catch (SQLException e) {
            fail(ExceptionMessage.TESTING_SQL_EXCEPTION);
        } catch (NullPointerException e) {
            fail(ExceptionMessage.TESTING_NPE);
        }
    }

    @Test
    public void checkIfCancelAppointedWorksCorrectly() {
        try {
            when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
            repository.cancelAppointed(anyInt(), connection);
        }catch (SQLException e) {
            fail(ExceptionMessage.TESTING_SQL_EXCEPTION);
        } catch (NullPointerException e) {
            fail(ExceptionMessage.TESTING_NPE);
        }
    }
}
