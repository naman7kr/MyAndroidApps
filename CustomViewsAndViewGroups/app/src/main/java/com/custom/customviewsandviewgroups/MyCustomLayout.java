package com.custom.customviewsandviewgroups;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

class CustomLayout extends RelativeLayout {
    LayoutInflater inflater=null;
    TextView tv;
    public CustomLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }



    public CustomLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomLayout(Context context) {
        super(context);
        init(context);
    }
    void init(Context context){
        inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.custom,this,true);
        tv = findViewById(R.id.custom_tv);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        setMeasuredDimension(100,100);
//    }

}