package controller;

import commonlyUsedStrings.PageName;
import dtos.AppointmentSchedule;
import enums.AppointmentTypes;
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
        AppointmentTypes appointedType = AppointmentsFactory.getAppointmentType(type);
        if (!clinicStaff.getTitle().equals("doctor") && appointedType == AppointmentTypes.OPERATION) {
            return PageName.ACCESS_ERROR;
        } else {
            int performerId = clinicStaff.getId();
            List<AppointmentSchedule> appointmentSchedules = appointingScheduleService
                    .searchScheduleForToday(performerId, appointedType);
            if (appointmentSchedules.size() == 0) {
                return PageName.EMPTY_APPOINTMENTS;
            } else {
                int startingIndex = (int) req.getAttribute("startIdx");
                int endIdx = (int) req.getAttribute("endIdx");
                if(appointmentSchedules.size()-startingIndex<=10){
                    req.setAttribute("endOfList", true);
                    endIdx = appointmentSchedules.size();
                }
                appointmentSchedules = appointmentSchedules.subList(startingIndex, endIdx);
                req.setAttribute("schedules", appointmentSchedules);
                req.setAttribute("type", type);
                return PageName.APPOINTMENTS;

            }
        }
    }
}
