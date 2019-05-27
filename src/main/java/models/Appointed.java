package models;

import java.util.List;

public class Appointed {
    private int id;
    private String type;
    private String details;
    private List<AppointingTimeAndPerson> schedule;

    public Appointed(int id, String type, String details, List<AppointingTimeAndPerson> schedule) {
        this.id = id;
        this.type = type;
        this.details = details;
        this.schedule = schedule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<AppointingTimeAndPerson> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<AppointingTimeAndPerson> schedule) {
        this.schedule = schedule;
    }
}
