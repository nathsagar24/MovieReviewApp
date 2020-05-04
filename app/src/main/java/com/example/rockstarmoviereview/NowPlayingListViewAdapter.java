package com.example.rockstarmoviereview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.List;

public class NowPlayingListViewAdapter extends ListViewAdapter {

    public NowPlayingListViewAdapter(List<ListItem.Results> results, Context context){
        super(results,context);
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_content_now_playing,parent,false);
        return new CustomViewHolder(v);
    }
}
