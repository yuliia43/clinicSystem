package services;

import dtos.ClinicStaffWithPasswords;
import converters.ClinicStaffConverter;
import jdbc.ConnectionPoolHolder;
import models.ClinicStaff;
import repositories.ClinicStaffRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClinicStaffService {
    private static final ClinicStaffRepository clinicStaffRepository = ClinicStaffRepository.getClinicStaffRepository();

    public ClinicStaff getOneById(int id) throws SQLException {
        Connection connection = ConnectionPoolHolder.getConnection();
        ClinicStaffWithPasswords staffWithPasswords = clinicStaffRepository.getOneById(id, connection);
        return ClinicStaffConverter.convertIntoModel(staffWithPasswords);
    }

    public List<ClinicStaff> getAll() throws SQLException {
        Connection connection = ConnectionPoolHolder.getConnection();
        List<ClinicStaffWithPasswords> all = clinicStaffRepository.getAll(connection);
        Stream<ClinicStaffWithPasswords> stream = all.stream();
        Stream<ClinicStaff> clinicStaffStream = stream
                .map(staff -> ClinicStaffConverter.convertIntoModel(staff));
        return clinicStaffStream
                .collect(Collectors.toList());
    }

    public List<ClinicStaff> getAllDoctors() throws SQLException {
        Connection connection = ConnectionPoolHolder.getConnection();
        return clinicStaffRepository.getAllDoctors(connection).stream()
                .map(staff -> ClinicStaffConverter.convertIntoModel(staff))
                .collect(Collectors.toList());
    }

    public ClinicStaff checkAuthorization(String email, String password) throws SQLException {
        Connection connection = ConnectionPoolHolder.getConnection();
        return clinicStaffRepository.checkAuthorization(email, password, connection);
    }

    public void add(ClinicStaffWithPasswords clinicStaffWithPasswords) throws SQLException {
        Connection connection = ConnectionPoolHolder.getConnection();
        clinicStaffRepository.add(clinicStaffWithPasswords, connection);
    }
}
