package com.example.wheretowatch;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.PopupMenu;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RankingFragment extends Fragment {

    private Spinner spinner;
    private static final String[] genre = {"로맨스", "공포/ 스릴러", "SF/ 판타지", "액션", "코미디"};
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private GridLayoutManager gridLayoutManager;
    private ArrayList<Movie> movies = new ArrayList<Movie>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ranking, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinner = (Spinner)view.findViewById(R.id.spinner_genre);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, genre);
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("menu", position+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        for(int i=0; i<5; i++) {
//            movies.add(new ItemMovie(R.drawable.about));
//            movies.add(new ItemMovie(R.drawable.notebook));
//        }

        recyclerView = (RecyclerView)view.findViewById(R.id.rvRanking);
        adapter = new RecyclerViewAdapter(getContext(), movies);

        gridLayoutManager = new GridLayoutManager(getContext(), 2);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}

