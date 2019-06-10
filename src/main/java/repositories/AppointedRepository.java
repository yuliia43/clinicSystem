package repositories;

import jdbc.ConnectionPoolHolder;
import models.Appointed;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointedRepository implements Repository<Appointed> {
    private static final Logger logger = Logger.getLogger(AppointedRepository.class);
    private static final AppointedRepository appointedRepository = new AppointedRepository();

    private AppointedRepository(){

    }

    public static AppointedRepository getAppointedRepository() {
        return appointedRepository;
    }

    @Override
    public void add(Appointed item, Connection connection) throws SQLException {
        addItem(item, connection);
    }

    @Override
    public void add(Iterable<Appointed> items, Connection connection) throws SQLException {
        for (Appointed appointed : items) {
            addItem(appointed, connection);
        }
    }

    private void addItem(Appointed item, Connection connection) throws SQLException {
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

    @Override
    public void update(Appointed item, Connection connection) throws SQLException {
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

    @Override
    public void remove(Appointed item, Connection connection) throws SQLException {
        removeItem(item, connection);
    }

    @Override
    public void remove(Iterable<Appointed> items, Connection connection) throws SQLException {
        for (Appointed appointed : items) {
            removeItem(appointed, connection);
        }
    }

    private void removeItem(Appointed item, Connection connection) throws SQLException {
        String sqlRemoveAppointed = "DELETE from appointed " +
                "where appointed_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlRemoveAppointed);
        preparedStatement.setInt(1, item.getId());
        preparedStatement.executeUpdate();
        logger.info("Removed appointed with id " + item.getId());
    }

    @Override
    public List<Appointed> query(String query, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        List<Appointed> appointedList = getAppointedList(resultSet);
        return appointedList;
    }

    @Override
    public List<Appointed> getAll(Connection connection) throws SQLException {
        String sqlSelect = "SELECT * from appointed;";
        return query(sqlSelect, connection);
    }

    @Override
    public Appointed getOneById(int id, Connection connection) throws SQLException {
        String sqlSelect = "SELECT * from appointed WHERE appointed_id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Appointed> appointedList = getAppointedList(resultSet);
        if(appointedList.size() == 0)
            return null;
        return appointedList.get(0);
    }

    private List<Appointed> getAppointedList(ResultSet resultSet) throws SQLException {
        List<Appointed> patientCards = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            int diagnosisId = resultSet.getInt(2);
            String type = resultSet.getString(3);
            String details = resultSet.getString(4);
            patientCards.add(new Appointed(id, diagnosisId, type, details));
        }
        return patientCards;
    }

    public List<Appointed> getAllByDiagnosisId(int diagnosisId, Connection connection) throws SQLException {
        String sqlSelect = "SELECT * from appointed WHERE diagnosis_id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
        preparedStatement.setInt(1, diagnosisId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Appointed> appointedList = getAppointedList(resultSet);
        return appointedList;
    }

    public Appointed getLast(Connection connection) throws SQLException {
        String sqlSelect = "SELECT * FROM appointed " +
                "WHERE appointed_id=(SELECT MAX(appointed_id) FROM appointed);";
        List<Appointed> query = query(sqlSelect, connection);
        return query.get(0);
    }
}
