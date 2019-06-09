package transactionServices;

import models.Appointed;
import services.Service;
import transactions.AddAppointment;

import java.sql.Connection;
import java.sql.SQLException;

public class AddAppointmentService extends Service {
    public boolean addAppointment(Appointed appointed) throws SQLException {
        try (Connection connection = receiveConnection()) {
            return AddAppointment.getInstance().execute(appointed, connection);
        }
    }
}
