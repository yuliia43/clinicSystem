package exceptions;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class UnAuthorisedException extends Throwable {

    @Override
    public String getMessage() {
        return "User not authorised";
    }
}
