package com.example.wheretowatch;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainFragment extends Fragment {

    private String start_url;
    private RecyclerView recyclerView_romance, recyclerView_thrill, recyclerView_sf, recyclerView_action, recyclerView_comedy;
    private RecyclerViewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Movie> mMovieList = new ArrayList<Movie>();
    TextView txt_romance; //리싸이클러뷰 테스트용 text

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        String url = "https://api.themoviedb.org/3/movie/upcoming?api_key=826cafb8c742dafa93adc0139b528230&language=ko-KR&page=1";

        recyclerView_romance = (RecyclerView) view.findViewById(R.id.main_rvRanking_romance);
        recyclerView_thrill = (RecyclerView) view.findViewById(R.id.main_rvRanking_thrill);
        recyclerView_sf = (RecyclerView) view.findViewById(R.id.main_rvRanking_sf);
        recyclerView_action = (RecyclerView) view.findViewById(R.id.main_rvRanking_action);
        recyclerView_comedy = (RecyclerView) view.findViewById(R.id.main_rvRanking_comedy);

        // 임시 클릭리스너
        txt_romance = (TextView) view.findViewById(R.id.txt_romance);
        txt_romance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 상세 액티비티로 이동
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                startActivity(intent);
            }
        });

        new MyAsyncTask(getContext(), new TaskCompleted() {
            @Override
            public void onTaskComplete(Movie[] result) {
                for(Movie p : result){
                    mMovieList.add(p);
                }
                adapter = new RecyclerViewAdapter(getContext() , mMovieList);
                recyclerView_romance.setAdapter(adapter);

                adapter = new RecyclerViewAdapter(getContext() , mMovieList);
                recyclerView_thrill.setAdapter(adapter);

                adapter = new RecyclerViewAdapter(getContext() , mMovieList);
                recyclerView_sf.setAdapter(adapter);

                adapter = new RecyclerViewAdapter(getContext() , mMovieList);
                recyclerView_action.setAdapter(adapter);

                adapter = new RecyclerViewAdapter(getContext() , mMovieList);
                recyclerView_comedy.setAdapter(adapter);

            }
        }).execute(url);


        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_romance.setHasFixedSize(true);
        recyclerView_romance.setLayoutManager(linearLayoutManager);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_thrill.setHasFixedSize(true);
        recyclerView_thrill.setLayoutManager(linearLayoutManager);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_sf.setHasFixedSize(true);
        recyclerView_sf.setLayoutManager(linearLayoutManager);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_action.setHasFixedSize(true);
        recyclerView_action.setLayoutManager(linearLayoutManager);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_comedy.setHasFixedSize(true);
        recyclerView_comedy.setLayoutManager(linearLayoutManager);

        return view;


    }




}