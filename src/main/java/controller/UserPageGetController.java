package controller;

import commonlyUsedStrings.PageName;
import models.ClinicStaff;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class UserPageGetController implements Controller {
    /**
     * @param request
     * @return
     * @throws SQLException
     */
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        ClinicStaff user = (ClinicStaff) request.getSession().getAttribute("user");
        if (user != null)
            return PageName.USER_PAGE;
        else
            return PageName.AUTHORISATION_ERROR;
    }
}
