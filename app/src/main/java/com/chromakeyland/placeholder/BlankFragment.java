package com.chromakeyland.placeholder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.VideoView;

//import com.aghajari.axvideotimelineview.AXTimelineViewListener;
//import com.aghajari.axvideotimelineview.AXVideoTimelineView;

import com.chromakeyland.AlphaMovieView;
import com.chromakeyland.Edite;
import com.chromakeyland.EditeAdapter;
import com.chromakeyland.EditorR;
import com.chromakeyland.R;
import com.chromakeyland.i.AdapterE;
import com.chromakeyland.i.Fst;
import com.chromakeyland.i.TimelineHandler;
import com.chromakeyland.i.i;
import com.chromakeyland.view.EyeDropper;
import com.chromakeyland.view.EyeDropperx;
import com.chromakeyland.view.VideoSurfaceView;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.video.VideoFrameMetadataListener;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.chromakeyland.databinding.EditebsBinding;
//  import com.sherazkhilji.videffects.NoEffect;
//  import com.sherazkhilji.videffects.interfaces.ShaderInterface;
import com.skydoves.colorpickerview.ActionMode;
import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.flag.BubbleFlag;
import com.skydoves.colorpickerview.flag.FlagMode;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;
import com.squareup.picasso.Picasso;
import com.video.timeline.ImageLoader;
import com.video.timeline.RetroInstance;
import com.video.timeline.VideoMetadata;
import com.video.timeline.VideoTimeLine;
import com.video.timeline.android.MediaRetrieverAdapter;
import com.video.timeline.render.TimelineGlSurfaceView;
import com.video.timeline.tools.MediaHelper;

import net.kibotu.fastexoplayerseeker.SeekPositionEmitter;
import net.kibotu.fastexoplayerseeker.FastExoPlayerSeekerKt.*;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    private ArrayList<String> p=new ArrayList<>();
    private ImageLoader picassoLoader = (file, view) -> {
        if (file == null || !file.exists()) {
            view.setImageDrawable(new ColorDrawable(Color.LTGRAY));
        } else {
           Picasso.get().load(Uri.fromFile(file)).placeholder(new ColorDrawable(Color.LTGRAY)).into(view);
        }
    };
    public BaseB baseB;
    boolean l,scroll=false;
    @SuppressLint("StaticFieldLeak")
    int w,of,po=0;
    public static Context context;
    static int id = R.id.video_player;
    public static FloatingActionButton f;
    Context thisContext;
    public EditorR editorR;
   public AlphaMovieView videoView;
   public PlayerView player;
   public SimpleExoPlayer simpleExoPlayer;
   public AlphaMovieView alphaMovieView;
  // public AXVideoTimelineView axVideoTimelineView;
   public RecyclerView timeline;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public com.chromakeyland.i.i i;
