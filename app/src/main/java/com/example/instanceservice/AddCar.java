package com.example.instanceservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;

public class AddCar extends AppCompatActivity {
    EditText Model, Brand, NumberPlate, fuel;
    String model, brand, number, fuel_type;

    FirebaseAuth fAuth;
    FirebaseUser fUser;
    FirebaseFirestore fStore;
    String UserId;
    Button btnSaveCar;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        getSupportActionBar().hide();

        Model = findViewById(R.id.editModel);
        Brand = findViewById(R.id.editBrand);
        NumberPlate = findViewById(R.id.editplatenumber);
        fuel = findViewById(R.id.editFuel);

        fAuth = FirebaseAuth.getInstance();
        fUser = fAuth.getCurrentUser();
        fStore = FirebaseFirestore.getInstance();
        UserId = fUser.getUid();
        btnSaveCar = findViewById(R.id.btnSave_Car);
        btnSaveCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model = Model.getText().toString();
                brand = Brand.getText().toString();
                number = NumberPlate.getText().toString();
                fuel_type = fuel.getText().toString();
                Map<String, Object> map  = new HashMap<String, Object>();

                map.put("Model", model);
                map.put("Brand", brand);
                map.put("Number", number);
                map.put("fuel-type", fuel_type);

                reference = FirebaseDatabase.getInstance().getReference().child("users").child(UserId).child("Cars");
                reference.push().setValue(map);
            }
        });
    }

}