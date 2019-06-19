package com.example.patfinal.Fragment_Users;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.patfinal.Objects.Query;
import com.example.patfinal.Objects.Week;
import com.example.patfinal.Personal_Info;
import com.example.patfinal.Processes.Populate_Spinner;
import com.example.patfinal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;


public class Fragment_In_or_Out extends Fragment {

    private RadioButton RBIm, RBOm, RBItu, RBOtu, RBIw, RBOw, RBIth, RBOth, RBIf, RBOf, RBIsa, RBOsa, RBIsu, RBOsu;
    private RadioGroup rgmon, rgtue, rgwed, rgthu, rgfri, rgsat, rgsun;
    private Button sub;
    private static Spinner spinner;
    private FrameLayout querry;
    public static Query[] queryList = new Query[7];
    static int queryListLen = 0;
    private boolean mon;
    private boolean tue;
    private boolean wed;
    private boolean thu;
    private boolean fri;
    private boolean sat;
    private boolean sun;
    private DatabaseReference databaseReference;
    private static Week weekDownload;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_in_out, container, false);

        //Populate the spinner
        spinner = view.findViewById(R.id.spinner);
        Populate_Spinner.populate();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, Populate_Spinner.week);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        //get database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //Link the buttons, text views and progressBar
        RBIm = view.findViewById(R.id.rbINmon);
        RBOm = view.findViewById(R.id.rbOUTmon);
        RBItu = view.findViewById(R.id.rbINtue);
        RBOtu = view.findViewById(R.id.rbOUTtue);
        RBIw = view.findViewById(R.id.rbINwed);
        RBOw = view.findViewById(R.id.rbOUTwed);
        RBIth = view.findViewById(R.id.rbINthu);
        RBOth = view.findViewById(R.id.rbOUTthu);
        RBIf = view.findViewById(R.id.rbINfri);
        RBOf = view.findViewById(R.id.rbOUTfri);
        RBIsa = view.findViewById(R.id.rbINsat);
        RBOsa = view.findViewById(R.id.rbOUTsat);
        RBIsu = view.findViewById(R.id.rbINsun);
        RBOsu = view.findViewById(R.id.rbOUTsun);

        rgmon = view.findViewById(R.id.rgmon);
        rgtue = view.findViewById(R.id.rgtue);
        rgwed = view.findViewById(R.id.rgwed);
        rgthu = view.findViewById(R.id.rgthu);
        rgfri = view.findViewById(R.id.rgfri);
        rgsat = view.findViewById(R.id.rgsat);
        rgsun = view.findViewById(R.id.rgsun);

        sub = view.findViewById(R.id.button);

        progressBar = view.findViewById(R.id.progressBar);

        querry = view.findViewById(R.id.FrameQuerry);

        //Invokes when an item is selected in the spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selection();
                progressBar.setVisibility(View.VISIBLE);
                download(spinner.getSelectedItem().toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // If an option is removed then what to do
                // or anything else
            }

        });

        //Invoked when the submit button is pressed
        sub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Intent intent = new Intent(getActivity(), Main_Activity_Student.class);
                //startActivity(intent);
                progressBar.setVisibility(View.INVISIBLE);
                writeToFile(spinner.getSelectedItem().toString());
                System.out.println(weekDownload);
                seeQuery();
// save(spinner.getSelectedItem().toString());


            }
        });

