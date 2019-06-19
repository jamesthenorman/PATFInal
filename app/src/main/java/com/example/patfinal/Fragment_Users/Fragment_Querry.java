package com.example.patfinal.Fragment_Users;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.patfinal.Objects.Query;
import com.example.patfinal.Personal_Info;
import com.example.patfinal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.patfinal.Fragment_Users.Fragment_In_or_Out.queryList;
import static com.example.patfinal.Fragment_Users.Fragment_In_or_Out.queryListLen;

public class Fragment_Querry extends Fragment {
    Button button1;
    EditText timeOut;
    EditText timeIn;
    TextView Week;
    TextView day;
    CheckBox outNight;
    EditText method;
    EditText notes;
    int x = queryListLen;
    int saves = 0;
    ProgressBar progressBar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_querries, container, false);

        timeIn = view.findViewById(R.id.timein);
        timeOut = view.findViewById(R.id.outtime);
        button1 = view.findViewById(R.id.Button4);
        Week = view.findViewById(R.id.week);
        day = view.findViewById(R.id.dayOfWeek);
        outNight = view.findViewById(R.id.outForNight);
        method = view.findViewById(R.id.method);
        notes = view.findViewById(R.id.notes);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        openFirst();


        timeOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        timeOut.setText(hourOfDay + ":" + minutes);
                    }
                }, 0, 0, false);

                timePickerDialog.show();

            }
        });

        timeIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        timeIn.setText(hourOfDay + ":" + minutes);
                    }
                }, 0, 0, false);

                timePickerDialog.show();

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                openNext();
                saves++;
                //TODO: load week and day into query
                //setStart();

            }
        });

        return view;
    }

    public void save() {
        progressBar.setVisibility(View.VISIBLE);
        System.out.println(timeIn.getText().toString());
        String timeout = timeOut.getText().toString();
        String timein = timeIn.getText().toString();
        boolean outnight = outNight.isChecked();
        String Method = method.getText().toString();
        String Notes = notes.getText().toString();
        String week = Week.getText().toString();
        String Day = day.getText().toString();

        Query query = new Query(timeout, timein, outnight, Method, Notes);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users").child(Personal_Info.getSchool_number()).child("Queries").child(week).child(Day);
        myRef.setValue(query).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });


    }

    public void openNext() {
        System.out.println(x + "X");
        System.out.println(saves + "Saves");
        if (x > saves) {

            String setWeek;
            String setDay;
            setWeek = queryList[saves].getWeek();
            setDay = queryList[saves].getDay();
            Week.setText(setWeek);
            day.setText(setDay);
            timeIn.setText("");
            timeOut.setText("");
            outNight.setChecked(false);
            method.setText("");
            notes.setText("");

        } else {
            getFragmentManager().beginTransaction().remove(Fragment_Querry.this).commit();
            saves = 0;
            queryListLen = 0;

        }

    }

    public void openFirst() {
        String setWeek;
        String setDay;
        setWeek = queryList[saves].getWeek();
        setDay = queryList[saves].getDay();
        Week.setText(setWeek);
        day.setText(setDay);
        saves++;
    }

}



