package com.example.rockstarmoviereview;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface tmdbAPI {
    @GET("movie/popular")
    Call<ListItem> getListItems(
            @Query("api_key")String api_key
    );
    @GET("movie/{id}")
    Call<MovieDetails> getMovieDetails(
            @Path("id")int id,
            @Query("api_key")String api_key
    );

}
