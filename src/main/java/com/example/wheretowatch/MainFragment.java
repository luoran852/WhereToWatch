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

import java.io.Serializable;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class MainFragment extends Fragment implements RecyclerViewAdapter.OnMovieClickListener {

    private RecyclerView recyclerView_drama, recyclerView_thrill, recyclerView_sf, recyclerView_action, recyclerView_comedy;
    private RecyclerViewAdapter adapter_romance, adapter_thrill, adapter_sf, adapter_action, adapter_comedy;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Movie> mMovieList = new ArrayList<Movie>();
    private ArrayList<Movie> drama = new ArrayList<Movie>();
    private ArrayList<Movie> horror = new ArrayList<Movie>();
    private ArrayList<Movie> fantasy = new ArrayList<Movie>();
    private ArrayList<Movie> action = new ArrayList<Movie>();
    private ArrayList<Movie> comedy = new ArrayList<Movie>();
    private ArrayList<Integer> etc = new ArrayList<Integer>();
    TextView txt_romance; //리싸이클러뷰 테스트용 text
    private ProgressDialog mDialog;

    private String api_key = "89247ab465eba040acb566dcd1724b96";
    private final String baseUrl = "https://api.themoviedb.org/3/";
    MoviePopularRequest moviePopularRequest;

    //Genre Romance: 10749, Horror/Thriller:36, 53, SF/Fantasy: 14, 878
    //Action: 28, Comedy: 35

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        recyclerView_drama = (RecyclerView) view.findViewById(R.id.main_rvRanking_romance);
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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        moviePopularRequest = retrofit.create(MoviePopularRequest.class);



        //로맨스 장르
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_drama.setHasFixedSize(true);
        recyclerView_drama.setLayoutManager(linearLayoutManager);
        adapter_romance = new RecyclerViewAdapter(getContext(), drama, this);
        recyclerView_drama.setAdapter(adapter_romance);

        //스릴러 장르
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_thrill.setHasFixedSize(true);
        recyclerView_thrill.setLayoutManager(linearLayoutManager);
        adapter_thrill = new RecyclerViewAdapter(getContext(), horror, this);
        recyclerView_thrill.setAdapter(adapter_thrill);

        //판타지 장르
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_sf.setHasFixedSize(true);
        recyclerView_sf.setLayoutManager(linearLayoutManager);
        adapter_sf = new RecyclerViewAdapter(getContext(), fantasy, this);
        recyclerView_sf.setAdapter(adapter_sf);

        //액션 장르
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_action.setHasFixedSize(true);
        recyclerView_action.setLayoutManager(linearLayoutManager);
        adapter_action = new RecyclerViewAdapter(getContext(), action, this);
        recyclerView_action.setAdapter(adapter_action);

        //코미디 장르
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_comedy.setHasFixedSize(true);
        recyclerView_comedy.setLayoutManager(linearLayoutManager);
        adapter_comedy = new RecyclerViewAdapter(getContext(), comedy, this);
        recyclerView_comedy.setAdapter(adapter_comedy);

        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Call<MoviePopularResponse> searchMovie = moviePopularRequest.getMoviePopular(api_key);
        searchMovie.enqueue(responseCallback);
        mDialog = new ProgressDialog(getContext());
        mDialog.setMessage("로딩중입니다...");
        mDialog.show();
    }

    private Callback<MoviePopularResponse> responseCallback = new Callback<MoviePopularResponse>() {

        @Override
        public void onResponse(Call<MoviePopularResponse> call, Response<MoviePopularResponse> response) {
            if (response.isSuccessful()) {
                mDialog.dismiss();
                Log.d("ResponseSuccess", "Success");
                SearchedMovie[] result= response.body().getResults();
                int total = result.length;
                Log.d("ResponseSuccess", String.valueOf(total));

                for (int i=0; i<total; i++) {
                    Log.d("ResponseSuccess", result[i].getTitle());
                    ArrayList<Integer> genres = result[i].getGenre_ids();
                    for (int j=0; j<genres.size(); j++) {
                        Log.d("ResponseSuccess", String.valueOf(genres.get(j)));
                        if (genres.get(j) == 18) {
                            drama.add(new Movie(result[i].getTitle(), result[i].getOriginal_title(), result[i].getTitle(), result[i].getId(),
                                    result[i].getPoster_path(), result[i].getOverview(), result[i].getBackdrop_path(), result[i].getRelease_date()));
                            adapter_romance.notifyDataSetChanged();
                        }
                        if (genres.get(j) == 27 || genres.get(j)==53) {
                            horror.add(new Movie(result[i].getTitle(), result[i].getOriginal_title(), result[i].getTitle(), result[i].getId(),
                                    result[i].getPoster_path(), result[i].getOverview(), result[i].getBackdrop_path(), result[i].getRelease_date()));
                            adapter_thrill.notifyDataSetChanged();
                        }
                        if (genres.get(j) == 14 || genres.get(j)==878) {
                            fantasy.add(new Movie(result[i].getTitle(), result[i].getOriginal_title(), result[i].getTitle(), result[i].getId(),
                                    result[i].getPoster_path(), result[i].getOverview(), result[i].getBackdrop_path(), result[i].getRelease_date()));
                            adapter_sf.notifyDataSetChanged();
                        }
                        if (genres.get(j) == 28 ) {
                            action.add(new Movie(result[i].getTitle(), result[i].getOriginal_title(), result[i].getTitle(), result[i].getId(),
                                    result[i].getPoster_path(), result[i].getOverview(), result[i].getBackdrop_path(), result[i].getRelease_date()));
                            adapter_action.notifyDataSetChanged();
                        }
                        if (genres.get(j) == 35 ) {
                            comedy.add(new Movie(result[i].getTitle(), result[i].getOriginal_title(), result[i].getTitle(), result[i].getId(),
                                    result[i].getPoster_path(), result[i].getOverview(), result[i].getBackdrop_path(), result[i].getRelease_date()));
                            adapter_comedy.notifyDataSetChanged();
                        }
                        else {
                            etc.add(genres.get(j));
                        }
                    }

                }
                Log.d("Genres Romance", drama.toString());
                Log.d("Genres horror", horror.toString());
                Log.d("Genres fantasy", String.valueOf(fantasy));
                Log.d("Genres action", String.valueOf(action));
                Log.d("Genres comedy", String.valueOf(comedy));
                Log.d("Genres etc", etc.toString());

            }
            else {
                Log.d("ResponseError", response.raw().toString());
            }
        }

        @Override
        public void onFailure(Call<MoviePopularResponse> call, Throwable t) {

        }
    };


    @Override
    public void onMovieClick(int position, ArrayList<Movie> mMovieList) {
        Log.e(TAG, "onMovieClick: 영화 아이템이 클릭됨" + position);

        // 세부 액티비티로 이동
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("movieList", (Serializable)mMovieList.get(position));
        startActivity(intent);
    }
}