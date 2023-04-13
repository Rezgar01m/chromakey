package com.chromakeyland.placeholder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.chromakeyland.Edite;
import com.chromakeyland.ItemFragment;
import com.chromakeyland.MyItemRecyclerViewAdapter;
import com.chromakeyland.R;
import com.chromakeyland.i.i;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class PlaceholderContent {

    /**
     * An array of sample (placeholder) items.
     */
    public static MyItemRecyclerViewAdapter myItemRecyclerViewAdapter;
    public static MyItemRecyclerViewAdapter myItemRecyclerViewAdapter1;
    public static final List<PlaceholderItem> ITEMS = new ArrayList<PlaceholderItem>();
    public static final List<PlaceholderItem> ITEMS2 = new ArrayList<PlaceholderItem>();
    public static final List<PlaceholderItem> ITEMim = new ArrayList<PlaceholderItem>();
    public static com.chromakeyland.i.i i;
    public static ItemFragment itemFragment;
    /**
     * A map of sample (placeholder) items, by ID.
     */
    public static final Map<String, PlaceholderItem> ITEM_MAP = new HashMap<String, PlaceholderItem>();
    public static final Map<String, PlaceholderItem> Im = new HashMap<String, PlaceholderItem>();
    private static final int COUNT = 25;
public static Thread t;
    static {
        addItemim(new PlaceholderItem(R.drawable.icon_newvideo,"add chroma"));
        addItemim(new PlaceholderItem(R.drawable.icon_saturation,"eye Draper "));
        addItemim(new PlaceholderItem(R.drawable.icon_trans_flim04,"    size       "));
        addItemim(new PlaceholderItem(R.drawable.icon_edit_extract,"accuracy"));
        addItemim(new PlaceholderItem(R.drawable.icon_trans_flim04,"null"));
        // Add some sample items.

        File file = Environment.getExternalStorageDirectory();
       // if (file.exists())
        //    findVideos(new File(file.getAbsolutePath()));
    //else

      t= new Thread(new Runnable() {
            @Override
            public void run() {
                findVideos(new File("/storage/emulated/0/"));
            }
        });
      t.start();

    }
    public static void findVideos(File dir){
      //  Log.e("e",dir.getAbsolutePath());
        if (dir.listFiles()==null)
        {

        }else {
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) findVideos(file);
            else if (file.getAbsolutePath().contains(".mp4"))
                addItem(createPlaceholderItem(file.getAbsolutePath()));
            else if (file.getAbsolutePath().contains(".jpg"))
                addItemImage(createPlaceholderItem(file.getAbsolutePath()));
        } }
    }
    private static void addItemim(PlaceholderItem item) {
        ITEMim.add(item);
        ITEM_MAP.put(item.id, item);
    }
    private static void addItemImage(PlaceholderItem item) {
        ITEMS2.add(item);
        ITEM_MAP.put(item.id, item);
        itemFragment.s(new i() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void g() {
                myItemRecyclerViewAdapter1.notifyDataSetChanged();
            }
        });

    }
    private static void addItem(PlaceholderItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
        itemFragment.s(new i() {
            @Override
            public void g() {
                myItemRecyclerViewAdapter.notifyDataSetChanged();

            }
        });

    }

    @SuppressLint("NewApi")
    private static PlaceholderItem createPlaceholderItem(String file) {
        File file1=new File(file);
        Bitmap bmThumbnail ;
if (file.contains(".mp4")) {
   // Debog.DebogE(file);

    synchronized (PlaceholderItem.class) {
        bmThumbnail = ThumbnailUtils.createVideoThumbnail(file1.getAbsolutePath().toString(), MediaStore.Images.Thumbnails.MINI_KIND);

    }
}
else {
   // Debog.DebogE(file);
    BitmapFactory.Options o2 = new BitmapFactory.Options();
    o2.inSampleSize=8;
    bmThumbnail = BitmapFactory.decodeFile(file,o2);


}
PlaceholderItem placeholderItem=new PlaceholderItem(bmThumbnail,file1.getName());
placeholderItem.id=file1.getAbsolutePath();
        return placeholderItem;
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A placeholder item representing a piece of content.
     */
    public static class PlaceholderItem {
        public String id;
        public String content;
        public String details;
        public int imge;
        public Bitmap bitmap;
        public PlaceholderItem(Bitmap bitmap1,String content){
            bitmap=bitmap1;
            this.content=content;
        }
        public PlaceholderItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }
public PlaceholderItem(int i,String text){
            imge=i;
            id=text;
}

        @Override
        public String toString() {
            return content;
        }
    }
}