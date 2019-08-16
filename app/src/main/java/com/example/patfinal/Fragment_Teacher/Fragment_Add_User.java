package com.example.patfinal.Fragment_Teacher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patfinal.Objects.Personal_Details;
import com.example.patfinal.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;


public class Fragment_Add_User extends Fragment {

    private TextView Fill;
    private EditText First1;
    private EditText Last1;
    private EditText schoolNum1;
    private EditText Room1;
    private ProgressBar progressBar;
    private Button button;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_add_user, container, false);


        First1 = view.findViewById(R.id.etFirst);
        Last1 = view.findViewById(R.id.etLast);
        schoolNum1 = view.findViewById(R.id.etSchool);
        Room1 = view.findViewById(R.id.etRoom);

        Fill = view.findViewById(R.id.fill);

        button = view.findViewById(R.id.aubtn);

        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                Fill.setVisibility(View.INVISIBLE);
                save();
                //saveFireStore();

            }
        });


        return view;
    }       //Runs on the creation of the screen

    private void save() {
        try {
            String first = First1.getText().toString();
            String last = Last1.getText().toString();
            String school = schoolNum1.getText().toString();
            String room = Room1.getText().toString();
            if (!first.equals("") && !last.equals("") && !school.equals("") && !room.equals("")) {

                Personal_Details personal_details = new Personal_Details(first, last, room);
                DatabaseReference myRef = database.getReference().child("Users").child(school).child("Personal Details");
                myRef.setValue(personal_details);
                First1.setText("");
                Last1.setText("");
                schoolNum1.setText("");
                Room1.setText("");
                Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show();

            }

        } catch (Exception e) {
            Toast.makeText(getContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show();

        }

        progressBar.setVisibility(View.INVISIBLE);


    }           //Method to save the user












/*
    public void saveFireStore() {
        try {
            String first = First1.getText().toString();
            String last = Last1.getText().toString();
            String school = schoolNum1.getText().toString();
            String room = Room1.getText().toString();
            // DocumentReference noteRef = db.collection("Users").document(Personal_Info.getSchool_number()).collection("Personal Details").document("Document");


            final Personal_Details personal_details = new Personal_Details(first, last, room);
            System.out.println(personal_details);
            db.collection("Users").document(school).collection("Personal Details").document("Document").set(personal_details)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println(personal_details);
                            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            System.out.println("Error");
                        }
                    });

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(getContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show();

        }
        First1.setText("");
        Last1.setText("");
        schoolNum1.setText("");
        Room1.setText("");
        Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();


    }
*/
}




