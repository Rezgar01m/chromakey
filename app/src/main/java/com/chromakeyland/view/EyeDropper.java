package com.chromakeyland.view;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.chromakeyland.AlphaMovieView;
import com.chromakeyland.Edite;

public class EyeDropper {
    private static final Matrix INVERT_MATRIX = new Matrix();
    private static final int NO_COLOR = 0;
    private View targetView;
    private ColorSelectionListener colorListener;
    private int xTouch;
    private int yTouch;
    private SelectionListener selectionListener;
private int d=0;
    public EyeDropper(@NonNull View view, @NonNull ColorSelectionListener listener) {
        this.colorListener = listener;
        this.setTargetView(view);
        this.setTouchListener();
    }

    public void setD(int d) {
        this.d = d;
    }

    private boolean shouldDrawingCacheBeEnabled(@NonNull View targetView) {
        return !(targetView instanceof ImageView) && !targetView.isDrawingCacheEnabled();
    }

    private void setTouchListener() {
        this.targetView.setOnTouchListener(new OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getActionMasked();
            xTouch = (int)event.getX();
            yTouch = (int)event.getY();
            handleSelectionStart(event, action);
            notifyColorSelected(getColorAtPoint(xTouch,yTouch));
            handleSelectionEnd(event, action);
                return true;
            }
        });
    }

    private void handleSelectionEnd(MotionEvent event, int action) {
        if (this.selectionListener != null && action == 1) {
            this.selectionListener.onSelectionEnd(event);
        }

    }

    private void handleSelectionStart(MotionEvent event, int action) {
        if (this.selectionListener != null && action == 0) {
            this.selectionListener.onSelectionStart(event);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private int getColorAtPoint(int x, int y) {
        if (this.targetView instanceof ImageView) {
            return this.handleIfImageView(x, y);
        }else if (targetView instanceof AlphaMovieView)
        {
            MediaMetadataRetriever m=new MediaMetadataRetriever();
            m.setDataSource(Edite.path);
            Bitmap f=m.getFrameAtTime(d);
            f.setPremultiplied(false);
            return getPixelAtPoint(f,x,y);
        }
        else {
            Bitmap drawingCache = this.targetView.getDrawingCache();
            return this.getPixelAtPoint(drawingCache, x, y);
        }
    }

    private int handleIfImageView(int x, int y) {
        ImageView targetImageView = (ImageView)this.targetView;
        Drawable drawable = targetImageView.getDrawable();
        if (drawable instanceof BitmapDrawable) {
            targetImageView.getImageMatrix().invert(INVERT_MATRIX);
            float[] mappedPoints = new float[]{(float)x, (float)y};
            INVERT_MATRIX.mapPoints(mappedPoints);
            Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
            return this.getPixelAtPoint(bitmap, (int)mappedPoints[0], (int)mappedPoints[1]);
        } else {
            return 0;
        }
    }

    private int getPixelAtPoint(Bitmap bitmap, int x, int y) {
        return this.isValidPoint(x, y, bitmap) ? bitmap.getPixel(x, y) : 0;
    }

    private boolean isValidPoint(int x, int y, Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        return this.isValidCoordinate(x, width) && this.isValidCoordinate(y, height);
    }

    private boolean isValidCoordinate(int coordinate, int size) {
        return coordinate > 0 && coordinate < size;
    }

    private void notifyColorSelected(int color) {
        if (this.colorListener != null) {
            this.colorListener.onColorSelected(color);
        }

    }

    @SuppressLint("WrongConstant")
    private void setTargetView(@NonNull View targetView) {
        this.targetView = targetView;
        if (this.shouldDrawingCacheBeEnabled(targetView)) {
            targetView.setDrawingCacheEnabled(true);
            targetView.setDrawingCacheQuality(524288);
        }

    }

    public void setSelectionListener(@NonNull SelectionListener listener) {
        this.selectionListener = listener;
    }

    public interface ColorSelectionListener {
        void onColorSelected(@ColorInt int var1);
    }

    public interface SelectionListener {
        void onSelectionStart(@NonNull MotionEvent var1);

        void onSelectionEnd(@NonNull MotionEvent var1);
    }
}
