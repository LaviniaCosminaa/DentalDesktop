package com.example.demodental;
public class MedicalRecord{
    private int id;
    private String allergies, treatment, lastTreatmentDate, otherSurgery, vices;
    /**
     * constructor
     */
    public MedicalRecord(int id, String allergies, String treatment, String lastTreatmentDate, String otherSurgery, String vices) {
        this.id = id;
        this.allergies = allergies;
        this.treatment = treatment;
        this.lastTreatmentDate = lastTreatmentDate;
        this.otherSurgery = otherSurgery;
        this.vices = vices;
    }
    public MedicalRecord() {}

    /**
     * set/get
     */
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getAllergies() {
        return allergies;
    }
    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getTreatment() {
        return treatment;
    }
    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getLastTreatmentDate() {
        return lastTreatmentDate;
    }
    public void setLastTreatmentDate(String lastTreatmentDate) {
        this.lastTreatmentDate = lastTreatmentDate;
    }

    public String getOtherSurgery() {
        return otherSurgery;
    }
    public void setOtherSurgery(String otherSurgery) {
        this.otherSurgery = otherSurgery;
    }

    public String getVices() {
        return vices;
    }
    public void setVices(String vices) {
        this.vices = vices;
    }

    /**
     * toString
     */
    @Override
    public String toString() {
        return "MedicalRecord{" +
                "id=" + id +
                ", allergies='" + allergies + '\'' +
                ", treatment='" + treatment + '\'' +
                ", lastTreatmentDate='" + lastTreatmentDate + '\'' +
                ", otherSurgery='" + otherSurgery + '\'' +
                ", vices='" + vices +
                '}';
    }
}