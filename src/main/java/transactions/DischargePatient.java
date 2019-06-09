package transactions;

import jdbc.ConnectionPoolHolder;

import java.sql.Connection;
import java.sql.SQLException;

public class DischargePatient implements Transaction{

    @Override
    public boolean execute(int id) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getConnection();
            connection.setAutoCommit(false);
           // connection.prepareStatement("UPDATE ")
            return true;
        } catch (SQLException e) {
            return false;
        }
        finally {
            connection.setAutoCommit(true);
            connection.close();
            return false;
        }
    }
}
