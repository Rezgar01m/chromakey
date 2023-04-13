package com.chromakeyland.placeholder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.chromakeyland.AlphaMovieView;
import com.chromakeyland.Edite;
import com.chromakeyland.EditorR;
import com.chromakeyland.databinding.ExBinding;
import com.chromakeyland.i.Ex;
import com.chromakeyland.i.Paths;
import com.chromakeyland.i.Save;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import kotlin.io.path.PathsKt;

public class EXActivity extends AppCompatActivity implements Ex {
    public static ArrayList<Save> saves=new ArrayList<>();
    EditorR editorR = new EditorR(EXActivity.this,this);
    ExBinding binding;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 23) {
            String[] PERMISSIONS = {android.Manifest.permission.READ_EXTERNAL_STORAGE,android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (!hasPermissions(EXActivity.this, PERMISSIONS)) {
                ActivityCompat.requestPermissions(EXActivity.this, PERMISSIONS, 1 );
            }}
            binding = ExBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
binding.fillableLoader.setSvgPath(Paths.INDOMINUS_REX);
binding.fillableLoader.setFillDuration(10000);

binding.fillableLoader.start();
        binding.elasticDownloadView.startIntro();
binding.elasticDownloadView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        int i = 0;
        while (true) {
            i++;
            binding.elasticDownloadView.setProgress(i);
            if (i == 100) break;
        }
    }
});
editorR.setX((int) AlphaMovieView.x);
        editorR.setY((int) AlphaMovieView.y);
        editorR.setW(AlphaMovieView.w);
        editorR.setH(AlphaMovieView.h);
        editorR.setImage(new File(Edite.path));
        editorR.setMp4(new File(Edite.addchroma));
        editorR.setOut(new File(getExternalFilesDir(null)+new File(Edite.path).getName()));
        editorR.setSave(sv());
        editorR.start();
        try {

                new File(getExternalFilesDir(null),"hi").createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private synchronized void run(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Save save:saves) {

                }
            }
        }).start();
    //    binding.elasticDownloadView.startIntro();

    }

    @Override
    public void ex(String s) {
binding.txt.setText(s);
       // binding.elasticDownloadView.setProgress();

    }

    @Override
    public void f() {
binding.elasticDownloadView.fail();

    }

    @Override
    public void progres(float f) {
        float i =   f * 100f;
        if (i >= 0 && i <= 100) {
            binding.elasticDownloadView.setProgress(i);
        }



    }
    @Override
    public void s() {
binding.elasticDownloadView.success();
    }
        private static boolean hasPermissions(Context context, String... permissions) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
                for (String permission : permissions) {
                    if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                        return false;
                    }
                }
            }
            return true;
        }
        private Save sv(){
           Save sv= new Save();
           sv.setColor(AlphaMovieView.color);
        return sv;
        }
}
