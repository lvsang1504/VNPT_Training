package com.devpro.train_19_07_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView receiver_msg;
    TextView received_value_object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        receiver_msg = (TextView) findViewById(R.id.received_value_id);
        received_value_object = (TextView) findViewById(R.id.received_value_object);

        Customer myObject = (Customer) getIntent().getSerializableExtra("KEY_NAME");

        String str = "";

        str = getIntent().getStringExtra("message_key");
        if (str != null)
            receiver_msg.setText(str);
        if (myObject != null)
            received_value_object.setText(myObject.printValues());
    }
}