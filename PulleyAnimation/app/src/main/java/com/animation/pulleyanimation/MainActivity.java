package com.animation.pulleyanimation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button stop_btn;
    View line_left,line_right;
    PulleyWheel wheel;
    Animation scale_left,scale_right,translate_left,translate_right;
    Canvas canvas=null;
    TextView m_left,m_right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        line_left = findViewById(R.id.left);
        line_right = findViewById(R.id.right);

        stop_btn = findViewById(R.id.stop_btn);
        wheel = findViewById(R.id.pulley_wheel);
        m_left = findViewById(R.id.m_left);
        m_right = findViewById(R.id.m_right);

        stop_btn.setText("stop");
        scale_left = AnimationUtils.loadAnimation(this,R.anim.scale_left);
        scale_right = AnimationUtils.loadAnimation(this,R.anim.scale_right);
        translate_left = AnimationUtils.loadAnimation(this,R.anim.translate_left);
        translate_right = AnimationUtils.loadAnimation(this,R.anim.translate_right);
        createSubmitButton();


        wheel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                line_left.startAnimation(scale_left);
                line_right.startAnimation(scale_right);
                m_left.startAnimation(translate_left);
                m_right.startAnimation(translate_right);


            }
        });
        stop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                line_left.clearAnimation();
                line_right.clearAnimation();
                m_left.clearAnimation();
                m_right.clearAnimation();
            }
        });

    }

    private void createSubmitButton() {
        Paint p1 = new Paint();
        Paint p2 = new Paint();
        p1.setColor(Color.parseColor("#000000"));
        p1.setStyle(Paint.Style.FILL);
//        p2.setColor(Color.parseColor("#ffffff"));
//        p2.setStyle(Paint.Style.STROKE);

        canvas = new Canvas();
        canvas.drawCircle(100,100,50,p1);
        //canvas.drawText("Submit",50,10,p2);
        wheel.draw(canvas);
       //wheel.invalidate();
    }
}
