package services;

import dtos.AppointmentSchedule;
import enums.AppointmentTypes;
import repositories.AppointingScheduleRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class AppointingScheduleService extends Service {
    private static final AppointingScheduleRepository appointingScheduleRepository =
            AppointingScheduleRepository.getAppointingScheduleRepository();

    /**
     * @param performerId
     * @param type
     * @return
     * @throws SQLException
     */
    public List<AppointmentSchedule> searchScheduleForToday(int performerId, AppointmentTypes type) throws SQLException {
        try (Connection connection = receiveConnection()) {
            String typeString = type.toString().toLowerCase();
            return appointingScheduleRepository
                    .searchScheduleForToday(performerId, typeString, connection);
        }
    }

    /**
     * @param appointedId
     * @throws SQLException
     */
    public void cancelAppointed(int appointedId) throws SQLException {
        try (Connection connection = receiveConnection()) {
            appointingScheduleRepository.cancelAppointed(appointedId, connection);
        }
    }

    /**
     * @param id
     * @throws SQLException
     */
    public void doAppointment(int id) throws SQLException {
        try (Connection connection = receiveConnection()) {
            appointingScheduleRepository.doAppointment(id, connection);
        }
    }


}
