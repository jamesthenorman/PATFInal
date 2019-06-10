package com.example.patfinal.Objects;

public class Query {
  private   String week;
  private   String day;
  private   String OutTime;
  private   String InTime;
  private   Boolean OutForTheNight;
  private   String OutMethod;
  private   String Notes;

    public Query(String week, String day) {
        this.week = week;
        this.day = day;
    }

    public Query(String outTime, String inTime, Boolean outForTheNight, String outMethod, String notes) {
        OutTime = outTime;
        InTime = inTime;
        OutForTheNight = outForTheNight;
        OutMethod = outMethod;
        Notes = notes;
    }

    public Query(String outTime, Boolean outForTheNight, String outMethod, String notes) {
        OutTime = outTime;
        OutForTheNight = outForTheNight;
        OutMethod = outMethod;
        Notes = notes;
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

    public void setDay(String day) {
        this.day = day;
    }

    public String getOutTime() {
        return OutTime;
    }

    public void setOutTime(String outTime) {
        OutTime = outTime;
    }

    public String getInTime() {
        return InTime;
    }

    public void setInTime(String inTime) {
        InTime = inTime;
    }

    public Boolean getOutForTheNight() {
        return OutForTheNight;
    }

    public void setOutForTheNight(Boolean outForTheNight) {
        OutForTheNight = outForTheNight;
    }

    public String getOutMethod() {
        return OutMethod;
    }

    public void setOutMethod(String outMethod) {
        OutMethod = outMethod;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }
}
