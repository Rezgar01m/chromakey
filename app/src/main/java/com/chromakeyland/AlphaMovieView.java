package com.chromakeyland;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.MediaDataSource;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.ViewGroup;

import com.chromakeyland.i.AdapterE;
import com.chromakeyland.placeholder.BlankFragment;
import com.chromakeyland.placeholder.Debog;

import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Objects;

@SuppressLint("ViewConstructor")
public class AlphaMovieView extends GLTextureView {
public static AlphaMovieView ah;
    private static final int GL_CONTEXT_VERSION = 2;
   public static String fragmentShader = "#extension GL_OES_EGL_image_external : require\n"
            + "precision mediump float;\n"
            + "varying vec2 vTextureCoord;\n"
            + "uniform samplerExternalOES sTexture;\n"
            + "void main() {\n"
            + "  vec4 color = texture2D(sTexture, vTextureCoord);\n"
            + "  float colorR = (1.0 - color.r) / 1.0;\n"
            + "  float colorG = (1.0 - color.g) / 1.0;\n"
            + "  float colorB = (1.0 - color.b) / 1.0;\n"
            + "  gl_FragColor = vec4(colorR, colorG, colorB, color.a);\n"
            + "}\n";
    private static final int NOT_DEFINED = -1;
    private static final int NOT_DEFINED_COLOR = 0;
    public static int color=0;
    public static float y;
    public static float x;

    public static int w;
    public static int h;
    public static int wa;
    public static int ha;
    private static final String TAG = "VideoSurfaceView";
public static Matrix m;
    private static final float VIEW_ASPECT_RATIO = 4f / 3f;
    private float videoAspectRatio = VIEW_ASPECT_RATIO;
    public static Surface surface;
    VideoRenderer renderer;
    private MediaPlayer mediaPlayer;

    private OnVideoStartedListener onVideoStartedListener;
    private OnVideoEndedListener onVideoEndedListener;

    private boolean isSurfaceCreated;
    private boolean isDataSourceSet;
    public static AttributeSet a;
    private PlayerState state = PlayerState.NOT_PREPARED;

    public AlphaMovieView(Context context, AttributeSet attrs) {
        super(context, attrs);
a=attrs;
        if (!isInEditMode()) {
            init(attrs);
        }
    }

     void init(AttributeSet attrs) {
        if (b) {
            setEGLContextClientVersion(GL_CONTEXT_VERSION);
            setEGLConfigChooser(8, 8, 8, 8, 16, 0);

            initMediaPlayer();

            renderer = new VideoRenderer();
        }
       obtainRendererOptions(attrs);

        this.addOnSurfacePrepareListener();
        if (b)
        setRenderer(renderer);

        bringToFront();
        setPreserveEGLContextOnPause(true);
        setOpaque(false);
         m=getMatrix();
    }
public String getV(){
        return renderer.alphaShader;
}
    private void initMediaPlayer() {
        mediaPlayer = new MediaPlayer();

        setScreenOnWhilePlaying(true);
        setLooping(true);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                state = PlayerState.PAUSED;
                if (onVideoEndedListener != null) {
                    onVideoEndedListener.onVideoEnded();
                }
            }
        });
    }


