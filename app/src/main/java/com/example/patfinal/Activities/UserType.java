package com.example.patfinal.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.patfinal.R;
import com.google.firebase.analytics.FirebaseAnalytics;


public class UserType extends AppCompatActivity {


    RadioButton radioButton;
    RadioGroup group;
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        group = findViewById(R.id.radioGroup);

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
    }

    public void checkButton(View v) {
        int radioId = group.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

        System.out.println(radioButton.toString());
    }

    public void openMainScreen() {
        Intent intent = new Intent(this, Main_Activity_Student.class);
        startActivity(intent);

    }

    public void openParentScreen() {
        Intent intent = new Intent(this, Main_Activity_Parent.class);
        startActivity(intent);

    }

}

