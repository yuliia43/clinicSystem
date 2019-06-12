package controller;

import commonlyUsedStrings.PageName;
import exceptions.UnAuthorisedException;
import exceptions.Validator;
import models.ClinicStaff;
import models.PatientCard;
import services.PatientCardsService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class PatientsGetController implements Controller {
    private PatientCardsService patientCardsService = new PatientCardsService();

    /**
     * @param req
     * @return
     * @throws SQLException
     */
    @Override
    public String execute(HttpServletRequest req) throws SQLException, UnAuthorisedException {
        ClinicStaff staff = (ClinicStaff) req.getSession().getAttribute("user");
        Validator.checkIfAuthorised(staff);
        if (staff.getTitle().equals("doctor")) {
            List<PatientCard> allPatients = patientCardsService.getAllExceptDoctorsPatients(staff.getId());
            req.setAttribute("allPatients", allPatients);
            List<PatientCard> cards = patientCardsService.getAllByDoctorId(staff.getId());
            req.setAttribute("doctorsPatients", cards);
            return PageName.DOCTORS_PATIENTS;
        } else {
            return PageName.ACCESS_ERROR;
        }
    }
}
