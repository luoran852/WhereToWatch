package com.example.wheretowatch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class OttRvAdapter extends RecyclerView.Adapter<OttRvAdapter.ViewHolder> {

    private ArrayList<String> contents;
    private Context context;

    public OttRvAdapter(Context context, ArrayList searchData) {
        this.context = context;
        contents = searchData;
    }

    @NonNull
    @Override
    public OttRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ott, parent,false);
        OttRvAdapter.ViewHolder viewHolder = new OttRvAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OttRvAdapter.ViewHolder holder, int position) {
        String url = "https://image.tmdb.org/t/p/w500" + contents.get(position);
        Glide.with(context)
                .load(url)
                .into(holder.img_ott);

    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_ott;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_ott = (ImageView)itemView.findViewById(R.id.ott_img);
        }
    }
}