public  String setVideoPath=Edite.path;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private boolean b=true;
    public ImageView cl,ok;
    boolean a;
    public Edite edite;
    View main;
    View lv;
    public EyeDropperx ed;
    public BlankFragment() {
        // Required empty public constructor
    }

    public void setEdite(Edite edite) {
        this.edite = edite;
    }

    public void setI(com.chromakeyland.i.i i) {
        this.i = i;
    }

    private void setSetVideoPath(String setVideoPath) {
        this.setVideoPath = setVideoPath;
        if (videoView!=null){
            FileInputStream fileInputStream1 = null;
            try {
                fileInputStream1 = new FileInputStream(new File(

                        setVideoPath));

                 videoView.setVideoFromFile(fileInputStream1.getFD());
                 //axVideoTimelineView.setVideoPath(setVideoPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
public BlankFragment(String setVideoPath){
        setSetVideoPath(setVideoPath);
}
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();

        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
public void addc(){
        boolean test=false;
    try {
        FileInputStream fileInputStream;

        if (test) {
            fileInputStream = new FileInputStream(new File(

                    "storage/emul/ated/0/Pictures/bin/InShot_20220214_170731002.mp4"));
        }else {
            fileInputStream = new FileInputStream(new File(

                    Edite.addchroma));

        }
        if (alphaMovieView!=null) {
            alphaMovieView.setVisibility(View.VISIBLE);
            alphaMovieView.setEnabled(true);
            alphaMovieView.setVideoFromFile(fileInputStream.getFD());
            alphaMovieView.start();
          //  AlphaMovieView.wa=alphaMovieView.getWidth();
          //  AlphaMovieView.ha=alphaMovieView.getHeight();

        }else Toast.makeText(getActivity(), "erorr", Toast.LENGTH_SHORT).show();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v=inflater.inflate(R.layout.e, container, false);
        thisContext=v.getContext();
        lv=v;
    //     axVideoTimelineView=v.findViewById(R.id.axView);
        timeline=v.findViewById(R.id.axView);
        p.add(setVideoPath);
        List<VideoMetadata> mets = new ArrayList<>();

        for (String video: p) {
            VideoMetadata videoMetadata = new VideoMetadata();
            MediaHelper.getVideoMets(thisContext, video, videoMetadata);
            mets.add(videoMetadata);
        }
        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(v.getContext(),LinearLayoutManager.HORIZONTAL,false);
        timeline.setLayoutManager(linearLayoutManager1);

        player =v.findViewById(R.id.v);
        simpleExoPlayer = new SimpleExoPlayer.Builder(thisContext).build();
        MediaSource mediaSource = new ProgressiveMediaSource.Factory
                (new DefaultDataSourceFactory(thisContext, "geo"), new DefaultExtractorsFactory())
                .createMediaSource( Uri.fromFile(new File(setVideoPath)));
        simpleExoPlayer.prepare(mediaSource);
        player.setPlayer(simpleExoPlayer);
//        Debog.DebogE( Long.parseLong(videoView.getM().extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION))+" D");
        boolean c=true;
        RecyclerView.Adapter a;
        if (c)
        a=new MediaRetrieverAdapter(thisContext,setVideoPath,  2000,250,picassoLoader);
        else a=new Adapter(getRetor(),2000,picassoLoader,p,mets);
timeline.setAdapter(a);

   //    TimelineHandler timelineHandler=new TimelineHandler(thisContext,p,simpleExoPlayer,timeline);
     // timelineHandler.init();
        po= (int) simpleExoPlayer.getContentPosition();

simpleExoPlayer.addListener(new Player.EventListener() {

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int state) {

        if (state == ExoPlayer.STATE_ENDED) {
            //player back ended
            po=0;
            w=0;
            of=1;
            simpleExoPlayer.seekTo(0);
        }
        if (state == Player.STATE_READY) {
            // look for MappedTrackInfo
            if (Objects.requireNonNull(simpleExoPlayer.getVideoFormat()).height>=1000) {
                Debog.DebogE("hhh pppppppppppppppppppppppppp"+  player.getLayoutParams().height);
                player.getLayoutParams().height = 900;
                player.requestLayout();
            }
        }
        Debog.DebogE("stat"+state);
    }
    @Override
    public void onSeekProcessed() {
        Debog.DebogE("seek");

        // Toast.makeText(thisContext, "onseek", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPositionDiscontinuity(int reason) {
        // Toast.makeText(thisContext, "r"+reason, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTimelineChanged(Timeline timeline, int reason) {
        Toast.makeText(thisContext, "timelinechenged", Toast.LENGTH_SHORT).show();

    }


});


 // player.getLayoutParams().width=thisContext.getResources().getDisplayMetrics().widthPixels;

Log.w("sc",thisContext.getResources().getDisplayMetrics().widthPixels+"h:");
        Fst fst=new Fst();
      //  fst.set(simpleExoPlayer);
        simpleExoPlayer.setSeekParameters(SeekParameters.CLOSEST_SYNC);
        simpleExoPlayer.setVideoFrameMetadataListener(new VideoFrameMetadataListener() {
            @Override
            public void onVideoFrameAboutToBeRendered(long presentationTimeUs, long releaseTimeNs, Format format, @Nullable MediaFormat mediaFormat) {
                Debog.DebogE(presentationTimeUs+"");
            }
        });

      timeline.addOnScrollListener(new RecyclerView.OnScrollListener() {
         @Override
         public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
             if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                 long seekPos = (long) (recyclerView.computeHorizontalScrollOffset() * 1F /
                         recyclerView.computeHorizontalScrollRange()
                         * simpleExoPlayer.getDuration());
               //  simpleExoPlayer.seekTo((int) seekPos);
                 scroll=false;
                 Debog.DebogE("seek on scroll");
                 Debog.DebogE("of" + recyclerView.computeHorizontalScrollOffset()+"w"+recyclerView.computeHorizontalScrollRange());
             }else if (newState==RecyclerView.SCROLL_STATE_DRAGGING){
                 Debog.DebogE("is Scrolling");
                 scroll=true;
             }
         }
//

          @Override
          public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
              super.onScrolled(recyclerView, dx, dy);
              if (l) {
                  if (simpleExoPlayer.isPlaying()) simpleExoPlayer.stop();
              }
              if (recyclerView.computeHorizontalScrollRange()!=0) {
                  w = recyclerView.computeHorizontalScrollRange();
                  of = recyclerView.computeHorizontalScrollOffset();

              }
              if (recyclerView.computeHorizontalScrollOffset()==0){
                  long seekPos = (long) (of * 1F /
                          w
                          * simpleExoPlayer.getDuration());
                  long l1= (long) ((long) dx* (double)0.75 *-1*simpleExoPlayer.getDuration()+simpleExoPlayer.getContentPosition());
                  long lm=((l1+simpleExoPlayer.getDuration())%simpleExoPlayer.getDuration());
                  if (!simpleExoPlayer.isPlaying()) {
                      simpleExoPlayer.seekTo( seekPos);
                  //    simpleExoPlayer.seekTo(lm);
                   //   fst.seek(seekPos);
                      simpleExoPlayer.setPlayWhenReady(false);

                      l=false;
                      Debog.DebogE("onScroll main"+seekPos);
                  }
              }else {
                  long seekPos = (long) (recyclerView.computeHorizontalScrollOffset() * 1F /
                          recyclerView.computeHorizontalScrollRange()
                          * simpleExoPlayer.getDuration());
                  long l1= (long) ((long) dx*0.75 *-1*simpleExoPlayer.getDuration()+simpleExoPlayer.getContentPosition());
long lm=((l1+simpleExoPlayer.getDuration())%simpleExoPlayer.getDuration());
                  if (!simpleExoPlayer.isPlaying()) {
                      simpleExoPlayer.seekTo( seekPos);
                      //simpleExoPlayer.seekTo(lm);
                   //   fst.seek(seekPos);
                      simpleExoPlayer.setPlayWhenReady(false);
                      l=false;
                      Debog.DebogE("onScroll main"+seekPos);
                  }
              }

          }
      });
      timeline.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              l=true;
              Debog.DebogE("on click");
          }
      });
      timeline.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
          @Override
          public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
              Debog.DebogE("Item");
              l=true;
              return false;
          }

          @Override
          public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

          }

          @Override
          public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

          }
      });

      Handler handler = new Handler();
      Runnable runnable = new Runnable() {
          @Override
          public void run() {
              if (simpleExoPlayer.getContentPosition()!=0) {
//if (po<=simpleExoPlayer.getContentPosition())
    po= (int) simpleExoPlayer.getContentPosition();
              }else {
                 // if (simpleExoPlayer.isPlaying())
                 // Toast.makeText(thisContext, "zero", Toast.LENGTH_SHORT).show();
              }
            // if (!l)
                 if (!scroll)
                     if (simpleExoPlayer.isPlaying())
           linearLayoutManager1.scrollToPositionWithOffset(0,
                  -((int) ((po*10000)/4/simpleExoPlayer.getDuration())));
              boolean b = handler.postDelayed(this, 100);

         //     Debog.DebogE((int) (po*1000/simpleExoPlayer.getDuration())+"p"+simpleExoPlayer.getContentPosition());
          }
      };
      handler.postDelayed(runnable, 0);
        v.findViewById(R.id.axView).setY(1700.f);
     //  axVideoTimelineView.setVideoPath(setVideoPath);

        TimelineGlSurfaceView glSurfaceView = v.findViewById(R.id.fixed_thumb_list);

        VideoTimeLine.with(setVideoPath).into(glSurfaceView);

