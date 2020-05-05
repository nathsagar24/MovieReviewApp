package com.example.rockstarmoviereview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.user_id)
    EditText user_id;
    @BindView(R.id.pass)
    EditText pass;
    @BindView(R.id.login_button)
    Button login_button;
    @BindView(R.id.login_header)
    ImageView login_header;

    //Resolving the issue of screen rotation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        if(isAuthorized()) {
            getIntent().putExtra("LoggedIn",false);
            Intent intent=new Intent(this,ListViewActivity.class);
            startActivity(intent);
        }

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

    @OnClick(R.id.login_button)
    void onLoginButtonClick(){
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
}
