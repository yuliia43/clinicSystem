package transactions;

import dtos.AppointedSchedule;
import models.Appointed;
import org.apache.log4j.Logger;
import repositories.AppointedRepository;
import repositories.AppointingScheduleRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class AddAppointment {

    private static final AddAppointment singleton = new AddAppointment();
    private static final AppointedRepository appointedRepository =
            AppointedRepository.getAppointedRepository();
    private static final AppointingScheduleRepository appointingScheduleRepository =
            AppointingScheduleRepository.getAppointingScheduleRepository();
    private static final Logger logger = Logger.getLogger(AddAppointment.class);

    private AddAppointment() {
    }

    public static AddAppointment getInstance(){
        return singleton;
    }

    public boolean execute(Appointed appointment, Connection connection) throws SQLException {
        try {
            logger.info("Transaction of adding appointment started");
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            appointedRepository.add(appointment, connection);
            int appointedId = appointedRepository.getLast(connection).getId();
            appointment.getSchedule().stream()
                    .forEach(schedule->schedule.setAppointedId(appointedId));
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
