package com.chromakeyland;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chromakeyland.placeholder.Debog;
import com.chromakeyland.placeholder.PlaceholderContent;

/**
 * A fragment representing a list of Items.
 */
public class ItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    public static int mColumnCount = 4;
    public static Context context;

public static Edite edite;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount, Edite e) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        ItemFragment.mColumnCount=columnCount;
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        edite=e;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
                recyclerView.setLayoutManager(new GridLayoutManager(context,3 ));

MyItemRecyclerViewAdapter myItemRecyclerViewAdapter=new MyItemRecyclerViewAdapter();
myItemRecyclerViewAdapter.edite=edite;

PlaceholderContent.itemFragment=this;
            Debog.DebogE(mColumnCount+"p");
if (mColumnCount==1){
    PlaceholderContent.myItemRecyclerViewAdapter=myItemRecyclerViewAdapter;
    myItemRecyclerViewAdapter.setmValues(PlaceholderContent.ITEMS);
}else {
    PlaceholderContent.myItemRecyclerViewAdapter1=myItemRecyclerViewAdapter;
    myItemRecyclerViewAdapter.setmValues(PlaceholderContent.ITEMS2);
}
            recyclerView.setAdapter(myItemRecyclerViewAdapter);
        }
        return view;
    }
public void s(com.chromakeyland.i.i i){
       try {

           getActivity().runOnUiThread(new Runnable() {
               @Override
               public void run() {
                   i.g();
               }
           });
       }catch (NullPointerException e){

       }
}
boolean g() {
        return true;
}
}