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

import org.w3c.dom.Text;

public class LabTestDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);
        TextView packageName=findViewById(R.id.packname);
        TextView totalCost=findViewById(R.id.tatalcost);
        EditText details=findViewById(R.id.editTextTextMultiLine);


        Button addcart=findViewById(R.id.cartadd);
        Button back=findViewById(R.id.back);

        details.setKeyListener(null);

        packageName.setText(getIntent().getStringExtra("text1"));
        details.setText(getIntent().getStringExtra("text2"));

        totalCost.setText("Total Cost : "+getIntent().getStringExtra("text3")+"$");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LabTestActivity.class));
            }
        });

        Intent it=getIntent();


        addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("shredPrefs",MODE_PRIVATE);
                String Username=sharedPreferences.getString("username","").toString();
                String Product=packageName.getText().toString();
                float price=Float.parseFloat(it.getStringExtra("text3").toString());
                Database db=new Database(getApplicationContext(),"healthcare",null,1);

                if(db.checkCart(Username,Product)==1){
                    Toast.makeText(LabTestDetailsActivity.this, "Product Already Added", Toast.LENGTH_SHORT).show();
                }

                else {
                    db.addtoCart(Username,Product,price,"lab");
                    Toast.makeText(LabTestDetailsActivity.this, "Added To cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),LabTestActivity.class));
                }
            }
        });




    }
}