package com.example.patfinal.Objects;

public class Personal_Details {
    private String FirstName;
    private String LastName;
    private String PhoneNumber;
    private String ParentPhoneNumber;
    private String EmergencyContact;
    private String RoomNumber;
    private String Allergies;


    public Personal_Details() {
    }

    public Personal_Details(String firstname, String lastname, String phonenumber,
                            String parentPhoneNumber, String emergencyContact, String roomnumber,
                            String allergies) {

        FirstName = firstname;
        LastName = lastname;
        PhoneNumber = phonenumber;
        ParentPhoneNumber = parentPhoneNumber;
        EmergencyContact = emergencyContact;
        RoomNumber = roomnumber;
        Allergies = allergies;
    }

    public Personal_Details(String firstname, String lastname,String roomnumber) {
        FirstName = firstname;
        LastName = lastname;
        RoomNumber = roomnumber;

    }

    @Override
    public String toString() {
        return "Personal_Details{" +
                "FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", RoomNumber='"+RoomNumber+'\''+
                '}';
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstname) {
        FirstName = firstname;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getParentPhoneNumber() {
        return ParentPhoneNumber;
    }

    public void setParentPhoneNumber(String parentPhoneNumber) {
        ParentPhoneNumber = parentPhoneNumber;
    }

    public String getEmergencyContact() {
        return EmergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        EmergencyContact = emergencyContact;
    }

    public String getRoomNumber() {
        return RoomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        RoomNumber = roomNumber;
    }

    public String getAllergies() {
        return Allergies;
    }

    public void setAllergies(String allergies) {
        Allergies = allergies;
    }


}
