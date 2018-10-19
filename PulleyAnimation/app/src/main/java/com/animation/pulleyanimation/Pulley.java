package com.animation.pulleyanimation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Pulley extends RelativeLayout {

    LayoutInflater inflater;
    PulleyWheel wheel;
    View leftRope;
    View rightRope;
    TextView leftBlock;
    TextView rightBlock;

    LayoutParams lpw;
    LayoutParams lplr;
    LayoutParams lplb;
    LayoutParams lprr;
    LayoutParams lprb;
    TypedArray a;

    public Pulley(Context context) {
        super(context);
        initViews(context);
    }



    public Pulley(Context context, AttributeSet attrs){
        super(context,attrs);
        initViews(context);
        a = context.getTheme().obtainStyledAttributes(attrs,R.styleable.pulley,0,0);
    }
    private void initViews(final Context context) {
      inflater = LayoutInflater.from(context);
      View view = inflater.inflate(R.layout.pulley_elements,this,true);
      wheel = view.findViewById(R.id.pulley_wheel);
      leftRope = view.findViewById(R.id.left_rope);
      rightRope = view.findViewById(R.id.right_rope);
      leftBlock = view.findViewById(R.id.m_left);
      rightBlock = view.findViewById(R.id.m_right);

      //Log.e("LOL", String.valueOf(""));



        wheel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(context);
            }
        });

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);

        Log.e("LOL", String.valueOf(a.getDimension(R.styleable.pulley_rope_length,100)));
        leftRope.setLayoutParams(new LayoutParams((int) a.getDimension(R.styleable.pulley_rope_width,1),
                (int)( a.getDimension(R.styleable.pulley_rope_length,toPxl(180))*3)/5));
        rightRope.setLayoutParams(new LayoutParams((int) a.getDimension(R.styleable.pulley_rope_width,1),
                (int) (a.getDimension(R.styleable.pulley_rope_length,toPxl(180))*4)/5));
        leftBlock.setLayoutParams(new LayoutParams((int)a.getDimension(R.styleable.pulley_left_block_width,80),
                (int)a.getDimension(R.styleable.pulley_left_block_height,50)));
        rightBlock.setLayoutParams(new LayoutParams((int)a.getDimension(R.styleable.pulley_right_block_width,80),
                (int)a.getDimension(R.styleable.pulley_right_block_height,50)));
        lplr = (LayoutParams) leftRope.getLayoutParams();
        lprr = (LayoutParams) rightRope.getLayoutParams();
        lplb = (LayoutParams) leftBlock.getLayoutParams();
        lprb = (LayoutParams) rightBlock.getLayoutParams();


        wheel.setLayoutParams(new LayoutParams((parentWidth-lplb.width/2-lprb.width/2),(parentWidth-lplb.width/2-lprb.width/2)));
        lpw = (LayoutParams) wheel.getLayoutParams();


        lpw.setMargins(lplb.width/2,toPxl(0),toPxl(0),toPxl(0));
        lplr.setMargins(lplb.width/2,lpw.height/2,0,0);
        lprr.setMargins(lplb.width/2+lpw.width-toPxl(1),lpw.height/2,0,0);
        lplb.setMargins(0,lpw.height/2+lplr.height,0,0);
        lprb.setMargins(lplb.width/2+lpw.width-lprb.width/2,lpw.height/2+lprr.height,0,0);



        this.setMeasuredDimension(parentWidth, parentHeight);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    int toPxl(double dp){
        return (int)(dp*getResources().getDisplayMetrics().density);
    }
    void startAnimation(Context context){
        Animation scale_left,scale_right,translate_left,translate_right;
        scale_left = AnimationUtils.loadAnimation(context,R.anim.scale_left);
        scale_right = AnimationUtils.loadAnimation(context,R.anim.scale_right);
        translate_left = AnimationUtils.loadAnimation(context,R.anim.translate_left);
        translate_right = AnimationUtils.loadAnimation(context,R.anim.translate_right);

        leftRope.startAnimation(scale_left);
        rightRope.startAnimation(scale_right);
        leftBlock.startAnimation(translate_left);
        rightBlock.startAnimation(translate_right);
    }
    void stopAnimation(){
        rightRope.clearAnimation();
        leftRope.clearAnimation();
        leftBlock.clearAnimation();
        rightBlock.clearAnimation();

    }
}
