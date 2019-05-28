package models;

import java.util.List;

public class Diagnosis {
    private int id;
    private int cardId;
    private String diagnosis;
    private int doctorId;
    private boolean isFinal;
    private List<Appointed> recomedations;


    public Diagnosis(int id, int cardId, String diagnosis, int doctorId, boolean isFinal) {
        this.id = id;
        this.cardId = cardId;
        this.diagnosis = diagnosis;
        this.doctorId = doctorId;
        this.isFinal = isFinal;
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

    public List<Appointed> getRecomedations() {
        return recomedations;
    }

    public void setRecomedations(List<Appointed> recomedations) {
        this.recomedations = recomedations;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }
}
