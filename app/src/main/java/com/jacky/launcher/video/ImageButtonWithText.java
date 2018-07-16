package com.jacky.launcher.video;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ImageButtonWithText extends LinearLayout {
    public ImageView imageView;
    public TextView textView;
    public ImageButtonWithText(Context context) {
        super(context);
        imageView = new ImageView(context);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(270, 378));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setBackgroundColor(Color.BLACK);

        textView =new TextView(context);
        textView.setGravity(android.view.Gravity.CENTER_HORIZONTAL);
        textView.setPadding(3, 0, 0, 0);
        textView.setLayoutParams(new ViewGroup.LayoutParams(270, 40));
        textView.setBackgroundColor(Color.BLACK);
            textView.setTextColor(Color.WHITE);

        setClickable(true);
        setFocusable(true);
        setOrientation(LinearLayout.VERTICAL);

        addView(imageView);
        addView(textView);
    }
    public void setText(int resId) {
        textView.setText(resId);
    }
    public void setText(CharSequence buttonText) {
        textView.setText(buttonText);
    }
    public void setTextColor(int color) {
        textView.setTextColor(color);
    }
}
