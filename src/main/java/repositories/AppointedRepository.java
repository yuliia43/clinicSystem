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
    public void add(Appointed item) throws SQLException {
        String sqlAddAppointed = "INSERT INTO " +
                "appointed(diagnosis_id,`type`,details)" +
                "VALUES(?, ? , ?);";
        Connection connection = ConnectionPoolHolder.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlAddAppointed);
        statement.setInt(1, item.getDiagnosisId());
        statement.setString(2, item.getType());
        statement.setString(3, item.getDetails());
        statement.executeUpdate();
        logger.info("Added appointed for diagnosis with id " + item.getDiagnosisId());
        connection.close();
    }

    @Override
    public void add(Iterable<Appointed> items) throws SQLException {
        for (Appointed appointed : items) {
            add(appointed);
        }
    }

    @Override
    public void update(Appointed item) throws SQLException {
        String sqlUpdateAppointed = "UPDATE appointed " +
                "SET diagnosis_id = ?, `type` = ?, details = ?" +
                "where appointed_id = ?;";
        Connection connection = ConnectionPoolHolder.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlUpdateAppointed);
        statement.setInt(1, item.getDiagnosisId());
        statement.setString(2, item.getType());
        statement.setString(3, item.getDetails());
        statement.setInt(4, item.getId());
        statement.executeUpdate();
        logger.info("Updated appointed with id " + item.getId());
        connection.close();
    }

    @Override
    public void remove(Appointed item) throws SQLException {
        String sqlRemoveAppointed = "DELETE from appointed " +
                "where appointed_id = ?";

        Connection connection = ConnectionPoolHolder.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlRemoveAppointed);
        preparedStatement.setInt(1, item.getId());
        preparedStatement.executeUpdate();
        logger.info("Removed appointed with id " + item.getId());
        connection.close();
    }

    @Override
    public void remove(Iterable<Appointed> items) throws SQLException {
        for (Appointed appointed : items) {
            remove(appointed);
        }
    }

    @Override
    public List<Appointed> query(String query) throws SQLException {
        Connection connection = ConnectionPoolHolder.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        List<Appointed> appointedList = getAppointedList(resultSet);
        connection.close();
        return appointedList;
    }

    @Override
    public List<Appointed> getAll() throws SQLException {
        String sqlSelect = "SELECT * from appointed;";
        return query(sqlSelect);
    }

    @Override
    public Appointed getOneById(int id) throws SQLException {
        String sqlSelect = "SELECT * from appointed WHERE appointed_id = ?;";
        Connection connection = ConnectionPoolHolder.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Appointed> appointedList = getAppointedList(resultSet);
        connection.close();
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
}
