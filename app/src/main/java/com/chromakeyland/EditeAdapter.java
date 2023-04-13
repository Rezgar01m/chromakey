package com.chromakeyland;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chromakeyland.databinding.EditebsBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.chromakeyland.databinding.FragmentItemBinding;
import com.chromakeyland.placeholder.BlankFragment;
import com.chromakeyland.placeholder.PlaceholderContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class EditeAdapter extends RecyclerView.Adapter<EditeAdapter.ViewHolder> {

    private final List<PlaceholderContent.PlaceholderItem> mValues;
private LayoutInflater context;
private  BlankFragment blankFragment;
    public EditeAdapter(List<PlaceholderContent.PlaceholderItem> items, Context c,BlankFragment b) {
        mValues = items;
        context=LayoutInflater.from(c);
        blankFragment=b;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EditeAdapter.ViewHolder(EditebsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setBackgroundResource(mValues.get(position).imge);
       holder.text.setText(mValues.get(position).id);

    holder.mIdView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.e("1","oo");
            if (position==0){
                Edite.adapterEdite=1;
                blankFragment.edite.f2();
            }else if (position==1){
                if (Edite.addchroma==null)
                    Toast.makeText(holder.mIdView.getContext(), "please add chroma", Toast.LENGTH_SHORT).show();
                else
                blankFragment.addDropper();
            }else if (position==3) {
                blankFragment.baseB.exB();
                blankFragment.a();
            }
        }
    });


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
private void addc(){
        blankFragment.alphaMovieView.setVisibility(View.VISIBLE);
    try {
        FileInputStream fileInputStream = new FileInputStream(new File(Edite.addchroma));
        blankFragment.alphaMovieView.setVideoFromFile(fileInputStream.getFD());
    } catch (IOException e) {
        e.printStackTrace();
    }

    blankFragment.alphaMovieView.setEnabled(true);
}
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView text;
        public PlaceholderContent.PlaceholderItem mItem;

        @SuppressLint("ResourceType")
        public ViewHolder(EditebsBinding binding) {
            super(binding.getRoot());
             mIdView=binding.fabe;
             text=binding.text;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + "e" + "'";
        }
    }
}