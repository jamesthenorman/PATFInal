package com.example.patfinal.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.patfinal.R;
import com.google.firebase.analytics.FirebaseAnalytics;


public class UserType extends AppCompatActivity {


    private RadioButton radioButton;
    private RadioGroup group;
    private FirebaseAnalytics mFirebaseAnalytics;
    private FloatingActionButton help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        group = findViewById(R.id.radioGroup);

        help = findViewById(R.id.floatingActionButton4);

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHelpScreen();
            }
        });

        Button sub = findViewById(R.id.btnSub);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = group.getCheckedRadioButtonId();

                radioButton = findViewById(radioId);
                switch (radioId) {
                    case R.id.radio_one:
                        openMainScreen();
                        return;
                    case R.id.radio_two:
                        openParentScreen();
                        return;
                    default:
                        Toast.makeText(getApplicationContext(), "Please select an option", Toast.LENGTH_SHORT).show();
                        return;
                }


            }
        });
    }       //Method that runs on the creation of the screen

    public void checkButton(View v) {
        int radioId = group.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

        System.out.println(radioButton.toString());
    }                         //Method to output teh checked button

    private void openMainScreen() {
        Intent intent = new Intent(this, Main_Activity_Student.class);
        startActivity(intent);

    }                           //Method to open teh main screen

    private void openParentScreen() {
        Intent intent = new Intent(this, Main_Activity_Parent.class);
        startActivity(intent);

    }                       //Method to open teh parent screen

    private void openHelpScreen() {
        Intent intent = new Intent(this, Activity_Help.class);
        startActivity(intent);

    }                           //Method to open the help screen
}

