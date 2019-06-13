package repositories;

import commonlyUsedStrings.ExceptionMessage;
import dtos.ClinicStaffWithPasswords;
import models.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class RepositoryTest {
    @Parameterized.Parameter(0)
    public Repository repository;
    @Parameterized.Parameter(1)
    public Object type;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private Statement statement;
    @Mock
    private ResultSet resultSet;

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> getTestData(){
        Collection<Object[]> collection = new ArrayList<>();
        collection.add(new Object[]{AppointmentRepository.getAppointmentRepository(), new Appointment()});
        collection.add(new Object[]{AppointingScheduleRepository.getAppointingScheduleRepository(), new AppointingTimeAndPerson()});
        collection.add(new Object[]{ClinicStaffRepository.getClinicStaffRepository(), new ClinicStaffWithPasswords()});
        collection.add(new Object[]{DiagnosisRepository.getDiagnosisRepository(), new Diagnosis()});
        collection.add(new Object[]{PatientCardsRepository.getPatientCardsRepository(), new PatientCard()});
        return collection;
    }

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkIfAddWorksCorrectly() {
        try {
            when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
            repository.add(type, connection);
        } catch (SQLException e) {
            fail(ExceptionMessage.TESTING_SQL_EXCEPTION);
        }
        catch (NullPointerException e){
            fail(ExceptionMessage.TESTING_NPE);
        }
    }

    @Test
    public void checkIfAddMultipleWorksCorrectly() {
        try {
            when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
            repository.add(new ArrayList<>(), connection);
        } catch (SQLException e) {
            fail(ExceptionMessage.TESTING_SQL_EXCEPTION);
        }
        catch (NullPointerException e){
            fail(ExceptionMessage.TESTING_NPE);
        }
    }

    @Test
    public void checkIfUpdateWorksCorrectly() {
        try {
            when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
            repository.update(type, connection);
        } catch (SQLException e) {
            fail(ExceptionMessage.TESTING_SQL_EXCEPTION);
        }
        catch (NullPointerException e){
            fail(ExceptionMessage.TESTING_NPE);
        }
    }

    @Test
    public void checkIfRemoveWorksCorrectly()  {
        try {
            when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
            repository.remove(type, connection);
        }  catch (SQLException e) {
            fail(ExceptionMessage.TESTING_SQL_EXCEPTION);
        }
        catch (NullPointerException e){
            fail(ExceptionMessage.TESTING_NPE);
        }
    }

    @Test
    public void checkIfRemoveMultipleWorksCorrectly() {
        try {
            when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
            repository.remove(new ArrayList<>(), connection);
        } catch (SQLException e) {
            fail(ExceptionMessage.TESTING_SQL_EXCEPTION);
        }
        catch (NullPointerException e){
            fail(ExceptionMessage.TESTING_NPE);
        }
    }

    @Test
    public void checkIfQueryWorksCorrectly() {
        try {
            when(connection.createStatement()).thenReturn(statement);
            when(statement.executeQuery(anyString())).thenReturn(resultSet);
            repository.query("SELECT * from appointing_schedule;", connection);
        } catch (SQLException e) {
            fail(ExceptionMessage.TESTING_SQL_EXCEPTION);
        }
        catch (NullPointerException e){
            fail(ExceptionMessage.TESTING_NPE);
        }
    }

    @Test
    public void checkIfGetAllWorksCorrectly(){
        try {
            when(connection.createStatement()).thenReturn(statement);
            when(statement.executeQuery(anyString())).thenReturn(resultSet);
            repository.getAll(connection);
        } catch (SQLException e) {
            fail(ExceptionMessage.TESTING_SQL_EXCEPTION);
        }
        catch (NullPointerException e){
            fail(ExceptionMessage.TESTING_NPE);
        }
    }

    @Test
    public void checkIfGetOneByIdWorksCorrectly() {
        try {
            when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            repository.getOneById(1, connection);
        } catch (SQLException e) {
            fail("SQL Exception was thrown");
        }
    }

}
