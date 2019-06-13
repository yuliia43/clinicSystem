package services;

import commonlyUsedStrings.ExceptionMessage;
import models.PatientCard;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.sql.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class PatientCardsServiceTest {

    @Spy
    public PatientCardsService patientCardsService;
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
        when(patientCardsService.receiveConnection())
                .thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection.createStatement()).thenReturn(statement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);
    }

    @Test
    public void checkIfConnectionIsClosedAfterGetAllByDoctorId() {
        try {
            patientCardsService.getAllByDoctorId(1);
            verify(connection).close();
        } catch (SQLException e) {
            fail(ExceptionMessage.TESTING_SQL_EXCEPTION);
        }
    }

    @Test
    public void checkIfConnectionIsClosedAfterGetOneById() {
        try {
            patientCardsService.getOneById(1);
            verify(connection).close();
        } catch (SQLException e) {
            fail(ExceptionMessage.TESTING_SQL_EXCEPTION);
        }
    }

    @Test
    public void checkIfConnectionIsClosedAfterAdd() {
        try {
            patientCardsService.add(new PatientCard());
            verify(connection).close();
        } catch (SQLException e) {
            fail(ExceptionMessage.TESTING_SQL_EXCEPTION);
        }
    }

    @Test
    public void checkIfConnectionIsClosedAfterGetAll() {
        try {
            patientCardsService.getAll();
            verify(connection).close();
        } catch (SQLException e) {
            fail(ExceptionMessage.TESTING_SQL_EXCEPTION);
        }
    }
}
