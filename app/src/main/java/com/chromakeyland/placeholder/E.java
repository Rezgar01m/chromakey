package com.chromakeyland.placeholder;

import android.content.Context;

import com.chromakeyland.EditorR;

import java.io.File;

public class E {
public static void run(EditorR editorR, String b, String mp4, Context c){
    editorR.setImage(new File("/storage/emulated/0/Pictures/bin/InShot_20220214_170703113.jpg"));
    editorR.setMp4(new File("/storage/emulated/0/Pictures/bin/InShot_20220214_170731002.mp4"));

    editorR.setOut(new File(c.getExternalFilesDir(null).getAbsolutePath()+"/rezgar.mp4"));
    editorR.start(1);
}
}
