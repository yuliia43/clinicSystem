package transactions;

import models.Appointment;
import models.Diagnosis;
import org.apache.log4j.Logger;
import services.AppointmentService;
import services.AppointingScheduleService;
import services.DiagnosisService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class DischargePatientTransaction {
    private static final DischargePatientTransaction singleton = new DischargePatientTransaction();
    private static final Logger logger = Logger.getLogger(DischargePatientTransaction.class);
    private static final DiagnosisService diagnosisService = new DiagnosisService();
    private static final AppointingScheduleService appointingScheduleService =
            new AppointingScheduleService();
    private static final AppointmentService APPOINTMENT_SERVICE =
            new AppointmentService();

    /**
     *
     */
    private DischargePatientTransaction() {

    }

    /**
     * @return
     */
    public static DischargePatientTransaction getInstance() {
        return singleton;
    }

    /**
     * @param patientId
     * @param connection
     * @return
     * @throws SQLException
     */
    public boolean execute(int patientId, Connection connection) throws SQLException {
        try {
            connection.setAutoCommit(false);
            List<Diagnosis> diagnoses = diagnosisService.getAllLastDiagnosesForPatient(patientId);
            List<Appointment> appointmentsToDelete = new ArrayList<>();
            for (Diagnosis diagnosis :
                    diagnoses) {
                appointmentsToDelete.addAll(APPOINTMENT_SERVICE.getAllByDiagnosisId(diagnosis.getId()));
            }
            for (Appointment appointment : appointmentsToDelete) {
                appointingScheduleService.cancelAppointed(appointment.getId());
            }
            diagnoses.get(0).setFinal(true);
            diagnosisService.update(diagnoses.get(0));
            connection.commit();
            connection.setAutoCommit(true);
            logger.info("Transaction of discharging patient commited!");
            return true;
        } catch (SQLException e) {
            connection.rollback();
            connection.setAutoCommit(true);
            logger.error("Transaction of discharging patient rolled back!");
            return false;
        }
    }
}
