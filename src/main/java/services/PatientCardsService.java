package services;

import models.PatientCard;
import repositories.PatientCardsRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class PatientCardsService extends Service {
    private PatientCardsRepository patientCardsRepository = PatientCardsRepository
            .getPatientCardsRepository();

    /**
     * @param id
     * @return
     * @throws SQLException
     */
    public List<PatientCard> getAllByDoctorId(int id) throws SQLException {
        try (Connection connection = receiveConnection()) {
            return patientCardsRepository.getAllByDoctorId(id, connection);
        }
    }

    /**
     * @param id
     * @return
     * @throws SQLException
     */
    public PatientCard getOneById(int id) throws SQLException {
        try (Connection connection = receiveConnection()) {
            return patientCardsRepository.getOneById(id, connection);
        }
    }

    /**
     * @param patientCard
     * @throws SQLException
     */
    public void add(PatientCard patientCard) throws SQLException {
        try (Connection connection = receiveConnection()) {
            patientCardsRepository.add(patientCard, connection);
        }
    }

    /**
     * @return
     * @throws SQLException
     */
    public List<PatientCard> getAll() throws SQLException {
        try (Connection connection = receiveConnection()) {
            return patientCardsRepository.getAll(connection);
        }
    }

    /**
     * @param id
     * @return
     * @throws SQLException
     */
    public List<PatientCard> getAllExceptDoctorsPatients(int id) throws SQLException {
        try (Connection connection = receiveConnection()) {
            return patientCardsRepository.getAllExceptDoctorsPatients(id, connection);
        }
    }
}
