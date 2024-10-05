package com.example.healthservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] familyPhysiciansDet = {
            {"Doctor Name: Dr.Nilesh Borate", "Hospital Address: Pimpri", "Experience: 10 years", "Mobile No: 9898989898", "Consultancy Fee: $50"},
            {"Doctor Name: Dr.Prasad Pawar", "Hospital Address: Pune", "Experience: 12 years", "Mobile No: 7898989898", "Consultancy Fee: $60"},
            {"Doctor Name: Dr.Swapnil Kate", "Hospital Address: Pune", "Experience: 15 years", "Mobile No: 8898989898", "Consultancy Fee: $70"},
            {"Doctor Name: Dr.Deepak Kumar", "Hospital Address: Chinchwad", "Experience: 8 years", "Mobile No: 9898000000", "Consultancy Fee: $55"},
            {"Doctor Name: Dr.Ankul Panda", "Hospital Address: PS", "Experience: 7 years", "Mobile No: 7798989898", "Consultancy Fee: $45"}
    };

    private String[][] dieticiansDet = {
            {"Doctor Name: Dr.Sarah Johnson", "Experience: 15 years", "Hospital Address: Cityville", "Mobile No: 123-456-7890", "Consultancy Fee: $40"},
            {"Doctor Name: Dr.Michael Lee", "Experience: 10 years", "Hospital Address: Townburg", "Mobile No: 234-567-8901", "Consultancy Fee: $35"},
            {"Doctor Name: Dr.Emily Chen", "Experience: 20 years", "Hospital Address: Villagetown", "Mobile No: 345-678-9012", "Consultancy Fee: $50"},
            {"Doctor Name: Dr.John Smith", "Experience: 12 years", "Hospital Address: Hamletville", "Mobile No: 456-789-0123", "Consultancy Fee: $45"},
            {"Doctor Name: Dr.Lisa Patel", "Experience: 18 years", "Hospital Address: Riverside", "Mobile No: 567-890-1234", "Consultancy Fee: $55"}
    };

    private String[][] cardiologistsDet = {
            {"Doctor Name: Dr.David Wilson", "Experience: 25 years", "Hospital Address: Mountainview", "Mobile No: 678-901-2345", "Consultancy Fee: $80"},
            {"Doctor Name: Dr.Jennifer Garcia", "Experience: 8 years", "Hospital Address: Lakeside", "Mobile No: 789-012-3456", "Consultancy Fee: $70"},
            {"Doctor Name: Dr.James Nguyen", "Experience: 17 years", "Hospital Address: Hillcrest", "Mobile No: 890-123-4567", "Consultancy Fee: $75"},
            {"Doctor Name: Dr.Ashley Brown", "Experience: 22 years", "Hospital Address: Riverview", "Mobile No: 901-234-5678", "Consultancy Fee: $85"},
            {"Doctor Name: Dr.Amanda White", "Experience: 13 years", "Hospital Address: Springdale", "Mobile No: 012-345-6789", "Consultancy Fee: $65"}
    };

    private String[][] surgeonsDet = {
            {"Doctor Name: Dr.Matthew Clark", "Experience: 18 years", "Hospital Address: Brookside", "Mobile No: 123-456-7890", "Consultancy Fee: $90"},
            {"Doctor Name: Dr.Samantha Green", "Experience: 11 years", "Hospital Address: Hillside", "Mobile No: 234-567-8901", "Consultancy Fee: $75"},
            {"Doctor Name: Dr.Daniel Rodriguez", "Experience: 23 years", "Hospital Address: Lakeshore", "Mobile No: 345-678-9012", "Consultancy Fee: $100"},
            {"Doctor Name: Dr.Linda Martinez", "Experience: 16 years", "Hospital Address: Riverbend", "Mobile No: 456-789-0123", "Consultancy Fee: $85"},
            {"Doctor Name: Dr.Richard Wright", "Experience: 20 years", "Hospital Address: Parkview", "Mobile No: 567-890-1234", "Consultancy Fee: $95"}
    };

    private String[][] dentistsDet = {
            {"Doctor Name: Dr.Laura Wilson", "Experience: 12 years", "Hospital Address: Springdale", "Mobile No: 678-901-2345", "Consultancy Fee: $60"},
            {"Doctor Name: Dr.Mark Thompson", "Experience: 9 years", "Hospital Address: Brookside", "Mobile No: 789-012-3456", "Consultancy Fee: $55"},
            {"Doctor Name: Dr.Sarah Hall", "Experience: 15 years", "Hospital Address: Riverview", "Mobile No: 890-123-4567", "Consultancy Fee: $70"},
            {"Doctor Name: Dr.Kevin Davis", "Experience: 7 years", "Hospital Address: Hillside", "Mobile No: 901-234-5678", "Consultancy Fee: $50"},
            {"Doctor Name: Dr.Lisa Moore", "Experience: 10 years", "Hospital Address: Riverbend", "Mobile No: 012-345-6789", "Consultancy Fee: $65"}
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String [][] doctor_details={};
        ArrayList list;
        SimpleAdapter sa;
        HashMap<String,String>item;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        ListView listView=findViewById(R.id.namesofDoctor);

        TextView ddTitle=findViewById(R.id.familyPhysician);
        Intent it=getIntent();
        String title=it.getStringExtra("title");
        Button btn=findViewById(R.id.logout);
        ddTitle.setText(title);

        if (title.compareTo("Family Physicans")==0) {
            doctor_details = familyPhysiciansDet;
        } else if (title.compareTo("Dietician")==0) {
            doctor_details = dieticiansDet;
        } else if (title.compareTo("Cardiolgist")==0) {
            doctor_details = cardiologistsDet;
        } else if (title.compareTo("Surgeon")==0) {
            doctor_details = surgeonsDet;
        } else if (title.compareTo("Dentist")==0) {
            doctor_details = dentistsDet;
        }

        list=new ArrayList();

        for(int i=0;i<doctor_details.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","Consu fee "+ doctor_details[i][4]);
            list.add(item);
        }
        sa=new SimpleAdapter(getApplicationContext(),list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });

        listView.setAdapter(sa);

        String[][] finalDoctor_details = doctor_details;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(DoctorDetailsActivity.this, BookappoinmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text1", finalDoctor_details[i][0]);
                it.putExtra("text2", finalDoctor_details[i][2]);
                it.putExtra("text3", finalDoctor_details[i][3]);
                it.putExtra("text4", finalDoctor_details[i][4]);
                startActivity(it);

            }
        });
    }
}