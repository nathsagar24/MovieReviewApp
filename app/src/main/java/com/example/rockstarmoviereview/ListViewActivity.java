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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListViewActivity extends AppCompatActivity {

    private Button logout_button;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter  listViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        recyclerView=findViewById(R.id.recycler_view);
        layoutManager=new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        logout_button=findViewById(R.id.logout_button);
        logout_button.setOnClickListener(
                listener
        );

        Retrofit retrofit=new Retrofit.Builder()
                                .baseUrl("https://api.themoviedb.org/3/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
        tmdbAPI api=retrofit.create(tmdbAPI.class);
        Call<ListItem> call=api.getListItems("412956dda4b6897f4a828149dfceb7fc");
        call.enqueue(new Callback<ListItem>() {
            @Override
            public void onResponse(Call<ListItem> call, Response<ListItem> response) {
                ListItem listItems=response.body();
                List<ListItem.Results> results=listItems.getResults();
                listViewAdapter=new ListViewAdapter(results,ListViewActivity.this);
               recyclerView.setAdapter(listViewAdapter);

            }

            @Override
            public void onFailure(Call<ListItem> call, Throwable t) {
                Log.v("ALERT","FAILURE in API Call");

            }
        });

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
            Intent intent=new Intent(ListViewActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    };

}
