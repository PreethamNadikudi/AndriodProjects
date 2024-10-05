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

public class LabTestBookActivity extends AppCompatActivity {
   Button btnBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);
        btnBook=findViewById(R.id.cartbookNow);

        EditText name=findViewById(R.id.textnameofDoc);
        EditText address=findViewById(R.id.textadress);
        EditText pincode=findViewById(R.id.pincode);
        EditText contact=findViewById(R.id.textcontact);

        Intent it=getIntent();
        String []price=it.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date=it.getStringExtra("date");
        String time=it.getStringExtra("time");

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("shredPrefs",MODE_PRIVATE);
                String Username=sharedPreferences.getString("username","").toString();

                Database db=new Database(getApplicationContext(),"healthcare",null,1);

                if (price.length > 1) {
                    db.addOrder(Username, name.getText().toString(), address.getText().toString(), contact.getText().toString(), pincode.getText().toString(), date.toString(), time.toString(), (price[1].toString()), "lab");
                } else {
                    // Display a toast message indicating that the price is missing
                    Toast.makeText(LabTestBookActivity.this, "Error: Price is missing", Toast.LENGTH_SHORT).show();
                    return; // Exit the onClick method to prevent further execution
                }

                db.removeCart(Username,"lab");

                Toast.makeText(LabTestBookActivity.this, "Booking Done Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LabTestBookActivity.this,SucessfullActivity.class));
            }
        });


    }
}