package com.example.demodental;

public class Sterilization {
    private int id;
    private String date, begin, end, device, content, name;

    public Sterilization(int id, String date, String begin, String end, String device, String content, String name) {
        this.id = id;
        this.date = date;
        this.begin = begin;
        this.end = end;
        this.device = device;
        this.content = content;
        this.name = name;
    }

    public Sterilization() {

    }

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

    public String getBegin() {
        return begin;
    }
    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }
    public void setEnd(String end) {
        this.end = end;
    }

    public String getDevice() {
        return device;
    }
    public void setDevice(String device) {
        this.device = device;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Sterilization{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", begin='" + begin + '\'' +
                ", end='" + end + '\'' +
                ", device='" + device + '\'' +
                ", content='" + content + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

