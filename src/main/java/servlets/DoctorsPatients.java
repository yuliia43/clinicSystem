package servlets;


import models.ClinicStaff;
import models.PatientCard;
import org.apache.log4j.Logger;
import repositories.PatientCardsRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DoctorsPatients extends HttpServlet {

    private static final Logger logger = Logger.getLogger(DoctorsPatients.class);
    private PatientCardsRepository patientCardsRepository = PatientCardsRepository.getPatientCardsRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClinicStaff staff = (ClinicStaff) req.getSession().getAttribute("user");
            if(staff.getTitle().equals("doctor")){
                try {
                    List<PatientCard> cards = patientCardsRepository.getAllByDoctorId(staff.getId());
                    req.setAttribute("patients", cards);
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
}
