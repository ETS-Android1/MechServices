package com.example.instanceservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.instanceservice.ui.profile.ProfileFragement;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {

    EditText Name, Email, Mobile, Address;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;

    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Intent data  = getIntent();

        String name = data.getStringExtra("name");
        String email = data.getStringExtra("email");
        String mobile = data.getStringExtra("mobile");
        String address = data.getStringExtra("address");

        Name = findViewById(R.id.editName);
        Email = findViewById(R.id.editEmail);
        Mobile = findViewById(R.id.editMobileNumber);
        Address = findViewById(R.id.editAddress);

        Name.setText(name.toString());
        Email.setText(email.toString());
        Mobile.setText(mobile.toString());
        Address.setText(address.toString());

        btnSave = findViewById(R.id.btnSave);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Name.getText().toString().isEmpty() || Email.getText().toString().isEmpty() || Mobile.getText().toString().isEmpty()){
                    Toast.makeText(EditProfile.this, "One or many fields are Empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String user_email = Email.getText().toString();
                user.updateEmail(user_email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        DocumentReference docRef = fStore.collection("users").document(user.getUid());
                        Map<String, Object> edited = new HashMap<String, Object>();

                        edited.put("Email", user_email);
                        edited.put("MobileNumber", Mobile.getText().toString());
                        edited.put("Name", Name.getText().toString());
                        edited.put("Address", Address.getText().toString());
                        docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(EditProfile.this, "Profile Updated Successfully.", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(EditProfile.this, "Profile Updating Failed.", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(EditProfile.this, ProfileFragement.class));
                                finish();
                            }
                        });

                        Toast.makeText(EditProfile.this, "Email is Chaged", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }
}