package com.example.patfinal.Fragment_Teacher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.patfinal.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Fragment_Teacher_Query extends Fragment {
    Button button1;
    EditText timeOut;
    EditText timeIn;
    TextView Week;
    TextView day;
    CheckBox outNight;
    EditText method;
    EditText notes;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_query, container, false);

        timeIn = view.findViewById(R.id.timein);
        timeOut = view.findViewById(R.id.outtime);
        button1 = view.findViewById(R.id.Button4);
        Week = view.findViewById(R.id.week);
        day = view.findViewById(R.id.dayOfWeek);
        outNight = view.findViewById(R.id.outForNight);
        method = view.findViewById(R.id.method);
        notes = view.findViewById(R.id.notes);
        progressBar=view.findViewById(R.id.progressBar);
        set();
        disable();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(Fragment_Teacher_Query.this).commit();


            }
        });


        return view;
    }

    public void set() {
        System.out.println(Fragment_Teacher_Check.test.getText());
        System.out.println(getWeek());
        System.out.println(getDay());
        //Fragment_Teacher_Check.test.getText().toString()
        //databaseReference.child("Users").child("14664").child("Queries").child(getWeek()).child(getDay())
        System.out.println(Fragment_Teacher_Check.test.getText().toString());
        databaseReference.child("Users").child(Fragment_Teacher_Check.test.getText().toString()).child("Queries").child(getWeek()).child(getDay()).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                try {
                    timeIn.setText(dataSnapshot.child("inTime").getValue().toString());
                    timeOut.setText(dataSnapshot.child("outTime").getValue().toString());
                    outNight.setChecked((boolean) dataSnapshot.child("outForTheNight").getValue());
                    method.setText(dataSnapshot.child("outMethod").getValue().toString());
                    notes.setText(dataSnapshot.child("notes").getValue().toString());
                    Week.setText(getWeek());
                    day.setText(getDay());
                    progressBar.setVisibility(View.INVISIBLE);


                } catch (Exception e) {
                    String error = "No Data";
                    timeIn.setText(error);
                    timeOut.setText(error);
                    outNight.setChecked(false);
                    method.setText(error);
                    notes.setText(error);
                    progressBar.setVisibility(View.INVISIBLE);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public String getDay() {
        String x = Fragment_View_In_Out.day.getText().toString();
        return x;

    }

    public String getWeek() {
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
        String temp = one + " - " + two;


        return temp;
    }

    private void disable() {
        String disableColour = ("#FFFF");
        timeIn.setEnabled(false);
        outNight.setEnabled(false);
        timeOut.setEnabled(false);
        notes.setEnabled(false);
        method.setEnabled(false);
        timeIn.setTextColor(getResources().getColor(R.color.Black));
        timeOut.setTextColor(getResources().getColor(R.color.Black));
        notes.setTextColor(getResources().getColor(R.color.Black));
        method.setTextColor(getResources().getColor(R.color.Black));
    }


}
