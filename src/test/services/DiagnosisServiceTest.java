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
import repositories.AppointedRepository;
import repositories.DiagnosisRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


public class DiagnosisServiceTest {

    public DiagnosisService diagnosisService = new DiagnosisService();

    @Mock
    private Connection connection;
    @Mock
    private DiagnosisRepository diagnosisRepository;
    @Mock
    private AppointedRepository appointedRepository;
    @Mock
    private Service service;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Ignore
    @Test
    public void getDiagnosisForPatient() {
        try {
            List<Diagnosis> diagnoses = new ArrayList<>();
            List<Appointed> appointments = new ArrayList<>();
            appointments.add(new Appointed());
            appointments.add(new Appointed());
            when(diagnosisService.receiveConnection())
                    .thenReturn(connection);
            when(diagnosisRepository.getAllLastDiagnosesForPatient(anyInt(), eq(connection)))
                    .thenReturn(diagnoses);
            when(appointedRepository.getAllByDiagnosisId(anyInt(), eq(connection)))
                    .thenReturn(appointments);
            diagnosisService.getDiagnosisForPatient(1);
            //verify(diagnosisService.receiveConnection(), atLeast(3));
            verify(connection).close();
        } catch (SQLException e) {
            fail(CommonlyUsedStrings.TESTING_SQL_EXCEPTION);
        }
    }

    @Ignore
    @Test
    public void add() {
        try {
            when(diagnosisService.receiveConnection()).thenReturn(connection);
            doNothing()
                    .when(diagnosisRepository)
                    .add(any(Diagnosis.class), eq(connection));
            diagnosisService.add(new Diagnosis());
            //verify(diagnosisService.receiveConnection(), atLeastOnce());
            verify(connection).close();
        } catch (SQLException e) {
            fail(CommonlyUsedStrings.TESTING_SQL_EXCEPTION);
        }
    }
}
