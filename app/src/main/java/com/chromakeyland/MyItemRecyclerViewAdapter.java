package com.chromakeyland;

import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chromakeyland.i.i;
import com.chromakeyland.placeholder.BlankFragment;
import com.chromakeyland.placeholder.PlaceholderContent;
import com.chromakeyland.placeholder.PlaceholderContent.PlaceholderItem;
import com.chromakeyland.databinding.FragmentItemBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private List<PlaceholderItem> mValues;
Edite edite;

    public MyItemRecyclerViewAdapter(List<PlaceholderItem> items) {
        mValues = items;
    }
    public MyItemRecyclerViewAdapter() {

    }
    public void setmValues(List<PlaceholderItem> items){
        mValues = items;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    public void setEdite(Edite edite) {
        this.edite = edite;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).content);
        holder.mContentView.setImageBitmap(mValues.get(position).bitmap);
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        if (Edite.adapterEdite==1){
            Edite.addchroma=mValues.get(position).id;
            edite.f1();
        }else {
            Edite.path=mValues.get(position).id;
            edite.f1();
            PlaceholderContent.t.interrupt();
        }
    }
});
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final ImageView mContentView;
        public PlaceholderItem mItem;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            mIdView = binding.tvTimelineHorizontalCardName;
            mContentView = binding.ivHorizontalCardImage;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + "'";
        }
    }
}