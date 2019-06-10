package factories;

import controller.*;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class ControllerFactory {

    /**
     * @param uri
     * @return
     */
    public static Controller chooseGetMethodController(String uri) {
        switch (uri) {
            case ("admin"): {
                return new AdminPageGetController();
            }
            case ("main"): {
                return new MainPageGetController();
            }
            case ("authorisation"): {
                return new AuthorisationGetController();
            }
            case ("userPage"): {
                return new UserPageGetController();
            }
            case ("patients"): {
                return new PatientsGetController();
            }
            case ("diagnoses"): {
                return new PatientsDiagnosesGetController();
            }
            case ("registration"): {
                return new RegistrationPageGetController();
            }
            case ("appointments"): {
                return new AppointmentGetController();
            }
            default: {
                return new PageNotFoundGetController();
            }
        }
    }


    /**
     * @param uri
     * @return
     */
    public static Controller choosePostMethodController(String uri) {
        switch (uri) {
            case ("admin"): {
                return new AdminPagePostController();
            }
            case ("authorisation"): {
                return new AuthorisationPostController();
            }
            case ("patients"): {
                return new PatientsPostController();
            }
            case ("diagnoses"): {
                return new PatientsDiagnosesPostController();
            }
            case ("registration"): {
                return new RegistrationPagePostController();
            }
            case ("appointments"): {
                return new AppointmentPostController();
            }
            default: {
                return new PageNotFoundGetController();
            }
        }
    }

}
