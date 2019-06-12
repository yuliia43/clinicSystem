package repositories;

import models.Appointment;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class AppointmentRepository implements Repository<Appointment> {
    private static final Logger logger = Logger.getLogger(AppointmentRepository.class);
    private static final AppointmentRepository APPOINTMENT_REPOSITORY = new AppointmentRepository();

    /**
     *
     */
    private AppointmentRepository() {

    }

    /**
     * @return
     */
    public static AppointmentRepository getAppointmentRepository() {
        return APPOINTMENT_REPOSITORY;
    }

    /**
     * @param item
     * @param connection
     * @throws SQLException
     */
    @Override
    public void add(Appointment item, Connection connection) throws SQLException {
        addItem(item, connection);
    }

    /**
     * @param items
     * @param connection
     * @throws SQLException
     */
    @Override
    public void add(Iterable<Appointment> items, Connection connection) throws SQLException {
        for (Appointment appointment : items) {
            addItem(appointment, connection);
        }
    }

    /**
     * @param item
     * @param connection
     * @throws SQLException
     */
    private void addItem(Appointment item, Connection connection) throws SQLException {
        String sqlAddAppointed = "INSERT INTO " +
                "appointed(diagnosis_id,`type`,details)" +
                "VALUES(?, ? , ?);";
        PreparedStatement statement = connection.prepareStatement(sqlAddAppointed);
        statement.setInt(1, item.getDiagnosisId());
        statement.setString(2, item.getType());
        statement.setString(3, item.getDetails());
        statement.executeUpdate();
        logger.info("Added appointed for diagnosis with id " + item.getDiagnosisId());
    }

    /**
     * @param item
     * @param connection
     * @throws SQLException
     */
    @Override
    public void update(Appointment item, Connection connection) throws SQLException {
        String sqlUpdateAppointed = "UPDATE appointed " +
                "SET diagnosis_id = ?, `type` = ?, details = ?" +
                "where appointed_id = ?;";
        PreparedStatement statement = connection.prepareStatement(sqlUpdateAppointed);
        statement.setInt(1, item.getDiagnosisId());
        statement.setString(2, item.getType());
        statement.setString(3, item.getDetails());
        statement.setInt(4, item.getId());
        statement.executeUpdate();
        logger.info("Updated appointed with id " + item.getId());
    }

    /**
     * @param item
     * @param connection
     * @throws SQLException
     */
    @Override
    public void remove(Appointment item, Connection connection) throws SQLException {
        removeItem(item, connection);
    }

    /**
     * @param items
     * @param connection
     * @throws SQLException
     */
    @Override
    public void remove(Iterable<Appointment> items, Connection connection) throws SQLException {
        for (Appointment appointment : items) {
            removeItem(appointment, connection);
        }
    }

    /**
     * @param item
     * @param connection
     * @throws SQLException
     */
    private void removeItem(Appointment item, Connection connection) throws SQLException {
        String sqlRemoveAppointed = "DELETE from appointed " +
                "where appointed_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlRemoveAppointed);
        preparedStatement.setInt(1, item.getId());
        preparedStatement.executeUpdate();
        logger.info("Removed appointed with id " + item.getId());
    }

    /**
     * @param query
     * @param connection
     * @return
     * @throws SQLException
     */
    @Override
    public List<Appointment> query(String query, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        List<Appointment> appointmentList = getAppointedList(resultSet);
        return appointmentList;
    }

    /**
     * @param connection
     * @return
     * @throws SQLException
     */
    @Override
    public List<Appointment> getAll(Connection connection) throws SQLException {
        String sqlSelect = "SELECT * from appointed;";
        return query(sqlSelect, connection);
    }

    /**
     * @param id
     * @param connection
     * @return
     * @throws SQLException
     */
    @Override
    public Appointment getOneById(int id, Connection connection) throws SQLException {
        String sqlSelect = "SELECT * from appointed WHERE appointed_id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Appointment> appointmentList = getAppointedList(resultSet);
        if (appointmentList.size() == 0)
            return null;
        return appointmentList.get(0);
    }

    /**
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private List<Appointment> getAppointedList(ResultSet resultSet) throws SQLException {
        List<Appointment> patientCards = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            int diagnosisId = resultSet.getInt(2);
            String type = resultSet.getString(3);
            String details = resultSet.getString(4);
            patientCards.add(new Appointment(id, diagnosisId, type, details));
        }
        return patientCards;
    }

    /**
     * @param diagnosisId
     * @param connection
     * @return
     * @throws SQLException
     */
    public List<Appointment> getAllByDiagnosisId(int diagnosisId, Connection connection) throws SQLException {
        String sqlSelect = "SELECT * from appointed WHERE diagnosis_id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
        preparedStatement.setInt(1, diagnosisId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Appointment> appointmentList = getAppointedList(resultSet);
        return appointmentList;
    }

    /**
     * @param connection
     * @return
     * @throws SQLException
     */
    public Appointment getLast(Connection connection) throws SQLException {
        String sqlSelect = "SELECT * FROM appointed " +
                "WHERE appointed_id=(SELECT MAX(appointed_id) FROM appointed);";
        List<Appointment> query = query(sqlSelect, connection);
        return query.get(0);
    }
}
