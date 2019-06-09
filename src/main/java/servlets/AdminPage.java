package servlets;

import models.PatientCard;
import org.apache.log4j.Logger;
import services.PatientCardsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AdminPage extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AdminPage.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("pages/adminPage.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        char sex = req.getParameter("sex").charAt(0);
        Date birthday = Date
                .from(LocalDate
                        .parse(req.getParameter("birthday"))
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant());
        PatientCard patientCard = new PatientCard(firstName, lastName, sex, birthday);
        PatientCardsService service = new PatientCardsService();
        try {
            service.add(patientCard);
            logger.info("Added patient " + firstName + " " + lastName);
        }catch (SQLException e) {
            logger.error("Sql error occured!");
            req.getRequestDispatcher("errorPages/SQlError.jsp")
                    .forward(req, resp);
        }
    }
}
