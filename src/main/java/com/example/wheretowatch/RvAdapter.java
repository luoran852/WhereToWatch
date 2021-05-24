package com.example.wheretowatch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Math.round;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {

    private ArrayList<SearchedMovie> contents;
    private Context context;

    public RvAdapter(Context context, ArrayList searchData) {
        this.context = context;
        contents = searchData;
        initMap();
    }

    @NonNull
    @Override
    public RvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapter.ViewHolder holder, int position) {

        holder.searchedTitle.setText(contents.get(position).getTitle());
        holder.searchedYear.setText(contents.get(position).getRelease_date());
        holder.searchedPopularity.setText(contents.get(position).getVote_average()+"");
        if (contents.get(position).getAdult()) {
            holder.searchedAge.setVisibility(View.VISIBLE);
        }

        String url = "https://image.tmdb.org/t/p/w500" + contents.get(position).getPoster_path();

        Glide.with(context)
                .load(url)
                .into(holder.searchedPoster);
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView searchedPoster;
        TextView searchedTitle;
        TextView searchedGenre;
        TextView searchedYear;
        TextView searchedAge;
        TextView searchedPopularity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            searchedPoster = (ImageView)itemView.findViewById(R.id.searchedPoster);
            searchedTitle = (TextView)itemView.findViewById(R.id.searchedTitle);
            searchedGenre = (TextView)itemView.findViewById(R.id.searchedGenre);
            searchedYear = (TextView)itemView.findViewById(R.id.searchedYear);
            searchedAge = (TextView)itemView.findViewById(R.id.searchedAge);
            searchedPopularity = itemView.findViewById(R.id.searchedPopularity);
        }
    }

    public void initMap() {
        HashMap<Integer, String> genre = new HashMap<>();
        genre.put(28, "ACTION");
    }
}
