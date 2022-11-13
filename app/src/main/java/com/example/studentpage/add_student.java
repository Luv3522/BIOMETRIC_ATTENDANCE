package com.example.studentpage;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthMultiFactorException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
//import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class add_student extends AppCompatActivity {
    private static final String TAG = "";
    private EditText email;
    private EditText password;
    private FirebaseAuth auth=FirebaseAuth.getInstance();
    private FirebaseFirestore db=FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
//        getSupportActionBar().hide();

        EditText name = findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.pass);
        Button addStudent = findViewById(R.id.add_student);

        addStudent.setOnClickListener(view -> {
            String name_st=name.getText().toString();
            String email_st=email.getText().toString();
            String password_st=password.getText().toString();
            insertStudent(name_st,email_st);
            AddStudent(name_st,email_st,password_st);
//            insertStudent(name_st,email_st);
            name.setText("");
            email.setText("");
            password.setText("");

        });

    }

    private void insertStudent(String name,String email) {
        CollectionReference newdetailRef = db.collection("student_details");



        Query query = newdetailRef.whereEqualTo("email", email);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot documentSnapshot : task.getResult()){
                        String email = documentSnapshot.getString("email");

                        if(email.equals(email)){
                            Log.d(TAG, "User Exists");
                            Toast.makeText(add_student.this, "Email exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                if(task.getResult().size() == 0 ){
                    Log.d(TAG, "Email does not Exists");
                    //You can store new user information here
                    Map<String,String> items= new HashMap<>();
                    items.put("name",name);
                    items.put("email",email);
                    newdetailRef.add(items).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(add_student.this, "Data Added", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(add_student.this, "Failed to ADD", Toast.LENGTH_SHORT).show();

                        }
                    });


                }
            }
        });


    }


    private void AddStudent(String name,String email,String password) {


        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("success", "createUserWithEmail:success");
                        Toast.makeText(add_student.this, "STUDENT SUCCESSFULLY ADDED.",
                                Toast.LENGTH_SHORT).show();
//                        FirebaseUser user = auth.getCurrentUser();



                    } else{
                        try {
                            throw task.getException();
                        }  catch(FirebaseAuthInvalidCredentialsException e) {
                            Toast.makeText(add_student.this, "EMAIL OR PASSWORD INCORRECT.",
                                    Toast.LENGTH_SHORT).show();
                        } catch(FirebaseAuthUserCollisionException e) {
                            Toast.makeText(add_student.this, "USER ALREADY EXISTS",
                                    Toast.LENGTH_SHORT).show();
                        } catch(Exception e) {
                            Log.e("ERROR", e.getMessage());
                        }
                        // If sign in fails, display a message to the user.
                        Log.w("fails", "createUserWithEmail:failure", task.getException());

                        Toast.makeText(add_student.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();

                    }
                });
    }
}