cl=v.findViewById(R.id.cl);
ok=v.findViewById(R.id.ok);
setlforEyed();
hide();

main=v;
  //      videoView.setVisibility(View.GONE);
        FileInputStream fileInputStream1 = null;
        try {
            fileInputStream1 = new FileInputStream(new File(

                        setVideoPath));

//        videoView.setVideoFromFile(fileInputStream1.getFD());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       player.setX((float) -4.340393);
       player.setY((float) -3.8416748);
        alphaMovieView = (AlphaMovieView) v.findViewById(R.id.video_player);
         editorR=new EditorR(v.getContext(),null);
      alphaMovieView.setOnTouchListener(editorR);
       alphaMovieView.setEnabled(false);
       alphaMovieView.setVisibility(View.GONE);
       //Log.e("p",alphaMovieView.getCurrentPosition()+"");

       alphaMovieView.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
           @Override
           public void onSeekComplete(MediaPlayer mediaPlayer) {

           }
       });
        ((EyeDropperx)lv.findViewById(R.id.eyed)).setVisibility(View.GONE);
        //       videoView.setX(100.f);
   //    v.findViewById(R.id.c).getLayoutParams().width=1072;
   //    v.findViewById(R.id.c).getLayoutParams().height=945;
        v.findViewById(R.id.c).setX((float) 3.4916992);
        v.findViewById(R.id.c).setY((float) 321.83862);
        v.findViewById(R.id.c).requestLayout();
