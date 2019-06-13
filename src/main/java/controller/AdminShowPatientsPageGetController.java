package controller;

import commonlyUsedStrings.PageName;
import exceptions.UnAuthorisedException;
import models.PatientCard;
import services.PatientCardsService;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class AdminShowPatientsPageGetController implements Controller {


    private static final PatientCardsService patientCardsService = new PatientCardsService();

    /**
     * @param request
     * @return
     * @throws SQLException
     */
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
        List<PatientCard> patients = patientCardsService.getAll();
        request.setAttribute("patients", patients);
        return PageName.ADMIN_SHOW_PATIENT;
    }
}
