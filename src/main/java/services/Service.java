package services;

import jdbc.ConnectionPoolHolder;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class Service {
    public Connection receiveConnection() throws SQLException {
        return ConnectionPoolHolder.getConnection();
    }
}