editorR.setVd(v.findViewById(R.id.d));
        v.findViewById(R.id.c).setOnTouchListener(editorR);
      //  videoView.setEnabled(false);
      //  videoView.setVisibility(View.GONE);
      //  videoView.setOnTouchListener(editorR);

//       axVideoTimelineView.setListener(new AXTimelineViewListener() {
//           @Override
//           public void onLeftProgressChanged(float progress) {
//               Log.e("onl",""+progress);
//           }

//           @Override
//           public void onRightProgressChanged(float progress) {
//               Log.e("on r",""+progress);
//           }

//           @Override
//           public void onDurationChanged(long Duration) {
//               Log.w("ondc",""+Duration);
//           }

//           @Override
//           public void onPlayProgressChanged(float progress) {
//               Log.e("on ppc",""+(int)(progress * 100));
//  int i=(int)(progress * 100);
//   videoView.seekTo(i);

//

//          @Override
//          public void onDraggingStateChanged(boolean isDragging) {
//              Log.e("D",""+isDragging);
//          }
//      });
        v.findViewById(R.id.d).setOnTouchListener(editorR);
//axVideoTimelineView.setPlayLineEnabled(true);
// v.findViewById(R.id.v).setY(300.f);
        FloatingActionButton actionButton=v.findViewById(R.id.fab2);
        actionButton.setX(450);
      //  actionButton.setY(1300.f);
        f=actionButton;
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  if (b)
                if (!simpleExoPlayer.isPlaying())
                {

                    Debog.DebogE(player.getWidth()+"h"+player.getHeight());
                    actionButton.setImageResource(R.drawable.exo_icon_stop);
                    simpleExoPlayer.setPlayWhenReady(true);

                    //alphaMovieView.start();
                    MediaMetadataRetriever m=new MediaMetadataRetriever();
                    m.setDataSource(setVideoPath);
                    Log.e(""+v.findViewById(R.id.v).getX(),""+v.findViewById(R.id.v).getY()+"w"+v.findViewById(R.id.c).getWidth()+"h"+v.findViewById(R.id.c).getHeight());
                    b=false;
                }else {
                    actionButton.setImageResource(R.drawable.exo_icon_pause);
                    simpleExoPlayer.stop();
      //              alphaMovieView.stop();
                    b=true;
                }
            }
        });
       RecyclerView recyclerView=v.findViewById(R.id.r1);
       LinearLayoutManager linearLayoutManager=new LinearLayoutManager(v.getContext(),LinearLayoutManager.HORIZONTAL,false);
       recyclerView.setLayoutManager(linearLayoutManager);
   recyclerView.setAdapter(new EditeAdapter(PlaceholderContent.ITEMim,v.getContext(),this));
   recyclerView.setY(thisContext.getResources().getDisplayMetrics().heightPixels-500);
   editorR.setLc(v.findViewById(R.id.c));
 baseB=new BaseB(v,alphaMovieView);
        baseB.Hideb();


   /*
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
Log.e("onp","play");
            }
        });
        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
                Log.e("onp","play1");
                return true;
            }
        }); */

        if (i!=null)
        i.g();
    //  start(axVideoTimelineView,videoView);
    return v;
    }

    private void setlforEyed() {
        ok.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
               if (a) {
                   hide();
                   a=false;
                   baseB.Hideb();
               }
               else {
                    AdapterE.i++;
                    int i = AdapterE.i;
                    float redParam = (float) Color.red(ed.getColor()) / 255;
                    float greenParam = (float) Color.green(ed.getColor()) / 255;
                    float blueParam = (float) Color.blue(ed.getColor()) / 255;
                    String s = "  float red" + i + " = %f;\n"
                            + "  float green" + i + " = %f;\n"
                            + "  float blue" + i + " = %f;\n"
                            + "  float accuracy" + i + " = %f;\n";
                    String c =
                            "  || abs(color.r - red" + i + ") <= accuracy" + i + " && abs(color.g - green" + i + ") <= accuracy" + i + " && abs(color.b - blue" + i + ") <= accuracy" + i + "";
                    AdapterE.alphaShader += String.format(Locale.ENGLISH, s,
                            redParam, greenParam, blueParam, 1 - 0.96);
                    AdapterE.c += c;
                    AdapterE.lastI=1 - 0.96;
                    // AdapterE.cMain.append(AdapterE.c);

                    hide();
                    AdapterE.main = AdapterE.alphaShader + AdapterE.c + AdapterE.en;

                    //       alphaMovieView.color(ed.getColor());
                    alphaMovieView.shader(AdapterE.main.replace("null", ""));

                    AdapterE.integers.add(ed.getColor());

                    Log.e("l", "" + AdapterE.integers.size());
                }
            }

        });
        cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide();
            }
        });
    }

  //private void start(AXVideoTimelineView a,VideoView videoView){
  //    getActivity().runOnUiThread(new Runnable() {
  //        @Override
  //        public void run() {
  //            while (true){
  //  a.setProgress(videoView.getCurrentPosition());
  //            }
  //        }
  //    });
