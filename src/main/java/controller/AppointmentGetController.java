package controller;

import dtos.AppointedSchedule;
import enums.AppointedTypes;
import factories.AppointmentsFactory;
import models.ClinicStaff;
import services.AppointingScheduleService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class AppointmentGetController implements Controller {
    private static final AppointingScheduleService appointingScheduleService =
            new AppointingScheduleService();


    @Override
    public String execute(HttpServletRequest req) throws SQLException {
        ClinicStaff clinicStaff = (ClinicStaff) req.getSession().getAttribute("user");
        String type = req.getParameter("type");
        AppointedTypes appointedType = AppointmentsFactory.getAppointmentType(type);
        if (!clinicStaff.getTitle().equals("doctor") && appointedType == AppointedTypes.OPERATION ) {
            return "errorPages/accessError.jsp";
        } else {
            int performerId = clinicStaff.getId();
            List<AppointedSchedule> appointedSchedules = appointingScheduleService
                    .searchScheduleForToday(performerId, appointedType);
            if(appointedSchedules.size() == 0){
                return "errorPages/emptyList.jsp";
            }
            else{
                req.setAttribute("schedules", appointedSchedules);
                req.setAttribute("type", type);
                return "pages/appointments.jsp";

            }
        }
    }
}
