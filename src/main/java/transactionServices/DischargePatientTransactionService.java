package transactionServices;

import services.Service;
import transactions.DischargePatientTransaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class DischargePatientTransactionService extends Service {
    /**
     * @param patientId
     * @return
     * @throws SQLException
     */
    public boolean execute(int patientId) throws SQLException {
        try (Connection connection = receiveConnection()) {
            return DischargePatientTransaction.getInstance().execute(patientId, connection);
        }
    }
}
