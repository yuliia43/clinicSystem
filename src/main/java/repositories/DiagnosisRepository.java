package repositories;

import jdbc.ConnectionPoolHolder;
import models.Diagnosis;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiagnosisRepository<T> implements Repository<Diagnosis> {

    private static final Logger logger = Logger.getLogger(DiagnosisRepository.class);

    @Override
    public void add(Diagnosis item) throws SQLException {
        String sqlAddDiagnosis = "INSERT INTO " +
                "diagnosis(patient_card_id, doctor_id, diagnosis, is_final_diagnosis)" +
                "VALUES(?, ? , ?, ?);";
        Connection connection = ConnectionPoolHolder.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlAddDiagnosis);
        statement.setInt(1, item.getCardId());
        statement.setInt(2, item.getDoctorId());
        statement.setString(3, item.getDiagnosis());
        statement.setBoolean(4, item.isFinal());
        statement.executeUpdate();
        logger.info("Added diagnosis for card " + item.getCardId());
        connection.close();
    }

    @Override
    public void add(Iterable<Diagnosis> items) throws SQLException {
        for (Diagnosis diagnosis : items) {
            add(diagnosis);
        }
    }

    @Override
    public void update(Diagnosis item) throws SQLException {
        String sqlUpdateDiagnosis = "UPDATE diagnosis " +
                "SET patient_card_id = ?, doctor_id = ?, diagnosis = ?, is_final_diagnosis = ?" +
                "where diagnosis_id = ?;";
        Connection connection = ConnectionPoolHolder.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlUpdateDiagnosis);
        statement.setInt(1, item.getCardId());
        statement.setInt(2, item.getDoctorId());
        statement.setString(3, item.getDiagnosis());
        statement.setBoolean(4, item.isFinal());
        statement.setInt(5, item.getId());
        statement.executeUpdate();
        logger.info("Updated diagnosis with id " + item.getId());
        connection.close();
    }

    @Override
    public void remove(Diagnosis item) throws SQLException {
        String sqlRemoveDiagnosis = "DELETE from diagnosis where diagnosis_id = ?";

        Connection connection = ConnectionPoolHolder.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlRemoveDiagnosis);
        preparedStatement.setInt(1, item.getId());
        preparedStatement.executeUpdate();
        logger.info("Removed diagnosis with id " + item.getId());
        connection.close();
    }

    @Override
    public void remove(Iterable<Diagnosis> items) throws SQLException {
        for (Diagnosis diagnosis : items) {
            remove(diagnosis);
        }
    }

    @Override
    public List<Diagnosis> query(String query) throws SQLException {
        Connection connection = ConnectionPoolHolder.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        List<Diagnosis> diagnoses = getDiagnosis(resultSet);
        connection.close();
        return diagnoses;
    }

    private List<Diagnosis> getDiagnosis(ResultSet resultSet) throws SQLException {
        List<Diagnosis> diagnoses = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            int cardId = resultSet.getInt(2);
            int doctorId = resultSet.getInt(3);
            String diagnosis = resultSet.getString(4);
            boolean is_final = resultSet.getBoolean(5);
            diagnoses.add(new Diagnosis(id, cardId, diagnosis, doctorId, is_final));
        }

        return diagnoses;
    }

    @Override
    public List<Diagnosis> getAll() throws SQLException {
        String sqlSelect = "SELECT * from diagnosis;";
        return query(sqlSelect);
    }

    @Override
    public Diagnosis getOneById(int id) throws SQLException {
        String sqlSelect = "SELECT * from diagnosis WHERE diagnosis_id = ?;";
        Connection connection = ConnectionPoolHolder.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Diagnosis> diagnoses = getDiagnosis(resultSet);
        connection.close();
        if(diagnoses.size() == 0)
            return null;
        return diagnoses.get(0);
    }
}
