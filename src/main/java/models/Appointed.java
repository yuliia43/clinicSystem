package models;

import java.util.List;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class Appointed {
    private int id;
    private int diagnosisId;
    private String type;
    private String details;
    private List<AppointingTimeAndPerson> schedule;

    /**
     * @param id
     * @param diagnosisId
     * @param type
     * @param details
     */
    public Appointed(int id, int diagnosisId, String type, String details) {
        this.id = id;
        this.diagnosisId = diagnosisId;
        this.type = type;
        this.details = details;
    }

    /**
     * @param diagnosisId
     * @param type
     * @param details
     */
    public Appointed(int diagnosisId, String type, String details) {
        this.diagnosisId = diagnosisId;
        this.type = type;
        this.details = details;
    }


    /**
     *
     */
    public Appointed() {
    }

    /**
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
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
    public int getDiagnosisId() {
        return diagnosisId;
    }

    /**
     * @param diagnosisId
     */
    public void setDiagnosisId(int diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    /**
     * @return
     */
    public List<AppointingTimeAndPerson> getSchedule() {
        return schedule;
    }

    /**
     * @param schedule
     */
    public void setSchedule(List<AppointingTimeAndPerson> schedule) {
        this.schedule = schedule;
    }
}
