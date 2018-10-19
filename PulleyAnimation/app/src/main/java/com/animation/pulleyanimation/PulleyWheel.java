package com.animation.pulleyanimation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static java.util.Collections.max;

public class PulleyWheel extends View {
    Paint fill_brush,stroke_brush;
     int  mWidth, mHeight;
     float mRadius;
    Context context;
    AttributeSet attrs;

    public PulleyWheel(Context context, AttributeSet attrs) {
        super(context,attrs);
        this.context = context;
        this.attrs = attrs;

        setBrush();
    }

    private void setBrush() {

        fill_brush = new Paint();
        fill_brush.setColor(Color.parseColor("#3A3A3A"));
        fill_brush.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        mWidth = getMeasuredWidth()/2;
        mHeight = getMeasuredHeight()/2;

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,R.styleable.PulleyWheel,0,0);
        mRadius = a.getFloat(R.styleable.PulleyWheel_radius,Math.max(mWidth,mHeight));
//        while(true){
//            Log.e("TODP", String.valueOf(getMeasuredWidth()));
//            if(txt_size>getMeasuredWidth()){
//                break;
//            }
//            txt_size++;
//        }

      //  stroke_brush.setTextSize(txt_size);
        canvas.drawCircle(mWidth,mHeight,mRadius,fill_brush);
        invalidate();
    }

}