public void shader(String s){
    renderer.setAlphaMovieView(this);
    m=getMatrix();
    ah=this;
    renderer.setCustomShader(s+AdapterE.end);
    renderer.Change();
    surfaceCreated(getSurfaceTexture());
}
    public void color(int i) {

  //  renderer.setCustomShader("#extension GL_OES_EGL_image_external : require\n" +
       //     "     precision mediump float; varying vec2 vTextureCoord; uniform samplerExternalOES sTexture; varying mediump float text_alpha_out; void main() { vec4 color = texture2D(sTexture, vTextureCoord); if (color.g - color.r >= 0.1 && color.g - color.b >= 0.1) { gl_FragColor = vec4(color.r, (color.r + color.b) / 2.0, color.b, 1.0 - color.g); } else { gl_FragColor = vec4(color.r, color.g, color.b, color.a); } }");
//renderer.setAlphaColor(i);
//renderer.setAccuracy(0.7);

 //   renderer.setCustomShader(fragmentShader);

       // obtainRendererOptions(a);

        m=getMatrix();
ah=this;
//renderer.setCustomShader(BlankFragment.context.getString(R.string.shader_custom));
        renderer.setAlphaColor(i);
renderer.setAlphaMovieView(this);
//TODO
    //  b=false;
//   Class<?> myClass=AlphaMovieView.class;
//   System.out.printf("my class is Class@%x%n", myClass.hashCode());
//   System.out.println("reloading");
//   URL[] urls= new URL[0];
//   try {
//       try {
//           urls = new URL[]{myClass.getProtectionDomain().getCodeSource().getLocation().toURI().toURL()
//                    };
//       } catch (URISyntaxException e) {
//           e.printStackTrace();
//       }
//   } catch (MalformedURLException e) {
//       e.printStackTrace();
//   }
//   ClassLoader delegateParent = myClass.getClassLoader().getParent();
//   try(URLClassLoader cl=new URLClassLoader(urls, delegateParent)) {
//       Class<?> reloaded=cl.loadClass(myClass.getName());
//renderer.Change();

// mGLThread=null;
    //  mediaPlayer.setSurface(surface);

renderer.Change();

        surfaceCreated(getSurfaceTexture());
}
    private void obtainRendererOptions(AttributeSet attrs) {
   //  renderer.setCustomShader("#extension GL_OES_EGL_image_external : require\n" +
   //          "     precision mediump float; varying vec2 vTextureCoord; uniform samplerExternalOES sTexture; varying mediump float text_alpha_out; void main() { vec4 color = texture2D(sTexture, vTextureCoord); if (color.g - color.r >= 0.1 && color.g - color.b >= 0.1) { gl_FragColor = vec4(color.r, (color.r + color.b) / 2.0, color.b, 1.0 - color.g); } else { gl_FragColor = vec4(color.r, color.g, color.b, color.a); } }");
       renderer.setAlphaColor(Color.GREEN);
       renderer.setAccuracy(0.3);
        if (attrs != null) {
            TypedArray arr = getContext().obtainStyledAttributes(attrs, R.styleable.AlphaMovieView);
            int alphaColor = arr.getColor(R.styleable.AlphaMovieView_alphaColor, NOT_DEFINED_COLOR);
            if (alphaColor != NOT_DEFINED_COLOR) {
               // renderer.setAlphaColor(alphaColor);
            }
            String shader = arr.getString(R.styleable.AlphaMovieView_shader);
            if (shader != null) {
                Log.e("shader",shader);
               //renderer.setCustomShader(shader);
            }
            float accuracy = arr.getFloat(R.styleable.AlphaMovieView_accuracy, NOT_DEFINED);
            if (accuracy != NOT_DEFINED) {
              //  renderer.setAccuracy(accuracy);
            }
            arr.recycle();
        }
    }

    private void addOnSurfacePrepareListener() {
        if (renderer != null) {
            renderer.setOnSurfacePrepareListener(new VideoRenderer.OnSurfacePrepareListener() {
                @Override
                public void surfacePrepared(Surface surface) {
                    isSurfaceCreated = true;
                    mediaPlayer.setSurface(surface);
                    AlphaMovieView.surface=surface;
                    surface.release();
                    if (isDataSourceSet) {
                        prepareAndStartMediaPlayer();
                    }
                }
            });
        }
    }

    private void prepareAndStartMediaPlayer() {
        prepareAsync(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                start();
            }
        });
    }

    private void calculateVideoAspectRatio(int videoWidth, int videoHeight) {
        if (videoWidth > 0 && videoHeight > 0) {

            videoAspectRatio = (float) videoWidth / videoHeight ;
        }

        requestLayout();
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        double currentAspectRatio = (double) widthSize / heightSize;
        if (currentAspectRatio > videoAspectRatio) {
            widthSize = (int) (heightSize * videoAspectRatio );
        } else {
            heightSize = (int) (widthSize / videoAspectRatio);
        }

        super.onMeasure(MeasureSpec.makeMeasureSpec(w=widthSize/2
                , widthMode),
                MeasureSpec.makeMeasureSpec(h=heightSize/2 , heightMode ));
        Debog.DebogE("alpha move wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww"+h);

    }

    private void onDataSourceSet(MediaMetadataRetriever retriever) {
        int videoWidth = Integer.parseInt(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH));

        int videoHeight = Integer.parseInt(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT));

        calculateVideoAspectRatio(videoWidth, videoHeight);
        isDataSourceSet = true;

        if (isSurfaceCreated) {
            prepareAndStartMediaPlayer();
        }
    }

    public void setVideoFromAssets(String assetsFileName) {
        reset();

        try {
            AssetFileDescriptor assetFileDescriptor = getContext().getAssets().openFd(assetsFileName);
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());

            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());

            onDataSourceSet(retriever);

        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
