package com.myservices.services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button start,stop,switchActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        findViewById(R.id.switch_activity).setOnClickListener(this);
        findViewById(R.id.start_alarm).setOnClickListener(this);
        findViewById(R.id.stop_alarm).setOnClickListener(this);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.start){
            Intent  intent = new Intent(this , NotificationService.class);
            startService(intent);
        }
        else if(v.getId() == R.id.start_alarm){
            Intent intent = new Intent(this, AlarmService.class);
            startService(intent);
        }
        else if(v.getId() == R.id.switch_activity){
            startActivity(new Intent(this, TempActivity.class));
        }
        else if(v.getId() == R.id.stop_alarm){
            Intent intent = new Intent(this, AlarmService.class);
            stopService(intent);
        }
        else{
            Intent intent =new Intent(this, NotificationService.class);
            stopService(intent);
        }
    }
}
