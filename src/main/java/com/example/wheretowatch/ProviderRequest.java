package com.example.wheretowatch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProviderRequest {
    @GET("movie/{movie_id}")
    Call<ProviderResponse> getProvider(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key

    );

}
