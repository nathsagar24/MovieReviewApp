package com.example.rockstarmoviereview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
    private String user_id;
    private String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        authenticate();
    }

    private void authenticate(){
        SharedPreferences sharedPreferences=getSharedPreferences("login_details",MODE_PRIVATE);
        user_id=sharedPreferences.getString("user_id","Default");
        pass=sharedPreferences.getString("pass","Default");
        Intent intent=new Intent(this,LoginActivity.class);
        if(user_id.equals("user") && pass.equals("password")){
                    intent.putExtra("LoggedIn",true);
        }
        startActivity(intent);
    }
}
