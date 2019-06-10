package services;

import commonlyUsedStrings.CommonlyUsedStrings;
import jdbc.ConnectionPoolHolder;
import models.Appointed;
import models.Diagnosis;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import repositories.AppointedRepository;
import repositories.DiagnosisRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


public class DiagnosisServiceTest {


    @Spy
    public DiagnosisService diagnosisService;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;


    @Before
    public void setRules() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(diagnosisService.receiveConnection())
                .thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @Test
    public void checkIfConnectionIsClosedAfterGetDiagnosisForPatient() {
        try {
            diagnosisService.getDiagnosisForPatient(1);
            verify(connection).close();
        } catch (SQLException e) {
            fail(CommonlyUsedStrings.TESTING_SQL_EXCEPTION);
        }
    }

    @Test
    public void checkIfConnectionIsClosedAfterAdd() {
        try {
            diagnosisService.add(new Diagnosis());
            verify(connection).close();
        } catch (SQLException e) {
            fail(CommonlyUsedStrings.TESTING_SQL_EXCEPTION);
        }
    }
}
