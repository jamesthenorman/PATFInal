package com.example.patfinal.Fragment_Users;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.patfinal.Objects.Personal_Details;
import com.example.patfinal.Objects.Query;
import com.example.patfinal.Personal_Info;
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

import static android.support.constraint.Constraints.TAG;
import static java.lang.String.valueOf;



public class Fragment_Personal_Details extends Fragment {
    private EditText first;
    private EditText last;
    private EditText phone;
    private EditText parent;
    private EditText emergency;
    private EditText room;
    private EditText allergies;
    private Button submit;
    private ProgressBar progressBar;
    private DatabaseReference mDatabase;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_details, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        submit = view.findViewById(R.id.btnSPD);

        first = view.findViewById(R.id.et_FN);
        last = view.findViewById(R.id.et_SN);
        phone = view.findViewById(R.id.et_CN);
        parent = view.findViewById(R.id.et_PC);
        emergency = view.findViewById(R.id.et_EC);
        room = view.findViewById(R.id.et_RN);
        allergies = view.findViewById(R.id.et_AL);

        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        download();
        //  Download();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                writeToFile();


            }
        });

        return view;
    }

    //Download and set the texts from the database
    private void download() {

        databaseReference.child("Users").child(Personal_Info.getSchool_number()).child("Personal Details").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Personal_Details user = dataSnapshot.getValue(Personal_Details.class);
                String First = user.getFirstName();
                String Last = user.getLastName();
                String Phone = user.getPhoneNumber();
                String Parent = user.getParentPhoneNumber();
                String Emergency = user.getEmergencyContact();
                String Room = user.getRoomNumber();
                String Allergies = user.getAllergies();

                Personal_Info.setName(First + " " + Last);

                first.setText(First);
                last.setText(Last);
                phone.setText(Phone);
                parent.setText(Parent);
                emergency.setText(Emergency);
                room.setText(Room);
                allergies.setText(Allergies);
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


    }


    //Save the data
    private void writeToFile() {

        String First = first.getText().toString();
        String Last = last.getText().toString();
        String Phone = phone.getText().toString();
        String Parent = parent.getText().toString();
        String Emergency = emergency.getText().toString();
        String Room = room.getText().toString();
        String Allergies = allergies.getText().toString();

        Personal_Details personal_details = new Personal_Details(First, Last, Phone, Parent, Emergency, Room, Allergies);
        String id = Personal_Info.getSchool_number();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users").child(id).child("Personal Details");
        myRef.setValue(personal_details).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "Saved ", Toast.LENGTH_SHORT).show();

            }
        });

    }







/*
    public void save2() {
        DocumentReference noteRef = db.collection("Users").document(Personal_Info.getSchool_number()).collection("Personal Details").document("Document");

        String First = first.getText().toString();
        String Last = last.getText().toString();
        String Phone = phone.getText().toString();
        String Parent = parent.getText().toString();
        String Emergency = emergency.getText().toString();
        String Room = room.getText().toString();
        String Allergies = allergies.getText().toString();

        Personal_Details personal_details = new Personal_Details(First, Last, Phone, Parent, Emergency, Room, Allergies);
        noteRef.set(personal_details)
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
*/

/*
    public void Download() {
        DocumentReference noteRef = db.collection("Users").document(Personal_Info.getSchool_number()).collection("Personal Details").document("Document");

        noteRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            Personal_Details personal_details = documentSnapshot.toObject(Personal_Details.class);
                            String First = personal_details.getFirstName();
                            String Last = personal_details.getLastName();
                            String Phone = personal_details.getPhoneNumber();
                            String Parent = personal_details.getParentPhoneNumber();
                            String Emergency = personal_details.getEmergencyContact();
                            String Room = personal_details.getRoomNumber();
                            String Allergies = personal_details.getAllergies();

                            first.setText(valueOf(First));
                            last.setText(valueOf(Last));
                            phone.setText(valueOf(Phone));
                            parent.setText(valueOf(Parent));
                            emergency.setText(valueOf(Emergency));
                            room.setText(valueOf(Room));
                            allergies.setText(valueOf(Allergies));


                        } else {
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                }).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                progressBar.setVisibility(View.INVISIBLE);

            }
        });
    }
*/

}




