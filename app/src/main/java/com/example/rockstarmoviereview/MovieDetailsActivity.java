package com.example.rockstarmoviereview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetailsActivity extends AppCompatActivity {
    public static final String BASE_URL="https://api.themoviedb.org/3/";
    public static final String API_KEY="412956dda4b6897f4a828149dfceb7fc";
    private static final String IMAGE_PREFIX="https://image.tmdb.org/t/p/w500";
    private int id;
    private ImageView poster;
    private TextView movieName,overview,original_language,genre,budget,vote_average;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        poster=findViewById(R.id.poster);
        movieName=findViewById(R.id.movie_name);
        overview=findViewById(R.id.overview);
        genre=findViewById(R.id.genre);
        original_language=findViewById(R.id.original_language);
        budget=findViewById(R.id.budget);
        vote_average=findViewById(R.id.vote_average);
        id=getIntent().getExtras().getInt("Id");
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        tmdbAPI api=retrofit.create(tmdbAPI.class);
        Call<MovieDetails> call=api.getMovieDetails(id,API_KEY);
        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                MovieDetails movieDetails=response.body();

                Picasso.with(getBaseContext())
                        .load("https://image.tmdb.org/t/p/w500"+movieDetails.getPoster_path())
                        .into(poster);
                movieName.setText(movieDetails.getMovie_name());
                overview.setText(movieDetails.getOverview());
                String genreCollection="";
                for(MovieDetails.Genres gen:movieDetails.getGenreList()){
                    genreCollection+=gen.getGenre() + " | ";
                }
                genre.setText("Genre: " + genreCollection);
                original_language.setText("Language: " + movieDetails.getOriginal_language());
                budget.setText("Budget: "+movieDetails.getBudget());
                vote_average.setText("Rating: "+movieDetails.getVote_average());


            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
               Log.v("ALERT","API Call Failed");
            }
        });
    }

}
