package controller;

import converters.StringConverter;
import models.Appointment;
import models.AppointingTimeAndPerson;
import models.ClinicStaff;
import models.Diagnosis;
import org.apache.log4j.Logger;
import services.AppointingScheduleService;
import services.DiagnosisService;
import services.PatientCardsService;
import servlets.DispatcherServlet;
import transactionServices.AddAppointmentTransactionService;
import transactionServices.DischargePatientTransactionService;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class PatientsDiagnosesPostController implements Controller {
    private static final PatientCardsService patientsCardsService =
            new PatientCardsService();
    private static final AppointingScheduleService appointingScheduleService =
            new AppointingScheduleService();
    private static final DiagnosisService diagnosisService =
            new DiagnosisService();
    private static final Logger logger = Logger.getLogger(DispatcherServlet.class);

    /**
     * @param req
     * @return
     * @throws SQLException
     */
    @Override
    public String execute(HttpServletRequest req) throws SQLException, UnsupportedEncodingException {
        doReceivedMethod(req);
        return new PatientsDiagnosesGetController().execute(req);
    }

    /**
     * @param req
     * @throws SQLException
     */
    private void doReceivedMethod(HttpServletRequest req) throws SQLException, UnsupportedEncodingException {
        String method = req.getParameter("method");
        switch (method) {
            case ("delete_recommendation"): {
                int appointedId = Integer.parseInt(req.getParameter("appointedId"), 10);
                appointingScheduleService.cancelAppointed(appointedId);
            }
            case ("add_diagnosis"): {
                String diagnosisStr = StringConverter.convertToUTF8(req.getParameter("diagnosis"));
                int doctorId = ((ClinicStaff) req.getSession().getAttribute("user")).getId();
                int patientId = Integer.parseInt(req.getParameter("patientId"), 10);
                Diagnosis diagnosis = new Diagnosis();
                diagnosis.setDoctorId(doctorId);
                diagnosis.setCardId(patientId);
                diagnosis.setDiagnosis(diagnosisStr);
                diagnosisService.add(diagnosis);
            }
            case ("add_appointed"): {
                String details = StringConverter.convertToUTF8(req.getParameter("details"));
                int num_days = Integer.parseInt(req.getParameter("num_days"), 10);
                String[] times = req.getParameterValues("time");
                int performerId = Integer.parseInt(req.getParameter("performerId"), 10);
                int diagnosisId = Integer.parseInt(req.getParameter("diagnosisId"), 10);
                String type = req.getParameter("type");
                Appointment appointment =
                        setAppointment(details, num_days, times, performerId, diagnosisId, type);
                AddAppointmentTransactionService addAppointmentTransactionService = new AddAppointmentTransactionService();
                boolean success = addAppointmentTransactionService.addAppointment(appointment);
                if (success)
                    logger.info("Added appointment");
            }
            case ("discharge"): {
                int patientId = Integer.parseInt(req.getParameter("patientId"), 10);
                DischargePatientTransactionService dischargePatientTransaction = new DischargePatientTransactionService();
                boolean success = dischargePatientTransaction.execute(patientId);
                if (success)
                    logger.info("Discharged patient with id " + patientId);
            }
        }
    }

    /**
     * @param details
     * @param num_days
     * @param times
     * @param performerId
     * @param diagnosisId
     * @param type
     * @return
     */
    private Appointment setAppointment(String details, int num_days, String[] times, int performerId, int diagnosisId, String type) {
        Appointment appointment = new Appointment(diagnosisId, type, details);
        List<AppointingTimeAndPerson> list = new ArrayList<>();
        for (int i = 1; i <= num_days; i++) {
            LocalDate date = LocalDate.now().plusDays(i);
            for (int j = 0; j < times.length; j++) {
                LocalDateTime time = LocalTime.parse(times[j]).atDate(date);
                Timestamp dateTime = Timestamp.from(time.atZone(ZoneId.systemDefault()).toInstant());
                list.add(new AppointingTimeAndPerson(dateTime, performerId));
            }
        }
        appointment.setSchedule(list);
        return appointment;
    }
}
