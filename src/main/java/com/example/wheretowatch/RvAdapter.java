package com.example.wheretowatch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {

    private ArrayList<ItemSearched> contents;

    public RvAdapter(ArrayList searchData) {
        contents = searchData;
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
        holder.searchedPoster.setImageResource(contents.get(position).image);
        holder.searchedTitle.setText(contents.get(position).title);
        holder.searchedYear.setText(contents.get(position).year);
        holder.searchedAge.setText(contents.get(position).age);
        holder.searchedGenre.setText(contents.get(position).genre);
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            searchedPoster = (ImageView)itemView.findViewById(R.id.searchedPoster);
            searchedTitle = (TextView)itemView.findViewById(R.id.searchedTitle);
            searchedGenre = (TextView)itemView.findViewById(R.id.searchedGenre);
            searchedYear = (TextView)itemView.findViewById(R.id.searchedYear);
            searchedAge = (TextView)itemView.findViewById(R.id.searchedAge);
        }
    }
}
