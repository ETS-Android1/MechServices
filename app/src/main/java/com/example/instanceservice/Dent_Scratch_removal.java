package com.example.instanceservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dent_Scratch_removal extends AppCompatActivity {

    Button btnService;
    String service_number = "2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dent__scratch_removal);
        getSupportActionBar().hide();


        btnService = findViewById(R.id.btnBookService);
        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dent_Scratch_removal.this, FinalPayment.class);
                intent.putExtra("Service",service_number);
                startActivity(intent);
            }
        });
    }
}