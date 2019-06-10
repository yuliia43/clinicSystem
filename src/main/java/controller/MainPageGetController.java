package controller;

import models.ClinicStaff;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class MainPageGetController implements Controller {
    @Override
    public String execute(HttpServletRequest req) throws SQLException {
        ClinicStaff staff = new ClinicStaff(2, "Samwell", "Tarly", "doctor", "sam@gmail.com");
        req.getSession().setAttribute("user", staff);
        return "pages/mainPage.jsp";
    }
}
