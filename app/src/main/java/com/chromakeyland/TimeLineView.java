package com.chromakeyland;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TimeLineView extends View {

    private static final String TAG = "TimelineView";

    private int mThumbnailsCount = 10;
    private  List<Bitmap> mVideoThumbnails = new ArrayList<>();
    private Uri mVideoURI;
    private int mWidth, mHeight;

    public TimeLineView(Context context) {
        super(context);
        init(null, 0);
    }

    public TimeLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public TimeLineView(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        init(attrs, style);
    }

    @Nullable
    public Uri getVideoURI() {
        return mVideoURI;
    }
    private void init(AttributeSet attrs, int style) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.TimelineView, style, 0);
     //   mThumbnailsCount = a.getInt(R.styleable.TimelineView_thumbnails_count, mThumbnailsCount);
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int count = mVideoThumbnails.size();
        if (count <= 0) {
            return;
        }
        int width = mWidth / count;
        float offset = 0f;
        for (int i = 0; i < count; i++) {
            Bitmap thumbnail = mVideoThumbnails.get(i);
            if (thumbnail != null) {
                canvas.drawBitmap(thumbnail, offset, 0f, null);
            }
            offset += width;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int ow, int oh) {
        super.onSizeChanged(w, h, ow, oh);
        Log.v(TAG, "Size changed from " + ow + "x" + oh + " to " + w + "x" + h + ".");
        mWidth = w;
        mHeight = h;
        if (h != oh && mVideoURI != null) {
            refresh();
        }
    }

    protected void refresh() {
        List<Bitmap> thumbnails = new ArrayList<>();
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(getContext(), mVideoURI);
        String duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        //noinspection ConstantConditions
        long millis = Long.parseLong(duration);
        long interval = millis / 10;
        Log.v(TAG, "Extracting " + mThumbnailsCount + " frames at " + interval + "ms each.");
        for (int i = 0; i < mThumbnailsCount; i++) {
            Bitmap thumbnail = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
                //noinspection SuspiciousNameCombination
                thumbnail = mmr.getScaledFrameAtTime(
                        TimeUnit.MILLISECONDS.toMicros(i * interval),
                        MediaMetadataRetriever.OPTION_CLOSEST_SYNC,
                        mHeight,
                        mHeight
                );
            } else {
                Bitmap frame = mmr.getFrameAtTime(
                        TimeUnit.MILLISECONDS.toMicros(i * interval),
                        MediaMetadataRetriever.OPTION_CLOSEST_SYNC
                );
                if (frame != null) {
                    //noinspection SuspiciousNameCombination
                    thumbnail = ThumbnailUtils.extractThumbnail(frame, mHeight, mHeight);
                    frame.recycle();
                }
            }
            thumbnails.add(thumbnail);
        }
        mmr.release();
        mVideoThumbnails.clear();
        mVideoThumbnails.addAll(thumbnails);
        invalidate();
    }

    public void setVideoURI(@NonNull Uri uri) {
        mVideoURI = uri;
        if (mHeight > 0) {
            refresh();
        }
    }
}