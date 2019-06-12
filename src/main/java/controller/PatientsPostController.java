package controller;

import converters.StringConverter;
import models.ClinicStaff;
import models.Diagnosis;
import org.apache.log4j.Logger;
import services.DiagnosisService;
import servlets.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class PatientsPostController implements Controller {
    private static final Logger logger = Logger.getLogger(DispatcherServlet.class);
    private DiagnosisService diagnosisService = new DiagnosisService();

    /**
     * @param req
     * @return
     * @throws SQLException
     */
    @Override
    public String execute(HttpServletRequest req) throws SQLException, UnsupportedEncodingException {
        int patientId = Integer.parseInt(req.getParameter("patientId"), 10);
        String diagnosis = StringConverter.convertToUTF8(req.getParameter("diagnosis"));
        int doctorId = ((ClinicStaff) req.getSession().getAttribute("user")).getId();
        if (diagnosis.isEmpty()){
            req.setAttribute("openedMenu", true);
            req.setAttribute("fail", true);
        }
        else {
            diagnosisService.add(new Diagnosis(patientId, diagnosis, doctorId));
            logger.info("Added diagnosis to patient with id " + patientId);
        }
        return new PatientsGetController().execute(req);
    }
}
