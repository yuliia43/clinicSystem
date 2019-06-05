package services;

import dtos.ClinicStaffWithPasswords;
import facades.ClinicStaffConverter;
import models.ClinicStaff;
import repositories.ClinicStaffRepository;

import java.sql.SQLException;

public class ClinicStaffService {
    private static final ClinicStaffRepository clinicStaffRepository = new ClinicStaffRepository();

    public ClinicStaff getOneById(int id) throws SQLException {
        ClinicStaffWithPasswords staffWithPasswords = clinicStaffRepository.getOneById(id);
        return ClinicStaffConverter.convertIntoModel(staffWithPasswords);
    }
}
