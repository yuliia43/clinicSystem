package models;

import java.sql.Timestamp;

public class AppointingTimeAndPerson {
    private int id;
    private Timestamp time;
    private ClinicStaff appointer;
    private boolean wasAppointed;

    public AppointingTimeAndPerson(int id, Timestamp time, ClinicStaff appointer, boolean wasAppointed) {
        this.id = id;
        this.time = time;
        this.appointer = appointer;
        this.wasAppointed = wasAppointed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public ClinicStaff getAppointer() {
        return appointer;
    }

    public void setAppointer(ClinicStaff appointer) {
        this.appointer = appointer;
    }

    public boolean isWasAppointed() {
        return wasAppointed;
    }

    public void setWasAppointed(boolean wasAppointed) {
        this.wasAppointed = wasAppointed;
    }
}
