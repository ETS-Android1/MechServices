package com.example.instanceservice;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    EditText emailId, password, name, mobile_number;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore F_store;
    String userId;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        F_store = FirebaseFirestore.getInstance();

        emailId = findViewById(R.id.textLoginEmail);
        password = findViewById(R.id.textLoginPassword);
        name = findViewById(R.id.txtName);
        mobile_number = findViewById(R.id.txtPhone);
        btnSignUp = findViewById(R.id.btnRegister);
        tvSignIn = findViewById(R.id.textLoginLink);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }

    private void Register() {
        String email = emailId.getText().toString();
        String pwd = password.getText().toString();
        String Name = name.getText().toString();
        String mobile = mobile_number.getText().toString();

        if (email.isEmpty()) {
            emailId.setError("Please Enter email Id.");
            emailId.requestFocus();
        } else if (pwd.isEmpty()) {
            password.setError("Please Enter your password.");
            password.requestFocus();
        } else if (pwd.length() < 6) {
            password.setError("Please Enter password with more than 6 characters.");
            password.requestFocus();
        } else {
            mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "There is Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Successfully Registered.", Toast.LENGTH_SHORT).show();
                        userId = mFirebaseAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = F_store.collection("users").document(userId);

                        Map<String, Object> user = new HashMap<>();
                        user.put("Name", Name);
                        user.put("Email", email);
                        user.put("MobileNumber", mobile);
                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "User created successfully", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("Firebase Error", "on Failure : "+e.toString());
                            }
                        });

                        Intent In = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(In);
                    }
                }
            });
        }
    }
}