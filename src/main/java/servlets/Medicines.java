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
import java.util.List;

public class Medicines extends HttpServlet {
    private static final AppointingScheduleRepository appointingScheduleRepository =
            AppointingScheduleRepository.getAppointingScheduleRepository();
    private static final Logger logger = Logger.getLogger(Medicines.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int performerId = ((ClinicStaff)req.getSession().getAttribute("user")).getId();
        try {
            List<AppointedSchedule> appointedSchedules = appointingScheduleRepository
                    .searchScheduleForToday(performerId, AppointedTypes.MEDICINE);
            req.setAttribute("schedules", appointedSchedules);
            req.getRequestDispatcher("pages/medicines.jsp")
                    .forward(req,resp);
        } catch (SQLException e) {
            logger.error("Sql error occured!");
            req.getRequestDispatcher("errorPages/SQlError.jsp")
                    .forward(req,resp);
        }
    }
}
