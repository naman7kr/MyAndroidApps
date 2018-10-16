package com.animation.pulleyanimation;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Main2Activity extends AppCompatActivity {
    Pulley p1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        p1 = findViewById(R.id.pulley1);

    }

    @Override
    protected void onPause() {
        super.onPause();
        p1.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        p1.onResume();
    }
}
