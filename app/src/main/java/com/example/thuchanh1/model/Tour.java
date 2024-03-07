package com.example.thuchanh1.model;

public class Tour {
    private String schedule;
    private String time;
    private int img;

    public Tour() {
    }

    public Tour(String schedule, String time, int img) {
        this.schedule = schedule;
        this.time = time;
        this.img = img;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
