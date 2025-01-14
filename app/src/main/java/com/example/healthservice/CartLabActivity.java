package com.example.healthservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartLabActivity extends AppCompatActivity {

    Button dateButton,checkOut,back;
    Button timeButton;
    ArrayList list;
    SimpleAdapter sa;
    TextView total;
    HashMap<String,String>item;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;

    String packages[][]={};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_lab);

        dateButton=findViewById(R.id.cartdatebtn);
        timeButton=findViewById(R.id.carttimebtn);
        checkOut=findViewById(R.id.cartcheckout);
        back=findViewById(R.id.cartback);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LabTestActivity.class));
            }
        });

        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });




        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });

        SharedPreferences sharedPreferences=getSharedPreferences("shredPrefs",MODE_PRIVATE);
        String Username=sharedPreferences.getString("username","").toString();
        Database db=new Database(getApplicationContext(),"healthcare",null,1);

        float totalAmount=0;
        ArrayList dbdata=db.getCartData(Username,"lab");
        Toast.makeText(this, ""+dbdata, Toast.LENGTH_SHORT).show();

        packages=new String[dbdata.size()][];
        for(int i=0;i<packages.length;i++){
            packages[i]=new String[5];
        }

        for(int i=0;i<dbdata.size();i++){
            String arrData=dbdata.get(i).toString();
            String []strData=arrData.split(java.util.regex.Pattern.quote("$"));
            packages[i][0]=strData[0];
            packages[i][4]="Cost : "+strData[1]+"$";
            totalAmount=totalAmount+Float.parseFloat(strData[1]);
        }

        TextView TotalAmount=findViewById(R.id.carttotalcost);
        TotalAmount.setText("Total Amount : "+totalAmount+" $");

        list=new ArrayList<>();
        ListView lv=findViewById(R.id.carteditTextTextMultiLine);
        for(int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Consu fee "+ packages[i][4]);
            list.add(item);
        }
        sa=new SimpleAdapter(getApplicationContext(),list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
 lv.setAdapter(sa);

 checkOut.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         Intent it=new Intent(CartLabActivity.this, LabTestBookActivity.class);
         it.putExtra("price",TotalAmount.getText());
         it.putExtra("date",dateButton.getText());
         it.putExtra("time",timeButton.getText());
         startActivity(it);
     }
 });

    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                dateButton.setText(String.format("%d/%d/%d", i2, i1, i));
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000);
    }

    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeButton.setText(i + ":" + i1);
            }
        };
        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int mins = cal.get(Calendar.MINUTE);

        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this, style, timeSetListener, hrs, mins, true);
    }
}