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
    public void add(AppointingTimeAndPerson item) throws SQLException {
        String sqlAddSchedule = "INSERT INTO " +
                "appointing_schedule (appointed_id,pursuance_time,performer_id,is_performed)" +
                "VALUES(?, ? , ?, ?);";
        Connection connection = ConnectionPoolHolder.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlAddSchedule);
        statement.setInt(1, item.getAppointedId());
        statement.setTimestamp(2, item.getTime());
        statement.setInt(3, item.getPerformerId());
        statement.setBoolean(4, item.isWasAppointed());
        statement.executeUpdate();
        logger.info("Added appointed schedule for appointed with id " + item.getAppointedId());
        connection.close();

    }

    @Override
    public void add(Iterable<AppointingTimeAndPerson> items) throws SQLException {
        for (AppointingTimeAndPerson schedule : items) {
            add(schedule);
        }
    }


    @Override
    public void update(AppointingTimeAndPerson item) throws SQLException {
        String sqlUpdateSchedule = "UPDATE appointing_schedule " +
                "SET pursuance_time = ?, performer_id = ?, is_performed = ? " +
                "WHERE schedule_id = ?;";
        Connection connection = ConnectionPoolHolder.getConnection();
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
    public void remove(AppointingTimeAndPerson item) throws SQLException {
        String sqlRemoveSchedule = "DELETE from appointing_schedule where schedule_id = ?";

        Connection connection = ConnectionPoolHolder.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlRemoveSchedule);
        preparedStatement.setInt(1, item.getId());
        preparedStatement.executeUpdate();
        logger.info("Rmoved appointed schedule with id " + item.getId());
        connection.close();
    }

    @Override
    public void remove(Iterable<AppointingTimeAndPerson> items) throws SQLException {
        for (AppointingTimeAndPerson schedule : items) {
            remove(schedule);
        }
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
    public List<AppointingTimeAndPerson> query(String query) throws SQLException {
        Connection connection = ConnectionPoolHolder.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        List<AppointingTimeAndPerson> patientCards = getSchedule(resultSet);
        connection.close();
        return patientCards;
    }

    @Override
    public List<AppointingTimeAndPerson> getAll() throws SQLException {
        String sqlSelect = "SELECT * from appointing_schedule;";
        return query(sqlSelect);
    }

    @Override
    public AppointingTimeAndPerson getOneById(int id) throws SQLException {
        String sqlSelect = "SELECT * from appointing_schedule WHERE schedule_id = ?;";
        Connection connection = ConnectionPoolHolder.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<AppointingTimeAndPerson> patientCards = getSchedule(resultSet);
        connection.close();
        if(patientCards.size() == 0)
            return null;
        return patientCards.get(0);
    }

    public List<AppointingTimeAndPerson> getAllByPerformerIdAndTypeForToday(int performerId, String type){
        String sqlSelect = "SELECT schedule_id, pursuance_time, details\n" +
                "FROM appointing_schedule" +
                " WHERE date(pursuance_time) = CURDATE()" +
                "AND performer_id=? AND `type`=?;";
        return null;
    }

    public List<AppointedSchedule> searchScheduleForToday(int performerId, AppointedTypes type) throws SQLException {
        String typeString = type.toString().toLowerCase();
        String sqlSelect = "SELECT schedule_id, time(pursuance_time), details, concat(patient_surname, ' ', patient_name)\n" +
                "FROM appointing_schedule, appointed, patients_cards\n" +
                "WHERE appointing_schedule.appointed_id = appointed.appointed_id\n" +
                "AND date(pursuance_time) = CURDATE()\n" +
                "AND is_performed=0 AND performer_id=? AND `type`=?\n" +
                "AND patient_card_id = (SELECT patient_card_id FROM diagnosis \n" +
                "WHERE diagnosis.diagnosis_id = appointed.diagnosis_id);";
        Connection connection = ConnectionPoolHolder.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
        preparedStatement.setInt(1, performerId);
        preparedStatement.setString(2, typeString);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<AppointedSchedule> schedules = new ArrayList<>();
        while(resultSet.next()){
            int scheduleId = resultSet.getInt(1);
            Time pursuanceTime = Time.valueOf(resultSet.getString(2));
            String details = resultSet.getString(3);
            String patient = resultSet.getString(4);
            schedules.add(new AppointedSchedule(scheduleId, performerId, pursuanceTime, details, patient));
        }
        return schedules;
    }

    public void doAppointment(int id) throws SQLException {
        String sqlUpdateSchedule = "UPDATE appointing_schedule " +
                "SET is_performed = 1 " +
                "WHERE schedule_id = ?;";
        Connection connection = ConnectionPoolHolder.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlUpdateSchedule);
        statement.setInt(1, id);
        statement.executeUpdate();
        logger.info("Performed appointment for schedule with id " + id);
        connection.close();
    }

    public int cancelAppointed(int appointedId) throws SQLException {
        String sqlUpdateSchedule = "DELETE FROM appointing_schedule " +
                "WHERE appointed_id = ? AND is_performed <> 1 " +
                "AND pursuance_time > current_time();";
        Connection connection = ConnectionPoolHolder.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlUpdateSchedule);
        statement.setInt(1, appointedId);
        int num = statement.executeUpdate();
        logger.info("Cancelled appointment with id " + appointedId);
        connection.close();
        return num;
    }
}
