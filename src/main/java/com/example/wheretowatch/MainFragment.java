package com.example.wheretowatch;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
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
    //테스트용 주석
    private Spinner type_spinner, genre_spinner;
    private String start_url;
    private RecyclerView recyclerView_romance, recyclerView_thrill, recyclerView_sf, recyclerView_action, recyclerView_comedy;
    private static final String[] type = {"영화", "드라마"};
    private static final String[] genre = {"로맨스", "공포/ 스릴러", "SF/ 판타지", "액션", "코미디"};
    private RecyclerViewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Movie> mMovieList = new ArrayList<Movie>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        String url = "https://api.themoviedb.org/3/movie/popular?api_key=826cafb8c742dafa93adc0139b528230&language=ko-KR&page=1";

        recyclerView_romance = (RecyclerView)view.findViewById(R.id.main_rvRanking_romance);

        new MyAsyncTask(getContext(), new TaskCompleted() {
            @Override
            public void onTaskComplete(Movie[] result) {
                for(Movie p : result){
                    mMovieList.add(p);
                }
                adapter = new RecyclerViewAdapter(getContext(), mMovieList);
                recyclerView_romance.setAdapter(adapter);
            }
        }).execute(url);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_romance.setHasFixedSize(true);
        recyclerView_romance.setLayoutManager(linearLayoutManager);
        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //종류 spinner
        type_spinner = (Spinner) view.findViewById(R.id.main_spinner_type);
        ArrayAdapter<String> typeSpinnerAdapter = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, type);
        typeSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        type_spinner.setAdapter(typeSpinnerAdapter);
        type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("menu", position + "");


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //장르 spinner
        genre_spinner = (Spinner) view.findViewById(R.id.main_spinner_genre);
        ArrayAdapter<String> genreSpinnerAdapter = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, genre);
        genreSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        genre_spinner.setAdapter(genreSpinnerAdapter);
        genre_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("menu", position + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }



<<<<<<< HEAD
}
=======
}
>>>>>>> 7376e321a39499d0d4c5137d62962e6adadccda8
