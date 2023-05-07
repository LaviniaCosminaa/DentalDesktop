package com.example.demodental;

import java.util.*;
public class Cabinet {
    protected String cabinetsName, doctorName;

    /**
     * constructor
     */
    public Cabinet(String cabinetsName, String doctorName) {
        this.cabinetsName = cabinetsName;
        this.doctorName = doctorName;
    }

    /**
     * set/get
     */
    public String getCabinetsName() {
        return cabinetsName;
    }
    public void setCabinetsName(String cabinetsName) {
        this.cabinetsName = cabinetsName;
    }

    public String getDoctorName() {
        return doctorName;
    }
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    /**
     * toString
     */

    @Override
    public String toString() {
        return "Cabinet{" +
                "cabinetsName='" + cabinetsName + '\'' +
                ", doctorName='" + doctorName + '\'' +
                '}';
    }

}
