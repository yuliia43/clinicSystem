package repositories;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public interface Repository<T> {
    void add(T item, Connection connection) throws SQLException;

    void add(Iterable<T> items, Connection connection) throws SQLException;

    void update(T item, Connection connection) throws SQLException;

    void remove(T item, Connection connection) throws SQLException;

    void remove(Iterable<T> items, Connection connection) throws SQLException;

    List<T> query(String query, Connection connection) throws SQLException;

    List<T> getAll(Connection connection) throws SQLException;

    T getOneById(int id, Connection connection) throws SQLException;

}
