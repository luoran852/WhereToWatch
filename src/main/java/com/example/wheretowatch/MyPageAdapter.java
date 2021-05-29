package com.example.wheretowatch;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyPageAdapter extends RecyclerView.Adapter<MyPageAdapter.RecyclerViewHolders> {

    private ArrayList<Movie> mMovieList;
    private Context context;
    private OnMovieClickListener mOnMovieClickListener;

    //constructor
    public MyPageAdapter(Context context, ArrayList<Movie> itemList, OnMovieClickListener mOnMovieClickListener) {
        super();
        this.context = context;
        this.mMovieList = itemList;
        this.mOnMovieClickListener = mOnMovieClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.img_item, parent, false);
        return new RecyclerViewHolders(view, mOnMovieClickListener);
    }




    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolders holder, int position) {

        //String url = mMovieList.get(position).getPoster_path();
        String url = "https://image.tmdb.org/t/p/w500" + mMovieList.get(position).getPoster_path();

        Glide.with(holder.itemView.getContext())
                .load(url)
                .into(holder.poster);
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, DetailActivity.class);
//                intent.putExtra("id", mMovieList.get(position).getId());
//                intent.putExtra("title", mMovieList.get(position).getTitle());
//                intent.putExtra("original_title", mMovieList.get(position).getOriginal_title());
//                intent.putExtra("poster_path", mMovieList.get(position).getPoster_path());
//                intent.putExtra("overview", mMovieList.get(position).getOverview());
//                intent.putExtra("release_date", mMovieList.get(position).getRelease_date());
//                intent.putExtra("name", mMovieList.get(position).getName());
//
//                context.startActivity(intent);
//            }
//        });
    }


    @Override
    public int getItemCount()
    {
        return this.mMovieList.size();
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView poster;
        OnMovieClickListener mOnMovieClickListener;

        public RecyclerViewHolders(View itemView, OnMovieClickListener mOnMovieClickListener) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.ivPoster);
            this.mOnMovieClickListener = mOnMovieClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnMovieClickListener.onMovieClick(getAdapterPosition(), mMovieList);
        }
    }

    public interface OnMovieClickListener {
        void onMovieClick(int position, ArrayList<Movie> mMovieList);
    }

}