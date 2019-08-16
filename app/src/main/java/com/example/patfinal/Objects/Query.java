package com.example.patfinal.Objects;

public class Query {
    private String week;
    private String day;
    private String OutTime;
    private String InTime;
    private Boolean OutForTheNight;
    private String OutMethod;
    private String Notes;

    public Query(String week, String day) {
        this.week = week;
        this.day = day;
    }

    public Query(String week, String day, String outTime, String inTime, Boolean outForTheNight, String outMethod, String notes) {
        this.week = week;
        this.day = day;
        OutTime = outTime;
        InTime = inTime;
        OutForTheNight = outForTheNight;
        OutMethod = outMethod;
        Notes = notes;
    }

    public Query(String outTime, String inTime, Boolean outForTheNight, String outMethod, String notes) {
        OutTime = outTime;
        InTime = inTime;
        OutForTheNight = outForTheNight;
        OutMethod = outMethod;
        Notes = notes;
    }

    @Override
    public String toString() {
        return "Query{" +
                ", OutTime='" + OutTime + '\'' +
                ", InTime='" + InTime + '\'' +
                ", OutForTheNight=" + OutForTheNight +
                ", OutMethod='" + OutMethod + '\'' +
                ", Notes='" + Notes + '\'' +
                '}';
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDay() {
        return day;
    }

}
