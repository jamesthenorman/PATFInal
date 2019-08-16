package com.example.patfinal.Fragment_Users;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.patfinal.Objects.Teacher;
import com.example.patfinal.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.support.constraint.Constraints.TAG;

public class Fragment_Teacher_Contact extends Fragment {

    private TextView TeacherName1, TeacherName2, TeacherName3, TeacherName4, TeacherName5;
    private TextView TeacherNumber1, TeacherNumber2, TeacherNumber3, TeacherNumber4, TeacherNumber5;
    private TextView TeacherRoom1, TeacherRoom2, TeacherRoom3, TeacherRoom4, TeacherRoom5;
    private TextView TeacherEmail1, TeacherEmail2, TeacherEmail3, TeacherEmail4, TeacherEmail5;
    private ProgressBar progressBar;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    //Runs on the creation of the screen adn initialises the buttons and text views
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_contact, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        TeacherName1 = view.findViewById(R.id.teachername1);
        TeacherName2 = view.findViewById(R.id.teachername2);
        TeacherName3 = view.findViewById(R.id.teachername3);
        TeacherName4 = view.findViewById(R.id.teachername4);
        TeacherName5 = view.findViewById(R.id.teachername5);


        TeacherNumber1 = view.findViewById(R.id.teachernumber1);
        TeacherNumber2 = view.findViewById(R.id.teachernumber2);
        TeacherNumber3 = view.findViewById(R.id.teachernumber3);
        TeacherNumber4 = view.findViewById(R.id.teachernumber4);
        TeacherNumber5 = view.findViewById(R.id.teachernumber5);


        TeacherRoom1 = view.findViewById(R.id.teacherroom1);
        TeacherRoom2 = view.findViewById(R.id.teacherroom2);
        TeacherRoom3 = view.findViewById(R.id.teacherroom3);
        TeacherRoom4 = view.findViewById(R.id.teacherroom4);
        TeacherRoom5 = view.findViewById(R.id.teacherroom5);


        TeacherEmail1 = view.findViewById(R.id.TeacherEmail1);
        TeacherEmail2 = view.findViewById(R.id.TeacherEmail2);
        TeacherEmail3 = view.findViewById(R.id.TeacherEmail3);
        TeacherEmail4 = view.findViewById(R.id.TeacherEmail4);
        TeacherEmail5 = view.findViewById(R.id.TeacherEmail5);

        progressBar = view.findViewById(R.id.progressBar);
        //Download();
        GetAndSet();

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

