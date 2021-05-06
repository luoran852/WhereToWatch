package com.example.wheretowatch;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainFragment extends Fragment {
//테스트용 주석
    private Spinner type_spinner, genre_spinner;
    private static final String[] type = {"영화", "드라마"};
    private static final String[] genre = {"로맨스", "공포/ 스릴러", "SF/ 판타지", "액션", "코미디"};
    private RecyclerView recyclerView_romance, recyclerView_thrill, recyclerView_sf, recyclerView_action, recyclerView_comedy;
    private RecyclerViewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<ItemMovie> movies = new ArrayList<ItemMovie>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //종류 spinner
        type_spinner = (Spinner)view.findViewById(R.id.main_spinner_type);
        ArrayAdapter<String> typeSpinnerAdapter = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, type);
        typeSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        type_spinner.setAdapter(typeSpinnerAdapter);
        type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("menu", position+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //장르 spinner
        genre_spinner = (Spinner)view.findViewById(R.id.main_spinner_genre);
        ArrayAdapter<String> genreSpinnerAdapter = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, genre);
        genreSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        genre_spinner.setAdapter(genreSpinnerAdapter);
        genre_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("menu", position+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        for(int i=0; i<5; i++) {
            movies.add(new ItemMovie(R.drawable.about));
            movies.add(new ItemMovie(R.drawable.notebook));
        }

        //recyclerview 로맨스
        recyclerView_romance = (RecyclerView)view.findViewById(R.id.main_rvRanking_romance);
        adapter = new RecyclerViewAdapter(getContext(), movies);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_romance.setHasFixedSize(true);
        recyclerView_romance.setLayoutManager(linearLayoutManager);
        recyclerView_romance.setAdapter(adapter);

        //recyclerview 공포/스릴러
        recyclerView_thrill = (RecyclerView)view.findViewById(R.id.main_rvRanking_thrill);
        adapter = new RecyclerViewAdapter(getContext(), movies);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_thrill.setHasFixedSize(true);
        recyclerView_thrill.setLayoutManager(linearLayoutManager);
        recyclerView_thrill.setAdapter(adapter);

        //recyclerview SF/판타지
        recyclerView_sf = (RecyclerView)view.findViewById(R.id.main_rvRanking_sf);
        adapter = new RecyclerViewAdapter(getContext(), movies);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_sf.setHasFixedSize(true);
        recyclerView_sf.setLayoutManager(linearLayoutManager);
        recyclerView_sf.setAdapter(adapter);

        //recyclerview 액션
        recyclerView_action = (RecyclerView)view.findViewById(R.id.main_rvRanking_action);
        adapter = new RecyclerViewAdapter(getContext(), movies);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_action.setHasFixedSize(true);
        recyclerView_action.setLayoutManager(linearLayoutManager);
        recyclerView_action.setAdapter(adapter);

        //recyclerview 코미디
        recyclerView_comedy = (RecyclerView)view.findViewById(R.id.main_rvRanking_comedy);
        adapter = new RecyclerViewAdapter(getContext(), movies);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_comedy.setHasFixedSize(true);
        recyclerView_comedy.setLayoutManager(linearLayoutManager);
        recyclerView_comedy.setAdapter(adapter);
    }
}
