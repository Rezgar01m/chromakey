package com.chromakeyland;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chromakeyland.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
public static int rezgar=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
   //    binding.w.setY(1200.f);
   //    binding.fab.setX(220.f);
   //   binding.fab3.setX(540.f);
   //   binding.w.setX(10.f);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Edite.y=0;
                Intent i=new Intent(MainActivity.this, Edite.class);
                startActivity(i);
            }
        });
        if (Build.VERSION.SDK_INT >= 23) {
            if (MainActivity.this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.v("e", "Permission is granted");

            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
            //      getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,ItemFragment.newInstance(2)).commit();
        }}

    @Override
    protected void onPause() {
        super.onPause();
    }

  // @Override
  // protected void onResume() {
  //     super.onResume();
  // }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}