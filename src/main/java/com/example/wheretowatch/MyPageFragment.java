package com.example.wheretowatch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyPageFragment extends Fragment {

    private RecyclerView recyclerView_keep, recyclerView_record;
    private RecyclerViewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<ItemMovie> movies = new ArrayList<ItemMovie>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mypage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        for(int i=0; i<5; i++) {
            movies.add(new ItemMovie(R.drawable.about));
            movies.add(new ItemMovie(R.drawable.notebook));
        }

        //recyclerview 보고싶어요(찜하기+평가하기)
        recyclerView_keep = (RecyclerView)view.findViewById(R.id.mypage_rvRanking_keep);
        adapter = new RecyclerViewAdapter(getContext(), movies);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_keep.setHasFixedSize(true);
        recyclerView_keep.setLayoutManager(linearLayoutManager);
        recyclerView_keep.setAdapter(adapter);

        //recyclerview 시청기록
        recyclerView_record = (RecyclerView)view.findViewById(R.id.mypage_rvRanking_record);
        adapter = new RecyclerViewAdapter(getContext(), movies);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_record.setHasFixedSize(true);
        recyclerView_record.setLayoutManager(linearLayoutManager);
        recyclerView_record.setAdapter(adapter);

    }
}
