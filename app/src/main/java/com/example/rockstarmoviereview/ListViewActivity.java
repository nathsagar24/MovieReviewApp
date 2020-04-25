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

import dagger.internal.DaggerCollections;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListViewActivity extends AppCompatActivity {

    private Button logout_button;
    private RecyclerView recyclerView;
    @Inject
    ListViewAdapter  listViewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private tmdbAPI api;
    private Call<ListItem> call;
    private ListItem listItems;
    private List<ListItem.Results> results;
    private ListAdapterComponent component;
    public static String API_KEY="412956dda4b6897f4a828149dfceb7fc";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        initRecyclerView();
        initLogout();
        initRetrofit();
    }

    private void initRetrofit(){
        api=RetrofitAPIProvider.getRetrofitAPI();
        call=api.getListItems(API_KEY);
        call.enqueue(new Callback<ListItem>() {
            @Override
            public void onResponse(Call<ListItem> call, Response<ListItem> response) {
                listItems=response.body();
                results=listItems.getResults();
                setListAdapter(results);
            }

            @Override
            public void onFailure(Call<ListItem> call, Throwable t) {
                Log.v("ALERT","FAILURE in API Call"+t.getMessage());

            }
        });
    }

    private void setListAdapter(List<ListItem.Results> results){
        component=DaggerListAdapterComponent.builder()
                .listModule(new ListModule(results,this))
                .build();
        component.inject(ListViewActivity.this);
        Log.v("ALERT","RESPONSED"+listViewAdapter);
        recyclerView.setAdapter(listViewAdapter);
    }

    private void initRecyclerView(){
        recyclerView=findViewById(R.id.recycler_view);
        layoutManager=new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initLogout(){
        logout_button=findViewById(R.id.logout_button);
        logout_button.setOnClickListener(
                listener
        );
    }

    private void clearSession(){
        SharedPreferences sharedPreferences=getSharedPreferences("login_details",MODE_PRIVATE);
        sharedPreferences.edit().remove("user_id").commit();
        sharedPreferences.edit().remove("pass").commit();
    }

    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            clearSession();
            ListViewActivity.this.finish();
            //Intent intent=new Intent(ListViewActivity.this,LoginActivity.class);
            //startActivity(intent);
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
