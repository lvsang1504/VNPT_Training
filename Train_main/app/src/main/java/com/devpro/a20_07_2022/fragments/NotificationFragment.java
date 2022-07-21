package com.devpro.a20_07_2022.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.devpro.a20_07_2022.R;
import com.devpro.a20_07_2022.activities.DemoActivity;

public class NotificationFragment extends Fragment {

    TextView button;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification,
                container, false);
        Log.d("AAAAA", "DDDDD");
        button = view.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("AAAAA", "DDDDD");
                Intent intent = new Intent(getContext(), DemoActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}