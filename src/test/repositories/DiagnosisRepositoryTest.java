package repositories;

import commonlyUsedStrings.ExceptionMessage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DiagnosisRepositoryTest {

    private DiagnosisRepository repository = DiagnosisRepository.getDiagnosisRepository();
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
    public void checkIfGetAllLastDiagnosesForPatientWorksCorrectly() {
        try {
            when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            repository.getAllLastDiagnosesByPatientIdAndDoctorId(1,2, connection);
        } catch (SQLException e) {
            fail(ExceptionMessage.TESTING_SQL_EXCEPTION);
        } catch (NullPointerException e) {
            fail(ExceptionMessage.TESTING_NPE);
        }
    }


}
