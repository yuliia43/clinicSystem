package controller;

import commonlyUsedStrings.PageName;
import converters.StringConverter;
import exceptions.UnAuthorisedException;
import models.PatientCard;
import org.apache.log4j.Logger;
import services.PatientCardsService;
import servlets.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class AdminAddPatientsPagePostController implements Controller {

    private static final Logger logger = Logger.getLogger(DispatcherServlet.class);

    /**
     * @param req
     * @return
     * @throws SQLException
     */
    @Override
    public String execute(HttpServletRequest req) throws SQLException, UnsupportedEncodingException {
        String firstName = StringConverter.convertToUTF8(req.getParameter("firstName"));
        String lastName = StringConverter.convertToUTF8(req.getParameter("lastName"));
        char sex = req.getParameter("sex").charAt(0);
        String birthdayStr = req.getParameter("birthday");
        Date birthday = Date
                .from(LocalDate
                        .parse(birthdayStr)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant());
        PatientCard patientCard = new PatientCard(firstName, lastName, sex, birthday);
        PatientCardsService service = new PatientCardsService();
        service.add(patientCard);
        logger.info("Added patient " + firstName + " " + lastName);
        return new AdminShowPatientsPageGetController().execute(req);
    }
}
