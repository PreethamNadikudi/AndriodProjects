package com.example.healthservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences sharedPreferences=getSharedPreferences("shredPrefs",MODE_PRIVATE);
        String Username=sharedPreferences.getString("username","").toString();
        Toast.makeText(this, "Welcome "+Username, Toast.LENGTH_SHORT).show();

        CardView labTest=findViewById(R.id.labtest);
        CardView buyMedicine=findViewById(R.id.buy_medicine);
        CardView findDoc=findViewById(R.id.finddoctor);
        CardView healthArti=findViewById(R.id.healthdoctor);
        CardView order=findViewById(R.id.orderdetails);
        CardView logout=findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        findDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),FindDoctorActivity.class));
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DoctorDetailsActivity.class));
            }
        });

        labTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LabTestActivity.class));
            }
        });


    }
}