package com.example.patfinal.Fragment_Teacher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patfinal.Personal_Info;
import com.example.patfinal.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Fragment_Teacher_Check extends Fragment {

    private static String number;
    private Button btnSub;
    private EditText schoolNumber;
    public static TextView test;
    private ProgressBar progressBar;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher__check, container, false);
        btnSub = view.findViewById(R.id.check);
        schoolNumber = view.findViewById(R.id.SchoolNumber);
        test = view.findViewById(R.id.textView6);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                searchSchoolNumber(schoolNumber);

            }
        });

        return view;
    }

    private void searchSchoolNumber(final EditText x) {
        try {
            databaseReference.child("Users").child(x.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists() && !x.getText().toString().equals("")) {
                        //bus number exists in Database
                        Personal_Info.setSchool_number(x.getText().toString());
                        openTeacherCheck();
                        test.setText(x.getText().toString());
                    } else {
                        //bus number doesn't exists.
                        Toast toast = Toast.makeText(getContext(), "No User Exists", Toast.LENGTH_SHORT);
                        toast.show();

                        System.out.println("Error");
                    }
                    progressBar.setVisibility(View.INVISIBLE);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } catch (Exception e) {
            System.out.println("Error in try catch");
        }
    }


    private void openTeacherCheck() {
        FragmentTransaction fr1 = getFragmentManager().beginTransaction().addToBackStack("Tag");
        fr1.replace(R.id.fragment_container, new Fragment_View_In_Out());
        fr1.commit();
    }

/*
    public void SearchRoom() {
        final String Number = roomNumber.getText().toString().trim().toUpperCase();

        databaseReference.child("Users").child("Personal Details").orderByChild("firstName").orderByChild("lastName").equalTo(Number);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {

                    String title = (String) singleSnapshot.child("firstName").getValue(String.class);
                    System.out.println("TITLE: " + title);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
*/

}