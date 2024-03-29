package com.example.patfinal.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.patfinal.Fragment_Teacher.Fragment_Add_User;
import com.example.patfinal.Fragment_Teacher.Fragment_Teacher_Check;
import com.example.patfinal.R;
import com.google.firebase.analytics.FirebaseAnalytics;

public class Teacher extends AppCompatActivity {
    private TextView mTextMessage;
    private FirebaseAnalytics mFirebaseAnalytics;
    private FloatingActionButton help;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    FragmentTransaction fr = getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, new Fragment_Add_User());
                    fr.commit();
                    return true;
                case R.id.navigation_dashboard:
                    FragmentTransaction fr1 = getSupportFragmentManager().beginTransaction();
                    fr1.replace(R.id.fragment_container, new Fragment_Teacher_Check());
                    fr1.commit();
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        setContentView(R.layout.activity_teacher);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setSelectedItemId(R.id.navigation_home);
        help = findViewById(R.id.floatingActionButton3);

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHelpScreen();
            }
        });

    }               //Method to run on the creation of the screen

    private void openHelpScreen() {
        Intent intent = new Intent(this, Activity_Help.class);
        startActivity(intent);

    }                                   //Method to open the help screen

}
