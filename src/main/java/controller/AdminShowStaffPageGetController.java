package controller;

import commonlyUsedStrings.PageName;
import exceptions.UnAuthorisedException;
import models.ClinicStaff;
import services.ClinicStaffService;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class AdminShowStaffPageGetController implements Controller {
    private static final ClinicStaffService clinicStaffService = new ClinicStaffService();

    @Override
    public String execute(HttpServletRequest request) throws SQLException, UnsupportedEncodingException, UnAuthorisedException {
        List<ClinicStaff> staffList = clinicStaffService.getAll();
        request.setAttribute("staffList", staffList);
        return PageName.ADMIN_SHOW_STAFF;
    }
}
