package repositories;

import models.Diagnosis;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class DiagnosisRepository implements Repository<Diagnosis> {

    private static final Logger logger = Logger.getLogger(DiagnosisRepository.class);
    private static final DiagnosisRepository diagnosisRepository =
            new DiagnosisRepository();

    /**
     *
     */
    private DiagnosisRepository() {

    }

    /**
     * @return
     */
    public static DiagnosisRepository getDiagnosisRepository() {
        return diagnosisRepository;
    }

    /**
     * @param item
     * @param connection
     * @throws SQLException
     */
    @Override
    public void add(Diagnosis item, Connection connection) throws SQLException {
        addItem(item, connection);
    }

    /**
     * @param item
     * @param connection
     * @throws SQLException
     */
    private void addItem(Diagnosis item, Connection connection) throws SQLException {
        String sqlAddDiagnosis = "INSERT INTO " +
                "diagnosis(patient_card_id, doctor_id, diagnosis, is_final_diagnosis, set_date) " +
                "VALUES(?, ? , ?, false, CURDATE());";
        PreparedStatement statement = connection.prepareStatement(sqlAddDiagnosis);
        statement.setInt(1, item.getCardId());
        statement.setInt(2, item.getDoctorId());
        statement.setString(3, item.getDiagnosis());
        statement.executeUpdate();
        logger.info("Added diagnosis for card " + item.getCardId());
    }

    /**
     * @param items
     * @param connection
     * @throws SQLException
     */
    @Override
    public void add(Iterable<Diagnosis> items, Connection connection) throws SQLException {
        for (Diagnosis diagnosis : items) {
            addItem(diagnosis, connection);
        }
    }

    /**
     * @param item
     * @param connection
     * @throws SQLException
     */
    @Override
    public void update(Diagnosis item, Connection connection) throws SQLException {
        String sqlUpdateDiagnosis = "UPDATE diagnosis " +
                "SET patient_card_id = ?, doctor_id = ?, diagnosis = ?, is_final_diagnosis = ? " +
                "WHERE diagnosis_id = ?;";
        PreparedStatement statement = connection.prepareStatement(sqlUpdateDiagnosis);
        statement.setInt(1, item.getCardId());
        statement.setInt(2, item.getDoctorId());
        statement.setString(3, item.getDiagnosis());
        statement.setBoolean(4, item.isFinal());
        statement.setInt(5, item.getId());
        statement.executeUpdate();
        logger.info("Updated diagnosis with id " + item.getId());
    }

    /**
     * @param item
     * @param connection
     * @throws SQLException
     */
    @Override
    public void remove(Diagnosis item, Connection connection) throws SQLException {
        removeItem(item, connection);
    }

    /**
     * @param item
     * @param connection
     * @throws SQLException
     */
    private void removeItem(Diagnosis item, Connection connection) throws SQLException {
        String sqlRemoveDiagnosis = "DELETE from diagnosis where diagnosis_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlRemoveDiagnosis);
        preparedStatement.setInt(1, item.getId());
        preparedStatement.executeUpdate();
        logger.info("Removed diagnosis with id " + item.getId());
    }

    /**
     * @param items
     * @param connection
     * @throws SQLException
     */
    @Override
    public void remove(Iterable<Diagnosis> items, Connection connection) throws SQLException {
        for (Diagnosis diagnosis : items) {
            removeItem(diagnosis, connection);
        }
    }

    /**
     * @param query
     * @param connection
     * @return
     * @throws SQLException
     */
    @Override
    public List<Diagnosis> query(String query, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        List<Diagnosis> diagnoses = getDiagnosis(resultSet);
        return diagnoses;
    }

    /**
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private List<Diagnosis> getDiagnosis(ResultSet resultSet) throws SQLException {
        List<Diagnosis> diagnoses = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            int cardId = resultSet.getInt(2);
            int doctorId = resultSet.getInt(3);
            String diagnosis = resultSet.getString(4);
            Date date = resultSet.getDate(5);
            boolean is_final = resultSet.getBoolean(6);
            diagnoses.add(new Diagnosis(id, cardId, diagnosis, doctorId, date, is_final));
        }

        return diagnoses;
    }

    /**
     * @param connection
     * @return
     * @throws SQLException
     */
    @Override
    public List<Diagnosis> getAll(Connection connection) throws SQLException {
        String sqlSelect = "SELECT * from diagnosis;";
        return query(sqlSelect, connection);
    }

    /**
     * @param id
     * @param connection
     * @return
     * @throws SQLException
     */
    @Override
    public Diagnosis getOneById(int id, Connection connection) throws SQLException {
        String sqlSelect = "SELECT * from diagnosis WHERE diagnosis_id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Diagnosis> diagnoses = getDiagnosis(resultSet);
        if (diagnoses.size() == 0)
            return null;
        return diagnoses.get(0);
    }

    /**
     * @param patientId
     * @param connection
     * @return
     * @throws SQLException
     */
    public List<Diagnosis> getAllLastDiagnosesByPatientIdAndDoctorId(int patientId, int doctorId, Connection connection) throws SQLException {
        String sqlSelect = "SELECT * FROM diagnosis WHERE patient_card_id = ? and doctor_id=?\n" +
                "                and diagnosis_id > (SELECT MAX(diagnosis_id) FROM diagnosis \n" +
                "                WHERE patient_card_id = ? and is_final_diagnosis = 1) \n" +
                "UNION\n" +
                "SELECT * FROM diagnosis WHERE patient_card_id = ? and doctor_id=?\n" +
                "                GROUP BY patient_card_id HAVING SUM(is_final_diagnosis) = 0\n" +
                "                Order by diagnosis_id desc;";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
        preparedStatement.setInt(1, patientId);
        preparedStatement.setInt(2, doctorId);
        preparedStatement.setInt(3, patientId);
        preparedStatement.setInt(4, patientId);
        preparedStatement.setInt(5, doctorId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Diagnosis> diagnoses = getDiagnosis(resultSet);
        return diagnoses;
    }
}
