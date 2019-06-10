package services;

import models.Appointed;
import repositories.AppointedRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class AppointedService extends Service {
    private static final AppointedRepository appointedRepository =
            AppointedRepository.getAppointedRepository();

    /**
     * @param id
     * @return
     * @throws SQLException
     */
    public List<Appointed> getAllByDiagnosisId(int id) throws SQLException {
        try (Connection connection = receiveConnection()) {
            return appointedRepository.getAllByDiagnosisId(id, connection);
        }
    }
}
