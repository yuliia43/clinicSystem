package services;

import jdbc.ConnectionPoolHolder;
import models.PatientCard;
import repositories.PatientCardsRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PatientCardsService extends Service {
    private PatientCardsRepository patientCardsRepository = PatientCardsRepository
            .getPatientCardsRepository();

    public List<PatientCard> getAllByDoctorId(int id) throws SQLException {
        try (Connection connection = receiveConnection()) {
            return patientCardsRepository.getAllByDoctorId(id, connection);
        }
    }

    public PatientCard getOneById(int id) throws SQLException {
        try (Connection connection = receiveConnection()) {
            return patientCardsRepository.getOneById(id, connection);
        }
    }

    public void add(PatientCard patientCard) throws SQLException {
        try (Connection connection = receiveConnection()) {
            patientCardsRepository.add(patientCard, connection);
        }
    }

    public List<PatientCard> getAll() throws SQLException {
        try (Connection connection = receiveConnection()) {
            return patientCardsRepository.getAll(connection);
        }
    }
}
