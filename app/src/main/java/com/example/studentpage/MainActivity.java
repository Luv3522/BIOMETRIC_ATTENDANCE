package com.example.studentpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private LinearLayout profilel;
    private LinearLayout fingerprintl;
    private LinearLayout attendancel;
    private LinearLayout timetablel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profilel=findViewById(R.id.profile);
        fingerprintl=findViewById(R.id.fingerprint);
        attendancel=findViewById(R.id.attendance);
        timetablel=findViewById(R.id.timetable);

        profilel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "My Profile", Toast.LENGTH_SHORT).show();
            }
        });

        fingerprintl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "FingerPrint", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(i);




            }
        });

        attendancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "My Attendance", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(MainActivity.this,MainActivity3.class);
                startActivity(i);
            }
        });

        timetablel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "My timetable", Toast.LENGTH_SHORT).show();
            }
        });

    }
}