package com.example.studentpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class TeacherSignIn extends AppCompatActivity {

    private EditText teacher_email;
    private EditText teacher_password;
    private Button button;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_sign_in);

        teacher_email=findViewById(R.id.tv_email1);
        teacher_password=findViewById(R.id.editTextTextPassword);
        button=findViewById(R.id.add_student);
        auth=FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= teacher_email.getText().toString();
                String password=teacher_password.getText().toString();
                teacherLogin(email,password);
            }
        });


    }

    private void teacherLogin(String email, String password) {
        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(TeacherSignIn.this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Intent intent = new Intent(TeacherSignIn.this,TeacherMain.class);
                startActivity(intent);
                Toast.makeText(TeacherSignIn.this, "Login Successfull", Toast.LENGTH_SHORT).show();
            }

        });

    }

    public void StudentLogIn(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}