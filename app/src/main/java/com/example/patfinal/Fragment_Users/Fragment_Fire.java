package com.example.patfinal.Fragment_Users;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.patfinal.R;

public class Fragment_Fire extends Fragment {
    private ProgressBar progressBar;
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fire, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        imageView = view.findViewById(R.id.imageView2);

        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/patfinal-329c0.appspot.com/o/Fire.jpeg?alt=media&token=ee1e5697-6103-49fa-8777-ab40c4e22a05")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);


        return view;
    }
}