    //Downloads the data from the server and sets it into the text views and classes
    private void GetAndSet() {
        databaseReference.child("Teacher Contact").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String a = "Teacher 1";
                String b = "Teacher 2";
                String c = "Teacher 3";
                String d = "Teacher 4";
                String e = "Teacher 5";


                Teacher Teacher1 = dataSnapshot.child(a).getValue(Teacher.class);
                Teacher Teacher2 = dataSnapshot.child(b).getValue(Teacher.class);
                Teacher Teacher3 = dataSnapshot.child(c).getValue(Teacher.class);
                Teacher Teacher4 = dataSnapshot.child(d).getValue(Teacher.class);
                Teacher Teacher5 = dataSnapshot.child(e).getValue(Teacher.class);


                String name1 = Teacher1.getName();
                String number1 = Teacher1.getNumber();
                String room1 = Teacher1.getRoomNumber();
                String email1 = Teacher1.getEmail();


                String name2 = Teacher2.getName();
                String number2 = Teacher2.getNumber();
                String room2 = Teacher2.getRoomNumber();
                String email2 = Teacher2.getEmail();


                String name3 = Teacher3.getName();
                String number3 = Teacher3.getNumber();
                String room3 = Teacher3.getRoomNumber();
                String email3 = Teacher3.getEmail();


                String name4 = Teacher4.getName();
                String number4 = Teacher4.getNumber();
                String room4 = Teacher4.getRoomNumber();
                String email4 = Teacher4.getEmail();


                String name5 = Teacher5.getName();
                String number5 = Teacher5.getNumber();
                String room5 = Teacher5.getRoomNumber();
                String email5 = Teacher5.getEmail();


                TeacherName1.setText(String.valueOf(name1));
                TeacherNumber1.setText(String.valueOf(number1));
                TeacherRoom1.setText(String.valueOf(room1));
                TeacherEmail1.setText(String.valueOf(email1));


                TeacherName2.setText(String.valueOf(name2));
                TeacherNumber2.setText(String.valueOf(number2));
                TeacherRoom2.setText(String.valueOf(room2));
                TeacherEmail2.setText(String.valueOf(email2));


                TeacherName3.setText(String.valueOf(name3));
                TeacherNumber3.setText(String.valueOf(number3));
                TeacherRoom3.setText(String.valueOf(room3));
                TeacherEmail3.setText(String.valueOf(email3));


                TeacherName4.setText(String.valueOf(name4));
                TeacherNumber4.setText(String.valueOf(number4));
                TeacherRoom4.setText(String.valueOf(room4));
                TeacherEmail4.setText(String.valueOf(email4));


                TeacherName5.setText(String.valueOf(name5));
                TeacherNumber5.setText(String.valueOf(number5));
                TeacherRoom5.setText(String.valueOf(room5));
                TeacherEmail5.setText(String.valueOf(email5));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }








/*
    public void Download() {
        DocumentReference noteRef1 = db.collection("Teacher Details").document("Teacher 1");
        DocumentReference noteRef2 = db.collection("Teacher Details").document("Teacher 2");
        DocumentReference noteRef3 = db.collection("Teacher Details").document("Teacher 3");
        DocumentReference noteRef4 = db.collection("Teacher Details").document("Teacher 4");
        DocumentReference noteRef5 = db.collection("Teacher Details").document("Teacher 5");

        noteRef1.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            Teacher teacher = documentSnapshot.toObject(Teacher.class);
                            System.out.println(teacher);

                            String name1 = teacher.getName();
                            String number1 = teacher.getNumber();
                            String room1 = teacher.getRoomNumber();
                            String email1 = teacher.getEmail();

                            TeacherName1.setText(String.valueOf(name1));
                            TeacherNumber1.setText(String.valueOf(number1));
                            TeacherRoom1.setText(String.valueOf(room1));
                            TeacherEmail1.setText(String.valueOf(email1));


                        } else {
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
        noteRef2.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            Teacher teacher = documentSnapshot.toObject(Teacher.class);
                            System.out.println(teacher);

                            String name2 = teacher.getName();
                            String number2 = teacher.getNumber();
                            String room2 = teacher.getRoomNumber();
                            String email2 = teacher.getEmail();

                            TeacherName2.setText(String.valueOf(name2));
                            TeacherNumber2.setText(String.valueOf(number2));
                            TeacherRoom2.setText(String.valueOf(room2));
                            TeacherEmail2.setText(String.valueOf(email2));

                        } else {
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
        noteRef3.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            Teacher teacher = documentSnapshot.toObject(Teacher.class);
                            System.out.println(teacher);

                            String name3 = teacher.getName();
                            String number3 = teacher.getNumber();
                            String room3 = teacher.getRoomNumber();
                            String email3 = teacher.getEmail();

                            TeacherName3.setText(String.valueOf(name3));
                            TeacherNumber3.setText(String.valueOf(number3));
                            TeacherRoom3.setText(String.valueOf(room3));
                            TeacherEmail3.setText(String.valueOf(email3));

                        } else {
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
        noteRef4.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            Teacher teacher = documentSnapshot.toObject(Teacher.class);
                            System.out.println(teacher);

                            String name4 = teacher.getName();
                            String number4 = teacher.getNumber();
                            String room4 = teacher.getRoomNumber();
                            String email4 = teacher.getEmail();

                            TeacherName4.setText(String.valueOf(name4));
                            TeacherNumber4.setText(String.valueOf(number4));
                            TeacherRoom4.setText(String.valueOf(room4));
                            TeacherEmail4.setText(String.valueOf(email4));


                        } else {
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
        noteRef5.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            Teacher teacher = documentSnapshot.toObject(Teacher.class);
                            System.out.println(teacher);

                            String name5 = teacher.getName();
                            String number5 = teacher.getNumber();
                            String room5 = teacher.getRoomNumber();
                            String email5 = teacher.getEmail();

                            TeacherName5.setText(String.valueOf(name5));
                            TeacherNumber5.setText(String.valueOf(number5));
                            TeacherRoom5.setText(String.valueOf(room5));
                            TeacherEmail5.setText(String.valueOf(email5));

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