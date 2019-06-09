package servlets;


import models.ClinicStaff;
import models.Diagnosis;
import models.PatientCard;
import org.apache.log4j.Logger;
import services.DiagnosisService;
import services.PatientCardsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DoctorsPatients extends HttpServlet {

    private static final Logger logger = Logger.getLogger(DoctorsPatients.class);
    private PatientCardsService patientCardsService = new PatientCardsService();
    private DiagnosisService diagnosisService = new DiagnosisService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClinicStaff staff = (ClinicStaff) req.getSession().getAttribute("user");
            if(staff.getTitle().equals("doctor")){
                try {
                    List<PatientCard> allPatients = patientCardsService.getAll();
                    req.setAttribute("allPatients", allPatients);
                    List<PatientCard> cards = patientCardsService.getAllByDoctorId(staff.getId());
                    req.setAttribute("doctorsPatients", cards);
                    req.getRequestDispatcher("pages/DoctorsPatients.jsp").forward(req, resp);
                } catch (SQLException e) {
                    logger.error("Sql error occured!");
                    req.getRequestDispatcher("errorPages/SQlError.jsp")
                            .forward(req,resp);
                }
            }
            else {
                req.getRequestDispatcher("errorPages/accessError.jsp")
                        .forward(req, resp);
            }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int patientId = Integer.parseInt(req.getParameter("patientId"), 10);
        String diagnosis = req.getParameter("diagnosis");
        int doctorId = ((ClinicStaff) req.getSession().getAttribute("user")).getId();
        try {
            if(diagnosis.isEmpty())
                req.setAttribute("fail", true);
            else
                diagnosisService.add(new Diagnosis(patientId, diagnosis, doctorId));
            doGet(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
