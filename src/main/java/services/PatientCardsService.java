package services;

import jdbc.ConnectionPoolHolder;
import models.PatientCard;
import repositories.PatientCardsRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PatientCardsService {
    private PatientCardsRepository patientCardsRepository = PatientCardsRepository
            .getPatientCardsRepository();

    public List<PatientCard> getAllByDoctorId(int id) throws SQLException {
        Connection connection = ConnectionPoolHolder.getConnection();
        return patientCardsRepository.getAllByDoctorId(id, connection);
    }

    public PatientCard getOneById(int id) throws SQLException {
        Connection connection = ConnectionPoolHolder.getConnection();
        return patientCardsRepository.getOneById(id, connection);
    }
}
