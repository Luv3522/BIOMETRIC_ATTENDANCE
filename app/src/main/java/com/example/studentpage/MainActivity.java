package com.example.studentpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    private CardView card;
    private LinearLayout profilel;
    private LinearLayout fingerprintl;
    private LinearLayout attendancel;
    private LinearLayout timetablel;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profilel=findViewById(R.id.profile);
        fingerprintl=findViewById(R.id.biometric_Attendance);
        attendancel=findViewById(R.id.attendance);
        timetablel=findViewById(R.id.timetable);
        db=FirebaseFirestore.getInstance();
        card=findViewById(R.id.card_2);

        profilel.setOnClickListener(view -> {
                    Toast.makeText(MainActivity.this, "My Profile", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(MainActivity.this,Student_Profile.class);
                    startActivity(i);
                }

        );


        fingerprintl.setOnClickListener(view -> {

              getData();
//            if(returned_value){
//                Toast.makeText(MainActivity.this, "FingerPrint", Toast.LENGTH_SHORT).show();
//                Intent i=new Intent(MainActivity.this,MainActivity2.class);
//                startActivity(i);
//            }else{
//                Toast.makeText(MainActivity.this, "You are not within range of attendance", Toast.LENGTH_SHORT).show();
//
//            }

        });

        attendancel.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "My Attendance", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(MainActivity.this,MainActivity3.class);
            startActivity(i);
        });

        timetablel.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "My timetable", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(MainActivity.this,MapsActivity.class);
            startActivity(i);
        });

    }

    private void getData() {
//        final boolean[] value = new boolean[1];
        DocumentReference docRef = db.collection("constants").document("constant");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        boolean value = (boolean) document.get("value");
                        boolean timer= (boolean) document.get("timer");
                        if(value && timer){
                            Toast.makeText(MainActivity.this, "FingerPrint", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(MainActivity.this,MainActivity2.class);
                            startActivity(i);
                        }else if(timer){
                            Toast.makeText(MainActivity.this, "You are not within range of attendance ", Toast.LENGTH_SHORT).show();

                        }else if(value){
                            Toast.makeText(MainActivity.this, "Attendance not started ", Toast.LENGTH_SHORT).show();

                        }
//                        Log.i("LOGGER","First "+document.getString("first"));
//                        Log.i("LOGGER","Last "+document.getString("last"));
//                        Log.i("LOGGER","Born "+document.getString("born"));
                    } else {
                        Log.d("LOGGER", "No such document");
                    }
                } else {
                    Log.d("LOGGER", "get failed with ", task.getException());
                }
            }
        });
//        return  value[0];
    }
}