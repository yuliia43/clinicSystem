package controller;

import commonlyUsedStrings.PageName;
import models.Diagnosis;
import models.PatientCard;
import services.ClinicStaffService;
import services.DiagnosisService;
import services.PatientCardsService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class PatientsDiagnosesGetController implements Controller {
    private static final PatientCardsService patientsCardsService =
            new PatientCardsService();
    private static final DiagnosisService diagnosisService =
            new DiagnosisService();
    private static final ClinicStaffService clinicStaffService =
            new ClinicStaffService();


    /**
     * @param req
     * @return
     * @throws SQLException
     */
    @Override
    public String execute(HttpServletRequest req) throws SQLException {

        int id = Integer.parseInt(req.getParameter("patientId"), 10);
        PatientCard patientCard = patientsCardsService.getOneById(id);
        req.setAttribute("patient", patientCard);
        List<Diagnosis> diagnoses = diagnosisService.getDiagnosisForPatient(id);
        req.setAttribute("diagnoses", diagnoses);
        req.setAttribute("staff", clinicStaffService.getAll());
        req.setAttribute("doctors", clinicStaffService.getAllDoctors());
        return PageName.DIAGNOSES;
    }
}
