package com.chromakeyland.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

import com.skydoves.colorpickerview.ColorPickerView;

public class EyeDropperx extends ColorPickerView {
    public int color;
    public EyeDropperx(Context context) {
        super(context);
    }
    public EyeDropperx(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
    }
    public EyeDropperx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor2() {
        return color;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public EyeDropperx(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }
}
