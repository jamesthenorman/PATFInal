package com.example.patfinal.Fragment_Users;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.patfinal.R;


public class Fragment_Lightning extends Fragment {

    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lightning, container, false);
        imageView = view.findViewById(R.id.imageView2);
        String url = "https://firebasestorage.googleapis.com/v0/b/patfinal-329c0.appspot.com/o/Lightning.jpg?alt=media&token=327137a9-e840-4615-ae19-65023e5478e9";
        Glide.with(getActivity()).load(url).into(imageView);

        return view;
    }
}

