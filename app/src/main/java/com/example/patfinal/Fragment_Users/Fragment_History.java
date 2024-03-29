package com.example.patfinal.Fragment_Users;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.patfinal.Personal_Info;
import com.example.patfinal.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Fragment_History extends Fragment {

    private TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10, tv11, tv12, tv13, tv14, tv15, tv16, tv17, tv18, tv19, tv20, tv21, tv22, tv23, tv24, tv25, tv26, tv27;
    private static String b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, b21, b22, b23, b24, b25, b26, b27;
    private ProgressBar progressBar;
    private DatabaseReference databaseReference;
    private static String[] arr = {"", "", ""};
    TableLayout tableLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_history, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        tableLayout = view.findViewById(R.id.Table);
        tv1 = view.findViewById(R.id.tvh1);
        tv2 = view.findViewById(R.id.tvh2);
        tv3 = view.findViewById(R.id.tvh3);
        tv4 = view.findViewById(R.id.tvh4);
        tv5 = view.findViewById(R.id.tvh5);
        tv6 = view.findViewById(R.id.tvh6);
        tv7 = view.findViewById(R.id.tvh7);
        tv8 = view.findViewById(R.id.tvh8);
        tv9 = view.findViewById(R.id.tvh9);
        tv10 = view.findViewById(R.id.tvh10);
        tv11 = view.findViewById(R.id.tvh11);
        tv12 = view.findViewById(R.id.tvh12);
        tv13 = view.findViewById(R.id.tvh13);
        tv14 = view.findViewById(R.id.tvh14);
        tv15 = view.findViewById(R.id.tvh15);
        tv16 = view.findViewById(R.id.tvh16);
        tv17 = view.findViewById(R.id.tvh17);
        tv18 = view.findViewById(R.id.tvh18);
        tv19 = view.findViewById(R.id.tvh19);
        tv20 = view.findViewById(R.id.tvh20);
        tv21 = view.findViewById(R.id.tvh21);
        tv22 = view.findViewById(R.id.tvh22);
        tv23 = view.findViewById(R.id.tvh23);
        tv24 = view.findViewById(R.id.tvh24);
        tv25 = view.findViewById(R.id.tvh25);
        tv26 = view.findViewById(R.id.tvh26);
        tv27 = view.findViewById(R.id.tvh27);

        progressBar = view.findViewById(R.id.progressBar);
        download();


        return view;
    }

    //Method to return the date for the table
    private String getDate(int x) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("LLL-dd ");

        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int y = 0;
        switch (day) {
            case Calendar.MONDAY:
                break;
            case Calendar.TUESDAY:
                y = 1;
                break;
            case Calendar.WEDNESDAY:
                y = 2;
                break;
            case Calendar.THURSDAY:
                y = 3;
                break;
            case Calendar.FRIDAY:
                y = 4;
                break;
            case Calendar.SATURDAY:
                y = 5;
                break;
            case Calendar.SUNDAY:
                y = 6;
                break;


        }

        calendar.add(Calendar.DAY_OF_MONTH, -1 - y - x);


        return mdformat.format(calendar.getTime());
    }

    //Downloads all the data and puts them into a readable format on the table
    private boolean download() {
        String[] arr = getWeeks();
        System.out.println(arr[0]);

        final String text1 = arr[0];
        final String text2 = arr[1];
        final String text3 = arr[2];
        final String text4 = arr[3];

        databaseReference.child("Users").child(Personal_Info.getSchool_number()).child("User History").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try {

                    b1 = dataSnapshot.child(text1).child("monday").getValue().toString();
                    b2 = dataSnapshot.child(text1).child("tuesday").getValue().toString();
                    b3 = dataSnapshot.child(text1).child("wednesday").getValue().toString();
                    b4 = dataSnapshot.child(text1).child("thursday").getValue().toString();
                    b5 = dataSnapshot.child(text1).child("friday").getValue().toString();
                    b6 = dataSnapshot.child(text1).child("saturday").getValue().toString();
                    b7 = dataSnapshot.child(text1).child("sunday").getValue().toString();
                } catch (Exception e) {
                    b1 = getResources().getString(R.string.No_data);
                    b2 = getResources().getString(R.string.No_data);
                    b3 = getResources().getString(R.string.No_data);
                    b4 = getResources().getString(R.string.No_data);
                    b5 = getResources().getString(R.string.No_data);
                    b6 = getResources().getString(R.string.No_data);
                    b7 = getResources().getString(R.string.No_data);
                }
                try {
                    b8 = dataSnapshot.child(text2).child("monday").getValue().toString();
                    b9 = dataSnapshot.child(text2).child("tuesday").getValue().toString();
                    b10 = dataSnapshot.child(text2).child("wednesday").getValue().toString();
                    b11 = dataSnapshot.child(text2).child("thursday").getValue().toString();
                    b12 = dataSnapshot.child(text2).child("friday").getValue().toString();
                    b13 = dataSnapshot.child(text2).child("saturday").getValue().toString();
                    b14 = dataSnapshot.child(text2).child("sunday").getValue().toString();
                } catch (Exception e) {
                    b8 = getResources().getString(R.string.No_data);
                    b9 = getResources().getString(R.string.No_data);
                    b10 = getResources().getString(R.string.No_data);
                    b11 = getResources().getString(R.string.No_data);
                    b12 = getResources().getString(R.string.No_data);
                    b13 = getResources().getString(R.string.No_data);
                    b14 = getResources().getString(R.string.No_data);
                }
                try {
                    b15 = dataSnapshot.child(text3).child("monday").getValue().toString();
                    b16 = dataSnapshot.child(text3).child("tuesday").getValue().toString();
                    b17 = dataSnapshot.child(text3).child("wednesday").getValue().toString();
                    b18 = dataSnapshot.child(text3).child("thursday").getValue().toString();
                    b19 = dataSnapshot.child(text3).child("friday").getValue().toString();
                    b20 = dataSnapshot.child(text3).child("saturday").getValue().toString();
                    b21 = dataSnapshot.child(text3).child("sunday").getValue().toString();
                } catch (Exception e) {
                    b15 = getResources().getString(R.string.No_data);
                    b16 = getResources().getString(R.string.No_data);
                    b17 = getResources().getString(R.string.No_data);
                    b18 = getResources().getString(R.string.No_data);
                    b19 = getResources().getString(R.string.No_data);
                    b20 = getResources().getString(R.string.No_data);
                    b21 = getResources().getString(R.string.No_data);
                }
                try {
                    b22 = dataSnapshot.child(text4).child("monday").getValue().toString();
                    b23 = dataSnapshot.child(text4).child("tuesday").getValue().toString();
                    b24 = dataSnapshot.child(text4).child("wednesday").getValue().toString();
                    b25 = dataSnapshot.child(text4).child("thursday").getValue().toString();
                    b26 = dataSnapshot.child(text4).child("friday").getValue().toString();
                    b27 = dataSnapshot.child(text4).child("saturday").getValue().toString();


                } catch (Exception e) {

                    b22 = getResources().getString(R.string.No_data);
                    b23 = getResources().getString(R.string.No_data);
                    b24 = getResources().getString(R.string.No_data);
                    b25 = getResources().getString(R.string.No_data);
                    b26 = getResources().getString(R.string.No_data);
                    b27 = getResources().getString(R.string.No_data);
                }
                switch (b1) {
                    case "true":
                        b1 = getResources().getString(R.string.In);
                        break;
                    case "false":
                        b1 = getResources().getString(R.string.Out);
                        break;
                }
                switch (b2) {
                    case "true":
                        b2 = getResources().getString(R.string.In);
                        break;
                    case "false":
                        b2 = getResources().getString(R.string.Out);
                        break;

                }
                switch (b3) {
                    case "true":
                        b3 = getResources().getString(R.string.In);
                        break;
                    case "false":
                        b3 = getResources().getString(R.string.Out);
                        break;

                }
                switch (b4) {
                    case "true":
                        b4 = getResources().getString(R.string.In);
                        break;
                    case "false":
                        b4 = getResources().getString(R.string.Out);
                        break;

                }
                switch (b5) {
                    case "true":
                        b5 = getResources().getString(R.string.In);
                        break;
                    case "false":
                        b5 = getResources().getString(R.string.Out);
                        break;

                }
                switch (b6) {
                    case "true":
                        b6 = getResources().getString(R.string.In);
                        break;
                    case "false":
                        b6 = getResources().getString(R.string.Out);
                        break;

                }
                switch (b7) {
                    case "true":
                        b7 = getResources().getString(R.string.In);
                        break;
                    case "false":
                        b7 = getResources().getString(R.string.Out);
                        break;

                }
                switch (b8) {
                    case "true":
                        b8 = getResources().getString(R.string.In);
                        break;
                    case "false":
                        b8 = getResources().getString(R.string.Out);
                        break;
                }

                switch (b9) {
                    case "true":
                        b9 = getResources().getString(R.string.In);
                        break;
                    case "false":
                        b9 = getResources().getString(R.string.Out);
                        break;

                }
                switch (b10) {
                    case "true":
                        b10 = getResources().getString(R.string.In);
                        break;
                    case "false":
                        b10 = getResources().getString(R.string.Out);
                        break;

                }
                switch (b11) {
                    case "true":
                        b11 = getResources().getString(R.string.In);
                        break;
                    case "false":
                        b11 = getResources().getString(R.string.Out);
                        break;

                }
                switch (b12) {
                    case "true":
                        b12 = getResources().getString(R.string.In);
                        break;
                    case "false":
                        b12 = getResources().getString(R.string.Out);
                        break;

                }
                switch (b13) {
                    case "true":
                        b13 = getResources().getString(R.string.In);
                        break;
                    case "false":
                        b13 = getResources().getString(R.string.Out);
                        break;

                }
                switch (b14) {
                    case "true":
                        b14 = getResources().getString(R.string.In);
                        break;
                    case "false":
                        b14 = getResources().getString(R.string.Out);
                        break;

                }
                switch (b15) {
                    case "true":
                        b15 = getResources().getString(R.string.In);
                        break;
                    case "false":
                        b15 = getResources().getString(R.string.Out);
                        break;

                }
                switch (b16) {
                    case "true":
                        b16 = getResources().getString(R.string.In);
                        break;
                    case "false":
                        b16 = getResources().getString(R.string.Out);
                        break;

                }
                switch (b17) {
                    case "true":
                        b17 = getResources().getString(R.string.In);
                        break;
                    case "false":
                        b17 = getResources().getString(R.string.Out);
                        break;

                }
                switch (b18) {
                    case "true":
                        b18 = getResources().getString(R.string.In);
                        break;
                    case "false":
                        b18 = getResources().getString(R.string.Out);
                        break;

                }
                switch (b19) {
                    case "true":
                        b19 = getResources().getString(R.string.In);
                        break;
                    case "false":
                        b19 = getResources().getString(R.string.Out);
                        break;

                }
                switch (b20) {
                    case "true":
                        b20 = getResources().getString(R.string.In);
                        break;
                    case "false":
                        b20 = getResources().getString(R.string.Out);
                        break;

                }
                switch (b21) {
                    case "true":
                        b21 = getResources().getString(R.string.In);
                        break;
                    case "false":
                        b21 = getResources().getString(R.string.Out);
                        break;

                }
                switch (b22) {
                    case "true":
                        b22 = getResources().getString(R.string.In);
                        break;
                    case "false":
                        b22 = getResources().getString(R.string.Out);
                        break;

                }
                switch (b23) {
                    case "true":
                        b23 = getResources().getString(R.string.In);
                        break;
                    case "false":
                        b23 = getResources().getString(R.string.Out);
                        break;

                }
                switch (b24) {
                    case "true":
                        b24 = getResources().getString(R.string.In);
                        break;
                    case "false":
                        b24 = getResources().getString(R.string.Out);
                        break;

                }
                switch (b25) {
                    case "true":
                        b25 = getResources().getString(R.string.In);
                        break;
                    case "false":
                        b25 = getResources().getString(R.string.Out);
                        break;

                }
                switch (b26) {
                    case "true":
                        b26 = getResources().getString(R.string.In);
                        break;
                    case "false":
                        b26 = getResources().getString(R.string.Out);
                        break;

                }
                switch (b27) {
                    case "true":
                        b27 = getResources().getString(R.string.In);
                        break;
                    case "false":
                        b27 = getResources().getString(R.string.Out);
                        break;

                }
                int x = 0;

                tv1.setText(getDate(x) + "\n" + b1);
                x++;
                tv2.setText(getDate(x) + "\n" + b2);
                x++;
                tv3.setText(getDate(x) + "\n" + b3);
                x++;
                tv4.setText(getDate(x) + "\n" + b4);
                x++;
                tv5.setText(getDate(x) + "\n" + b5);
                x++;
                tv6.setText(getDate(x) + "\n" + b6);
                x++;
                tv7.setText(getDate(x) + "\n" + b7);
                x++;
                tv8.setText(getDate(x) + "\n" + b8);
                x++;
                tv9.setText(getDate(x) + "\n" + b9);
                x++;
                tv10.setText(getDate(x) + "\n" + b10);
                x++;
                tv11.setText(getDate(x) + "\n" + b11);
                x++;
                tv12.setText(getDate(x) + "\n" + b12);
                x++;
                tv13.setText(getDate(x) + "\n" + b13);
                x++;
                tv14.setText(getDate(x) + "\n" + b14);
                x++;
                tv15.setText(getDate(x) + "\n" + b15);
                x++;
                tv16.setText(getDate(x) + "\n" + b16);
                x++;
                tv17.setText(getDate(x) + "\n" + b17);
                x++;
                tv18.setText(getDate(x) + "\n" + b18);
                x++;
                tv19.setText(getDate(x) + "\n" + b19);
                x++;
                tv20.setText(getDate(x) + "\n" + b20);
                x++;
                tv21.setText(getDate(x) + "\n" + b21);
                x++;
                tv22.setText(getDate(x) + "\n" + b22);
                x++;
                tv23.setText(getDate(x) + "\n" + b23);
                x++;
                tv24.setText(getDate(x) + "\n" + b24);
                x++;
                tv25.setText(getDate(x) + "\n" + b25);
                x++;
                tv26.setText(getDate(x) + "\n" + b26);
                x++;
                tv27.setText(getDate(x) + "\n" + b27);

                databaseReference.removeEventListener(this);
                progressBar.setVisibility(View.INVISIBLE);
                tableLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return true;
    }

    //Method to input the next 3 weeks into a array
    private static String[] getWeeks() {
        String[] array = {"", "", "", ""};
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int x = 0;
        switch (day) {
            case Calendar.MONDAY:

                break;
            case Calendar.TUESDAY:
                x = 1;
                break;
            case Calendar.WEDNESDAY:
                x = 2;
                break;
            case Calendar.THURSDAY:
                x = 3;
                break;
            case Calendar.FRIDAY:
                x = 4;
                break;
            case Calendar.SATURDAY:
                x = 5;
                break;
            case Calendar.SUNDAY:
                x = 6;
                break;


        }
        SimpleDateFormat mdformat = new SimpleDateFormat("LLLL-dd ");
        calendar.add(Calendar.DAY_OF_MONTH, -1 - x);
        String one = mdformat.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, -6);
        String two = mdformat.format(calendar.getTime());
        array[0] = two + " - " + one;
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        one = mdformat.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, -6);
        two = mdformat.format(calendar.getTime());
        array[1] = two + " - " + one;
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        one = mdformat.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, -6);
        two = mdformat.format(calendar.getTime());
        array[2] = two + " - " + one;
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        one = mdformat.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, -6);
        two = mdformat.format(calendar.getTime());
        array[3] = two + " - " + one;
        System.out.println(arr[0]);
        return array;
    }


}