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
        return PageName.MAIN_PAGE;
    }
}
