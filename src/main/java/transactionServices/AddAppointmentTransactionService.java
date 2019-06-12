package transactionServices;

import models.Appointment;
import services.Service;
import transactions.AddAppointmentTransaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class AddAppointmentTransactionService extends Service {
    /**
     * @param appointment
     * @return
     * @throws SQLException
     */
    public boolean addAppointment(Appointment appointment) throws SQLException {
        try (Connection connection = receiveConnection()) {
            return AddAppointmentTransaction.getInstance().execute(appointment, connection);
        }
    }
}
