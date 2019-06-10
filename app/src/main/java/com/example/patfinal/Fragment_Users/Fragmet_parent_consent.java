package com.example.patfinal.Fragment_Users;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.patfinal.Objects.Week;
import com.example.patfinal.Personal_Info;
import com.example.patfinal.Processes.Populate_Spinner;
import com.example.patfinal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Fragmet_parent_consent extends Fragment {

    //Firebase

    Button btn;
    ToggleButton tbmon;
    ToggleButton tbtue;
    ToggleButton tbwed;
    ToggleButton tbthu;
    ToggleButton tbfri;
    ToggleButton tbsat;
    ToggleButton tbsun;

    TextView tvmon;
    TextView tvtue;
    TextView tvwed;
    TextView tvthu;
    TextView tvfri;
    TextView tvsat;
    TextView tvsun;

    boolean mon;
    boolean tue;
    boolean wed;
    boolean thu;
    boolean fri;
    boolean sat;
    boolean sun;

    Spinner spinner;

    ProgressBar progressBar;

    DatabaseReference databaseReference;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parent_consent, container, false);

        spinner = (Spinner) view.findViewById(R.id.spinner);
        Populate_Spinner.populate();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, Populate_Spinner.week);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        progressBar = view.findViewById(R.id.progressBar);

        tbmon = view.findViewById(R.id.tbmon);
        tbtue = view.findViewById(R.id.tbtue);
        tbwed = view.findViewById(R.id.tbwed);
        tbthu = view.findViewById(R.id.tbthu);
        tbfri = view.findViewById(R.id.tbfri);
        tbsat = view.findViewById(R.id.tbsat);
        tbsun = view.findViewById(R.id.tbsun);

        tvmon = view.findViewById(R.id.tvpmon);
        tvtue = view.findViewById(R.id.tvptue);
        tvwed = view.findViewById(R.id.tvpwed);
        tvthu = view.findViewById(R.id.tvpthu);
        tvfri = view.findViewById(R.id.tvpfri);
        tvsat = view.findViewById(R.id.tvpsat);
        tvsun = view.findViewById(R.id.tvpsun);

        btn = view.findViewById(R.id.button);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Intent intent = new Intent(getActivity(), Main_Activity_Student.class);
                //startActivity(intent);
                progressBar.setVisibility(View.VISIBLE);
                saveTB(spinner.getSelectedItem().toString());
               //
                // saveToggle(spinner.getSelectedItem().toString());


            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                progressBar.setVisibility(View.VISIBLE);
                downloadTb(spinner.getSelectedItem().toString());
                progressBar.setVisibility(View.VISIBLE);
                downloadTv(spinner.getSelectedItem().toString());
                //DownloadInOut(spinner.getSelectedItem().toString());
               // DownloadToggle(spinner.getSelectedItem().toString());


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // If an option is removed then what to do
                // or anything else
            }

        });


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        return view;
    }

    public void downloadTv(String text) {
        databaseReference.child("Users").child(Personal_Info.getSchool_number()).child("User History").child(text).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                try {
                    mon = Boolean.parseBoolean(dataSnapshot.child("monday").getValue().toString());
                    tue = Boolean.parseBoolean(dataSnapshot.child("tuesday").getValue().toString());
                    wed = Boolean.parseBoolean(dataSnapshot.child("wednsday").getValue().toString());
                    thu = Boolean.parseBoolean(dataSnapshot.child("thursday").getValue().toString());
                    fri = Boolean.parseBoolean(dataSnapshot.child("friday").getValue().toString());
                    sat = Boolean.parseBoolean(dataSnapshot.child("saturday").getValue().toString());
                    sun = Boolean.parseBoolean(dataSnapshot.child("sunday").getValue().toString());
                } catch (Exception e) {
                    mon = false;
                    tue = false;
                    wed = false;
                    thu = false;
                    fri = false;
                    sat = false;
                    sun = false;
                }
                set(mon, tue, wed, thu, fri, sat, sun);
                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void downloadTb(String text) {
        databaseReference.child("Users").child(Personal_Info.getSchool_number()).child("Consent History").child(text).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                try {
                    mon = Boolean.parseBoolean(dataSnapshot.child("monday").getValue().toString());
                    tue = Boolean.parseBoolean(dataSnapshot.child("tuesday").getValue().toString());
                    wed = Boolean.parseBoolean(dataSnapshot.child("wednesday").getValue().toString());
                    thu = Boolean.parseBoolean(dataSnapshot.child("thursday").getValue().toString());
                    fri = Boolean.parseBoolean(dataSnapshot.child("friday").getValue().toString());
                    sat = Boolean.parseBoolean(dataSnapshot.child("saturday").getValue().toString());
                    sun = Boolean.parseBoolean(dataSnapshot.child("sunday").getValue().toString());
                } catch (Exception e) {
                    mon = false;
                    tue = false;
                    wed = false;
                    thu = false;
                    fri = false;
                    sat = false;
                    sun = false;
                }
                setTB(mon, tue, wed, thu, fri, sat, sun);
                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void DownloadToggle(String week) {
        DocumentReference noteRef = db.collection("Users").document(Personal_Info.getSchool_number()).collection("Consent").document(week);
        noteRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if (documentSnapshot.exists()) {

                            try {


                                Week week = documentSnapshot.toObject(Week.class);

                                mon = week.isMonday();
                                tue = week.isTuesday();
                                wed = week.isWednesday();
                                thu = week.isThursday();
                                fri = week.isFriday();
                                sat = week.isSaturday();
                                sun = week.isSunday();
                                progressBar.setVisibility(View.GONE);
                                setTB(mon, tue, wed, thu, fri, sat, sun);


                            } catch (Exception e) {
                                mon = false;
                                tue = false;
                                wed = false;
                                thu = false;
                                fri = false;
                                sat = false;
                                sun = false;
                                System.out.println("Error");
                                progressBar.setVisibility(View.GONE);
                            }
                            setTB(mon, tue, wed, thu, fri, sat, sun);


                        } else {
                            progressBar.setVisibility(View.GONE);

                            mon = false;
                            tue = false;
                            wed = false;
                            thu = false;
                            fri = false;
                            sat = false;
                            sun = false;
                            setTB(mon, tue, wed, thu, fri, sat, sun);
                            progressBar.setVisibility(View.GONE);


                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });

    }

    public void DownloadInOut(String week) {
        DocumentReference noteRef = db.collection("Users").document(Personal_Info.getSchool_number()).collection("In or Out").document(week);

        noteRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if (documentSnapshot.exists()) {

                            try {


                                Week week = documentSnapshot.toObject(Week.class);

                                mon = week.isMonday();
                                tue = week.isTuesday();
                                wed = week.isWednesday();
                                thu = week.isThursday();
                                fri = week.isFriday();
                                sat = week.isSaturday();
                                sun = week.isSunday();
                                progressBar.setVisibility(View.GONE);
                                set(mon, tue, wed, thu, fri, sat, sun);


                            } catch (Exception e) {
                                mon = false;
                                tue = false;
                                wed = false;
                                thu = false;
                                fri = false;
                                sat = false;
                                sun = false;
                                System.out.println("Error");
                                progressBar.setVisibility(View.GONE);
                            }
                            set(mon, tue, wed, thu, fri, sat, sun);


                        } else {
                            progressBar.setVisibility(View.GONE);

                            mon = false;
                            tue = false;
                            wed = false;
                            thu = false;
                            fri = false;
                            sat = false;
                            sun = false;
                            set(mon, tue, wed, thu, fri, sat, sun);
                            progressBar.setVisibility(View.GONE);


                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }

    public void set(boolean mon, boolean tue, boolean wed, boolean thu, boolean fri, boolean sat, boolean sun) {

        if (mon) {
            tvmon.setText("In");
        } else {
            tvmon.setText("Out");
        }
        if (tue) {
            tvtue.setText("In");
        } else {
            tvtue.setText("Out");
        }
        if (wed) {
            tvwed.setText("In");
        } else {
            tvthu.setText("Out");
        }
        if (thu) {
            tvthu.setText("In");
        } else {
            tvthu.setText("Out");
        }
        if (fri) {
            tvfri.setText("In");
        } else {
            tvfri.setText("Out");
        }
        if (sat) {
            tvsat.setText("In");
        } else {
            tvsat.setText("Out");
        }
        if (sun) {
            tvsun.setText("In");
        } else {
            tvsun.setText("Out");
        }


    }

    public void setTB(boolean mon, boolean tue, boolean wed, boolean thu, boolean fri, boolean sat, boolean sun) {

        if (mon == true) {
            tbmon.setChecked(true);
        } else {
            tbmon.setChecked(false);
        }
        if (tue == true) {
            tbtue.setChecked(true);
        } else {
            tbtue.setChecked(false);
        }
        if (wed == true) {
            tbwed.setChecked(true);
        } else {
            tbwed.setChecked(false);
        }
        if (thu == true) {
            tbwed.setChecked(true);
        } else {
            tbthu.setChecked(false);
        }
        if (fri == true) {
            tbwed.setChecked(true);
        } else {
            tbfri.setChecked(false);
        }
        if (sat == true) {
            tbwed.setChecked(true);
        } else {
            tbsat.setChecked(false);
        }
        if (sun == true) {
            tbwed.setChecked(true);
        } else {
            tbsun.setChecked(false);
        }


    }

    public void saveTB(String text) {


        boolean Mon = tbmon.isChecked();
        boolean tue = tbtue.isChecked();
        boolean wed = tbwed.isChecked();
        boolean thu = tbthu.isChecked();
        boolean fri = tbfri.isChecked();
        boolean sat = tbsun.isChecked();
        boolean sun = tbsat.isChecked();


        //String id = databaseReference.push().getKey();

        Week week = new Week(Mon, tue, wed, thu, fri, sat, sun);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users").child(Personal_Info.getSchool_number()).child("Consent History").child(text);

        myRef.setValue(week).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressBar.setVisibility(View.INVISIBLE);

            }
        });


    }

    public void saveToggle(String week1) {
        DocumentReference noteRef = db.collection("Users").document(Personal_Info.getSchool_number()).collection("Consent").document(week1);

        boolean Mon = tbmon.isChecked();
        boolean tue = tbtue.isChecked();
        boolean wed = tbwed.isChecked();
        boolean thu = tbthu.isChecked();
        boolean fri = tbfri.isChecked();
        boolean sat = tbsun.isChecked();
        boolean sun = tbsat.isChecked();


        //String id = databaseReference.push().getKey();

        Week week = new Week(Mon, tue, wed, thu, fri, sat, sun);
        noteRef.set(week)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }
}




