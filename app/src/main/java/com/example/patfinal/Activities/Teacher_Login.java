package com.example.patfinal.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.patfinal.R;
import com.google.firebase.analytics.FirebaseAnalytics;

public class Teacher_Login extends AppCompatActivity {
    private EditText password;
    private Button sub;
    private TextView Wrong;
    private String Pass = "pass";
    private FloatingActionButton help;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        password = findViewById(R.id.password);
        sub = findViewById(R.id.buttonSub);
        Wrong = findViewById(R.id.wrong);
        System.out.println(password.getText().toString());
        help = findViewById(R.id.floatingActionButton2);

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHelpScreen();
            }
        });


        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getText().toString().equals(Pass)) {
                    openTeacher();
                    Wrong.setVisibility(View.INVISIBLE);

                } else {
                    Wrong.setVisibility(View.VISIBLE);

                }
            }
        });
    }                   //Runs on the creation of the screen

    private void openTeacher() {
        Intent intent = new Intent(this, Teacher.class);
        startActivity(intent);

    }                                           //Method to open the teacher screen

    private void openHelpScreen() {
        Intent intent = new Intent(this, Activity_Help.class);
        startActivity(intent);

    }                                        //Method to open teh help screen


}
