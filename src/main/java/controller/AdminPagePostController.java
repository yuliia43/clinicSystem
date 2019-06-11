package controller;

import commonlyUsedStrings.PageName;
import models.PatientCard;
import org.apache.log4j.Logger;
import services.PatientCardsService;
import servlets.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class AdminPagePostController implements Controller {

    private static final Logger logger = Logger.getLogger(DispatcherServlet.class);

    /**
     * @param req
     * @return
     * @throws SQLException
     */
    @Override
    public String execute(HttpServletRequest req) throws SQLException {
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
        service.add(patientCard);
        logger.info("Added patient " + firstName + " " + lastName);
        return PageName.ADMIN_PAGE;
    }
}
