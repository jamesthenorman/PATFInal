package com.example.patfinal.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.patfinal.Personal_Info;
import com.example.patfinal.R;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    private EditText number;
    private Button bt;
    private TextView textView;
    private TextView Teacher;
    private ProgressBar progressBar;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAnalytics mFirebaseAnalytics;
    private FloatingActionButton help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Personal Details");
        setContentView(R.layout.activity_main);
        number = findViewById(R.id.etNumber);
        bt = findViewById(R.id.btnCont);
        textView = findViewById(R.id.textView2);
        Teacher = findViewById(R.id.TeacherLogin);


        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        Teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTeacherPass();

            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                Verify(number);

            }
        });

        help = findViewById(R.id.floatingActionButton4);

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHelpScreen();
            }
        });


    }       //Runs on the creation of the screen

    private void openUsertype() {
        Intent intent = new Intent(this, UserType.class);
        startActivity(intent);
    }                               //Method to open the user type screen

    private void openTeacherPass() {
        Intent intent = new Intent(this, Teacher_Login.class);
        startActivity(intent);

    }                           //Method to open the teacher password screen

    private void Verify(final EditText x) {
        try {
            databaseReference.child("Users").child(x.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists() && !x.getText().toString().isEmpty()) {
                        //bus number exists in Database
                        textView.setVisibility(View.INVISIBLE);
                        Personal_Info.setSchool_number(x.getText().toString());
                        progressBar.setVisibility(View.INVISIBLE);
                        setName();
                        openUsertype();

                    } else {
                        //bus number doesn't exists.
                        textView.setText("Wrong number.");
                        textView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } catch (Exception e) {
            System.out.println("Error in try catch");
        }
    }                   //Method to verify if the username is correct

    private void setName() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    String First = dataSnapshot.child("Users").child(Personal_Info.getSchool_number()).child("Personal Details").child("firstName").getValue().toString();
                    String Last = dataSnapshot.child("Users").child(Personal_Info.getSchool_number()).child("Personal Details").child("lastName").getValue().toString();
                    Personal_Info.setName(First + " " + Last);
                } catch (Exception e) {
                    Personal_Info.setName("ERR, enter name");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }                                   //Sets the name of the student

    private void openHelpScreen() {
        Intent intent = new Intent(this, Activity_Help.class);
        startActivity(intent);

    }                           //Opens the help screen

/*
    public void openTeacher() {
        Intent intent = new Intent(this, UserType.class);
        startActivity(intent);
    }
*/


/*
    public void test(final EditText a) {

        databaseReference.addValueEventListener(new ValueEventListener() {
            String First;

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                First = dataSnapshot.child("Personal Details").getValue().toString();
                System.out.println(First);


                for (int x = 0; x < First.length() - 5; x++) {
                    int first = x;
                    int last = x + 5;

                    if (First.substring(first, last).equals(a.getText().toString())) {

                        Personal_Info.setSchool_number(a.getText().toString());
                        setName();
                        openUsertype();

                    } else {

                        textView.setText("Wrong number.");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
*/


/*
    public void check2(final EditText num) {
        DocumentReference noteRef1 = db.collection("Users").document(num.getText().toString());
        noteRef1.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            textView.setVisibility(View.INVISIBLE);
                            Personal_Info.setSchool_number(num.getText().toString());
                            setName2();
                            openUsertype();
                        } else {
                            //bus number doesn't exists.
                            textView.setText("Wrong number.");
                            textView.setVisibility(View.VISIBLE);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        textView.setText("Wrong number. 12");
                        textView.setVisibility(View.VISIBLE);
                    }
                });
    }
*/


   /* public void check(final EditText num) {
        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        DocumentReference docIdRef = rootRef.collection("Users").document(num.getText().toString());
        System.out.println("Blue beans    " + docIdRef);
        docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        textView.setVisibility(View.INVISIBLE);
                        Personal_Info.setSchool_number(num.getText().toString());
                        setName();
                        openUsertype();
                    } else {
                        textView.setText("Wrong number.");
                        textView.setVisibility(View.VISIBLE);
                    }
                } else {

                }
            }
        });
    }*/


/*
    public void setName2() {
        DocumentReference noteRef = db.collection("Users").document(Personal_Info.getSchool_number()).collection("Personal Details").document("Document");
        noteRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            try {
                                Personal_Details personal_details = documentSnapshot.toObject(Personal_Details.class);

                                String First = personal_details.getFirstName();
                                String Last = personal_details.getLastName();
                                Personal_Info.setName(First + " " + Last);
                            } catch (Exception e) {
                                Personal_Info.setName("ERR, enter name");
                            }

                        } else {
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