@SuppressLint("ObsoleteSdkInt")
public MediaMetadataRetriever getM(){
    MediaMetadataRetriever retriever = new MediaMetadataRetriever();

   retriever.setDataSource(Edite.path);
    return retriever;
}
    public void setVideoByUrl(String url) {
        reset();

        try {
            mediaPlayer.setDataSource(url);

            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(url, new HashMap<String, String>());

            onDataSourceSet(retriever);

        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    public void setVideoFromFile(FileDescriptor fileDescriptor) {
        reset();

        try {
            mediaPlayer.setDataSource(fileDescriptor);
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(fileDescriptor);

            onDataSourceSet(retriever);

        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    public void setVideoFromFile(FileDescriptor fileDescriptor, int startOffset, int endOffset) {
        reset();

        try {
            mediaPlayer.setDataSource(fileDescriptor, startOffset, endOffset);

            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(fileDescriptor, startOffset, endOffset);

            onDataSourceSet(retriever);

        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    @TargetApi(23)
    public void setVideoFromMediaDataSource(MediaDataSource mediaDataSource) {
        reset();

        mediaPlayer.setDataSource(mediaDataSource);

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(mediaDataSource);

        onDataSourceSet(retriever);
    }

    public void setVideoFromUri(Context context, Uri uri) {
        reset();

        try {
            mediaPlayer.setDataSource(context, uri);

            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(context, uri);

            onDataSourceSet(retriever);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        pause();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        release();
    }

    private void prepareAsync(final MediaPlayer.OnPreparedListener onPreparedListener) {
        if (mediaPlayer != null && state == PlayerState.NOT_PREPARED
                || state == PlayerState.STOPPED) {
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    state = PlayerState.PREPARED;
                    onPreparedListener.onPrepared(mp);
                }
            });
            mediaPlayer.prepareAsync();
        }
    }

    public void start() {
        if (mediaPlayer != null) {
            switch (state) {
                case PREPARED:
                    mediaPlayer.start();
                    state = PlayerState.STARTED;
                    if (onVideoStartedListener != null) {
                        onVideoStartedListener.onVideoStarted();
                    }
                    break;
                case PAUSED:
                    mediaPlayer.start();
                    state = PlayerState.STARTED;
                    break;
                case STOPPED:
                    prepareAsync(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mediaPlayer.start();
                            state = PlayerState.STARTED;
                            if (onVideoStartedListener != null) {
                                onVideoStartedListener.onVideoStarted();
                            }
                        }
                    });
                    break;
            }
        }
    }

    public void pause() {
        if (mediaPlayer != null && state == PlayerState.STARTED) {
            mediaPlayer.pause();
            state = PlayerState.PAUSED;
        }
    }

    public void stop() {
        if (mediaPlayer != null && (state == PlayerState.STARTED || state == PlayerState.PAUSED)) {
            mediaPlayer.stop();
            state = PlayerState.STOPPED;
        }
    }

    public void reset() {
        if (mediaPlayer != null && (state == PlayerState.STARTED || state == PlayerState.PAUSED ||
                state == PlayerState.STOPPED)) {
            mediaPlayer.reset();
            state = PlayerState.NOT_PREPARED;
        }
    }

    public void release() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            state = PlayerState.RELEASE;
        }
    }

    public PlayerState getState() {
        return state;
    }

    public boolean isPlaying() {
        return state == PlayerState.STARTED;
    }

    public boolean isPaused() {
        return state == PlayerState.PAUSED;
    }

    public boolean isStopped() {
        return state == PlayerState.STOPPED;
    }

    public boolean isReleased() {
        return state == PlayerState.RELEASE;
    }

    public void seekTo(int msec) {
        mediaPlayer.seekTo(msec);
    }

    public void setLooping(boolean looping) {
        mediaPlayer.setLooping(looping);
    }

    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    public void setScreenOnWhilePlaying(boolean screenOn) {
        mediaPlayer.setScreenOnWhilePlaying(screenOn);
    }

    public void setOnErrorListener(MediaPlayer.OnErrorListener onErrorListener){
        mediaPlayer.setOnErrorListener(onErrorListener);
    }

    public void setOnVideoStartedListener(OnVideoStartedListener onVideoStartedListener) {
        this.onVideoStartedListener = onVideoStartedListener;
    }

    public void setOnVideoEndedListener(OnVideoEndedListener onVideoEndedListener) {
        this.onVideoEndedListener = onVideoEndedListener;
    }

    public void setOnSeekCompleteListener(MediaPlayer.OnSeekCompleteListener onSeekCompleteListener) {
        mediaPlayer.setOnSeekCompleteListener(onSeekCompleteListener);
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public interface OnVideoStartedListener {
        void onVideoStarted();
    }

    public interface OnVideoEndedListener {
        void onVideoEnded();
    }

    private enum PlayerState {
        NOT_PREPARED, PREPARED, STARTED, PAUSED, STOPPED, RELEASE
    }
}