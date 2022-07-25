package com.devpro.a20_07_2022.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.devpro.a20_07_2022.R;

public class TestBroadcastActivity extends AppCompatActivity {
//     Broadcast mBroadcast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_broadcast);

//        mBroadcast = new Broadcast();
//        IntentFilter filter = new IntentFilter("test.Broadcast");
//        registerReceiver(mBroadcast, filter);
//
//        Intent intent = new Intent();
//        intent.setAction("test.Broadcast");
//        sendBroadcast(intent);

    }

    @Override
    protected void onStop() {
        super.onStop();
//        unregisterReceiver(mBroadcast);
    }
}