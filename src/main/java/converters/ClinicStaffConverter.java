package converters;

import dtos.ClinicStaffWithPasswords;
import models.ClinicStaff;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class ClinicStaffConverter {
    /**
     * @param clinicStaffWithPasswords
     * @return
     */
    public static ClinicStaff convertIntoModel(ClinicStaffWithPasswords clinicStaffWithPasswords) {
        return new ClinicStaff(clinicStaffWithPasswords.getId(), clinicStaffWithPasswords.getSurname(), clinicStaffWithPasswords.getName(),
                clinicStaffWithPasswords.getTitle(), clinicStaffWithPasswords.getEmail());
    }
}

