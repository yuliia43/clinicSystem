package services;

import commonlyUsedStrings.CommonlyUsedStrings;
import enums.AppointedTypes;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class AppointingScheduleServiceTest {
    @Spy
    public AppointingScheduleService appointingScheduleService;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;

    @Before
    public void setRules() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(appointingScheduleService.receiveConnection())
                .thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @Test
    public void checkIfConnectionIsClosedAfterSearchScheduleForToday() {
        try {
            appointingScheduleService.searchScheduleForToday(1, AppointedTypes.OPERATION);
            verify(connection).close();
        } catch (SQLException e) {
            fail(CommonlyUsedStrings.TESTING_SQL_EXCEPTION);
        }
    }

    @Test
    public void checkIfConnectionIsClosedAfterCancelAppointed() {
        try {
            appointingScheduleService.cancelAppointed(1);
            verify(connection).close();
        } catch (SQLException e) {
            fail(CommonlyUsedStrings.TESTING_SQL_EXCEPTION);
        }
    }

    @Test
    public void checkIfConnectionIsClosedAfterDoAppointment() {
        try {
            appointingScheduleService.doAppointment(1);
            verify(connection).close();
        } catch (SQLException e) {
            fail(CommonlyUsedStrings.TESTING_SQL_EXCEPTION);
        }
    }
}
