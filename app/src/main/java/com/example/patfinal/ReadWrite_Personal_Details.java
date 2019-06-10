package com.example.patfinal;

import android.util.Log;

import com.example.patfinal.Objects.Personal_Details;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.support.constraint.Constraints.TAG;

public class ReadWrite_Personal_Details {
    private static DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    ;


    public static void write(Personal_Details details) {

        String id = Personal_Info.getSchool_number();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Personal Details").child(id);
        myRef.setValue(details);


    }

    static Personal_Details pd = new Personal_Details();

    public static Personal_Details read() {
        String num = Personal_Info.getSchool_number();
        mDatabase.child("Personal Details").child(num).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Personal_Details user = dataSnapshot.getValue(Personal_Details.class);

                final String First = user.getFirstName();
                final String Last = user.getLastName();
                final String Phone = user.getPhoneNumber();
                final String Parent = user.getParentPhoneNumber();
                final String Emergency = user.getEmergencyContact();
                final String Room = user.getRoomNumber();
                final String Allergies = user.getAllergies();
                Personal_Details pd1 = new Personal_Details(First, Last, Phone, Parent, Emergency, Room, Allergies);
                pd = pd1;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        return pd;
    }


}

