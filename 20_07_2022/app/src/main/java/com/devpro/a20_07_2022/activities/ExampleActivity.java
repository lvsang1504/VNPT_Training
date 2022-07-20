package com.devpro.a20_07_2022.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.devpro.a20_07_2022.R;
import com.devpro.a20_07_2022.fragments.NotificationFragment;

public class ExampleActivity extends FragmentActivity implements NotificationFragment.FirstFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
    }

    @Override
    public void onButtonClick(int fontsize, String text) {
        
    }
}