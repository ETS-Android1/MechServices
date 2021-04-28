package com.example.instanceservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Oil_change extends AppCompatActivity {
    Button btnService;
    String service_number = "6";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oil_change);
        getSupportActionBar().hide();

        btnService = findViewById(R.id.btnBookService);
        btnService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Oil_change.this, FinalPayment.class);
                intent.putExtra("Service",service_number);
                startActivity(intent);
            }
        });
    }
}