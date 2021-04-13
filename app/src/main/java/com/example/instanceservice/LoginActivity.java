package com.example.instanceservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText LoginEmailId, LoginPwd;
    Button btnSignIn;
    TextView tvSignUp;
    FirebaseAuth mFirebaseAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginEmailId = findViewById(R.id.textLoginEmail);
        LoginPwd = findViewById(R.id.textLoginPassword);
        btnSignIn = findViewById(R.id.btnLogin);
        tvSignUp = findViewById(R.id.textLoginLink);

        mFirebaseAuth = FirebaseAuth.getInstance();


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUserAccount();
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LogToSignUp = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(LogToSignUp);
            }
        });
    }

    private void LoginUserAccount() {
        String str_emailId = LoginEmailId.getText().toString();
        String str_password = LoginPwd.getText().toString();

        if (str_emailId.isEmpty()) {
            LoginEmailId.setError("Please Enter email Id.");
            LoginEmailId.requestFocus();
            return;
        }
        if (str_password.isEmpty()) {
            LoginPwd.setError("Please Enter your password.");
            LoginPwd.requestFocus();
            return;
        }
        mFirebaseAuth.signInWithEmailAndPassword(str_emailId, str_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent LogToHome = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(LogToHome);
                } else {
                    Toast.makeText(LoginActivity.this, "Login Error, Please Login Again. " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}