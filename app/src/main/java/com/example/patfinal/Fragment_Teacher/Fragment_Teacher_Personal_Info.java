package com.example.patfinal.Fragment_Teacher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.patfinal.Objects.Personal_Details;
import com.example.patfinal.Personal_Info;
import com.example.patfinal.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import static android.support.constraint.Constraints.TAG;


public class Fragment_Teacher_Personal_Info extends Fragment {
    private TextView first;
    private TextView last;
    private TextView phone;
    private TextView parent;
    private TextView emergency;
    private TextView room;
    private TextView allergies;
    private Button submit;
    private ProgressBar progressBar;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_personal_info, container, false);

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
        download();

        return view;
    }       //Runs on the creation of the screen


    //Download and set the texts
    private void download() {

        databaseReference.child("Users").child(Fragment_Teacher_Check.test.getText().toString()).child("Personal Details").addListenerForSingleValueEvent(new ValueEventListener() {

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
                System.out.println(phone.getText().toString());
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
}