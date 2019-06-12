package com.example.patfinal.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patfinal.Fragment_Users.Fragment_Contact_Us;
import com.example.patfinal.Fragment_Users.Fragment_Fire;
import com.example.patfinal.Fragment_Users.Fragment_History;
import com.example.patfinal.Fragment_Users.Fragment_Lightning;
import com.example.patfinal.Fragment_Users.Fragment_Personal_Details;
import com.example.patfinal.Fragment_Users.Fragment_Teacher_Contact;
import com.example.patfinal.Fragment_Users.Fragment_emergency_numbers;
import com.example.patfinal.Fragment_Users.Fragmet_parent_consent;
import com.example.patfinal.Personal_Info;
import com.example.patfinal.R;
import com.google.firebase.analytics.FirebaseAnalytics;

public class Main_Activity_Parent extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    static BottomNavigationView bottomnav;
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {

                        case R.id.nav_teacher_contact:
                            selectedFragment = new Fragment_Teacher_Contact();
                            break;

                        case R.id.nav_consent:
                            selectedFragment = new Fragmet_parent_consent();
                            break;

                        case R.id.nav_personal_details:
                            selectedFragment = new Fragment_Personal_Details();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    static public void deselect() {
        for (int i = 0; i < 4; i++) {
            bottomnav.getMenu().getItem(i).setChecked(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        setContentView(R.layout.activity_main_parent);
        bottomnav = findViewById(R.id.bottom_navigation);
        bottomnav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragmet_parent_consent()).commit();
        bottomnav.setSelectedItemId(R.id.nav_consent);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView tvname = (TextView) findViewById(R.id.name);
        tvname.setText(Personal_Info.getName() + " Parent");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.History:
                FragmentTransaction fr = getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Fragment_History());
                fr.commit();
                deselect();
                return true;

            case R.id.ICE:
                FragmentTransaction fr1 = getSupportFragmentManager().beginTransaction();
                fr1.replace(R.id.fragment_container, new Fragment_emergency_numbers());
                fr1.commit();
                deselect();
                return true;

            case R.id.Lightning:
                FragmentTransaction fr2 = getSupportFragmentManager().beginTransaction();
                fr2.replace(R.id.fragment_container, new Fragment_Lightning());
                fr2.commit();
                deselect();
                return true;

            case R.id.Fire:
                FragmentTransaction fr3 = getSupportFragmentManager().beginTransaction();
                fr3.replace(R.id.fragment_container, new Fragment_Fire());
                fr3.commit();
                deselect();
                return true;

            case R.id.Contact:
                FragmentTransaction fr4 = getSupportFragmentManager().beginTransaction();
                fr4.replace(R.id.fragment_container, new Fragment_Contact_Us());
                fr4.commit();
                deselect();
                return true;

            case R.id.Help:
                openHelpScreen();
                deselect();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void openHelpScreen() {
        Intent intent = new Intent(this, Activity_Help.class);
        startActivity(intent);

    }


}