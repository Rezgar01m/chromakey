package com.chromakeyland.placeholder;



import android.content.Context;
import android.view.View;
import android.widget.SeekBar;

import com.chromakeyland.AlphaMovieView;
import com.chromakeyland.R;
import com.chromakeyland.i.AdapterE;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class BaseB {
    Context context;
    View view;
    SeekBar a,ac;
    BottomSheetBehavior bottomSheetBehavior;
    AlphaMovieView alphaMovieView;
    public BaseB(View  view, AlphaMovieView alphaMovieView){
       // this.context=context;
        this.view=view;
        init();
        this.alphaMovieView=alphaMovieView;
    }
    public void init(){
         bottomSheetBehavior=BottomSheetBehavior.from(view.findViewById(R.id.design_bottom_sheet));
         a=view.findViewById(R.id.ac);
         a.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
             @Override
             public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                 double newa= i/100.f;
                 Debog.DebogE("on"+newa+"i"+i);
                 String s= AdapterE.main.replace("float accuracy"+AdapterE.i+" = 0.040000;","float accuracy"+AdapterE.i+" = "+newa+";");
                 alphaMovieView.shader(s.replace("null",""));
             }

             @Override
             public void onStartTrackingTouch(SeekBar seekBar) {

             }

             @Override
             public void onStopTrackingTouch(SeekBar seekBar) {

             }
         });
         ac=view.findViewById(R.id.a_c);
         ac.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
             @Override
             public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                 double newa= i/100.f;
                 String s= AdapterE.main.replace("float accuracy = 0.300000;","float accuracy = "+newa+";");
                 alphaMovieView.shader(s.replace("null",""));
             }

             @Override
             public void onStartTrackingTouch(SeekBar seekBar) {

             }

             @Override
             public void onStopTrackingTouch(SeekBar seekBar) {

             }
         });

    }
    public void Hideb(){
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }
    public void exB(){
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
}
