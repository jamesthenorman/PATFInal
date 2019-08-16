package com.example.patfinal.Objects;

public class Teacher {
    private String name;
    private String number;
    private String roomNumber;
    private String email;


    public Teacher(String name, String number, String roomNumber, String email) {
        this.name = name;
        this.number = number;
        this.roomNumber = roomNumber;
        this.email = email;
    }

    public Teacher() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getEmail() {
        return email;
    }


}
