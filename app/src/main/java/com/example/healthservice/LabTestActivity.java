package com.example.healthservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {

    String[][] packages = {
            {"Package 1: Full Body Checkup", "", "", "", "999"},
            {"Package 2: Blood Glucose Fasting", "", "", "", "299"},
            {"Package 3: COVID-19 Antibody Test", "", "", "", "4990"},
            {"Package 5: Thyroid Check", "", "", "", "699"},
            {"Package 6: Immunity Check", "", "", "", "499"}
    };

    String[] package_detail = {
            "Blood Glucose Fasting\nComplete\nHbA1c\nIron Studies\nKidney Function Test\nLDH Lactate Dehydrogenase\nLipid Profile Xn\nLiver Function Test\n",
            "Blood Glucose Fasting",
            "COVID-19 Antibody IgG",
            "Serum",
            "Thyroid Profile-Total (T 3, T 4 & TSH Ultra-sensitive)",
            "Complete Hemogram\nCRP (C Reactive Protein) Quantitative, Serum",
            "Iron Studies",
            "Kidney Function Test",
            "Vitamin D Total-25 Hydroxy",
            "Liver Function Test",
            "Lipid Profile"
    };

    HashMap<String,String>mp;
    ArrayList list;
    SimpleAdapter sa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);
        Button cart=findViewById(R.id.cart);
        Button back=findViewById(R.id.logout);
        ListView la=findViewById(R.id.namesofDoctor);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });

        list=new ArrayList<>();
        for(int i=0;i<packages.length;i++){
            mp=new HashMap<>();
            mp.put("line1",packages[i][0]);
            mp.put("line2",packages[i][1]);
            mp.put("line3",packages[i][2]);
            mp.put("line4",packages[i][3]);
            mp.put("line5","Total Cost : "+packages[i][4]+"$");

            list.add(mp);
        }

        sa =new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        la.setAdapter(sa);

        la.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(LabTestActivity.this,LabTestDetailsActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_detail[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
            }
        });


        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),CartLabActivity.class));
            }
        });




    }
}