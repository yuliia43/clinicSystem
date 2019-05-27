package repositories;

import jdbc.ConnectionPoolHolder;
import models.PatientCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientCardsRepository implements Repository<PatientCard> {


    @Override
    public void add(PatientCard item) throws SQLException {
        String sqlAddPatient = "INSERT INTO " +
                "patients_cards (patient_surname, patient_name, birthday_date, sex)" +
                "VALUES(?, ? , ?, ?);";
        Connection connection = ConnectionPoolHolder.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlAddPatient);
        statement.setString(1, item.getSurname());
        statement.setString(2, item.getName());
        statement.setDate(3, Date.valueOf(item.getBirthday_date().toString()));
        statement.setString(4, ""+item.getSex());
        statement.executeUpdate();
        connection.close();
    }

    @Override
    public void add(Iterable<PatientCard> items) throws SQLException {
        for (PatientCard card : items) {
            add(card);
        }
    }

    @Override
    public void update(PatientCard item) throws SQLException {
        String sqlUpdatePatient = "UPDATE patients_cards " +
                "SET patient_surname = ?, patient_name = ?, birthday_date = ?, sex = ?" +
                "where patient_card_id = ?;";
        Connection connection = ConnectionPoolHolder.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlUpdatePatient);
        statement.setString(1, item.getSurname());
        statement.setString(2, item.getName());
        statement.setDate(3, Date.valueOf(item.getBirthday_date().toString()));
        statement.setString(4, ""+item.getSex());
        statement.setInt(5, item.getId());
        statement.executeUpdate();
        connection.close();
    }

    @Override
    public void remove(PatientCard item) throws SQLException {
        String sqlUpdatePatient = "DELETE from patients_cards where patient_card_id = ?";

        Connection connection = ConnectionPoolHolder.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdatePatient);
        preparedStatement.setInt(1, item.getId());
        preparedStatement.executeUpdate();
        connection.close();
    }

    @Override
    public void remove(Iterable<PatientCard> items) throws SQLException {
        for (PatientCard card : items) {
            remove(card);
        }
    }

    @Override
    public List<PatientCard> query(String query) throws SQLException {
        Connection connection = ConnectionPoolHolder.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        List<PatientCard> patientCards = getPatientCards(resultSet);
        connection.close();
        return patientCards;
    }

    private List<PatientCard> getPatientCards(ResultSet resultSet) throws SQLException {
        List<PatientCard> patientCards = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String surname = resultSet.getString(2);
            String name = resultSet.getString(3);
            char sex = resultSet.getString(4).charAt(0);
            java.util.Date birthday_date = resultSet.getDate(5);
            patientCards.add(new PatientCard(id, surname, name, sex, birthday_date));
        }
        return patientCards;
    }

    @Override
    public List<PatientCard> getAll() throws SQLException {
        String sqlSelect = "SELECT * from patients_cards;";
        return query(sqlSelect);
    }


    public List<PatientCard> getAllByDoctorId(int id) throws SQLException {
        String sqlSearchPatients = "SELECT * FROM patients_cards WHERE patient_card_id in\n" +
                " (SELECT  patient_card_id  FROM diagnosis WHERE doctor_id = 2 and is_final_diagnosis = 0\n" +
                " and diagnosis_id in (Select MAX(diagnosis_id) FROM  diagnosis GROUP BY(patient_card_id)));\n";
        Connection connection = ConnectionPoolHolder.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlSearchPatients);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<PatientCard> patientCards = getPatientCards(resultSet);
        connection.close();
        return patientCards;
    }


}
