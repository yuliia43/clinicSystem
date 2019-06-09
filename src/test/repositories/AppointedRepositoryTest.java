package repositories;

import commonlyUsedStrings.CommonlyUsedStrings;
import models.Appointed;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.ArrayList;

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

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void checkIfGetAllByDiagnosisIdWorksCorrectly() {
        try {
            when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            repository.getAllByDiagnosisId(anyInt(), connection);
        }catch (SQLException e) {
            fail(CommonlyUsedStrings.TESTING_SQL_EXCEPTION);
        } catch (NullPointerException e) {
            fail(CommonlyUsedStrings.TESTING_NPE);
        }
    }

    @Test
    public void getLast() {
        try {
            when(connection.createStatement()).thenReturn(statement);
            when(statement.executeQuery(anyString())).thenReturn(resultSet);
            repository.query(anyString(), eq(connection));
        } catch (SQLException e) {
            fail(CommonlyUsedStrings.TESTING_SQL_EXCEPTION);
        }
        catch (NullPointerException e){
            fail(CommonlyUsedStrings.TESTING_NPE);
        }
    }
}
