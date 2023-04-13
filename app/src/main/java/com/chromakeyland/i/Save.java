package com.chromakeyland.i;

public class Save {
    int w;
    int h;
    float x;
    float y;
    int color;
    int a;
    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setW(int w) {
        this.w = w;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
    public String getColorF(){
        return Integer.toHexString(getColor());
    }
    public int getA() {
        return a;
    }
}
