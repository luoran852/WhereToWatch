package com.example.wheretowatch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchRequest {
    @GET("search/movie")
    Call<SearchResponse> searchMovie(
            @Query("api_key") String api_key,
            @Query("query") String query
    );
}
