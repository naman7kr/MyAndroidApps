package com.animation.pulleyanimation;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Pulley extends SurfaceView implements Runnable {
    Thread thread = null;
    boolean canDraw = false;
    Canvas canvas;
    float mWidth, mHeight,w_width,w_height,lb_width,lb_height,rb_width,rb_height;
    Paint fill_brush, stroke_brush;
    Context context;
    SurfaceHolder surfaceHolder;
    TypedArray a;
    public Pulley(Context context, AttributeSet attrs) {
        super(context,attrs);
        surfaceHolder = getHolder();
        a = context.getTheme().obtainStyledAttributes(attrs,R.styleable.pulley,0,0);
        this.context = context;


    }

    @Override
    public void run() {
        prepBrush();
        while(canDraw){
            if(!surfaceHolder.getSurface().isValid()){
                continue;
            }
            canvas = surfaceHolder.lockCanvas();


           // Log.e("LBW", String.valueOf(a.getDimensionPixelSize(R.styleable.pulley_left_block_width,50)));
            lb_width = a.getDimension(R.styleable.pulley_left_block_width,toPxl(50));
            //lb_width =toPxl(50);
            lb_height = a.getDimension(R.styleable.pulley_left_block_height,toPxl(50));
           // lb_height =toPxl(50);

            rb_width = a.getDimension(R.styleable.pulley_right_block_width,toPxl(50));
           // rb_width =toPxl(50);
            rb_height = a.getDimension(R.styleable.pulley_right_block_height,toPxl(50));
            //rb_height =toPxl(50);


            mWidth = getWidth()-lb_width/2 - rb_width/2;
            mHeight = getHeight();

            drawRopes();
            drawPulley();
            movePulley();
            surfaceHolder.unlockCanvasAndPost(canvas);

        }
    }

    private void movePulley() {

    }

    private void drawRopes() {
        canvas.drawLine(lb_width/2,mWidth/2,lb_width/2,mHeight-lb_height,stroke_brush);
        canvas.drawLine(lb_width/2+mWidth,mWidth/2,lb_width/2+mWidth,mHeight/2-rb_height/2 +mWidth/2,stroke_brush);
    }

    private void prepBrush() {
        fill_brush = new Paint();
        fill_brush.setColor(Color.parseColor("#3A3A3A"));
        fill_brush.setStyle(Paint.Style.FILL_AND_STROKE);

        stroke_brush = new Paint();
        stroke_brush.setColor(Color.BLACK);
        stroke_brush.setStyle(Paint.Style.STROKE);
        stroke_brush.setStrokeWidth(5);
    }

    private void drawPulley() {


        Path pulleyPath = new Path();
        pulleyPath.moveTo(lb_width+mWidth/2,mWidth/2);
        pulleyPath.addCircle(lb_width/2+mWidth/2,mWidth/2,mWidth/2,Path.Direction.CW);
        pulleyPath.moveTo(lb_width/2,mWidth/2);
        pulleyPath.lineTo(lb_width/2,mHeight-lb_height);
        pulleyPath.addRect(0,mHeight-lb_height,lb_width,mHeight,Path.Direction.CW);

        pulleyPath.moveTo(mWidth+lb_width/2,mWidth/2);
        pulleyPath.lineTo(mWidth+lb_width/2,mHeight/2+mWidth/2-rb_height/2);
        pulleyPath.addRect(lb_width/2+mWidth-rb_width/2,mHeight/2-rb_height/2 +mWidth/2,
                mWidth+lb_width+rb_width,mHeight/2 + rb_height/2+mWidth/2,Path.Direction.CW);
        canvas.drawPath(pulleyPath,fill_brush);
    }

    int toPxl(int dp){
        return (int)(dp*getResources().getDisplayMetrics().density);
    }
    public void onPause(){
        canDraw = false;
        while(true) {
            try {
                thread.join();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        thread = null;
    }
    public void onResume(){
        canDraw = true;
        thread = new Thread(this);
        thread.start();
    }
}
