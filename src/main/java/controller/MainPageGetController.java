package controller;

import commonlyUsedStrings.PageName;
import models.ClinicStaff;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class MainPageGetController implements Controller {

    /**
     * @param req
     * @return
     * @throws SQLException
     */
    @Override
    public String execute(HttpServletRequest req) throws SQLException {
        ClinicStaff staff = new ClinicStaff(2, "Samwell", "Tarly", "doctor", "sam@gmail.com");
        req.getSession().setAttribute("user", staff);
        return PageName.MAIN_PAGE;
    }
}
