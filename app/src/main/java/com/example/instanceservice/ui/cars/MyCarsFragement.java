package com.example.instanceservice.ui.cars;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.instanceservice.AddCar;
import com.example.instanceservice.R;

public class MyCarsFragement extends Fragment {
    Button btnAddCar;

    private MyCarsViewModel mViewModel;

    public static MyCarsFragement newInstance() {
        return new MyCarsFragement();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View V = inflater.inflate(R.layout.fragment_my_cars, container, false);

        btnAddCar = V.findViewById(R.id.btnAddCar);
        btnAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddCar.class);
                startActivity(intent);
            }
        });

        return V;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MyCarsViewModel.class);
        // TODO: Use the ViewModel
    }

}