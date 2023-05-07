package com.example.demodental;
import java.util.*;

public class Patient extends MedicalRecord {
    private int id;
    private String patientName;
    private int age;
    private String gender, phoneNumber;
    private int medicalRecordNumber;

    /**
     * constructor
     */
    public Patient(int id, String patientName, int age, String gender, String phoneNumber) {
        this.id = id;
        this.patientName = patientName;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }
    public Patient(int id, String patientName, int age, String gender, String phoneNumber, int medicalRecordNumber) {
        this.id = id;
        this.patientName = patientName;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.medicalRecordNumber = medicalRecordNumber;
    }
    public Patient() {}

    /**
     * set/get
     */
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getMedicalRecordNumber() {
        return medicalRecordNumber;
    }
    public void setMedicalRecordNumber(int medicalRecordNumber) {this.medicalRecordNumber = medicalRecordNumber;
    }

    /**
     * toString
     */
    @Override
    public String toString() {
        return "Pacient{" +
                "id=" + id +
                ", patientName='" + patientName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", medicalRecordNumber=" + medicalRecordNumber +
                '}';
    }
}