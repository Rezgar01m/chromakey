package com.chromakeyland;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;



import com.chromakeyland.databinding.EditeBinding;
import com.chromakeyland.i.i;
import com.chromakeyland.placeholder.BlankFragment;
import com.chromakeyland.placeholder.E;
import com.chromakeyland.placeholder.EXActivity;
import com.chromakeyland.view.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Edite extends AppCompatActivity {
public static int y=0;
public static String addchroma;
public static String path;
    private EditeBinding binding;
    public ArrayList<Integer> p;
    public static int adapterEdite=0;
private String t="انتخاب فایل";
public EditorR editorR;
    BlankFragment b;
    TabLayout tabs;
    boolean test=false;
@SuppressLint("StaticFieldLeak")
public  static BlankFragment blankFragment=new BlankFragment(path);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    binding = EditeBinding.inflate(getLayoutInflater());

    setContentView(binding.getRoot());

    setSupportActionBar(binding.tr);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(),this);
         tabs = binding.tabs;
        binding.viewPager.setAdapter(sectionsPagerAdapter);
        tabs.setupWithViewPager(binding.viewPager);
binding.fe.setVisibility(View.GONE);
        tabs.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {


                tabs.setupWithViewPager(binding.viewPager);

                tabs.removeOnLayoutChangeListener(this);
            }
        });
        if (y==0) {
            tabs.setVisibility(View.VISIBLE);
            binding.viewPager.setVisibility(View.VISIBLE);
      //      getSupportFragmentManager().beginTransaction()
            //        .replace(R.id.fe,ItemFragment.newInstance(2)).commit();

           f2();
        }else if (y==1){

binding.title.setText(t);
            f1();
        }
binding.save.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (path==null){
//blankFragment.alphaMovieView.getMediaPlayer().su
        }else
            if (test)
        E.run(b.editorR,addchroma,path,Edite.this);
            else {

                Intent intent=new Intent(Edite.this,EXActivity.class);
                startActivity(intent);
            }
    }
});
    }
    public void f1(){
        binding.fe.setVisibility(View.VISIBLE);
        Log.e("f1","ok");
        tabs.setVisibility(View.GONE);
        binding.viewPager.setVisibility(View.GONE);

        ((ViewGroup)binding.tabs.getChildAt(0)).getChildAt(0);
        BlankFragment.context=Edite.this;
        blankFragment=new BlankFragment(path);
 b=blankFragment;
        b.setEdite(this);

       getSupportFragmentManager().beginTransaction()
                .replace(R.id.fe,b).commit();
        if (adapterEdite==1){

            b.setI(new i() {
                @Override
                public void g() {
                    b.addc();
                }
            });
        }
    }
    public void f2(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fe,ItemFragment.newInstance(1,this)).commit();
    }

    @Override
    protected void onPause() {
       // blankFragment.alphaMovieView.setVisibility(View.GONE);
    //    blankFragment.videoView.reset();
        super.onPause();
    }
    /*
    private void f3(){
        TimelineFragment mFragment = new TimelineFragment();

//Set data
        mFragment.setData(loadDataInTimeline(), TimelineGroupType.MONTH);

//Set configurations
     //   mFragment.addOnClickListener(this);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fe, mFragment);

        transaction.commit();
    }
    private ArrayList<TimelineObject> loadDataInTimeline() {
        //Load the data in a list and sort it by times in milli
        ArrayList<TimelineObject> objs = new ArrayList<>();
        objs.add(new TestO(Long.parseLong("1483196400000"), "A", "/storage/emulated/0/Pictures/bin/InShot_20220214_170731002.mp4"));
      //  objs.add(new TestO(Long.parseLong("1483196400000"), "A", "url"));
      //  objs.add(new TestO(Long.parseLong("1483196400000"), "B", "url" ));
      //  objs.add(new TestO(Long.parseLong("1483196400000"), "C" , "url"));
      //  objs.add(new TestO(Long.parseLong("1484146800000"), "D" ,"url"));
        //Sort and return
        //Sort logic
        return objs;
    }
    public static class TestO implements TimelineObject {
        long timestamp;
        String name, url;
public TestO(Long timestamp,String name,String url){
    this.name=name;
    this.timestamp=timestamp;
    this.url=url;
}

        @Override
        public long getTimestamp() {
            return timestamp;
        }
        @Override
        public String getTitle() {
            return name;
        }
        @Override
        public String getImageUrl() {
            return url;
        }*/
}