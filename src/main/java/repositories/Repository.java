package repositories;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
    void add(T item) throws SQLException;

    void add(Iterable<T> items) throws SQLException;

    void update(T item) throws SQLException;

    void remove(T item) throws SQLException;

    void remove(Iterable<T> items) throws SQLException;

    List<T> query(String query) throws SQLException;

    List<T> getAll() throws SQLException;

    T getOneById(int id) throws SQLException;
}