package com.example.healthservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
  EditText userName,passWord;
  Button login;
  TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName=findViewById(R.id.username);
        passWord=findViewById(R.id.password);
        login=findViewById(R.id.login);
        register=findViewById(R.id.resigter);
        Database db=new Database(getApplicationContext(),"healthCare",null,1);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserName=userName.getText().toString();
                String PassWord=passWord.getText().toString();

                if(UserName.length()==0 || PassWord.length()==0){
                    Toast.makeText(getApplicationContext(), "Please Fill the details", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(db.login(UserName,PassWord)==1) {
                        Toast.makeText(getApplicationContext(), "LoginSucessfully", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences=getSharedPreferences("shredPrefs",MODE_PRIVATE);
                        SharedPreferences.Editor editor= sharedPreferences.edit();
                        editor.putString("username",UserName);
                        editor.apply();
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "UserNotFound", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }
}