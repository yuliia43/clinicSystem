package factories;

import enums.AppointmentTypes;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class AppointmentsFactory {
    /**
     * @param type
     * @return
     */
    public static AppointmentTypes getAppointmentType(String type) {
        switch (type) {
            case ("operation"): {
                return AppointmentTypes.OPERATION;
            }
            case ("medicine"): {
                return AppointmentTypes.MEDICINE;
            }
            case ("procedure"): {
                return AppointmentTypes.PROCEDURE;
            }
            default:
                return null;
        }
    }
}
