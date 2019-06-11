package controller;

import commonlyUsedStrings.PageName;
import dtos.ClinicStaffWithPasswords;
import org.apache.log4j.Logger;
import services.ClinicStaffService;
import servlets.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class RegistrationPagePostController implements Controller {
    private static final ClinicStaffService clinicStaffService =
            new ClinicStaffService();
    private static final Logger logger = Logger.getLogger(DispatcherServlet.class);


    /**
     * @param req
     * @return
     * @throws SQLException
     */
    @Override
    public String execute(HttpServletRequest req) throws SQLException {
        String name = req.getParameter("firstName");
        String surname = req.getParameter("lastName");
        String email = req.getParameter("email");
        String title = req.getParameter("title");
        String password = req.getParameter("password");
        if (!password.equals(req.getParameter("confirmedPassword"))) {
            req.setAttribute("notEqual", true);
            return PageName.REGISTRATION;
        } else if (name.isEmpty() || surname.isEmpty() || email.isEmpty()
                || title == null || password.isEmpty()) {
            req.setAttribute("fail", true);
            return PageName.REGISTRATION;
        } else {
            ClinicStaffWithPasswords clinicStaff = new ClinicStaffWithPasswords();
            clinicStaff.setName(name);
            clinicStaff.setSurname(surname);
            clinicStaff.setTitle(title);
            clinicStaff.setEmail(email);
            clinicStaff.setPassword(password);
            clinicStaffService.add(clinicStaff);
            logger.info("Clinic staff " + name + " " + surname + " added");
            return PageName.SUCCESSFUL_REGISTRATION;
        }
    }
}
