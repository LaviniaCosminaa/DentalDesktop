package com.example.demodental;

public class Consultation{
    protected int id;
    protected String date, hour, description, tooth;
    protected int patientId;
    //photo

    /**
     * constructor
     */
    public Consultation(int id, String date, String hour, String description, String tooth, int patientId) {
        this.id = id;
        this.hour = hour;
        this.date = date;
        this.description = description;
        this.tooth = tooth;
        this.patientId = patientId;
    }
    public Consultation() {}

    /**
     * set/get
     */
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }
    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getTooth() {
        return tooth;
    }
    public void setTooth(String tooth) {
        this.tooth = tooth;
    }

    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    /**
     * toString
     */
    @Override
    public String toString() {
        return "Consultation{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", hour='" + hour + '\'' +
                ", description='" + description + '\'' +
                ", tooth/s=" + tooth +
                ", patientId=" + patientId +
                '}';
    }
}