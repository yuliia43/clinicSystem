package transactionServices;

import services.Service;
import transactions.DischargePatient;

import java.sql.Connection;
import java.sql.SQLException;

public class DischargePatientService extends Service {
    public boolean execute(int patientId) throws SQLException {
        try(Connection connection = receiveConnection()){
            return DischargePatient.getInstance().execute(patientId, connection);
        }
    }
}
