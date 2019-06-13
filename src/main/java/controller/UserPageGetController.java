package controller;

import commonlyUsedStrings.PageName;
import exceptions.UnAuthorisedException;
import exceptions.Validator;
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
    public String execute(HttpServletRequest request) throws SQLException, UnAuthorisedException {
        ClinicStaff user = (ClinicStaff) request.getSession().getAttribute("user");
        Validator.checkIfAuthorised(user);
            return PageName.USER_PAGE;
    }
}
