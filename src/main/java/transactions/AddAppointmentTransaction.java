package transactions;

import models.Appointment;
import org.apache.log4j.Logger;
import repositories.AppointmentRepository;
import repositories.AppointingScheduleRepository;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class AddAppointmentTransaction {

    private static final AddAppointmentTransaction singleton = new AddAppointmentTransaction();
    private static final AppointmentRepository appointmentRepository =
            AppointmentRepository.getAppointmentRepository();
    private static final AppointingScheduleRepository appointingScheduleRepository =
            AppointingScheduleRepository.getAppointingScheduleRepository();
    private static final Logger logger = Logger.getLogger(AddAppointmentTransaction.class);

    /**
     *
     */
    private AddAppointmentTransaction() {
    }

    /**
     * @return
     */
    public static AddAppointmentTransaction getInstance() {
        return singleton;
    }

    /**
     * @param appointment
     * @param connection
     * @return
     * @throws SQLException
     */
    public boolean execute(Appointment appointment, Connection connection) throws SQLException {
        try {
            logger.info("Transaction of adding appointment started");
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            appointmentRepository.add(appointment, connection);
            int appointedId = appointmentRepository.getLast(connection).getId();
            appointment.getSchedule().stream()
                    .forEach(schedule -> schedule.setAppointedId(appointedId));
            appointingScheduleRepository.add(appointment.getSchedule(), connection);
            connection.commit();
            connection.setAutoCommit(true);
            logger.info("Transaction of adding appointment commited");
            return true;
        } catch (SQLException e) {
            connection.rollback();
            connection.setAutoCommit(true);
            logger.error("Transaction of adding appointment rolled back");
            return false;
        }
    }
}
