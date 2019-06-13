package services;

import models.Appointment;
import repositories.AppointmentRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class AppointmentService extends Service {
    private static final AppointmentRepository APPOINTMENT_REPOSITORY =
            AppointmentRepository.getAppointmentRepository();

    /**
     * @param id
     * @return
     * @throws SQLException
     */
    public List<Appointment> getAllByDiagnosisId(int id) throws SQLException {
        try (Connection connection = receiveConnection()) {
            return APPOINTMENT_REPOSITORY.getAllByDiagnosisId(id, connection);
        }
    }
}
