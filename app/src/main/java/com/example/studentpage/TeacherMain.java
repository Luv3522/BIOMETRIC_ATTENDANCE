package com.example.studentpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TeacherMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);

        LinearLayout profilel = findViewById(R.id.profile);
        LinearLayout fingerprintl = findViewById(R.id.biometric_Attendance);
        LinearLayout attendancel = findViewById(R.id.attendance);
        LinearLayout timetablel = findViewById(R.id.timetable);

        profilel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TeacherMain.this, "My Profile", Toast.LENGTH_SHORT).show();
            }
        });

        fingerprintl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TeacherMain.this, "FingerPrint", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(TeacherMain.this, timer.class);
                startActivity(i);
            }
        });

        attendancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TeacherMain.this, "My Attendance", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(TeacherMain.this,attendance_summary.class);
                startActivity(i);
            }
        });

        timetablel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TeacherMain.this, "My timetable", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(TeacherMain.this,add_student.class);
                startActivity(i);
            }
        });

    }
}