package controller;

import commonlyUsedStrings.PageName;
import converters.StringConverter;
import models.ClinicStaff;
import org.apache.log4j.Logger;
import services.ClinicStaffService;
import servlets.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class AuthorisationPostController implements Controller {
    private static final Logger logger = Logger.getLogger(DispatcherServlet.class);
    private static final ClinicStaffService clinicStaffService =
            new ClinicStaffService();

    /**
     * @param req
     * @return
     * @throws SQLException
     */
    @Override
    public String execute(HttpServletRequest req) throws SQLException, UnsupportedEncodingException {
        String email = StringConverter.convertToUTF8(req.getParameter("email"));
        String password = StringConverter.convertToUTF8(req.getParameter("password"));
        if (!email.isEmpty() && !password.isEmpty()) {
            ClinicStaff staff = clinicStaffService.checkAuthorization(email, password);
            if (staff != null) {
                logger.info("User with id " + staff.getId() + " authorised");
                req.getSession().setAttribute("user", staff);
                return PageName.USER_PAGE;
            } else {
                req.setAttribute("fail", true);
                return PageName.AUTHORISATION;
            }
        } else {
            req.setAttribute("isEmpty", true);
            return PageName.AUTHORISATION;
        }
    }
}