//setS();


    public void hide(){
        if (alphaMovieView!=null) {
            alphaMovieView.setVisibility(View.VISIBLE);

        }
        if (ed!=null)
        ed.setVisibility(View.GONE);
        cl.setVisibility(View.GONE);
        ok.setVisibility(View.GONE);
    }
    private void unhide(){
        ed.setVisibility(View.VISIBLE);
       // ed.setX(alphaMovieView.getX());
       // ed.setY(alphaMovieView.getY());
      //  ed.getLayoutParams().width=alphaMovieView.getLayoutParams().width;
      //  ed.getLayoutParams().height=alphaMovieView.getLayoutParams().height;
        //ed.requestLayout();
        alphaMovieView.setVisibility(View.GONE);
        cl.setVisibility(View.VISIBLE);
        ok.setVisibility(View.VISIBLE);
    }
    public void a(){
        ok.setVisibility(View.VISIBLE);
        a=true;
    }
public void addDropper(){
    ConstraintLayout c2m=main.findViewById(R.id.c);
    AlphaMovieView.wa=c2m.getWidth();
    AlphaMovieView.ha=c2m.getHeight();
Debog.DebogE("addDraper"+ AlphaMovieView.wa);
       // videoView.setVisibility(View.GONE);
    MediaMetadataRetriever m=new MediaMetadataRetriever();
    m.setDataSource(Edite.addchroma);
    Bitmap f=m.getFrameAtTime(800);
    RetroInstance retroInstance = new RetroInstance.Builder(thisContext)
            .softwareDecoder(false)
            .setFrameSizeDp(1000)
            .create();

   Debog.DebogE("addDropper()p"+alphaMovieView.getCurrentPosition());
   // f.setPremultiplied(false);
    Drawable drawable = new BitmapDrawable(getResources(), f);
   EyeDropperx eyeDropperx= (EyeDropperx)lv.findViewById(R.id.eyed);
   ed=eyeDropperx;
    retroInstance.load(Edite.addchroma, (long) alphaMovieView.getCurrentPosition(),eyeDropperx.hashCode(), file -> eyeDropperx.setPaletteDrawable(new BitmapDrawable(file.getAbsolutePath())));
     //      eyeDropperx .setPaletteDrawable(drawable);
           eyeDropperx.setSelectorDrawable(ContextCompat.getDrawable(context,R.drawable.wheel));
           eyeDropperx.setColorListener(new ColorEnvelopeListener() {
               @Override
               public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                   AlphaMovieView.color=envelope.getColor();
                   ed.setColor(envelope.getColor());
                   Debog.DebogE(envelope.getHexCode());
                   BlankFragment.f.setBackgroundTintList(ColorStateList.valueOf(ed.getColor()));
               }
           });
    unhide();
    Debog.DebogE("ggggggggggggggg"+alphaMovieView.getHeight());
    BubbleFlag bubbleFlag=new BubbleFlag(context);
    bubbleFlag.setFlagMode(FlagMode.FADE);
    eyeDropperx.setFlagView(bubbleFlag);
    EyeDropper eyeDropper= new EyeDropper(alphaMovieView, new EyeDropper.ColorSelectionListener() {
        @Override
        public void onColorSelected(int i) {
            Log.e("color",""+i);
AlphaMovieView.color=i;
// AlphaMovieView.x=alphaMovieView.getX();
// AlphaMovieView.y=alphaMovieView.getY();

//alphaMovieView.color(i);

       //     alphaMovieView.setVisibility(View.GONE);
    //    AlphaMovieView alphaMovieView1=new AlphaMovieView(main.getContext(),AlphaMovieView.a);
   //    ((ConstraintLayout)main.findViewById(R.id.c)).addView(alphaMovieView1,0);
   //    ConstraintLayout l= (ConstraintLayout)main.findViewById(R.id.c);
   //     ConstraintSet set = new ConstraintSet();
   //    set.clone(thisContext, R.layout.e);
   //  alphaMovieView1.setLayoutParams(new ConstraintLayout.LayoutParams(400,400));
   //  alphaMovieView1.setX(AlphaMovieView.x);
   //  alphaMovieView1.setId(findId());
   //  alphaMovieView1.setVisibility(View.VISIBLE);
   //  alphaMovieView1.setY(AlphaMovieView.y);
   //  set.constrainDefaultWidth(alphaMovieView1.getId(), ConstraintSet.MATCH_CONSTRAINT_SPREAD);
   //   set.applyTo(l);
        }
    });
  // eyeDropper.setD(alphaMovieView.getMediaPlayer().getDuration());
}
private void setS(){
//  com.sherazkhilji.videffects.view.VideoSurfaceView vd=lv.findViewById(R.id.v);
//  MediaPlayer mp=new MediaPlayer();
//  try {
//      mp.setDataSource(setVideoPath);
//  } catch (IOException e) {
//      e.printStackTrace();
//  }
//  vd.init(mp, new ShaderInterface() {
//      @Override
//      public String getShader(GLSurfaceView glSurfaceView) {
//          return"#extension GL_OES_EGL_image_external : require\n" +
//                  "            precision mediump float;\n" +
//                  "            varying vec2 vTextureCoord;\n" +
//                  "            uniform samplerExternalOES sTexture;\n" +
//                  "            varying mediump float text_alpha_out;\n" +
//                  "            void main() {\n" +
//                  "              vec4 color = texture2D(sTexture, vTextureCoord);\n" +
//                  "              if (color.g - color.r >= 0.1 &amp;&amp; color.g - color.b >= 0.1) {\n" +
//                  "                  gl_FragColor = vec4(color.r, (color.r + color.b) / 2.0, color.b, 1.0 - color.g);\n" +
//                  "              } else {\n" +
//                  "                  gl_FragColor = vec4(color.r, color.g, color.b, color.a);\n" +
//                  "              }\n" +
//                  "            }";
//      }
//  });
}
private RetroInstance getRetor() {
   RetroInstance retroInstance = new RetroInstance.Builder(thisContext)
            .softwareDecoder(false)
            .setFrameSizeDp(180)
            .create();
    return retroInstance;
}
    @Override
    public void onResume() {
        super.onResume();

    }

    public int findId(){
        View v = main.findViewById(id);
        while (v != null){
            v = main.findViewById(++id);
        }
        return id++;
    }
    @Override
    public void onPause() {
        super.onPause();
    }
}