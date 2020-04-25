package com.example.rockstarmoviereview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText user_id,pass;
    private Button login_button;

    //Resolving the issue of screen rotation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(isAuthorized()) {
            getIntent().putExtra("LoggedIn",false);
            Intent intent=new Intent(this,ListViewActivity.class);
            startActivity(intent);
        }
        user_id=findViewById(R.id.user_id);
        pass=findViewById(R.id.pass);
        login_button=findViewById(R.id.login_button);
        login_button.setOnClickListener(logoutButtonListener);
    }

    private boolean isAuthorized(){
        return getIntent().getBooleanExtra("LoggedIn",false);
    }

    private void StoreSession(String user_id,String pass){
        SharedPreferences sharedPreferences=getSharedPreferences("login_details",MODE_PRIVATE);
       SharedPreferences.Editor edt=sharedPreferences.edit();
       edt.putString("user_id",user_id);
       edt.putString("pass",pass);
       edt.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    View.OnClickListener logoutButtonListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(user_id.getText().toString().equals("user") && pass.getText().toString().equals("password")){
                StoreSession(user_id.getText().toString(),pass.getText().toString());
                Intent intent=new Intent(LoginActivity.this,ListViewActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(LoginActivity.this,"Wrong User_Id Or Password",Toast.LENGTH_LONG).show();
                user_id.setText("");
                pass.setText("");
            }
        }
    };
}
