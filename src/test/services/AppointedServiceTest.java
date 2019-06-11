package services;

import commonlyUsedStrings.ExceptionMessage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class AppointedServiceTest {

    @Spy
    public AppointedService appointedService;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;


    @Before
    public void setRules() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(appointedService.receiveConnection())
                .thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @Test
    public void checkIfConnectionIsClosedAfterGetAllByDiagnosisId() {
        try {
            appointedService.getAllByDiagnosisId(1);
            verify(connection).close();
        } catch (SQLException e) {
            fail(ExceptionMessage.TESTING_SQL_EXCEPTION);
        }
    }
}
