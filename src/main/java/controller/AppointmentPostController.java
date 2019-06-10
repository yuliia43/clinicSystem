package controller;

import dtos.AppointedSchedule;
import enums.AppointedTypes;
import models.ClinicStaff;
import org.apache.log4j.Logger;
import services.AppointingScheduleService;
import servlets.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class AppointmentPostController implements Controller {
    private static final Logger logger = Logger.getLogger(DispatcherServlet.class);
    private static final AppointingScheduleService appointingScheduleService =
            new AppointingScheduleService();


    /**
     * @param req
     * @return
     * @throws SQLException
     */
    @Override
    public String execute(HttpServletRequest req) throws SQLException {
        String[] appointments = req.getParameterValues("appointed");
        int[] ids;
        if (appointments != null)
            ids = Arrays.stream(appointments)
                    .mapToInt(str -> Integer.parseInt(str, 10)).toArray();
        else
            ids = new int[0];
        ClinicStaff user = (ClinicStaff) req.getSession().getAttribute("user");
        for (int i = 0; i < ids.length; i++) {
            appointingScheduleService.doAppointment(ids[i]);
            logger.info("Done appointment with id " + ids[i] + " by " + user.getSurname() + " " + user.getName());
        }
        int performerId = user.getId();
        List<AppointedSchedule> appointedSchedules = appointingScheduleService
                .searchScheduleForToday(performerId, AppointedTypes.OPERATION);
        req.setAttribute("schedules", appointedSchedules);
        return "pages/operations.jsp";
    }
}
