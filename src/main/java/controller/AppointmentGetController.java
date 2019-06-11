package controller;

import commonlyUsedStrings.PageName;
import dtos.AppointedSchedule;
import enums.AppointedTypes;
import factories.AppointmentsFactory;
import models.ClinicStaff;
import services.AppointingScheduleService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class AppointmentGetController implements Controller {
    private static final AppointingScheduleService appointingScheduleService =
            new AppointingScheduleService();


    /**
     * @param req
     * @return
     * @throws SQLException
     */
    @Override
    public String execute(HttpServletRequest req) throws SQLException {
        ClinicStaff clinicStaff = (ClinicStaff) req.getSession().getAttribute("user");
        String type = req.getParameter("type");
        AppointedTypes appointedType = AppointmentsFactory.getAppointmentType(type);
        if (!clinicStaff.getTitle().equals("doctor") && appointedType == AppointedTypes.OPERATION) {
            return PageName.ACCESS_ERROR;
        } else {
            int performerId = clinicStaff.getId();
            List<AppointedSchedule> appointedSchedules = appointingScheduleService
                    .searchScheduleForToday(performerId, appointedType);
            if (appointedSchedules.size() == 0) {
                return "pages/emptyAppointmentsList.jsp";
            } else {
                int startingIndex = (int) req.getAttribute("startIdx");
                int endIdx = (int) req.getAttribute("endIdx");
                if(appointedSchedules.size()-startingIndex<=10){
                    req.setAttribute("endOfList", true);
                    endIdx = appointedSchedules.size();
                }
                appointedSchedules = appointedSchedules.subList(startingIndex, endIdx);
                req.setAttribute("schedules", appointedSchedules);
                req.setAttribute("type", type);
                return PageName.APPOINTMENTS;

            }
        }
    }
}
