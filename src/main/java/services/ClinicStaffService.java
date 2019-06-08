package services;

import dtos.ClinicStaffWithPasswords;
import converters.ClinicStaffConverter;
import models.ClinicStaff;
import repositories.ClinicStaffRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClinicStaffService {
    private static final ClinicStaffRepository clinicStaffRepository = ClinicStaffRepository.getClinicStaffRepository();

    public ClinicStaff getOneById(int id) throws SQLException {
        ClinicStaffWithPasswords staffWithPasswords = clinicStaffRepository.getOneById(id);
        return ClinicStaffConverter.convertIntoModel(staffWithPasswords);
    }

    public List<ClinicStaff> getAll() throws SQLException {
        List<ClinicStaffWithPasswords> all = clinicStaffRepository.getAll();
        Stream<ClinicStaffWithPasswords> stream = all.stream();
        Stream<ClinicStaff> clinicStaffStream = stream
                .map(staff -> ClinicStaffConverter.convertIntoModel(staff));
        return clinicStaffStream
                .collect(Collectors.toList());
    }

    public List<ClinicStaff> getAllDoctors() throws SQLException {
        return clinicStaffRepository.getAllDoctors().stream()
                .map(staff -> ClinicStaffConverter.convertIntoModel(staff))
                .collect(Collectors.toList());
    }
}
