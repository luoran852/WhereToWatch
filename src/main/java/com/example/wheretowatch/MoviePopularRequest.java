package com.example.wheretowatch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviePopularRequest {
    @GET("movie/popular")
    Call<MoviePopularResponse> getMoviePopular(
            @Query("api_key") String api_key
    );
}
