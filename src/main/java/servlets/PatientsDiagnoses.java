package servlets;

import models.ClinicStaff;
import models.Diagnosis;
import models.PatientCard;
import org.apache.log4j.Logger;
import services.AppointingScheduleService;
import services.ClinicStaffService;
import services.DiagnosisService;
import services.PatientCardsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PatientsDiagnoses extends HttpServlet {
    private static final PatientCardsService patientsCardsService =
            new PatientCardsService();
    private static final AppointingScheduleService appointingScheduleService =
            new AppointingScheduleService();
    private static final DiagnosisService diagnosisService =
            new DiagnosisService();
    private static final ClinicStaffService clinicStaffService =
            new ClinicStaffService();
    private static final Logger logger = Logger.getLogger(PatientsDiagnoses.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("patientId"), 10);
            PatientCard patientCard = patientsCardsService.getOneById(id);
            req.setAttribute("patient", patientCard);
            List<Diagnosis> diagnoses = DiagnosisService.getDiagnosisForPatient(id);
            req.setAttribute("diagnoses", diagnoses);
            req.setAttribute("staff", clinicStaffService.getAll());
            req.setAttribute("doctors", clinicStaffService.getAllDoctors());
            req.getRequestDispatcher("pages/diagnoses.jsp").forward(req, resp);
        } catch (SQLException e) {
            logger.error("Sql error occured!");
            req.getRequestDispatcher("errorPages/SQlError.jsp")
                    .forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        try {
        switch (method) {
            case ("delete_recommendation"): {
                int appointedId = Integer.parseInt(req.getParameter("appointedId"), 10);
                appointingScheduleService.cancelAppointed(appointedId);
            }
            case ("add_diagnosis"): {
                String diagnosisStr = req.getParameter("diagnosis");
                int doctorId = ((ClinicStaff)req.getSession().getAttribute("user")).getId();
                int patientId = Integer.parseInt(req.getParameter("patientId"),10);
                Diagnosis diagnosis = new Diagnosis();
                diagnosis.setDoctorId(doctorId);
                diagnosis.setCardId(patientId);
                diagnosis.setDiagnosis(diagnosisStr);
                diagnosisService.add(diagnosis);
            }
            case ("add_appointed"):{
                String details = req.getParameter("details");
                int num_days = Integer.parseInt(req.getParameter("num_days"), 10);
                String[] times = req.getParameterValues("time");
                int performerId = Integer.parseInt(req.getParameter("performerId"), 10);
                for(int i = 1; i <= num_days; i++){
                    for(int j = 0; j < times.length; j++){

                    }
                }
                String diagnosisId = req.getParameter("diagnosisId");
            }
            case ("discharge"):{

            }
        }
        doGet(req, resp);
        } catch (SQLException e) {
            logger.error("Sql error occured!");
            req.getRequestDispatcher("errorPages/SQlError.jsp")
                    .forward(req, resp);
        }
    }
}
