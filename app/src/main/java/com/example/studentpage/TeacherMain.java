package com.example.studentpage;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TeacherMain extends AppCompatActivity {
    private ArrayList<String> userList=new ArrayList<String>();
    private Map<String,String> m= new HashMap<>();
    private ArrayList<String> attendList=new ArrayList<String>();
    private File file;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);

        LinearLayout profilel = findViewById(R.id.profile);
        LinearLayout fingerprintl = findViewById(R.id.biometric_Attendance);
        LinearLayout attendancel = findViewById(R.id.attendance);
        LinearLayout timetablel = findViewById(R.id.timetable);
        db=FirebaseFirestore.getInstance();

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

                Intent i=new Intent(TeacherMain.this, timer_broadcast.class);
                startActivity(i);
            }
        });

        attendancel.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
//                String date=java.time.LocalDate.now().toString();
//
//                CollectionReference user=db.collection(date);
//                user.get().addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        for (QueryDocumentSnapshot document : task.getResult()) {
////                            Log.d(TAG, document.getId() + " => " + document.getData());
//                            String email= (String) document.get("email");
//                            Boolean attendance= document.getBoolean(
//                                    "attendance taken"
//                            );
//                            if(Boolean.TRUE.equals(attendance)){
//                                m.put(email,"P");
//
//                            }
//
//                        }
//                    } else {
//                        Log.d("imad", "Error getting documents: ", task.getException());
//                    }
//                });
//                CollectionReference user_absent=db.collection("student_details");
//
//                user_absent.get().addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        for (QueryDocumentSnapshot document : task.getResult()) {
////                            Log.d(TAG, document.getId() + " => " + document.getData());
//                            String email= (String) document.get("email");
//                            if(!m.containsKey(email)){
//                                m.put(email,"A");
//                            }
//
//
//                        }
//                    } else {
//                        Log.d("iamd", "Error getting documents: ", task.getException());
//                    }
//                });

//                userList.addAll(m.keySet());
//                attendList.addAll(m.values());


                Toast.makeText(TeacherMain.this, "My Attendance", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(TeacherMain.this,attendance_summary.class);
//                i.putExtra("map", (Serializable) m);
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

//        private void updateGUI(Intent intent) {
//            if (intent.getExtras() != null) {
//
//                long millisUntilFinished = intent.getLongExtra("countdown",30000);
//                Log.i(,"Countdown seconds remaining:" + millisUntilFinished / 1000);
//                int seconds = (int) (millisUntilFinished / 1000) % 60;
//                int minutes=(int) millisUntilFinished / 60000;
//                txt.setText( String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds));
//                SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(),MODE_PRIVATE);
//
//                sharedPreferences.edit().putLong("time",millisUntilFinished).apply();
//            }
//        }
    };
