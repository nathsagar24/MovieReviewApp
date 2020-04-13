package com.example.rockstarmoviereview;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.CustomViewHolder> {
    Context context;
    private List<ListItem.Results> results;

    public class CustomViewHolder extends RecyclerView.ViewHolder{
            private TextView movie_name;
            private ImageView icon;
            CustomViewHolder(View itemView){
                super(itemView);
                movie_name=itemView.findViewById(R.id.movie_name);
                icon=itemView.findViewById(R.id.icon);
            }
    }

    public ListViewAdapter(List<ListItem.Results> results,Context context){
        this.results=results;
        this.context=context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_content,parent,false);
        CustomViewHolder viewHolder=new CustomViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.movie_name.setText(results.get(position).getMovieName());
        Picasso.with(holder.icon.getContext())
                .load("https://image.tmdb.org/t/p/w500"+results.get(position).getBackdrop_path())
                .into(holder.icon);
        holder.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(context,MovieDetailsActivity.class);
                        intent.putExtra("Id",results.get(position).getId());
                        context.startActivity(intent);
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
}
