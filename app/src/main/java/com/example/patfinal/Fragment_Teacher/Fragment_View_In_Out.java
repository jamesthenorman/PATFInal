package com.example.patfinal.Fragment_Teacher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.patfinal.Personal_Info;
import com.example.patfinal.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Fragment_View_In_Out extends Fragment {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    TextView nameandnum;
    String week;
    CheckBox consentMon, consentTue, consentWed, consentThu, consentFri, consentSat, consentSun;
    CheckBox InOutMon, InOutTue, InOutWed, InOutThu, InOutFri, InOutSat, InOutSun;
    TextView Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday;
    Button History;
    Button personalInfo;
    static TextView day;
    FrameLayout query;
    ProgressBar progressBar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_in_out, container, false);
        nameandnum = view.findViewById(R.id.NameandNumber);
        consentMon = view.findViewById(R.id.ConsentMon);
        consentTue = view.findViewById(R.id.ConsentTue);
        consentWed = view.findViewById(R.id.ConsentWed);
        consentThu = view.findViewById(R.id.ConsentThu);
        consentFri = view.findViewById(R.id.ConsentFri);
        consentSat = view.findViewById(R.id.ConsentSat);
        consentSun = view.findViewById(R.id.ConsentSun);

        InOutMon = view.findViewById(R.id.InOutMon);
        InOutTue = view.findViewById(R.id.InOutTue);
        InOutWed = view.findViewById(R.id.InOutWed);
        InOutThu = view.findViewById(R.id.InOutThu);
        InOutFri = view.findViewById(R.id.InOutFri);
        InOutSat = view.findViewById(R.id.InOutSat);
        InOutSun = view.findViewById(R.id.InOutSun);

        Monday = view.findViewById(R.id.textViewMonday);
        Tuesday = view.findViewById(R.id.textViewTuesday);
        Wednesday = view.findViewById(R.id.textViewWednesday);
        Thursday = view.findViewById(R.id.textViewThursday);
        Friday = view.findViewById(R.id.textViewFriday);
        Saturday = view.findViewById(R.id.textViewSaturday);
        Sunday = view.findViewById(R.id.textViewSunday);

        day = view.findViewById(R.id.Day);

        query = view.findViewById(R.id.query);

        progressBar = view.findViewById(R.id.progressBar);

        Monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day.setText("Monday");
                openQuery();
            }
        });

        Tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day.setText("Tuesday");
                openQuery();


            }
        });
        Wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day.setText("Wednesday");
                openQuery();


            }
        });
        Thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day.setText("Thursday");
                openQuery();


            }
        });
        Friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day.setText("Friday");
                openQuery();


            }
        });
        Saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day.setText("Saturday");
                openQuery();


            }
        });
        Sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day.setText("Sunday");
                openQuery();


            }
        });

        History = view.findViewById(R.id.btnHist);
        personalInfo = view.findViewById(R.id.btnInfo);

        History.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHistory();
            }
        });

        personalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInfo();
            }
        });


        disableClick();
        populate();


        return view;
    }

    public void populate() {
        SetNamenumber();
        populateConsent();
        populateInOut();
        progressBar.setVisibility(View.INVISIBLE);

    }

    public void SetNamenumber() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    String First = dataSnapshot.child("Users").child(Fragment_Teacher_Check.test.getText().toString()).child("Personal Details").child("firstName").getValue().toString();
                    String Last = dataSnapshot.child("Users").child(Fragment_Teacher_Check.test.getText().toString()).child("Personal Details").child("lastName").getValue().toString();
                    Personal_Info.setName(First + " " + Last);
                    nameandnum.setText(First + " " + Last + "\n" + Fragment_Teacher_Check.test.getText().toString());
                } catch (Exception e) {
                    Personal_Info.setName("ERR, enter name");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void populateConsent() {
        databaseReference.child("Users").child(Fragment_Teacher_Check.test.getText().toString()).child("Consent History").child(getweek()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                try {
                    consentMon.setChecked((boolean) dataSnapshot.child("monday").getValue());
                    consentTue.setChecked((boolean) dataSnapshot.child("tuesday").getValue());
                    consentWed.setChecked((boolean) dataSnapshot.child("wednesday").getValue());
                    consentThu.setChecked((boolean) dataSnapshot.child("thursday").getValue());
                    consentFri.setChecked((boolean) dataSnapshot.child("friday").getValue());
                    consentSat.setChecked((boolean) dataSnapshot.child("saturday").getValue());
                    consentSun.setChecked((boolean) dataSnapshot.child("sunday").getValue());


                } catch (Exception e) {
                    consentMon.setChecked(false);
                    consentTue.setChecked(false);
                    consentWed.setChecked(false);
                    consentThu.setChecked(false);
                    consentFri.setChecked(false);
                    consentSat.setChecked(false);
                    consentSun.setChecked(false);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void populateInOut() {
        databaseReference.child("Users").child(Fragment_Teacher_Check.test.getText().toString()).child("User History").child(getweek()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                try {
                    InOutMon.setChecked((boolean) dataSnapshot.child("monday").getValue());
                    InOutTue.setChecked((boolean) dataSnapshot.child("tuesday").getValue());
                    InOutWed.setChecked((boolean) dataSnapshot.child("wednesday").getValue());
                    InOutThu.setChecked((boolean) dataSnapshot.child("thursday").getValue());
                    InOutFri.setChecked((boolean) dataSnapshot.child("friday").getValue());
                    InOutSat.setChecked((boolean) dataSnapshot.child("saturday").getValue());
                    InOutSun.setChecked((boolean) dataSnapshot.child("sunday").getValue());


                } catch (Exception e) {
                    InOutMon.setChecked(false);
                    InOutTue.setChecked(false);
                    InOutWed.setChecked(false);
                    InOutThu.setChecked(false);
                    InOutFri.setChecked(false);
                    InOutSat.setChecked(false);
                    InOutSun.setChecked(false);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public String getweek() {
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
        week = one + " - " + two;
        return week;
    }

    public void disableClick() {

        nameandnum.setClickable(false);
        consentMon.setClickable(false);
        consentTue.setClickable(false);
        consentWed.setClickable(false);
        consentThu.setClickable(false);
        consentFri.setClickable(false);
        consentSat.setClickable(false);
        consentSun.setClickable(false);

        InOutMon.setClickable(false);
        InOutTue.setClickable(false);
        InOutWed.setClickable(false);
        InOutThu.setClickable(false);
        InOutFri.setClickable(false);
        InOutSat.setClickable(false);
        InOutSun.setClickable(false);


    }

    public void openHistory() {
        FragmentTransaction fr1 = getFragmentManager().beginTransaction().addToBackStack("Tag");
        fr1.replace(R.id.fragment_container, new Fragment_Teacher_History());
        fr1.commit();
    }

    public void openInfo() {
        FragmentTransaction fr1 = getFragmentManager().beginTransaction().addToBackStack("Tag");
        fr1.replace(R.id.fragment_container, new Fragment_Teacher_Personal_Info());
        fr1.commit();
    }

    public void openQuery() {
        query.setVisibility(View.VISIBLE);
        FragmentTransaction fr1 = getFragmentManager().beginTransaction().addToBackStack("Tag");
        fr1.add(R.id.query, new Fragment_Teacher_Query());
        fr1.commit();


    }


}