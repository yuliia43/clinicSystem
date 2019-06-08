package converters;

import dtos.ClinicStaffWithPasswords;
import enums.AppointedTypes;
import models.ClinicStaff;

public class ClinicStaffConverter {
    public static ClinicStaff convertIntoModel(ClinicStaffWithPasswords cswp){
        return new ClinicStaff(cswp.getId(), cswp.getSurname(),cswp.getName(),
                cswp.getTitle(), cswp.getEmail());
    }
}

