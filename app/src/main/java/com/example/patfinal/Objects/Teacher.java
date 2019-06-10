package com.example.patfinal.Objects;

public class Teacher {
    String name;
    String number;
    String roomNumber;
    String email;


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

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
