package repositories;

import commonlyUsedStrings.ExceptionMessage;
import models.Appointed;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class AppointedRepositoryTest {

    private AppointedRepository repository = AppointedRepository.getAppointedRepository();
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;
    @Mock
    private Statement statement;
    @Mock
    public List<Appointed> appointeds;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void checkIfGetAllByDiagnosisIdWorksCorrectly() {
        try {
            when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            repository.getAllByDiagnosisId(1, connection);
        }catch (SQLException e) {
            fail(ExceptionMessage.TESTING_SQL_EXCEPTION);
        } catch (NullPointerException e) {
            fail(ExceptionMessage.TESTING_NPE);
        }
    }

    @Test
    public void checkIfGetLastWorksCorrectly() {
        try {
            when(connection.createStatement()).thenReturn(statement);
            when(statement.executeQuery(anyString())).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(true).thenReturn(false);
            when(resultSet.getInt(anyInt())).thenReturn(0);
            when(resultSet.getString(anyInt())).thenReturn("");
            repository.getLast(connection);
        } catch (SQLException e) {
            fail(ExceptionMessage.TESTING_SQL_EXCEPTION);
        }
        catch (NullPointerException e){
            fail(ExceptionMessage.TESTING_NPE);
        }
    }
}
