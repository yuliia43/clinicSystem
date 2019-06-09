package transactions;

import java.sql.SQLException;

public interface Transaction {
    boolean execute(int id) throws SQLException;
}
