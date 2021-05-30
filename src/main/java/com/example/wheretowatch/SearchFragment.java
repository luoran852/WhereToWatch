package com.example.wheretowatch;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class SearchFragment extends Fragment implements RvAdapter.OnMovieClickListener {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RvAdapter mAdapter;
//    ArrayList<SearchedMovie> searchedItems;
    ArrayList<SearchedMovie> searchedMovies;

    private String api_key = "89247ab465eba040acb566dcd1724b96";
    private final String baseUrl = "https://api.themoviedb.org/3/";
    private SearchRequest searchRequest;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText searchText = (EditText)view.findViewById(R.id.etSearch);
        AppCompatImageButton searchBtn = (AppCompatImageButton)view.findViewById(R.id.btnSearch);
        AppCompatButton eraseBtn = (AppCompatButton)view.findViewById(R.id.btnErase);
        LinearLayout searchTextInfo = (LinearLayout)view.findViewById(R.id.searchTexts);
        TextView searchName = (TextView)view.findViewById(R.id.searchName);
        HorizontalScrollView recommends = (HorizontalScrollView)view.findViewById(R.id.searchRecs);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.rvSearched);
        searchedMovies = new ArrayList();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        searchRequest = retrofit.create(SearchRequest.class);

        // If enter pressed, do search
        searchText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    String searchString = searchText.getText().toString();
                    Log.d("search", searchString);
                    searchTextInfo.setVisibility(View.VISIBLE);
                    recommends.setVisibility(View.VISIBLE);
                    searchName.setText(searchString);

                    search(v, searchString);
                }
                return false;
            }
        });

        // If search button pressed, do search
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchString = searchText.getText().toString();
                Log.d("search", searchString);
                searchTextInfo.setVisibility(View.VISIBLE);
                recommends.setVisibility(View.VISIBLE);
                searchName.setText(searchString);

                search(view, searchString);
            }
        });

        // If erase button pressed, erase text
        eraseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchText.getText().clear();
                searchedMovies.clear();
                searchTextInfo.setVisibility(View.INVISIBLE);
                mRecyclerView.setVisibility(View.INVISIBLE);
                recommends.setVisibility(View.INVISIBLE);
            }
        });

    }

    public void search(View view, String query) {

        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new RvAdapter(getContext(), searchedMovies,this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setVisibility(View.VISIBLE);

        Call<SearchResponse> searchMovie = searchRequest.searchMovie(api_key, query);
        searchMovie.enqueue(responseCallback);
    }

    private Callback<SearchResponse> responseCallback = new Callback<SearchResponse>() {
        @Override
        public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
            if (response.isSuccessful()) {
                Log.d("ResponseSuccess", "Success");
                SearchedMovie[] result= response.body().getResults();
                int total = response.body().getResults().length;
                Log.d("ResponseSuccess", String.valueOf(total));

                for (int i=0; i<total; i++) {
                    Log.d("ResponseSuccess", result[i].getTitle() + " "+i);
                    Log.d("ResponseSuccess", result[i].getId()+"");
//                    searchedMovies.add(new Movie(result[i].getTitle(), result[i].getOriginal_title(), result[i].getTitle(), result[i].getId(),
//                            result[i].getPoster_path(), result[i].getOverview(), result[i].getBackdrop_path(), result[i].getRelease_date()));
                    searchedMovies.add(result[i]);
                    mAdapter.notifyDataSetChanged();
                }
            }
            else {
                Log.d("ResponseError", response.raw().toString());
            }
        }

        @Override
        public void onFailure(Call<SearchResponse> call, Throwable t) {
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
