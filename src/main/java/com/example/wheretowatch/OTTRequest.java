package com.example.wheretowatch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OTTRequest {
    @GET("movie/{movie_id}/watch/providers")
    Call<OTTResponse> getOTT (
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key
    );

}
