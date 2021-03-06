package repositories;

import dtos.ClinicStaffWithPasswords;
import models.ClinicStaff;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class ClinicStaffRepository implements Repository<ClinicStaffWithPasswords> {

    private static final Logger logger = Logger.getLogger(ClinicStaffRepository.class);
    private static final ClinicStaffRepository clinicStaffRepository =
            new ClinicStaffRepository();

    /**
     *
     */
    private ClinicStaffRepository() {

    }

    /**
     * @return
     */
    public static ClinicStaffRepository getClinicStaffRepository() {
        return clinicStaffRepository;
    }

    /**
     * @param item
     * @param connection
     * @throws SQLException
     */
    @Override
    public void add(ClinicStaffWithPasswords item, Connection connection) throws SQLException {
        addItem(item, connection);
    }

    /**
     * @param item
     * @param connection
     * @throws SQLException
     */
    private void addItem(ClinicStaffWithPasswords item, Connection connection) throws SQLException {
        String sqlAddStaff = "INSERT INTO " +
                "clinic_staff (staff_surname, staff_name, title, email,  `password`)" +
                "VALUES(?, ? , ?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(sqlAddStaff);
        statement.setString(1, item.getSurname());
        statement.setString(2, item.getName());
        statement.setString(3, item.getTitle());
        statement.setString(4, item.getEmail());
        statement.setString(5, item.getPassword());
        statement.executeUpdate();
        logger.info("Registration success!");
    }

    /**
     * @param items
     * @param connection
     * @throws SQLException
     */
    @Override
    public void add(Iterable<ClinicStaffWithPasswords> items, Connection connection) throws SQLException {
        for (ClinicStaffWithPasswords staff : items) {
            addItem(staff, connection);
        }
    }

    /**
     * @param item
     * @param connection
     * @throws SQLException
     */
    @Override
    public void update(ClinicStaffWithPasswords item, Connection connection) throws SQLException {
        String sqlUpdateStaff = "UPDATE clinic_staff " +
                "SET staff_surname = ?, staff_name = ?, title = ?, email = ?, " +
                "`password` = ? where staff_id = ?;";
        PreparedStatement statement = connection.prepareStatement(sqlUpdateStaff);
        statement.setString(1, item.getSurname());
        statement.setString(2, item.getName());
        statement.setString(3, item.getTitle());
        statement.setString(4, item.getEmail());
        statement.setString(5, item.getPassword());
        statement.setInt(6, item.getId());
        statement.executeUpdate();
        logger.info("Updated user with id " + item.getId());

    }

    /**
     * @param item
     * @param connection
     * @throws SQLException
     */
    @Override
    public void remove(ClinicStaffWithPasswords item, Connection connection) throws SQLException {
        removeItem(item, connection);
    }

    /**
     * @param item
     * @param connection
     * @throws SQLException
     */
    private void removeItem(ClinicStaffWithPasswords item, Connection connection) throws SQLException {
        String sqlUpdatePatient = "DELETE from clinic_staff where staff_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdatePatient);
        preparedStatement.setInt(1, item.getId());
        preparedStatement.executeUpdate();
        logger.info("Removed user with id " + item.getId());
    }

    /**
     * @param items
     * @param connection
     * @throws SQLException
     */
    @Override
    public void remove(Iterable<ClinicStaffWithPasswords> items, Connection connection) throws SQLException {
        for (ClinicStaffWithPasswords staff : items) {
            removeItem(staff, connection);
        }
    }

    /**
     * @param query
     * @param connection
     * @return
     * @throws SQLException
     */
    @Override
    public List<ClinicStaffWithPasswords> query(String query, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);


        List<ClinicStaffWithPasswords> clinicStaffWithPasswords = getClinicStaffWithPasswords(resultSet);
        return clinicStaffWithPasswords;
    }

    /**
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private List<ClinicStaffWithPasswords> getClinicStaffWithPasswords(ResultSet resultSet) throws SQLException {
        List<ClinicStaffWithPasswords> staff = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String surname = resultSet.getString(2);
            String name = resultSet.getString(3);
            String title = resultSet.getString(4);
            String email = resultSet.getString(5);
            String password = resultSet.getString(6);
            staff.add(new ClinicStaffWithPasswords(id, surname, name, title, email, password));
        }
        return staff;
    }

    /**
     * @param connection
     * @return
     * @throws SQLException
     */
    @Override
    public List<ClinicStaffWithPasswords> getAll(Connection connection) throws SQLException {
        String sqlSelect = "SELECT * FROM clinic_staff;";
        return query(sqlSelect, connection);
    }

    /**
     * @param connection
     * @return
     * @throws SQLException
     */
    public List<ClinicStaffWithPasswords> getAllDoctors(Connection connection) throws SQLException {
        String sqlSelect = "SELECT * FROM clinic_staff WHERE title='doctor';";
        return query(sqlSelect, connection);
    }

    /**
     * @param email
     * @param password
     * @param connection
     * @return
     * @throws SQLException
     */
    public ClinicStaff checkAuthorization(String email, String password, Connection connection) throws SQLException {
        String sqlSearch = "SELECT * FROM clinic_staff WHERE email = ? and `password` = ?;";
        PreparedStatement statement = connection.prepareStatement(sqlSearch);
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet set = statement.executeQuery();
        ClinicStaff staff = null;
        while (set.next()) {
            int id = set.getInt(1);
            String surname = set.getString(2);
            String name = set.getString(3);
            String title = set.getString(4);
            staff = new ClinicStaff(id, surname, name, title, email);
        }
        return staff;
    }

    /**
     * @param email
     * @param connection
     * @return
     * @throws SQLException
     */
    public boolean checkIfEmailExists(String email, Connection connection) throws SQLException {
        String sqlSearch = "SELECT * FROM clinic_staff WHERE email = ?;";
        PreparedStatement statement = connection.prepareStatement(sqlSearch);
        statement.setString(1, email);
        boolean executed = statement.executeQuery().next() ? true : false;
        return executed;
    }

    /**
     * @param id
     * @param connection
     * @return
     * @throws SQLException
     */
    @Override
    public ClinicStaffWithPasswords getOneById(int id, Connection connection) throws SQLException {
        String sqlSelect = "SELECT * from patients_cards WHERE patient_card_id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<ClinicStaffWithPasswords> staff = getClinicStaffWithPasswords(resultSet);
        if (staff.size() == 0)
            return null;
        return staff.get(0);
    }
}
