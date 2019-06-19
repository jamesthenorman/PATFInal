package com.example.patfinal.Processes;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Populate_Spinner {
    public static String[] week = {"", "", ""};

    public static void populate() {


        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int x = 0;
        switch (day) {
            case Calendar.MONDAY:

                break;
            case Calendar.TUESDAY:
                x = 1;
                break;
            case Calendar.WEDNESDAY:
                x = 2;
                break;
            case Calendar.THURSDAY:
                x = 3;
                break;
            case Calendar.FRIDAY:
                x = 4;
                break;
            case Calendar.SATURDAY:
                x = 5;
                break;
            case Calendar.SUNDAY:
                x = 6;
                break;


        }
        SimpleDateFormat mdformat = new SimpleDateFormat("LLLL-dd ");
        calendar.add(Calendar.DAY_OF_MONTH, -x);
        String one = mdformat.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, +6);
        String two = mdformat.format(calendar.getTime());
        week[0] = one + " - " + two;
        calendar.add(Calendar.DAY_OF_MONTH, +1);
        one = mdformat.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, +6);
        two = mdformat.format(calendar.getTime());
        week[1] = one + " - " + two;
        calendar.add(Calendar.DAY_OF_MONTH, +1);
        one = mdformat.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, +6);
        two = mdformat.format(calendar.getTime());
        week[2] = one + " - " + two;

    }
}
