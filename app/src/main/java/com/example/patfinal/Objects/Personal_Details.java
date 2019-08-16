package com.example.patfinal.Objects;

public class Personal_Details {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String parentPhoneNumber;
    private String emergencyContact;
    private String roomNumber;
    private String allergies;

    public Personal_Details() {
    }

    public Personal_Details(String firstName, String lastName, String phoneNumber, String parentPhoneNumber, String emergencyContact, String roomNumber, String allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.parentPhoneNumber = parentPhoneNumber;
        this.emergencyContact = emergencyContact;
        this.roomNumber = roomNumber;
        this.allergies = allergies;
    }

    public Personal_Details(String firstName, String lastName, String roomNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roomNumber = roomNumber;

    }

    @Override
    public String toString() {
        return "Personal_Details{" +
                "FirstName='" + firstName + '\'' +
                ", LastName='" + lastName + '\'' +
                ", RoomNumber='" + roomNumber + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getParentPhoneNumber() {
        return parentPhoneNumber;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getAllergies() {
        return allergies;
    }
}
