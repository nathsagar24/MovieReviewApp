package com.example.rockstarmoviereview;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface tmdbAPI {

    //Movies
    @GET("movie/now_playing")
    Call<ListItem> getMoviesNowPlaying(
            @Query("api_key")String api_key
    );

    @GET("movie/popular")
    Call<ListItem> getPopularMovies(
            @Query("api_key")String api_key
    );

    @GET("movie/upcoming")
    Call<ListItem> getUpcomingMovies(
            @Query("api_key")String api_key
    );

    //TV Shows
    @GET("tv/on_the_air")
    Call<ListItem> getShowsOnAir(
            @Query("api_key")String api_key
    );

    @GET("tv/popular")
    Call<ListItem> getPopularTVShows(
            @Query("api_key")String api_key
    );

    @GET("tv/airing_today")
    Call<ListItem> getTVShowsAiringToday(
            @Query("api_key")String api_key
    );

    @GET("movie/top_rated")
    Call<ListItem> getTopRatedMovies(
            @Query("api_key")String api_key
    );

    @GET("tv/top_rated")
    Call<ListItem> getTopRatedTVShows(
            @Query("api_key")String api_key
    );




    @GET("movie/{id}")
    Call<MovieDetails> getMovieDetails(
            @Path("id")int id,
            @Query("api_key")String api_key
    );

}
