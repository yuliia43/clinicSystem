package factories;

import enums.AppointedTypes;

public class AppointmentsFactory {
    public static AppointedTypes getAppointmentType(String type){
        switch (type){
            case("operation"):{
                return AppointedTypes.OPERATION;
            }
            case("medicine"):{
                return AppointedTypes.MEDICINE;
            }
            case("procedure"):{
                return AppointedTypes.PROCEDURE;
            }
            default:
                return null;
        }
    }
}
