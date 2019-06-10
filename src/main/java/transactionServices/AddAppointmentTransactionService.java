package transactionServices;

import models.Appointed;
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
     * @param appointed
     * @return
     * @throws SQLException
     */
    public boolean addAppointment(Appointed appointed) throws SQLException {
        try (Connection connection = receiveConnection()) {
            return AddAppointmentTransaction.getInstance().execute(appointed, connection);
        }
    }
}
