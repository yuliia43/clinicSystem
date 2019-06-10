package services;

import converters.ClinicStaffConverter;
import dtos.ClinicStaffWithPasswords;
import models.ClinicStaff;
import repositories.ClinicStaffRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class ClinicStaffService extends Service {
    private static final ClinicStaffRepository clinicStaffRepository = ClinicStaffRepository.getClinicStaffRepository();

    /**
     * @param id
     * @return
     * @throws SQLException
     */
    public ClinicStaff getOneById(int id) throws SQLException {
        ClinicStaffWithPasswords staffWithPasswords;
        try (Connection connection = receiveConnection()) {
            staffWithPasswords = clinicStaffRepository.getOneById(id, connection);
            return ClinicStaffConverter.convertIntoModel(staffWithPasswords);
        }
    }

    /**
     * @return
     * @throws SQLException
     */
    public List<ClinicStaff> getAll() throws SQLException {
        List<ClinicStaffWithPasswords> all;
        try (Connection connection = receiveConnection()) {
            all = clinicStaffRepository.getAll(connection);
            Stream<ClinicStaffWithPasswords> stream = all.stream();
            Stream<ClinicStaff> clinicStaffStream = stream
                    .map(staff -> ClinicStaffConverter.convertIntoModel(staff));
            return clinicStaffStream
                    .collect(Collectors.toList());
        }
    }

    /**
     * @return
     * @throws SQLException
     */
    public List<ClinicStaff> getAllDoctors() throws SQLException {
        try (Connection connection = receiveConnection()) {
            return clinicStaffRepository.getAllDoctors(connection).stream()
                    .map(staff -> ClinicStaffConverter.convertIntoModel(staff))
                    .collect(Collectors.toList());
        }
    }

    /**
     * @param email
     * @param password
     * @return
     * @throws SQLException
     */
    public ClinicStaff checkAuthorization(String email, String password) throws SQLException {
        try (Connection connection = receiveConnection()) {
            return clinicStaffRepository.checkAuthorization(email, password, connection);
        }
    }

    /**
     * @param clinicStaffWithPasswords
     * @throws SQLException
     */
    public void add(ClinicStaffWithPasswords clinicStaffWithPasswords) throws SQLException {
        try (Connection connection = receiveConnection()) {
            clinicStaffRepository.add(clinicStaffWithPasswords, connection);
        }
    }
}
