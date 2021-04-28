package com.example.instanceservice;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.instanceservice.ui.Service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.Date;

public class FinalPayment extends AppCompatActivity implements PaymentResultListener {
    Button btnMakePayment;
    TextView name, email, mobile,service, status;
    int amount;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    DatabaseReference serviceDb;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_payment);
        Checkout.preload(getApplicationContext());

        name = findViewById(R.id.text_name);
        email = findViewById(R.id.text_email);
        mobile = findViewById(R.id.text_number);
        service = findViewById(R.id.text_service);
        status = findViewById(R.id.payment_status);
        String service_no = getIntent().getStringExtra("Service");

        switch (service_no) {
            case "1":
                service.setText("General Service");
                amount = 1299;
                break;
            case "2":
                service.setText("Dent/Scratch Removal");
                amount = 900;
                break;
            case "3":
                service.setText("Interior Detailing");
                amount = 5500;
                break;
            case "4":
                service.setText("Car Polish");
                amount = 3400;
                break;
            case "5":
                service.setText("Bumper Repairing");
                amount = 2300;
                break;
            case "6":
                service.setText("Oil Change Package");
                amount = 4500;
                break;
            case "7":
                service.setText("Complete Car Spa");
                amount = 1200;
                break;
            case "8":
                service.setText("AC Service");
                amount = 780;
                break;
            case "9":
                service.setText("Full Body Painting");
                amount = 5600;
                break;
        }
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userId);

        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                name.setText(documentSnapshot.getString("Name"));
                email.setText(documentSnapshot.getString("Email"));
                mobile.setText(documentSnapshot.getString("MobileNumber"));
            }
        });
        btnMakePayment = findViewById(R.id.btnMakePayment);
        btnMakePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePayment();
            }
        });
    }
    private void makePayment() {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_V2pRR7gT0reYDW");
        checkout.setImage(R.drawable.logo);
        final Activity activity = this;
        try {
            JSONObject options = new JSONObject();
            options.put("name", "MechServices");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", amount+"00");
            options.put("prefill.email", email);
            options.put("prefill.contact",mobile);
            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }
    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onPaymentSuccess(String s) {
        status.setText("Successful payment ID : "+s);
        Date date = new Date();
        String date_time = String.valueOf(date);

        serviceDb = FirebaseDatabase.getInstance().getReference().child("users").child(userId).child("Services");
        String id = status.getText().toString();
        String number = service.getText().toString();

        Service s1 = new Service(id, number, amount, date_time);
        serviceDb.push().setValue(s1);
        Toast.makeText(this, "Service confirmed", Toast.LENGTH_LONG).show();
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onPaymentError(int i, String s) {
        status.setText("Failed and Caused is : "+s);
    }
}