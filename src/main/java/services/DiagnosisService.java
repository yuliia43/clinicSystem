package services;

import jdbc.ConnectionPoolHolder;
import models.Appointed;
import models.Diagnosis;
import repositories.AppointedRepository;
import repositories.DiagnosisRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DiagnosisService {
    private static final DiagnosisRepository diagnosisRepository =
            DiagnosisRepository.getDiagnosisRepository();
    private static final AppointedRepository appointedRepository =
            AppointedRepository.getAppointedRepository();

    public static List<Diagnosis> getDiagnosisForPatient(int patientId) throws SQLException {
        Connection connection = ConnectionPoolHolder.getConnection();
        List<Diagnosis> diagnoses = diagnosisRepository
                .getAllLastDiagnosesForPatient(patientId, connection);
        for (Diagnosis diagnosis : diagnoses) {
            List<Appointed> appointeds = appointedRepository
                    .getAllByDiagnosisId(diagnosis.getId(), connection);
            diagnosis.setRecommendations(appointeds);
        }
        return diagnoses;
    }

    public void add(Diagnosis diagnosis) throws SQLException {
        Connection connection = ConnectionPoolHolder.getConnection();
        diagnosisRepository.add(diagnosis, connection);
    }
}
