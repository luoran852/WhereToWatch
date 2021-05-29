package com.example.wheretowatch;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface MovieRequest {
    @GET("trending/all/week")
    Call<Movie> rankingMovie(
            @Query("api_key") String api_key
    );
}