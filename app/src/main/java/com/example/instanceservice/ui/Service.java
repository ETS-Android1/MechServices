package com.example.instanceservice.ui;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Service {
    String Service_id;
    String service_name;
    int Amount;
    String date_time;
    public Service(String service_id, String service_number, int Amount, String date_time){
        this.Service_id = service_id;
        this.service_name = service_number;
        this.Amount = Amount;
        this.date_time = date_time;
    }

    public int getAmount() {
        return Amount;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getService_id() {
        return Service_id;
    }

    public void setService_id(String service_id) {
        Service_id = service_id;
    }

    public String getService_number() {
        return service_name;
    }

    public void setService_number(String service_number) {
        this.service_name = service_number;
    }
}

