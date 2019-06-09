package models;

import java.sql.Timestamp;

public class AppointingTimeAndPerson {
    private int id;
    private int appointedId;
    private Timestamp time;
    private int performerId;
    private boolean wasAppointed;

    public AppointingTimeAndPerson(int id, int appointerId, Timestamp time, int performerId, boolean wasAppointed) {
        this.id = id;
        this.appointedId = appointerId;
        this.time = time;
        this.performerId = performerId;
        this.wasAppointed = wasAppointed;
    }

    public AppointingTimeAndPerson(Timestamp time, int performerId) {
        this.time = time;
        this.performerId = performerId;
    }

    public AppointingTimeAndPerson() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPerformerId() {
        return performerId;
    }

    public void setPerformerId(int performerId) {
        this.performerId = performerId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getAppointedId() {
        return appointedId;
    }

    public void setAppointedId(int appointedId) {
        this.appointedId = appointedId;
    }

    public boolean isWasAppointed() {
        return wasAppointed;
    }

    public void setWasAppointed(boolean wasAppointed) {
        this.wasAppointed = wasAppointed;
    }
}
