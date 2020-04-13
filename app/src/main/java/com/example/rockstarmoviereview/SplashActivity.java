package com.example.rockstarmoviereview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
    private String user_id;
    private String pass;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences sharedPreferences=getSharedPreferences("login_details",MODE_PRIVATE);
        user_id=sharedPreferences.getString("user_id","Default");
        pass=sharedPreferences.getString("pass","Default");
        if(user_id.equals("user") && pass.equals("password"))intent=new Intent(this,ListViewActivity.class);
        else intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
