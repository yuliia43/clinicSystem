package exceptions;

import models.ClinicStaff;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class Validator {

    public static void checkIfAuthorised(ClinicStaff staff) throws UnAuthorisedException {
        if(staff == null){
            throw new UnAuthorisedException();
        }
    }
}
