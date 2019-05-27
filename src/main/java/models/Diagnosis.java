package models;

import java.util.List;

public class Diagnosis {
    private int id;
    private String diagnosis;
    private ClinicStaff setBy;
    private boolean isFinal;
    private List<Appointed> recomedations;

    public Diagnosis(int id, String diagnosis, ClinicStaff setBy, boolean isFinal, List<Appointed> recomedations) {
        this.id = id;
        this.diagnosis = diagnosis;
        this.setBy = setBy;
        this.isFinal = isFinal;
        this.recomedations = recomedations;
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

    public ClinicStaff getSetBy() {
        return setBy;
    }

    public void setSetBy(ClinicStaff setBy) {
        this.setBy = setBy;
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
