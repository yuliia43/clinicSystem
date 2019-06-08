package services;

import models.Appointed;
import models.Diagnosis;
import repositories.AppointedRepository;
import repositories.DiagnosisRepository;

import java.sql.SQLException;
import java.util.List;

public class DiagnosisService {
    private static final DiagnosisRepository diagnosisRepository =
            DiagnosisRepository.getDiagnosisRepository();
    private static final AppointedRepository appointedRepository =
            AppointedRepository.getAppointedRepository();

    public static List<Diagnosis> getDiagnosisForPatient(int patientId) throws SQLException {
        List<Diagnosis> diagnoses = diagnosisRepository.getAllLastDiagnosesForPatient(patientId);
        for (Diagnosis diagnosis : diagnoses) {
            List<Appointed> appointeds = appointedRepository.getAllByDiagnosisId(diagnosis.getId());
            diagnosis.setRecommendations(appointeds);
        }
        return diagnoses;
    }
}
