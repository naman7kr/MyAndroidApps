package com.animation.pulleyanimation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import static java.util.Collections.max;

public class PulleyWheel extends View {
    Paint fill_brush;
    int mWidth, mHeight;
    float mRadius;
    Context context;
    AttributeSet attrs;

    public PulleyWheel(Context context, AttributeSet attrs) {
        super(context,attrs);
        this.context = context;
        this.attrs = attrs;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,R.styleable.PulleyWheel,0,0);
        mRadius = a.getFloat(R.styleable.PulleyWheel_radius,max(mWidth,mHeight));
        fill_brush = new Paint();
        mWidth = getMeasuredWidth()/2;
        mHeight = getMeasuredHeight()/2;

        fill_brush.setColor(Color.parseColor("#3A3A3A"));
        canvas.drawCircle(mWidth,mHeight,mRadius,fill_brush);
        invalidate();
    }

    private float max(int mWidth, int mHeight) {
        return (mWidth>mHeight?mWidth:mHeight);
    }
}
