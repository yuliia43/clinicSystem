package repositories;

import dtos.ClinicStaffWithPasswords;
import jdbc.ConnectionPoolHolder;
import models.ClinicStaff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClinicStaffRepository<T> implements Repository<ClinicStaffWithPasswords> {


    @Override
    public void add(ClinicStaffWithPasswords item) throws SQLException {
        String sqlAddStaff = "INSERT INTO " +
                "clinic_staff (staff_surname, staff_name, title, email,  `password`)" +
                "VALUES(?, ? , ?, ?, ?);";
        Connection connection = ConnectionPoolHolder.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlAddStaff);
        statement.setString(1, item.getSurname());
        statement.setString(2, item.getName());
        statement.setString(3, item.getTitle());
        statement.setString(4, item.getEmail());
        statement.setString(5, item.getPassword());
        connection.close();
    }

    @Override
    public void add(Iterable<ClinicStaffWithPasswords> items) throws SQLException {
        for (ClinicStaffWithPasswords staff : items) {
            add(staff);
        }
    }

    @Override
    public void update(ClinicStaffWithPasswords item) throws SQLException {
        String sqlUpdateStaff = "UPDATE clinic_staff " +
                "SET staff_surname = ?, staff_name = ?, title = ?, email = ?, " +
                "`password` = ? where staff_id = ?;";
        Connection connection = ConnectionPoolHolder.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlUpdateStaff);
        statement.setString(1, item.getSurname());
        statement.setString(2, item.getName());
        statement.setString(3, item.getTitle());
        statement.setString(4, item.getEmail());
        statement.setString(5, item.getPassword());
        statement.setInt(6, item.getId());
        connection.close();

    }

    @Override
    public void remove(ClinicStaffWithPasswords item) throws SQLException {
        String sqlUpdatePatient = "DELETE from clinic_staff where staff_id = ?";

        Connection connection = ConnectionPoolHolder.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdatePatient);
        preparedStatement.setInt(1, item.getId());
        preparedStatement.executeUpdate();
        connection.close();
    }

    @Override
    public void remove(Iterable<ClinicStaffWithPasswords> items) throws SQLException {
        for (ClinicStaffWithPasswords staff : items) {
            remove(staff);
        }
    }

    @Override
    public List<ClinicStaffWithPasswords> query(String query) throws SQLException {
        Connection connection = ConnectionPoolHolder.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        List<ClinicStaffWithPasswords> patientCards = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String surname = resultSet.getString(2);
            String name = resultSet.getString(3);
            String title  = resultSet.getString(4);
            String email = resultSet.getString(5);
            String password = resultSet.getString(6);
            patientCards.add(new ClinicStaffWithPasswords(id, surname, name, title, email, password));
        }
        connection.close();
        return patientCards;
    }

    @Override
    public List<ClinicStaffWithPasswords> getAll() throws SQLException {
        String sqlSelect = "SELECT * FROM patients_cards;";
        return query(sqlSelect);
    }

    public ClinicStaff checkAuthorization(String email, String password) throws SQLException {
        Connection connection = ConnectionPoolHolder.getConnection();
        String sqlSearch = "SELECT * FROM clinic_staff WHERE email = ? and `password` = ?;";
        PreparedStatement statement = connection.prepareStatement(sqlSearch);
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet set = statement.executeQuery();
        ClinicStaff staff = null;
        while (set.next()){
            int id = set.getInt(1);
            String surname = set.getString(2);
            String name = set.getString(3);
            String title  = set.getString(4);
            staff = new ClinicStaff(id, surname, name, title, email);
        }
        connection.close();
        return staff;
    }

    public boolean checkIfEmailExists(String email) throws SQLException {
        Connection connection = ConnectionPoolHolder.getConnection();
        String sqlSearch = "SELECT * FROM clinic_staff WHERE email = ?;";
        PreparedStatement statement = connection.prepareStatement(sqlSearch);
        statement.setString(1, email);
        return statement.executeQuery().next() ? true : false;
    }
}
