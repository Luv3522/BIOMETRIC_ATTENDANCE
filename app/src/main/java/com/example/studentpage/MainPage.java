package com.example.studentpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainPage extends AppCompatActivity {
    private LinearLayout student;
    private LinearLayout teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        student= findViewById(R.id.studentl);
        teacher=findViewById(R.id.teacherl);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainPage.this, "FingerPrint", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(MainPage.this,StudentSignIn.class);
                startActivity(i);
            }
        });
        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainPage.this, "FingerPrint", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(MainPage.this,TeacherSignIn.class);
                startActivity(i);
            }
        });

    }
}