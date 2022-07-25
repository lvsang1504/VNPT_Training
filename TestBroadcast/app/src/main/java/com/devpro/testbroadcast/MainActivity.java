package com.devpro.testbroadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Broadcast mBroadcast;

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mBroadcast);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setAction("test.Broadcast");
        sendBroadcast(intent);

        mBroadcast = new Broadcast();
        IntentFilter filter = new IntentFilter("test.Broadcast");
        registerReceiver(mBroadcast, filter);

        Button buttonTest = findViewById(R.id.buttonTest);

        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TestBroadcastActivity.class);
                startActivity(intent);
            }
        });
    }
}