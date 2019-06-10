package com.example.patfinal.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.patfinal.R;
import com.google.firebase.analytics.FirebaseAnalytics;

public class Teacher_Login extends AppCompatActivity {
    EditText password;
    Button sub;
    TextView Wrong;
    String Pass = "pass";
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


        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getText().toString().equals(Pass)) {
                    openTeacher();
                    System.out.println("Test");
                    Wrong.setVisibility(View.INVISIBLE);

                } else {
                    Wrong.setVisibility(View.VISIBLE);
                    System.out.println("yoooo");

                }
            }
        });
    }

    public void openTeacher() {
        Intent intent = new Intent(this, Teacher.class);
        startActivity(intent);

    }


}
