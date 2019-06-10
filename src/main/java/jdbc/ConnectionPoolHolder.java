package jdbc;


import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Yullia Shcherbakova
 */
public class ConnectionPoolHolder {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/clinic" +
            "?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "111111";

    private static BasicDataSource dataSource;

    private static final Logger logger = Logger.getLogger(ConnectionPoolHolder.class);


    /**
     *
     */
    private ConnectionPoolHolder() {

    }

    /**
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    dataSource = new BasicDataSource();
                    dataSource.setUrl(DB_URL);
                    dataSource.setUsername(USER);
                    dataSource.setPassword(PASSWORD);
                    dataSource.setMinIdle(0);
                    dataSource.setMaxIdle(20);
                    dataSource.setMaxTotal(20);
                    dataSource.setInitialSize(0);
                }
                logger.info("Connection pool created");
            }
        }
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            logger.info("Connection received");
            return connection;
        } catch (SQLException e) {
            logger.error("Error in getting connection from dbcp");
            throw e;
        }
    }

}
