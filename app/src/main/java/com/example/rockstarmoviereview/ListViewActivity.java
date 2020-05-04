package com.example.rockstarmoviereview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.internal.DaggerCollections;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListViewActivity extends AppCompatActivity {

    @BindView(R.id.logout_button)
    Button logout_button;
    @BindView(R.id.movies_popular_recycler_view)
    RecyclerView recyclerViewPopularMovies;
    @BindView(R.id.movies_now_playing_recycler_view)
    RecyclerView recyclerViewMoviesNowPlaying;
    @BindView(R.id.shows_on_air_recycler_view)
    RecyclerView recyclerViewShowsOnAir;
    @BindView(R.id.tvshows_popular_recycler_view)
    RecyclerView recyclerViewTVShowsPopular;
    @BindView(R.id.movies_upcoming_recycler_view)
    RecyclerView recyclerViewMoviesUpcoming;
    @BindView(R.id.tvshows_airing_today_recycler_view)
    RecyclerView recyclerViewTVShowsAiringToday;
    @BindView(R.id.movies_top_rated_recycler_view)
    RecyclerView recyclerViewMoviesTopRated;
    @BindView(R.id.tvshows_top_rated_recycler_view)
    RecyclerView recyclerViewTVShowsTopRated;
    @Inject
    ListViewAdapter  listViewAdapter;
    @Inject
    NowPlayingListViewAdapter nowPlayingListViewAdapter;
    RecyclerView.LayoutManager layoutManager;
    tmdbAPI api;
    Call<ListItem> call;
    ListItem listItem;
    List<ListItem.Results> results;
    ListAdapterComponent component;
    @BindString(R.string.api_key)
    String API_KEY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ButterKnife.bind(this);
        //Doing multiple recycler views in a less redundant way
        initRecyclerView();
        initRetrofit();
    }

    private void initRetrofit(){
        api=RetrofitAPIProvider.getRetrofitAPI();

        call=api.getPopularMovies(API_KEY);
        call.enqueue(new Callback<ListItem>() {
            @Override
            public void onResponse(Call<ListItem> call, Response<ListItem> response) {
                listItem=response.body();
                results=listItem.getResults();
                component=DaggerListAdapterComponent.builder()
                        .listModule(new ListModule(results,ListViewActivity.this))
                        .build();
                component.inject(ListViewActivity.this);
                recyclerViewPopularMovies.setAdapter(listViewAdapter);
            }

            @Override
            public void onFailure(Call<ListItem> call, Throwable t) {
                component=DaggerListAdapterComponent.builder()
                        .listModule(new ListModule(results,ListViewActivity.this))
                        .build();
                component.inject(ListViewActivity.this);
                recyclerViewPopularMovies.setAdapter(listViewAdapter);
            }
        });

        call=api.getMoviesNowPlaying(API_KEY);
        call.enqueue(new Callback<ListItem>() {
            @Override
            public void onResponse(Call<ListItem> call, Response<ListItem> response) {
                listItem = response.body();
                results=listItem.getResults();
                component=DaggerListAdapterComponent.builder()
                        .listModule(new ListModule(results,ListViewActivity.this))
                        .build();
                component.inject(ListViewActivity.this);
                recyclerViewMoviesNowPlaying.setAdapter(nowPlayingListViewAdapter);
            }

            @Override
            public void onFailure(Call<ListItem> call, Throwable t) {
                Log.v("ALERT","Movies Now Playing Failure");
                //For testing while api call doesn't work
                component=DaggerListAdapterComponent.builder()
                        .listModule(new ListModule(results,ListViewActivity.this))
                        .build();
                component.inject(ListViewActivity.this);
                recyclerViewMoviesNowPlaying.setAdapter(nowPlayingListViewAdapter);
            }
        });

        call=api.getShowsOnAir(API_KEY);
        call.enqueue(new Callback<ListItem>() {
            @Override
            public void onResponse(Call<ListItem> call, Response<ListItem> response) {
                listItem=response.body();
                results=listItem.getResults();
                component=DaggerListAdapterComponent.builder()
                        .listModule(new ListModule(results,ListViewActivity.this))
                        .build();
                component.inject(ListViewActivity.this);
                recyclerViewShowsOnAir.setAdapter(listViewAdapter);
            }

            @Override
            public void onFailure(Call<ListItem> call, Throwable t) {
                //For testing while api call doesn't work
                component=DaggerListAdapterComponent.builder()
                        .listModule(new ListModule(results,ListViewActivity.this))
                        .build();
                component.inject(ListViewActivity.this);
                recyclerViewShowsOnAir.setAdapter(listViewAdapter);
                Log.v("ALERT","FAILURE in API Call"+t.getMessage());

            }
        });
        call=api.getPopularTVShows(API_KEY);
        call.enqueue(new Callback<ListItem>() {
            @Override
            public void onResponse(Call<ListItem> call, Response<ListItem> response) {
                listItem = response.body();
                results=listItem.getResults();
                component=DaggerListAdapterComponent.builder()
                        .listModule(new ListModule(results,ListViewActivity.this))
                        .build();
                component.inject(ListViewActivity.this);
                recyclerViewTVShowsPopular.setAdapter(listViewAdapter);
            }

            @Override
            public void onFailure(Call<ListItem> call, Throwable t) {
                Log.v("ALERT","Movies Now Playing Failure");
                //For testing while api call doesn't work
                component=DaggerListAdapterComponent.builder()
                        .listModule(new ListModule(results,ListViewActivity.this))
                        .build();
                component.inject(ListViewActivity.this);
                recyclerViewTVShowsPopular.setAdapter(listViewAdapter);
            }
        });

        call=api.getUpcomingMovies(API_KEY);
        call.enqueue(new Callback<ListItem>() {
            @Override
            public void onResponse(Call<ListItem> call, Response<ListItem> response) {
                listItem=response.body();
                results=listItem.getResults();
                component=DaggerListAdapterComponent.builder()
                        .listModule(new ListModule(results,ListViewActivity.this))
                        .build();
                component.inject(ListViewActivity.this);
                recyclerViewMoviesUpcoming.setAdapter(listViewAdapter);
            }

            @Override
            public void onFailure(Call<ListItem> call, Throwable t) {
                //For testing while api call doesn't work
                component=DaggerListAdapterComponent.builder()
                        .listModule(new ListModule(results,ListViewActivity.this))
                        .build();
                component.inject(ListViewActivity.this);
                recyclerViewMoviesUpcoming.setAdapter(listViewAdapter);
                Log.v("ALERT","FAILURE in API Call"+t.getMessage());

            }
        });
        call=api.getTVShowsAiringToday(API_KEY);
        call.enqueue(new Callback<ListItem>() {
            @Override
            public void onResponse(Call<ListItem> call, Response<ListItem> response) {
                listItem=response.body();
                results=listItem.getResults();
                component=DaggerListAdapterComponent.builder()
                        .listModule(new ListModule(results,ListViewActivity.this))
                        .build();
                component.inject(ListViewActivity.this);
                recyclerViewTVShowsAiringToday.setAdapter(listViewAdapter);
            }

            @Override
            public void onFailure(Call<ListItem> call, Throwable t) {
                component=DaggerListAdapterComponent.builder()
                        .listModule(new ListModule(results,ListViewActivity.this))
                        .build();
                component.inject(ListViewActivity.this);
                recyclerViewTVShowsAiringToday.setAdapter(listViewAdapter);
            }
        });

        call=api.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<ListItem>() {
            @Override
            public void onResponse(Call<ListItem> call, Response<ListItem> response) {
                listItem=response.body();
                results=listItem.getResults();
                component=DaggerListAdapterComponent.builder()
                        .listModule(new ListModule(results,ListViewActivity.this))
                        .build();
                component.inject(ListViewActivity.this);
                recyclerViewMoviesTopRated.setAdapter(listViewAdapter);
            }

            @Override
            public void onFailure(Call<ListItem> call, Throwable t) {
                //For testing while api call doesn't work
                component=DaggerListAdapterComponent.builder()
                        .listModule(new ListModule(results,ListViewActivity.this))
                        .build();
                component.inject(ListViewActivity.this);
                recyclerViewMoviesTopRated.setAdapter(listViewAdapter);
                Log.v("ALERT","FAILURE in API Call"+t.getMessage());

            }
        });
        call=api.getTopRatedTVShows(API_KEY);
        call.enqueue(new Callback<ListItem>() {
            @Override
            public void onResponse(Call<ListItem> call, Response<ListItem> response) {
                listItem=response.body();
                results=listItem.getResults();
                component=DaggerListAdapterComponent.builder()
                        .listModule(new ListModule(results,ListViewActivity.this))
                        .build();
                component.inject(ListViewActivity.this);
                recyclerViewTVShowsTopRated.setAdapter(listViewAdapter);
            }

            @Override
            public void onFailure(Call<ListItem> call, Throwable t) {
                component=DaggerListAdapterComponent.builder()
                        .listModule(new ListModule(results,ListViewActivity.this))
                        .build();
                component.inject(ListViewActivity.this);
                recyclerViewTVShowsTopRated.setAdapter(listViewAdapter);
            }
        });

    }

    private void initRecyclerView(){
        layoutManager=new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewPopularMovies.setLayoutManager(layoutManager);
        layoutManager=new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewMoviesNowPlaying.setLayoutManager(layoutManager);
        layoutManager=new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewShowsOnAir.setLayoutManager(layoutManager);
        layoutManager=new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewTVShowsPopular.setLayoutManager(layoutManager);
        layoutManager=new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewMoviesUpcoming.setLayoutManager(layoutManager);
        layoutManager=new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewTVShowsAiringToday.setLayoutManager(layoutManager);
        layoutManager=new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewMoviesTopRated.setLayoutManager(layoutManager);
        layoutManager=new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setOrientation(RecyclerView.HORIZONTAL);
        recyclerViewTVShowsTopRated.setLayoutManager(layoutManager);

    }

    private void clearSession(){
        SharedPreferences sharedPreferences=getSharedPreferences("login_details",MODE_PRIVATE);
        sharedPreferences.edit().remove("user_id").commit();
        sharedPreferences.edit().remove("pass").commit();
    }

    @OnClick(R.id.logout_button)
    void logoutButtonPressed(){
        clearSession();
        ListViewActivity.this.finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
