package services;

import dtos.AppointedSchedule;
import enums.AppointedTypes;
import jdbc.ConnectionPoolHolder;
import repositories.AppointingScheduleRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AppointingScheduleService extends Service{
    private static final AppointingScheduleRepository appointingScheduleRepository =
            AppointingScheduleRepository.getAppointingScheduleRepository();

    public List<AppointedSchedule> searchScheduleForToday(int performerId, AppointedTypes type) throws SQLException {
        try(Connection connection = receiveConnection()){
            String typeString = type.toString().toLowerCase();
            return appointingScheduleRepository
                    .searchScheduleForToday(performerId, typeString, connection);
        }
    }

    public void cancelAppointed(int appointedId) throws SQLException {
        try (Connection connection = receiveConnection()) {
            appointingScheduleRepository.cancelAppointed(appointedId, connection);
        }
    }

    public void doAppointment(int id) throws SQLException {
        try (Connection connection = receiveConnection()) {
            appointingScheduleRepository.doAppointment(id, connection);
        }
    }


}
