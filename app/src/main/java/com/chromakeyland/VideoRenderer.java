/*
 * Copyright 2017 Pavel Semak
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.chromakeyland;

import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;
import android.view.Surface;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

class VideoRenderer implements GLTextureView.Renderer, SurfaceTexture.OnFrameAvailableListener {
    private static String TAG = "VideoRender";
public static int i=0;
    private final Queue<Runnable> runOnDraw;
    private AlphaMovieView alphaMovieView;
    private static final int COLOR_MAX_VALUE = 255;
    public static boolean deletprogram=false;
    private static final int FLOAT_SIZE_BYTES = 4;
    private static final int TRIANGLE_VERTICES_DATA_STRIDE_BYTES = 5 * FLOAT_SIZE_BYTES;
    private static final int TRIANGLE_VERTICES_DATA_POS_OFFSET = 0;
    private static final int TRIANGLE_VERTICES_DATA_UV_OFFSET = 3;
    private final float[] triangleVerticesData = {
            // X, Y, Z, U, V
            -1.0f, -1.0f, 0, 0.f, 0.f,
            1.0f, -1.0f, 0, 1.f, 0.f,
            -1.0f,  1.0f, 0, 0.f, 1.f,
            1.0f,  1.0f, 0, 1.f, 1.f,
    };

    private FloatBuffer triangleVertices;
private int vertexShaderI,pixelShaderI;
    public final String vertexShader =
            "uniform mat4 uMVPMatrix;\n" +
                    "uniform mat4 uSTMatrix;\n" +
                    "attribute vec4 aPosition;\n" +
                    "attribute vec4 aTextureCoord;\n" +
                    "varying vec2 vTextureCoord;\n" +
                    "void main() {\n" +
                    "  gl_Position = uMVPMatrix * aPosition;\n" +
                    "  vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n" +
                    "}\n";

    public final String alphaShader = "#extension GL_OES_EGL_image_external : require\n"
            + "precision mediump float;\n"
            + "varying vec2 vTextureCoord;\n"
            + "uniform samplerExternalOES sTexture;\n"
            + "varying mediump float text_alpha_out;\n"
            + "void main() {\n"
            + "  vec4 color = texture2D(sTexture, vTextureCoord);\n"
            + "  float red = %f;\n"
            + "  float green = %f;\n"
            + "  float blue = %f;\n"
            + "  float accuracy = %f;\n"
            + "  if (abs(color.r - red) <= accuracy && abs(color.g - green) <= accuracy && abs(color.b - blue) <= accuracy) {\n"
            + "      gl_FragColor = vec4(color.r, color.g, color.b, 0.0);\n"
            + "  } else {\n"
            + "      gl_FragColor = vec4(color.r, color.g, color.b, 1.0);\n"
            + "  }\n"
            + "}\n";

    private double accuracy = 0.95;

    private String shader = alphaShader;

    private float[] mVPMatrix = new float[16];
    private float[] sTMatrix = new float[16];

    private int program;
    private int textureID;
    private int uMVPMatrixHandle;
    private int uSTMatrixHandle;
    private int aPositionHandle;
    private int aTextureHandle;
private GLTextureView glTextureView;
    private SurfaceTexture surface;
    private boolean updateSurface = false;
boolean updateC=false;
    private static int GL_TEXTURE_EXTERNAL_OES = 0x8D65;

    private OnSurfacePrepareListener onSurfacePrepareListener;

    private boolean isCustom;

    private float redParam = 0.0f;
    private float greenParam = 1.0f;
    private float blueParam = 0.0f;

    VideoRenderer() {

        triangleVertices = ByteBuffer.allocateDirect(
                triangleVerticesData.length * FLOAT_SIZE_BYTES)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        triangleVertices.put(triangleVerticesData).position(0);

        Matrix.setIdentityM(sTMatrix, 0);
        runOnDraw = new LinkedList<>();
    }

    public void setGlTextureView(GLTextureView glTextureView) {
        this.glTextureView = glTextureView;
    }
    protected void runOnDraw(final Runnable runnable) {
        synchronized (runOnDraw) {
            runOnDraw.add(runnable);
        }
    }
    @Override
    public void onDrawFrame(GL10 glUnused) {
        synchronized(this) {
            if (updateSurface) {
                surface.updateTexImage();
                surface.getTransformMatrix(sTMatrix);
                updateSurface = false;
            }
        }
        if (updateC){
          // GLES20.glDeleteProgram(program);
          // program=0;
          // GLES20.glDeleteShader(vertexShaderI);
          // vertexShaderI=0;
          // GLES20.glDeleteShader(pixelShaderI);
          // pixelShaderI=0;
            createProgram(vertexShader,resolveShader());
            updateC=false;
        }
        runAll(runOnDraw);
     //   Log.e("on","Frame");
        GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);

        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        if (deletprogram)GLES20.glDeleteProgram(program);
        GLES20.glUseProgram(program);
        checkGlError("glUseProgram");

        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GL_TEXTURE_EXTERNAL_OES, textureID);

        triangleVertices.position(TRIANGLE_VERTICES_DATA_POS_OFFSET);
        GLES20.glVertexAttribPointer(aPositionHandle, 3, GLES20.GL_FLOAT, false,
                TRIANGLE_VERTICES_DATA_STRIDE_BYTES, triangleVertices);
        checkGlError("glVertexAttribPointer maPosition");
        GLES20.glEnableVertexAttribArray(aPositionHandle);
        checkGlError("glEnableVertexAttribArray aPositionHandle");

        triangleVertices.position(TRIANGLE_VERTICES_DATA_UV_OFFSET);
        GLES20.glVertexAttribPointer(aTextureHandle, 3, GLES20.GL_FLOAT, false,
                TRIANGLE_VERTICES_DATA_STRIDE_BYTES, triangleVertices);
        checkGlError("glVertexAttribPointer aTextureHandle");
        GLES20.glEnableVertexAttribArray(aTextureHandle);
        checkGlError("glEnableVertexAttribArray aTextureHandle");

        Matrix.setIdentityM(mVPMatrix, 0);
        GLES20.glUniformMatrix4fv(uMVPMatrixHandle, 1, false, mVPMatrix, 0);
        GLES20.glUniformMatrix4fv(uSTMatrixHandle, 1, false, sTMatrix, 0);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
        checkGlError("glDrawArrays");

        GLES20.glFinish();
    }

    @Override
    public void onSurfaceDestroyed(GL10 gl) {
    }

    @Override
    public void onSurfaceChanged(GL10 glUnused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        Log.e("on","surfaceChanged");
    }

    @Override
    public void onSurfaceCreated(GL10 glUnused, EGLConfig config) {
        Log.e("on","surfaceCreated");
        program = createProgram(vertexShader, this.resolveShader());
        if (program == 0) {
            return;
        }
        aPositionHandle = GLES20.glGetAttribLocation(program, "aPosition");
        checkGlError("glGetAttribLocation aPosition");
        if (aPositionHandle == -1) {
            throw new RuntimeException("Could not get attrib location for aPosition");
        }
        aTextureHandle = GLES20.glGetAttribLocation(program, "aTextureCoord");
        checkGlError("glGetAttribLocation aTextureCoord");
        if (aTextureHandle == -1) {
            throw new RuntimeException("Could not get attrib location for aTextureCoord");
        }

        uMVPMatrixHandle = GLES20.glGetUniformLocation(program, "uMVPMatrix");
        checkGlError("glGetUniformLocation uMVPMatrix");
        if (uMVPMatrixHandle == -1) {
            throw new RuntimeException("Could not get attrib location for uMVPMatrix");
        }

        uSTMatrixHandle = GLES20.glGetUniformLocation(program, "uSTMatrix");
        checkGlError("glGetUniformLocation uSTMatrix");
        if (uSTMatrixHandle == -1) {
            throw new RuntimeException("Could not get attrib location for uSTMatrix");
        }

        prepareSurface();
    }

    private void prepareSurface() {
        int[] textures = new int[1];
        GLES20.glGenTextures(1, textures, 0 );
Log.w("id",""+textures[0]);
      //  Log.w("id"+i,""+textures[1]);
      //  Log.w("id",""+textures[2]);

        textureID = textures[0];
        GLES20.glBindTexture(GL_TEXTURE_EXTERNAL_OES, textureID);

        checkGlError("glBindTexture textureID");

        GLES20.glTexParameterf(GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_MIN_FILTER,
                GLES20.GL_NEAREST);
        GLES20.glTexParameterf(GL_TEXTURE_EXTERNAL_OES, GLES20.GL_TEXTURE_MAG_FILTER,
                GLES20.GL_LINEAR);

        surface = new SurfaceTexture(textureID);
        surface.setOnFrameAvailableListener(this);

        Surface surface = new Surface(this.surface);
        onSurfacePrepareListener.surfacePrepared(surface);
    //    surface.release();

        synchronized(this) {
            updateSurface = false;
        }
    }

    synchronized public void onFrameAvailable(SurfaceTexture surface) {
        updateSurface = true;
    }

    private int loadShader(int shaderType, String source) {
        int shader = GLES20.glCreateShader(shaderType);
        if (shader != 0) {
Log.e("i",""+i);
            GLES20.glShaderSource(shader, source);
            GLES20.glCompileShader(shader);
            int[] compiled = new int[2];
            GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, i);
            if (compiled[i] == 0) {
                Log.e(TAG, "Could not compile shader " + shaderType + ":");
                Log.e(TAG, GLES20.glGetShaderInfoLog(shader));
                GLES20.glDeleteShader(shader);
                shader = 0;
            }
        }
        return shader;
    }

    private int createProgram(String vertexSource, String fragmentSource) {

         vertexShaderI = loadShader(35633, vertexSource);
        if (vertexShaderI == 0) {
            return 0;
        }
         pixelShaderI = loadShader(35632, fragmentSource);
        if (pixelShaderI == 0) {
            return 0;
        }
        int program = GLES20.glCreateProgram();
        if (program != 0) {
            GLES20.glAttachShader(program, vertexShaderI);
            checkGlError("glAttachShader");
            GLES20.glAttachShader(program, pixelShaderI);
            checkGlError("glAttachShader");
            GLES20.glLinkProgram(program);
            int[] linkStatus = new int[1];
            GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linkStatus, 0);
            if (linkStatus[0] != GLES20.GL_TRUE) {
                Log.e(TAG, "Could not link program: ");
                Log.e(TAG, GLES20.glGetProgramInfoLog(program));
                GLES20.glDeleteProgram(program);
                program = 0;
            }
        }
        return program;
    }

    void setAlphaColor(int color) {
        redParam = (float) Color.red(color) / COLOR_MAX_VALUE;
        greenParam = (float) Color.green(color) / COLOR_MAX_VALUE;
        blueParam = (float) Color.blue(color) / COLOR_MAX_VALUE;
    }

    void setCustomShader(String customShader) {
        isCustom = true;
        shader = customShader;
        Log.e("shader",customShader);
    }
    public void setAlphaMovieView(AlphaMovieView alphaMovieView) {
        this.alphaMovieView = alphaMovieView;
    }
public void Change(){

    i=0;
        runOnDraw(new Runnable() {
            @Override
            public void run() {
          //     GLES20.glDeleteProgram(program);
          //     program=0;
          //     GLES20.glDeleteShader(vertexShaderI);
          //     vertexShaderI=0;
          //     GLES20.glDeleteShader(pixelShaderI);
          //     pixelShaderI=0;
   //updateC=true;
                program = createProgram(vertexShader, this.resolveShader());
                if (program == 0) {
                    return;
                }
   //   prepareSurface();

                glTextureView.requestRender();
            }

            private String resolveShader() {
                return isCustom ? shader : String.format(Locale.ENGLISH, alphaShader,
                        redParam, greenParam, blueParam, 1 - accuracy);
            }
        });
      //  updateSurface=true;



  //  int[] textures = new int[2];
  //  GLES20.glGenTextures(2, textures, 1 );
  //  Log.w("id",""+textures[0]);
  // prepareSurface();
}
    private void runAll(Queue<Runnable> queue) {
        synchronized (queue) {
            while (!queue.isEmpty()) {
                queue.poll().run();
            }
        }
    }
    void setAccuracy(double accuracy) {
        if (accuracy > 1.0) {
            accuracy = 1.0;
        } else if (accuracy < 0.0) {
            accuracy = 0.0;
        }
        this.accuracy = accuracy;
    }

    public double getAccuracy() {
        return accuracy;
    }

    private String resolveShader() {
        return isCustom ? shader : String.format(Locale.ENGLISH, alphaShader,
                redParam, greenParam, blueParam, 1 - accuracy);
    }

    private void checkGlError(String op) {
        int error;
        if ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, op + ": glError " + error);
            throw new RuntimeException(op + ": glError " + error);
        }
    }

    void setOnSurfacePrepareListener(OnSurfacePrepareListener onSurfacePrepareListener) {
        this.onSurfacePrepareListener = onSurfacePrepareListener;
    }

    interface OnSurfacePrepareListener {
        void surfacePrepared(Surface surface);
    }

}