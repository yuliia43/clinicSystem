package models;

import java.util.List;

public class Appointed {
    private int id;
    private int diagnosisId;
    private String type;
    private String details;
    private List<AppointingTimeAndPerson> schedule;

    public Appointed(int id, int diagnosisId, String type, String details) {
        this.id = id;
        this.diagnosisId = diagnosisId;
        this.type = type;
        this.details = details;
    }

    public Appointed(int diagnosisId, String type, String details) {
        this.diagnosisId = diagnosisId;
        this.type = type;
        this.details = details;
    }



    public Appointed() {
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

    public int getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(int diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public List<AppointingTimeAndPerson> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<AppointingTimeAndPerson> schedule) {
        this.schedule = schedule;
    }
}
