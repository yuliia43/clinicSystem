package servlets;

import dtos.AppointedSchedule;
import enums.AppointedTypes;
import models.ClinicStaff;
import org.apache.log4j.Logger;
import repositories.AppointingScheduleRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Operations extends HttpServlet {
    private static final AppointingScheduleRepository appointingScheduleRepository =
            AppointingScheduleRepository.getAppointingScheduleRepository();
    private static final Logger logger = Logger.getLogger(Operations.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClinicStaff clinicStaff = (ClinicStaff) req.getSession().getAttribute("user");
        if (!clinicStaff.getTitle().equals("doctor")) {
            req.getRequestDispatcher("errorPages/accessError.jsp")
                    .forward(req, resp);
        } else {
            int performerId = clinicStaff.getId();
            try {
                List<AppointedSchedule> appointedSchedules = appointingScheduleRepository
                        .searchScheduleForToday(performerId, AppointedTypes.OPERATION);
                req.setAttribute("schedules", appointedSchedules);
                req.getRequestDispatcher("pages/operations.jsp")
                        .forward(req, resp);
            } catch (SQLException e) {
                logger.error("Sql error occured!");
                req.getRequestDispatcher("errorPages/SQlError.jsp")
                        .forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] appointeds = req.getParameterValues("appointed");
        int[] ids;
        if(appointeds!=null)
            ids = Arrays.stream(appointeds)
                    .mapToInt(str -> Integer.parseInt(str, 10)).toArray();
        else
            ids = new int[0];
        try {
            for (int i = 0; i < ids.length; i++) {
                appointingScheduleRepository.doAppointment(ids[i]);
            }
            int performerId = ((ClinicStaff) req.getSession().getAttribute("user")).getId();
            List<AppointedSchedule> appointedSchedules = appointingScheduleRepository
                    .searchScheduleForToday(performerId, AppointedTypes.OPERATION);
            req.setAttribute("schedules", appointedSchedules);
            req.getRequestDispatcher("pages/operations.jsp")
                    .forward(req, resp);
        } catch (SQLException e) {
            logger.error("Sql error occured!");
            req.getRequestDispatcher("errorPages/SQlError.jsp")
                    .forward(req, resp);
        }
    }
}
