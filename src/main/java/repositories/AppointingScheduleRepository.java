package repositories;

import dtos.AppointedSchedule;
import enums.AppointedTypes;
import jdbc.ConnectionPoolHolder;
import models.AppointingTimeAndPerson;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointingScheduleRepository implements Repository<AppointingTimeAndPerson> {
    private static final Logger logger = Logger.getLogger(AppointingScheduleRepository.class);
    private static final AppointingScheduleRepository appointingScheduleRepository =
            new AppointingScheduleRepository();
    private AppointingScheduleRepository(){

    }

    public static AppointingScheduleRepository getAppointingScheduleRepository() {
        return appointingScheduleRepository;
    }

    @Override
    public void add(AppointingTimeAndPerson item, Connection connection) throws SQLException {
        addItem(item, connection);
        connection.close();

    }

    private void addItem(AppointingTimeAndPerson item, Connection connection) throws SQLException {
        String sqlAddSchedule = "INSERT INTO " +
                "appointing_schedule (appointed_id,pursuance_time,performer_id,is_performed)" +
                "VALUES(?, ? , ?, ?);";
        PreparedStatement statement = connection.prepareStatement(sqlAddSchedule);
        statement.setInt(1, item.getAppointedId());
        statement.setTimestamp(2, item.getTime());
        statement.setInt(3, item.getPerformerId());
        statement.setBoolean(4, item.isWasAppointed());
        statement.executeUpdate();
        logger.info("Added appointed schedule for appointed with id " + item.getAppointedId());
    }

    @Override
    public void add(Iterable<AppointingTimeAndPerson> items, Connection connection) throws SQLException {
        for (AppointingTimeAndPerson schedule : items) {
            addItem(schedule, connection);
        }
        connection.close();
    }


    @Override
    public void update(AppointingTimeAndPerson item, Connection connection) throws SQLException {
        String sqlUpdateSchedule = "UPDATE appointing_schedule " +
                "SET pursuance_time = ?, performer_id = ?, is_performed = ? " +
                "WHERE schedule_id = ?;";
        PreparedStatement statement = connection.prepareStatement(sqlUpdateSchedule);
        statement.setTimestamp(1, item.getTime());
        statement.setInt(2, item.getPerformerId());
        statement.setBoolean(3, item.isWasAppointed());
        statement.setInt(4, item.getId());
        statement.executeUpdate();
        logger.info("Updated appointed schedule with id " + item.getId());
        connection.close();
    }

    @Override
    public void remove(AppointingTimeAndPerson item, Connection connection) throws SQLException {
        removeItem(item, connection);
        connection.close();
    }

    private void removeItem(AppointingTimeAndPerson item, Connection connection) throws SQLException {
        String sqlRemoveSchedule = "DELETE from appointing_schedule where schedule_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlRemoveSchedule);
        preparedStatement.setInt(1, item.getId());
        preparedStatement.executeUpdate();
        logger.info("Removed appointed schedule with id " + item.getId());
    }

    @Override
    public void remove(Iterable<AppointingTimeAndPerson> items, Connection connection) throws SQLException {
        for (AppointingTimeAndPerson schedule : items) {
            removeItem(schedule, connection);
        }
        connection.close();
    }


    private List<AppointingTimeAndPerson> getSchedule(ResultSet resultSet) throws SQLException {
        List<AppointingTimeAndPerson> schedule = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            int appointedId = resultSet.getInt(1);
            Timestamp time = resultSet.getTimestamp(3);
            int performerId = resultSet.getInt(4);
            boolean isPerformed = resultSet.getBoolean(5);
            schedule
                    .add(new AppointingTimeAndPerson(id, appointedId, time,performerId, isPerformed));
        }
        return schedule;
    }


    @Override
    public List<AppointingTimeAndPerson> query(String query, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        List<AppointingTimeAndPerson> patientCards = getSchedule(resultSet);
        connection.close();
        return patientCards;
    }

    @Override
    public List<AppointingTimeAndPerson> getAll(Connection connection) throws SQLException {
        String sqlSelect = "SELECT * from appointing_schedule;";
        return query(sqlSelect, connection);
    }

    @Override
    public AppointingTimeAndPerson getOneById(int id, Connection connection) throws SQLException {
        String sqlSelect = "SELECT * from appointing_schedule WHERE schedule_id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<AppointingTimeAndPerson> patientCards = getSchedule(resultSet);
        connection.close();
        if(patientCards.size() == 0)
            return null;
        return patientCards.get(0);
    }


    public List<AppointedSchedule> searchScheduleForToday(int performerId, String type, Connection connection) throws SQLException {
        String sqlSelect = "SELECT schedule_id, time(pursuance_time), details, concat(patient_surname, ' ', patient_name)\n" +
                "FROM appointing_schedule, appointed, patients_cards\n" +
                "WHERE appointing_schedule.appointed_id = appointed.appointed_id\n" +
                "AND date(pursuance_time) = CURDATE()\n" +
                "AND is_performed=0 AND performer_id=? AND `type`=?\n" +
                "AND patient_card_id = (SELECT patient_card_id FROM diagnosis \n" +
                "WHERE diagnosis.diagnosis_id = appointed.diagnosis_id);";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
        preparedStatement.setInt(1, performerId);
        preparedStatement.setString(2, type);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<AppointedSchedule> schedules = new ArrayList<>();
        while(resultSet.next()){
            int scheduleId = resultSet.getInt(1);
            Time pursuanceTime = Time.valueOf(resultSet.getString(2));
            String details = resultSet.getString(3);
            String patient = resultSet.getString(4);
            schedules.add(new AppointedSchedule(scheduleId, performerId, pursuanceTime, details, patient));
        }
        connection.close();
        return schedules;
    }

    public void doAppointment(int id, Connection connection) throws SQLException {
        String sqlUpdateSchedule = "UPDATE appointing_schedule " +
                "SET is_performed = 1 " +
                "WHERE schedule_id = ?;";
        PreparedStatement statement = connection.prepareStatement(sqlUpdateSchedule);
        statement.setInt(1, id);
        statement.executeUpdate();
        logger.info("Performed appointment for schedule with id " + id);
        connection.close();
    }

    public int cancelAppointed(int appointedId, Connection connection) throws SQLException {
        String sqlUpdateSchedule = "DELETE FROM appointing_schedule " +
                "WHERE appointed_id = ? AND is_performed <> 1 " +
                "AND pursuance_time > current_time();";
        PreparedStatement statement = connection.prepareStatement(sqlUpdateSchedule);
        statement.setInt(1, appointedId);
        int num = statement.executeUpdate();
        logger.info("Cancelled appointment with id " + appointedId);
        connection.close();
        return num;
    }
}
