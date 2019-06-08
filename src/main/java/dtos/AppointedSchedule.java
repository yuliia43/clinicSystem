package dtos;


import java.sql.Time;

public class AppointedSchedule {
    private int scheduleId;
    private int performerId;
    private Time pursuanceTime;
    private String details;
    private String patient;

    public AppointedSchedule(int scheduleId, int performerId, Time pursuanceTime, String details, String patient) {
        this.scheduleId = scheduleId;
        this.performerId = performerId;
        this.pursuanceTime = pursuanceTime;
        this.details = details;
        this.patient = patient;
    }

    public AppointedSchedule() {
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getPerformerId() {
        return performerId;
    }

    public void setPerformerId(int performerId) {
        this.performerId = performerId;
    }

    public Time getPursuanceTime() {
        return pursuanceTime;
    }

    public void setPursuanceTime(Time pursuanceTime) {
        this.pursuanceTime = pursuanceTime;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }
}
