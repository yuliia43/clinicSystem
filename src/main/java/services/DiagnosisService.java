package services;

import models.Appointment;
import models.Diagnosis;
import repositories.AppointmentRepository;
import repositories.DiagnosisRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class DiagnosisService extends Service {
    private static final DiagnosisRepository diagnosisRepository =
            DiagnosisRepository.getDiagnosisRepository();
    private static final AppointmentRepository APPOINTMENT_REPOSITORY =
            AppointmentRepository.getAppointmentRepository();

    /**
     * @param patientId
     * @return
     * @throws SQLException
     */
    public List<Diagnosis> getDiagnosisForPatient(int patientId) throws SQLException {
        try (Connection connection = receiveConnection()) {
            List<Diagnosis> diagnoses = diagnosisRepository
                    .getAllLastDiagnosesForPatient(patientId, connection);
            for (Diagnosis diagnosis : diagnoses) {
                List<Appointment> appointments = APPOINTMENT_REPOSITORY
                        .getAllByDiagnosisId(diagnosis.getId(), connection);
                diagnosis.setRecommendations(appointments);
            }
            return diagnoses;
        }
    }


    /**
     * @param diagnosis
     * @throws SQLException
     */
    public void add(Diagnosis diagnosis) throws SQLException {
        try (Connection connection = receiveConnection()) {
            diagnosisRepository.add(diagnosis, connection);
        }
    }

    /**
     * @param patientId
     * @return
     * @throws SQLException
     */
    public List<Diagnosis> getAllLastDiagnosesForPatient(int patientId) throws SQLException {
        try (Connection connection = receiveConnection()) {
            return diagnosisRepository.getAllLastDiagnosesForPatient(patientId, connection);
        }
    }

    /**
     * @param diagnosis
     * @throws SQLException
     */
    public void update(Diagnosis diagnosis) throws SQLException {
        try (Connection connection = receiveConnection()) {
            diagnosisRepository.update(diagnosis, connection);
        }
    }
}
