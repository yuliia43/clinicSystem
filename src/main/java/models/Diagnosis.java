package models;

import java.util.Date;
import java.util.List;

public class Diagnosis {
    private int id;
    private int cardId;
    private String diagnosis;
    private int doctorId;
    private Date setDate;
    private boolean isFinal;
    private List<Appointed> recommendations;


    public Diagnosis(int id, int cardId, String diagnosis, int doctorId, Date set_date, boolean isFinal) {
        this.id = id;
        this.cardId = cardId;
        this.diagnosis = diagnosis;
        this.doctorId = doctorId;
        this.setDate = set_date;
        this.isFinal = isFinal;
    }

    public Diagnosis(int cardId, String diagnosis, int doctorId) {
        this.cardId = cardId;
        this.diagnosis = diagnosis;
        this.doctorId = doctorId;
    }

    public Diagnosis() {
    }

    public Date getSetDate() {
        return setDate;
    }

    public void setSetDate(Date setDate) {
        this.setDate = setDate;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public List<Appointed> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Appointed> recommendations) {
        this.recommendations = recommendations;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }
}
