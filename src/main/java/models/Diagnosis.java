package models;

import java.util.Date;
import java.util.List;

/**
 * @author Yullia Shcherbakova
 * @project final
 */
public class Diagnosis {
    private int id;
    private int cardId;
    private String diagnosis;
    private int doctorId;
    private Date setDate;
    private boolean isFinal;
    private List<Appointment> recommendations;


    /**
     * @param id
     * @param cardId
     * @param diagnosis
     * @param doctorId
     * @param set_date
     * @param isFinal
     */
    public Diagnosis(int id, int cardId, String diagnosis, int doctorId, Date set_date, boolean isFinal) {
        this.id = id;
        this.cardId = cardId;
        this.diagnosis = diagnosis;
        this.doctorId = doctorId;
        this.setDate = set_date;
        this.isFinal = isFinal;
    }

    /**
     * @param cardId
     * @param diagnosis
     * @param doctorId
     */
    public Diagnosis(int cardId, String diagnosis, int doctorId) {
        this.cardId = cardId;
        this.diagnosis = diagnosis;
        this.doctorId = doctorId;
    }


    /**
     *
     */
    public Diagnosis() {
    }

    /**
     * @return
     */
    public Date getSetDate() {
        return setDate;
    }

    /**
     * @param setDate
     */
    public void setSetDate(Date setDate) {
        this.setDate = setDate;
    }

    /**
     * @return
     */
    public int getCardId() {
        return cardId;
    }

    /**
     * @param cardId
     */
    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    /**
     * @return
     */
    public int getDoctorId() {
        return doctorId;
    }

    /**
     * @param doctorId
     */
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
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
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * @param diagnosis
     */
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    /**
     * @return
     */
    public List<Appointment> getRecommendations() {
        return recommendations;
    }

    /**
     * @param recommendations
     */
    public void setRecommendations(List<Appointment> recommendations) {
        this.recommendations = recommendations;
    }

    /**
     * @return
     */
    public boolean isFinal() {
        return isFinal;
    }

    /**
     * @param aFinal
     */
    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }
}
