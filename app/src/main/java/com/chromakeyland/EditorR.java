package com.chromakeyland;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GestureDetectorCompat;

//import com.arthenica.mobileffmpeg.Statistics;

import com.chromakeyland.i.Ex;
import com.chromakeyland.i.Save;
import com.chromakeyland.placeholder.Debog;
import com.simform.videooperations.CallBackOfQuery;
import com.simform.videooperations.FFmpegCallBack;
import com.simform.videooperations.LogMessage;
import com.simform.videooperations.Statistics;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditorR
        //ExecuteBinaryResponseHandler
        implements View.OnTouchListener, FFmpegCallBack {
    //FFmpeg ffmpeg;

    String path;
    File mp4;
    File image;
    File out;
    int x;
    int y;
    int h;
    int w;
    int q;
    View vd;
    public static View lc;
    private String[] ex;
    public static boolean nm;
    public static Context context;
    boolean b=false;
    Ex ext;
    Save save;
    String eye="";
    private GestureDetectorCompat detector;
    public static int r;
    int videoLength;
    public EditorR(Context context,Ex ex1)  {
        this.context=context;
        this.ext=ex1;

        if (context!=null) {
    //    ffmpeg = FFmpeg.getInstance(context);
    // }else Log.w("erorr","ff");
    // try {

    //     ffmpeg.loadBinary(new FFmpegLoadBinaryResponseHandler() {
    //         @Override
    //         public void onFailure() {

    //         }

    //         @Override
    //         public void onSuccess() {

    //         }

    //         @Override
    //         public void onStart() {

    //         }

    //         @Override
    //         public void onFinish() {

    //         }
    //     });
     //   }catch (Exception e){
     //       e.printStackTrace();
     //
              }
//
    }

    public void setSave(Save save) {
        this.save = save;
    }

    public void setEye(Save eye) {
        q++;
        this.eye +="[ckout1]colorkey=color="+save.getColorF()+":similarity=0.20:blend=0.0[ckout2];";
    }

    private void set(){
        float i=w/h;
        String df="00FF00";
        ex = new String[]{"-i", image.getAbsolutePath(), "-i", mp4.getAbsolutePath(),
                "-filter_complex", "[1:v]scale="+w+":"+h+", colorkey=color="+"FFFFFFFF"+":similarity=0.1:blend=0.0:[ckout1];[ckout1]colorkey=color="+"0xFFFFFFFF"+":similarity=0.10:blend=0.0, chromakey=color=0x"+save.getColorF()+":similarity=0.1[ckout];[0:v][ckout]overlay=" + x + ":" + y+ "[out2]",
      //          "-filter_complex", "[1:v]colorkey=color="+"FFFFFFFF"+":similarity=0.1:blend=0.1[out];",
                "-map", "[out2] -map 1:a:?", out.getAbsolutePath()};
    }
    public void start(int i){
        switch (i){
            case 1:
                greenscreen();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + i);
        }
    }

    public static boolean isNm() {
        return nm;
    }

    public static void setNm(boolean nm) {
        EditorR.nm = nm;
    }

        public void setImage(File image) {
        this.image = image;
    }

    public void setLc(View lc) {
        this.lc = lc;
    }

    public void setMp4(File mp4) {
        this.mp4 = mp4;
        videoLength= MediaPlayer.create(context,Uri.parse(mp4.getAbsolutePath())).getDuration();
    }

    public void setOut(File out) {
        this.out = out;
        if (out.exists())out.delete();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setW(int w) {
        this.w = w;
       int w1= MediaPlayer.create(context,Uri.parse(Edite.path)).getVideoWidth();
       Debog.DebogE((float)AlphaMovieView.wa+"          t                tt                t 34rtjgjgjjggjjjjjjjjjjjjjjj jjjjjjjjjjjjj"+w);
       this.w=(int) ((float)w/AlphaMovieView.wa* w1);
    }

    public void setH(int h) {
        this.h = h;
        int h1=MediaPlayer.create(context,Uri.parse(Edite.path)).getVideoHeight();
        this.h=(int)((float) h/AlphaMovieView.ha *h1);
    }

    public void setVd(View vd) {
        this.vd = vd;
    }

    private void greenscreen(){
if (out.exists())out.delete();
String[] cmd={"-i",image.getAbsolutePath(),"-i",mp4.getAbsolutePath(),
        "-filter_complex","[1:v]colorkey=color=00FF00:similarity=0.60:blend=0.20:scale=w=100=h=100[ckout];[0:v][ckout]overlay=x="+x+":y="+y+"[out]",
        "-map","[out]", out.getAbsolutePath()};
E(cmd);

    }
    public void start() {
        set();
        new CallBackOfQuery().callQuery(ex,this );


     //  E(ex);
    }
    private void E(String[] cmd){
      //  try {
            // to execute "ffmpeg -version" command you just need to pass "-version"
      //     ffmpeg.execute(cmd,this);
      // } catch (FFmpegCommandAlreadyRunningException e) {
      //     // Handle if FFmpeg is already running
      //     e.printStackTrace();
       // }
    }
//  @Override
//  public void onStart() {

//  }

//  @Override
//  public void onProgress(String message) {
//   Log.w("m",message);
//   ext.ex(message);
//  }

//  @Override
//  public void onFailure(String message) {
//      Log.w("m",message);
//      ext.f();
//  }

//  @Override
//  public void onSuccess(String message) {
//      Log.w("m",message);
//      ext.s();
//  }

//  @Override
//  public void onFinish() {
//g.e("finish","w");
//  }
    float dX;
    float dY;
    int xdiff;
    int ydiff;
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        int width = view.getLayoutParams().width;
        int height = view.getLayoutParams().height;
        Log.e("e",view.getClass().getSimpleName());
        if (view.getClass().getSimpleName().equals("AppCompatImageView")) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.e(">>", "width:" + width + " height:" + height + " x:" + x + " y:" + y);
                    lc.getLayoutParams().width = x;
                    lc.getLayoutParams().height =y;
                    lc.requestLayout();
                    view.setX(x);
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
        } else {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:

                    dX = view.getX() - event.getRawX();
                    dY = view.getY() - event.getRawY();
                    xdiff = (int) event.getRawX();
                    ydiff = (int) event.getRawY();
                    Log.e("dawn", "" + event.getRawX());
                    if (event.getPointerCount() == 2) Log.e("p", "s");
                    break;

                case MotionEvent.ACTION_MOVE:
                    view.setY(event.getRawY() + dY);
                    view.setX(event.getRawX() + dX);
                   vd.setY(event.getRawY()+vd.getHeight());
                   vd.setX(event.getRawX()+dX );
                   if (view.getClass().getSimpleName().equals("AlphaMovieView")){
                      int w= MediaPlayer.create(context,Uri.parse(Edite.path)).getVideoWidth();
                      int h=MediaPlayer.create(context,Uri.parse(Edite.path)).getVideoHeight();
                              AlphaMovieView.y=view.getY() /lc.getHeight() * h;
                              AlphaMovieView.x=view.getX() /lc.getWidth() *w;
                   }
                    break;
                case MotionEvent.ACTION_UP:
                    int Xdiff = (int) (event.getRawX() - xdiff);
                    int Ydiff = (int) (event.getRawY() - ydiff);
                    Log.e("up", "" + Xdiff);
                    //  if (Ydiff <10 && Xdiff <10)  {
                    //clicked!!!
                    //   das(view);
                    //    }
                    break;
                default:
                    return false;
            }

        }
        return true;
    }

    public static void saveImage(final String folderName, final String fileName, final Bitmap image) {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File file = new File(path, folderName + "/" + fileName);
        try {
            file.getParentFile().mkdirs();
            image.compress(Bitmap.CompressFormat.JPEG, 80, new FileOutputStream(file));
            MediaScannerConnection.scanFile(context,
                    new String[]{
                            file.toString()
                    }, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(final String path, final Uri uri) {
                     //     if (listener != null) {
                     //         handler.post(new Runnable() {

                     //             @Override
                     //             public void run() {
                     //                 listener.onPictureSaved(uri);
                       //             }
                       //         });
                        //    }
                        }
                    });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void deleteCache(File dir) {
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files == null) return;

            for (File el: files) {
                el.delete();
            }
        }
    }

   public void s() {
        /*
       detector = new GestureDetectorCompat(this, new GestureDetector.SimpleOnGestureListener() {

           @Override
           public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
               Matrix m = new Matrix();
               yscale += distanceY / container.getHeight();
               m.setScale(1, yscale, 0, 0);
               image.setImageMatrix(m);
               return true;
           }
       });
   }*/
   }

    @Override
    public void cancel() {

    }

    @Override
    public void failed() {
ext.f();
    }


    @Override
    public void success() {
ext.s();
    }


    @Override
    public void process(@NonNull LogMessage logMessage) {
        Pattern timePattern = Pattern.compile("(?<=time=)[\\d:.]*");
        Scanner sc = new Scanner(logMessage.getText());

        String match = sc.findWithinHorizon(timePattern, 0);
        if (match != null) {
            String[] matchSplit = match.split(":");
if (videoLength!=0) {
    float progress = (Integer.parseInt(matchSplit[0]) * 3600 +
            Integer.parseInt(matchSplit[1]) * 60 +
            Float.parseFloat(matchSplit[2]) / videoLength);
    float showProgress = (progress * 100);
    ext.progres(showProgress);
    ext.ex(logMessage.getText() + x+"y"+y+"w"+w+"h"+h);
}
        }


    }

    @Override
    public void statisticsProcess(@NonNull Statistics statistics) {
        float progress = Float.parseFloat(String.valueOf(statistics.getTime())) / videoLength;
        float progressFinal = progress * 100;
        ext.progres(progress);
    }
}
