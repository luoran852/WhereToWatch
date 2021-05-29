package com.example.wheretowatch;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class RankingFragment extends Fragment implements RecyclerViewAdapter.OnMovieClickListener {
    RecyclerView mRecyclerView;
    RecyclerViewAdapter mAdapter;
    ArrayList<Movie> searchedItems;
    private String api_key = "89247ab465eba040acb566dcd1724b96";
    private final String baseUrl = "https://api.themoviedb.org/3/";
    private MovieRequest movieRequest;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ranking, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle
            savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        movieRequest = retrofit.create(MovieRequest.class);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvRanking);
        mRecyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),
                2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        searchedItems = new ArrayList<Movie>();
        mAdapter = new RecyclerViewAdapter(getContext(), searchedItems, this);
        mRecyclerView.setHasFixedSize(true);
        Call<Movie> rankingMovie = movieRequest.rankingMovie(api_key);
        rankingMovie.enqueue(responseCallback);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setVisibility(View.VISIBLE);
    }
    private Callback <Movie> responseCallback = new Callback<Movie>() {
        @Override
        public void onResponse(Call<Movie> call, Response<Movie> response) {
            if (response.isSuccessful()) {
                Log.d("ResponseSuccess", "Success");
                Movie[] result= response.body().getResults();
                int total = response.body().getResults().length;
                Log.d("ResponseSuccess", String.valueOf(total));
                for (int i=0; i<total; i++) {
                    Log.d("ResponseSuccess", result[i].getTitle() + " "+i);
                    searchedItems.add(result[i]);
                    mAdapter.notifyDataSetChanged();
                }
            }
            else {
                Log.d("ResponseError", response.raw().toString());
            }
        }
        @Override
        public void onFailure(Call<Movie> call, Throwable t) {
            Log.d("Response", "Fail");
            t.printStackTrace();
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