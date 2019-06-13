package controller;

import commonlyUsedStrings.PageName;
import exceptions.UnAuthorisedException;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class AdminAddPatientsPageGetController implements Controller {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, UnsupportedEncodingException, UnAuthorisedException {
        return PageName.ADMIN_ADD_PATIENT;
    }
}
