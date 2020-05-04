package com.example.rockstarmoviereview;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import java.util.List;

import butterknife.BindString;

import static java.lang.Math.min;
import static java.lang.Thread.sleep;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.CustomViewHolder> {
    Context context;
    private List<ListItem.Results> results;
    int view_holder_category;
    String IMAGE_PREFIX="https://image.tmdb.org/t/p/w500";

    public class CustomViewHolder extends RecyclerView.ViewHolder{
            private TextView movie_name;
            private ImageView icon;
            CustomViewHolder(View itemView){
                super(itemView);
                movie_name=itemView.findViewById(R.id.movie_name);
                icon=itemView.findViewById(R.id.icon);
            }
    }

    public ListViewAdapter(List<ListItem.Results> results,Context context,int view_holder_category){
        this.results=results;
        this.context=context;
        this.view_holder_category=view_holder_category;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_content, parent, false);
        CustomViewHolder viewHolder=new CustomViewHolder(v);
        Log.v("ALERT","view_holder_category: "+view_holder_category);
        if(view_holder_category==0)
        {
            viewHolder.icon.getLayoutParams().height=400;viewHolder.icon.getLayoutParams().width=600;
            viewHolder.icon.setScaleX(2);
            viewHolder.icon.setScaleY(2);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        /*For testing when api call doesn't work
        holder.movie_name.setText("Movie Name");
        holder.icon.setImageResource(R.drawable.ic_launcher_background);
        */
        //holder.icon.setMinimumWidth();
        if(results.get(position).getBackdrop_path()!=null)Log.v("ALERT",results.get(position).getBackdrop_path());
        if (results.get(position).getMovieName()==null)holder.movie_name.setText("Movie Name");
        else holder.movie_name.setText(results.get(position).getMovieName());
        if(results.get(position).getBackdrop_path()==null)holder.icon.setImageResource(R.mipmap.image_loading);
        else {
            Log.v("ALERT",IMAGE_PREFIX+results.get(position).getBackdrop_path());
            //See if we can cache the images

            Picasso.with(context)
                    .load(IMAGE_PREFIX+results.get(position).getBackdrop_path())
                    .placeholder(R.mipmap.image_loading)
                    .into(holder.icon);

        }
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

        /*For testing while api call is down
        return 5;
        */
        return results.size();
    }
}