//show Loading symbol
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        return view;
    }

    private void download(final String text) {

        databaseReference.child("Users").child(Personal_Info.getSchool_number()).child("User History").child(text).addListenerForSingleValueEvent(new ValueEventListener() {
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
                check(mon, tue, wed, thu, fri, sat, sun);
                Week w = new Week(mon, tue, wed, thu, fri, sat, sun);
                weekDownload = w;
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        boolean mon = RBIm.isChecked();
        boolean tue = RBItu.isChecked();
        boolean wed = RBIw.isChecked();
        boolean thu = RBIth.isChecked();
        boolean fri = RBIf.isChecked();
        boolean sat = RBIsa.isChecked();
        boolean sun = RBIsu.isChecked();
        Week w = new Week(mon, tue, wed, thu, fri, sat, sun);
        weekDownload = w;
    }

    private void writeToFile(String text) {


        boolean mon = RBIm.isChecked();
        boolean tue = RBItu.isChecked();
        boolean wed = RBIw.isChecked();
        boolean thu = RBIth.isChecked();
        boolean fri = RBIf.isChecked();
        boolean sat = RBIsa.isChecked();
        boolean sun = RBIsu.isChecked();

        Week week = new Week(mon, tue, wed, thu, fri, sat, sun);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users").child(Personal_Info.getSchool_number()).child("User History").child(text);

        myRef.setValue(week).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();

    }

    private void check(boolean mon, boolean tue, boolean wed, boolean thu, boolean fri, boolean sat, boolean sun) {
        if (mon == true) {
            RBIm.setChecked(true);
        } else {
            RBOm.setChecked(true);
        }

        if (tue == true) {
            RBItu.setChecked(true);
        } else {
            RBOtu.setChecked(true);
        }

        if (wed == true) {
            RBIw.setChecked(true);
        } else {
            RBOw.setChecked(true);
        }

        if (thu == true) {
            RBIth.setChecked(true);
        } else {
            RBOth.setChecked(true);
        }

        if (fri == true) {
            RBIf.setChecked(true);
        } else {
            RBOf.setChecked(true);
        }

        if (sat == true) {
            RBIsa.setChecked(true);
        } else {
            RBOsa.setChecked(true);
        }

        if (sun == true) {
            RBIsu.setChecked(true);
        } else {
            RBOsu.setChecked(true);
        }
    }

    private void selection() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int y = 0;
        System.out.println(spinner.getSelectedItemPosition());
        switch (day) {
            case Calendar.MONDAY:
                RBIm.setEnabled(true);
                RBOm.setEnabled(true);
                RBItu.setEnabled(true);
                RBOtu.setEnabled(true);
                RBIw.setEnabled(true);
                RBOw.setEnabled(true);
                RBIth.setEnabled(true);
                RBOth.setEnabled(true);
                RBIf.setEnabled(true);
                RBOf.setEnabled(true);
                RBIsa.setEnabled(true);
                RBOsa.setEnabled(true);

                break;
            case Calendar.TUESDAY:
                RBIm.setEnabled(true);
                RBOm.setEnabled(true);
                RBItu.setEnabled(true);
                RBOtu.setEnabled(true);
                RBIw.setEnabled(true);
                RBOw.setEnabled(true);
                RBIth.setEnabled(true);
                RBOth.setEnabled(true);
                RBIf.setEnabled(true);
                RBOf.setEnabled(true);
                RBIsa.setEnabled(true);
                RBOsa.setEnabled(true);
                if (spinner.getSelectedItemPosition() == 0) {
                    System.out.println(spinner.getSelectedItemPosition());
                    RBIm.setEnabled(false);
                    RBOm.setEnabled(false);
                }
                break;
            case Calendar.WEDNESDAY:
                RBIm.setEnabled(true);
                RBOm.setEnabled(true);
                RBItu.setEnabled(true);
                RBOtu.setEnabled(true);
                RBIw.setEnabled(true);
                RBOw.setEnabled(true);
                RBIth.setEnabled(true);
                RBOth.setEnabled(true);
                RBIf.setEnabled(true);
                RBOf.setEnabled(true);
                RBIsa.setEnabled(true);
                RBOsa.setEnabled(true);
                if (spinner.getSelectedItemPosition() == 0) {
                    RBIm.setEnabled(false);
                    RBOm.setEnabled(false);
                    RBItu.setEnabled(false);
                    RBOtu.setEnabled(false);
                }
                break;
            case Calendar.THURSDAY:
                RBIm.setEnabled(true);
                RBOm.setEnabled(true);
                RBItu.setEnabled(true);
                RBOtu.setEnabled(true);
                RBIw.setEnabled(true);
                RBOw.setEnabled(true);
                RBIth.setEnabled(true);
                RBOth.setEnabled(true);
                RBIf.setEnabled(true);
                RBOf.setEnabled(true);
                RBIsa.setEnabled(true);
                RBOsa.setEnabled(true);
                if (spinner.getSelectedItemPosition() == 0) {
                    RBIm.setEnabled(false);
                    RBOm.setEnabled(false);
                    RBItu.setEnabled(false);
                    RBOtu.setEnabled(false);
                    RBIw.setEnabled(false);
                    RBOw.setEnabled(false);
                }
                break;
            case Calendar.FRIDAY:
                RBIm.setEnabled(true);
                RBOm.setEnabled(true);
                RBItu.setEnabled(true);
                RBOtu.setEnabled(true);
                RBIw.setEnabled(true);
                RBOw.setEnabled(true);
                RBIth.setEnabled(true);
                RBOth.setEnabled(true);
                RBIf.setEnabled(true);
                RBOf.setEnabled(true);
                RBIsa.setEnabled(true);
                RBOsa.setEnabled(true);
                if (spinner.getSelectedItemPosition() == 0) {
                    RBIm.setEnabled(false);
                    RBOm.setEnabled(false);
                    RBItu.setEnabled(false);
                    RBOtu.setEnabled(false);
                    RBIw.setEnabled(false);
                    RBOw.setEnabled(false);
                    RBIth.setEnabled(false);
                    RBOth.setEnabled(false);
                }
                break;
            case Calendar.SATURDAY:
                RBIm.setEnabled(true);
                RBOm.setEnabled(true);
                RBItu.setEnabled(true);
                RBOtu.setEnabled(true);
                RBIw.setEnabled(true);
                RBOw.setEnabled(true);
                RBIth.setEnabled(true);
                RBOth.setEnabled(true);
                RBIf.setEnabled(true);
                RBOf.setEnabled(true);
                RBIsa.setEnabled(true);
                RBOsa.setEnabled(true);
                if (spinner.getSelectedItemPosition() == 0) {
                    RBIm.setEnabled(false);
                    RBOm.setEnabled(false);
                    RBItu.setEnabled(false);
                    RBOtu.setEnabled(false);
                    RBIw.setEnabled(false);
                    RBOw.setEnabled(false);
                    RBIth.setEnabled(false);
                    RBOth.setEnabled(false);
                    RBIf.setEnabled(false);
                    RBOf.setEnabled(false);
                }
                break;
            case Calendar.SUNDAY:
                RBIm.setEnabled(true);
                RBOm.setEnabled(true);
                RBItu.setEnabled(true);
                RBOtu.setEnabled(true);
                RBIw.setEnabled(true);
                RBOw.setEnabled(true);
                RBIth.setEnabled(true);
                RBOth.setEnabled(true);
                RBIf.setEnabled(true);
                RBOf.setEnabled(true);
                RBIsa.setEnabled(true);
                RBOsa.setEnabled(true);
                if (spinner.getSelectedItemPosition() == 0) {
                    RBIm.setEnabled(false);
                    RBOm.setEnabled(false);
                    RBItu.setEnabled(false);
                    RBOtu.setEnabled(false);
                    RBIw.setEnabled(false);
                    RBOw.setEnabled(false);
                    RBIth.setEnabled(false);
                    RBOth.setEnabled(false);
                    RBIf.setEnabled(false);
                    RBOf.setEnabled(false);
                    RBIsa.setEnabled(false);
                    RBOsa.setEnabled(false);
                }
                break;


        }
    }

    private void openQuery() {
        querry.setVisibility(View.VISIBLE);
        FragmentTransaction fr5 = getFragmentManager().beginTransaction();
        fr5.add(R.id.FrameQuerry, new Fragment_Querry());
        fr5.commit();


    }

    private void seeQuery() {

        int len = 0;

        boolean mon = RBIm.isChecked();
        boolean tue = RBItu.isChecked();
        boolean wed = RBIw.isChecked();
        boolean thu = RBIth.isChecked();
        boolean fri = RBIf.isChecked();
        boolean sat = RBIsa.isChecked();
        boolean sun = RBIsu.isChecked();

        boolean checkQyery = false;

        if (weekDownload.isMonday() != mon && !mon) {
            Query que = new Query(spinner.getSelectedItem().toString(), "Monday");
            queryList[len] = (que);
            System.out.println("Blue" + queryList[0].getDay());
            len++;
            queryListLen++;
            checkQyery = true;
        }
        if (weekDownload.isTuesday() != tue && !tue) {
            Query que = new Query(spinner.getSelectedItem().toString(), "Tuesday");
            queryList[len] = (que);
            len++;
            queryListLen++;

            checkQyery = true;

        }
        if (weekDownload.isWednesday() != wed && !wed) {
            Query que = new Query(spinner.getSelectedItem().toString(), "Wednesday");
            queryList[len] = (que);
            len++;
            queryListLen++;

            checkQyery = true;

        }
        if (weekDownload.isThursday() != thu && !thu) {
            Query que = new Query(spinner.getSelectedItem().toString(), "Thursday");
            queryList[len] = (que);
            len++;
            queryListLen++;

            checkQyery = true;

        }
        if (checkQyery) {
            openQuery();
        }

    }


/*
    public void save(String week1) {
        progressBar.setVisibility(View.VISIBLE);
        DocumentReference noteRef = db.collection("Users/" + Personal_Info.getSchool_number() + "/In or Out").document(week1);

        boolean Mon = RBIm.isChecked();
        boolean tue = RBItu.isChecked();
        boolean wed = RBIw.isChecked();
        boolean thu = RBIth.isChecked();
        boolean fri = RBIf.isChecked();
        boolean sat = RBIsa.isChecked();
        boolean sun = RBIsu.isChecked();

        Week week = new Week(Mon, tue, wed, thu, fri, sat, sun);


        noteRef.set(week)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }
*/

/*
    public void download2(String week1) {
        DocumentReference noteRef = db.collection("Users/").document(Personal_Info.getSchool_number()).collection("/In or Out").document(week1);

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
                            check(mon, tue, wed, thu, fri, sat, sun);


                        } else {
                            progressBar.setVisibility(View.GONE);

                            mon = false;
                            tue = false;
                            wed = false;
                            thu = false;
                            fri = false;
                            sat = false;
                            sun = false;
                            check(mon, tue, wed, thu, fri, sat, sun);
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
*/


}