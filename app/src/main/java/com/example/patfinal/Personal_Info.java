package com.example.patfinal;

public class Personal_Info {
    static String name = "James Norman";
    static String school_number = "";

    public static String getName() {
        return name;
    }

    public static String getSchool_number() {
        return school_number;
    }

    public static void setSchool_number(String school_number) {
        Personal_Info.school_number = school_number;
    }

    public static void setName(String name) {
        Personal_Info.name = name;
    }
}
