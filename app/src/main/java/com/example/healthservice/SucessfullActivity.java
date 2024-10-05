package com.example.healthservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SucessfullActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucessfull);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SucessfullActivity.this, HomeActivity.class);
                startActivity(intent);
                finish(); // Optional, depending on your use case
            }
        }, 3000); // 10 seconds in milliseconds

    }
}