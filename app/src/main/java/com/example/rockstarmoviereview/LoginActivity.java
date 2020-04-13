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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_id=findViewById(R.id.user_id);
        pass=findViewById(R.id.pass);
        login_button=findViewById(R.id.login_button);
        login_button.setOnClickListener(listener);
    }

    private void StoreSession(String user_id,String pass){
        SharedPreferences sharedPreferences=getSharedPreferences("login_details",MODE_PRIVATE);
       SharedPreferences.Editor edt=sharedPreferences.edit();
       edt.putString("user_id",user_id);
       edt.putString("pass",pass);
       edt.commit();
    }

    View.OnClickListener listener=new View.OnClickListener() {
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
