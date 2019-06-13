package dtos;


import java.sql.Time;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class AppointmentSchedule {
    private int scheduleId;
    private int performerId;
    private Time pursuanceTime;
    private String details;
    private String patient;

    /**
     * @param scheduleId
     * @param performerId
     * @param pursuanceTime
     * @param details
     * @param patient
     */
    public AppointmentSchedule(int scheduleId, int performerId, Time pursuanceTime, String details, String patient) {
        this.scheduleId = scheduleId;
        this.performerId = performerId;
        this.pursuanceTime = pursuanceTime;
        this.details = details;
        this.patient = patient;
    }

    /**
     *
     */
    public AppointmentSchedule() {
    }

    /**
     * @return
     */
    public int getScheduleId() {
        return scheduleId;
    }

    /**
     * @param scheduleId
     */
    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    /**
     * @return
     */
    public int getPerformerId() {
        return performerId;
    }

    /**
     * @param performerId
     */
    public void setPerformerId(int performerId) {
        this.performerId = performerId;
    }

    /**
     * @return
     */
    public Time getPursuanceTime() {
        return pursuanceTime;
    }

    /**
     * @param pursuanceTime
     */
    public void setPursuanceTime(Time pursuanceTime) {
        this.pursuanceTime = pursuanceTime;
    }

    /**
     * @return
     */
    public String getDetails() {
        return details;
    }

    /**
     * @param details
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * @return
     */
    public String getPatient() {
        return patient;
    }

    /**
     * @param patient
     */
    public void setPatient(String patient) {
        this.patient = patient;
    }
}
