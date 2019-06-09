package services;

import jdbc.ConnectionPoolHolder;

import java.sql.Connection;
import java.sql.SQLException;

public class Service {
    public Connection receiveConnection() throws SQLException {
        return ConnectionPoolHolder.getConnection();
    }
}
