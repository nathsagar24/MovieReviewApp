package com.example.rockstarmoviereview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import butterknife.BindString;
import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Setup data binding in butterknife
public class MovieDetailsActivity extends AppCompatActivity {

    @BindString(R.string.api_key)
    String API_KEY;
    @BindString(R.string.image_prefix)
    String IMAGE_PREFIX;

    private int id;
    @BindView(R.id.poster)
    ImageView poster;
    @BindView(R.id.movie_name)
    TextView movieName;
    @BindView(R.id.overview)
    TextView overview;
    @BindView(R.id.original_language)
    TextView original_language;
    @BindView(R.id.genre)
    TextView genre;
    @BindView(R.id.budget)
    TextView budget;
    @BindView(R.id.vote_average)
    TextView vote_average;
    private tmdbAPI api;
    private Call<MovieDetails> call;
    private MovieDetails movieDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        id=getIntent().getExtras().getInt("Id");
        initRetrofit();
    }

    private void initRetrofit(){
        api=RetrofitAPIProvider.getRetrofitAPI();
        call=api.getMovieDetails(id,API_KEY);
        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                movieDetails=response.body();
                populateViews(movieDetails);
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
                Log.v("ALERT","API Call Failed");
            }
        });
    }

    private void populateViews(MovieDetails movieDetails){
        Picasso.with(getBaseContext())
                .load("https://image.tmdb.org/t/p/w500"+movieDetails.getPoster_path())//something wrong with poster path
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
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
