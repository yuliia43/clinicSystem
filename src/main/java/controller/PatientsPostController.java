package controller;

import models.ClinicStaff;
import models.Diagnosis;
import org.apache.log4j.Logger;
import services.DiagnosisService;
import servlets.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class PatientsPostController implements Controller {
    private static final Logger logger = Logger.getLogger(DispatcherServlet.class);
    private DiagnosisService diagnosisService = new DiagnosisService();

    @Override
    public String execute(HttpServletRequest req) throws SQLException {
        int patientId = Integer.parseInt(req.getParameter("patientId"), 10);
        String diagnosis = req.getParameter("diagnosis");
        int doctorId = ((ClinicStaff) req.getSession().getAttribute("user")).getId();
            if(diagnosis.isEmpty())
                req.setAttribute("fail", true);
            else{
                diagnosisService.add(new Diagnosis(patientId, diagnosis, doctorId));
                logger.info("Added diagnosis to patient with id " + patientId);
            }
        return new PatientsGetController().execute(req);
    }
}
