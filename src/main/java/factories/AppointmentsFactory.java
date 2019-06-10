package factories;

import enums.AppointedTypes;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class AppointmentsFactory {
    /**
     * @param type
     * @return
     */
    public static AppointedTypes getAppointmentType(String type) {
        switch (type) {
            case ("operation"): {
                return AppointedTypes.OPERATION;
            }
            case ("medicine"): {
                return AppointedTypes.MEDICINE;
            }
            case ("procedure"): {
                return AppointedTypes.PROCEDURE;
            }
            default:
                return null;
        }
    }
}
