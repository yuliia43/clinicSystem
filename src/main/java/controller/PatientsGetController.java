package controller;

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
    public String execute(HttpServletRequest req) throws SQLException {
        ClinicStaff staff = (ClinicStaff) req.getSession().getAttribute("user");
        if (staff.getTitle().equals("doctor")) {
            List<PatientCard> allPatients = patientCardsService.getAll();
            if(allPatients.size() == 0)
                return "pages/noPatients.jsp";
            req.setAttribute("allPatients", allPatients);
            List<PatientCard> cards = patientCardsService.getAllByDoctorId(staff.getId());
            req.setAttribute("doctorsPatients", cards);
            return "pages/DoctorsPatients.jsp";
        } else {
            return "errorPages/accessError.jsp";
        }
    }
}
