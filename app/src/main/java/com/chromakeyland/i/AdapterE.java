package com.chromakeyland.i;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Locale;

public class AdapterE {
    int red;
    int b;
    int g;
    public static double lastI;
    public static int i=0;
    public static String main;
    public static StringBuilder cMain=new StringBuilder();
    public static String alphaShader = "#extension GL_OES_EGL_image_external : require\n"
            + "precision mediump float;\n"
            + "varying vec2 vTextureCoord;\n"
            + "uniform samplerExternalOES sTexture;\n"
            + "varying mediump float text_alpha_out;\n"
            + "void main() {\n"
            + "  vec4 color = texture2D(sTexture, vTextureCoord);\n"
            + "  float red = %f;\n"
            + "  float green = %f;\n"
            + "  float blue = %f;\n"
            + "  float accuracy = %f;\n";

           public static String c= " if (abs(color.r - red) <= accuracy && abs(color.g - green) <= accuracy && abs(color.b - blue) <= accuracy" ;
           public static String en=") {\n"
            + "      gl_FragColor = vec4(color.r, color.g, color.b, 0.0);\n"
            + "  } else {\n"
            + "      gl_FragColor = vec4(color.r, color.g, color.b, 1.0);\n"
            + "  }\n";
    static {
        int c=Color.GREEN;
        float redParam = (float) Color.red(c) / 255;
        float greenParam = (float) Color.green(c) / 255;
        float blueParam = (float) Color.blue(c) / 255;
       alphaShader= String.format(Locale.ENGLISH, alphaShader,
                redParam, greenParam, blueParam, 1 - 0.7);
    }
    public static String string ="#extension GL_OES_EGL_image_external : require\n" +
            "            precision mediump float;\n" +
            "            varying vec2 vTextureCoord;\n" +
            "            uniform samplerExternalOES sTexture;\n" +
            "            varying mediump float text_alpha_out;\n" +
            "            void main() {\n" +
            "              vec4 color = texture2D(sTexture, vTextureCoord);\n" +
            "              if (color.g - color.r >= 0.1 &amp;&amp; color.g - color.b >= 0.1) {\n" +
            "                  gl_FragColor = vec4(color.r, (color.r + color.b) / 2.0, color.b, 1.0 - color.g);\n" +
            "              } else {\n" +
            "                  gl_FragColor = vec4(color.r, color.g, color.b, color.a);\n}";
    public static String end= "            }";
public static ArrayList<AdapterE> arrayList=new ArrayList<>();
public static ArrayList<Integer>integers=new ArrayList<>();
    public void setG(int g) {
        this.g = g;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getB() {
        return b;
    }

    public int getG() {
        return g;
    }

    public int getRed() {
        return red;
    }
}
