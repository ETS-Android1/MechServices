package com.example.instanceservice.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.instanceservice.Dent_Scratch_removal;
import com.example.instanceservice.General_Services;
import com.example.instanceservice.Interior_detailing;
import com.example.instanceservice.Oil_change;
import com.example.instanceservice.R;
import com.example.instanceservice.ac_service;
import com.example.instanceservice.bumper_repair;
import com.example.instanceservice.car_polish;
import com.example.instanceservice.car_spa;
import com.example.instanceservice.full_body_paint;
import com.example.instanceservice.repair_job;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;

public class HomeFragment extends Fragment implements View.OnClickListener{

    private SliderLayout sliderLayout;

    private HomeViewModel homeViewModel;

    private CardView service1, service2, service3, service4, service5, service6, service7, service8, service9, service10;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        sliderLayout = root.findViewById(R.id.slider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(1);
        setSliderView();

        service1 = root.findViewById(R.id.service1);
        service2 = root.findViewById(R.id.service2);
        service3 = root.findViewById(R.id.service3);
        service4 = root.findViewById(R.id.service4);
        service5 = root.findViewById(R.id.service5);
        service6 = root.findViewById(R.id.service6);
        service7 = root.findViewById(R.id.service7);
        service8 = root.findViewById(R.id.service8);
        service9 = root.findViewById(R.id.service9);
        service10 = root.findViewById(R.id.service10);

        service1.setOnClickListener(this);
        service2.setOnClickListener(this);
        service3.setOnClickListener(this);
        service4.setOnClickListener(this);
        service5.setOnClickListener(this);
        service6.setOnClickListener(this);
        service7.setOnClickListener(this);
        service8.setOnClickListener(this);
        service9.setOnClickListener(this);
        service10.setOnClickListener(this);

        return root;
    }

    private void setSliderView() {
        for (int i = 0; i < 5; i++) {
            DefaultSliderView sliderView = new DefaultSliderView(getContext());

            switch (i){
                case 0:
                    sliderView.setDescription("General Service Related to Car.");
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/mechservicies.appspot.com/o/general.jpg?alt=media&token=91cae001-8888-417a-aed1-57bd209b8ff0");
                    break;
                case 1:
                    sliderView.setDescription("Car Spa & Cleaning.");
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/mechservicies.appspot.com/o/service7.png?alt=media&token=c9e488f5-69b0-4d92-a1ad-201c016cdb35");
                    break;
                case 2:
                    sliderView.setDescription("Tyre & Puncture Services");
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/mechservicies.appspot.com/o/service9.jpg?alt=media&token=ab0efc09-f985-4153-8143-60ba7424efe3");
                    break;
                case 3:
                    sliderView.setDescription("Battery & Maintenance Service");
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/mechservicies.appspot.com/o/bettery.jpg?alt=media&token=0e245270-8c69-4a6f-875d-d6472306b788");
                    break;
                case 4:
                    sliderView.setDescription("Other Mechanical Services");
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/mechservicies.appspot.com/o/service10.jpg?alt=media&token=f18fd1cd-ccda-491c-9dcb-b7411804dfbe");
                    break;
            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderLayout.addSliderView(sliderView);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.service1:
                i = new Intent(getActivity(), General_Services.class);
                startActivity(i);
                break;

            case R.id.service2:
                i = new Intent(getActivity(), Dent_Scratch_removal.class);
                startActivity(i);
                break;
            case R.id.service3:
                i = new Intent(getActivity(), Interior_detailing.class);
                startActivity(i);
                break;
            case R.id.service4:
                i = new Intent(getActivity(), car_polish.class);
                startActivity(i);
                break;
            case R.id.service5:
                i = new Intent(getActivity(), bumper_repair.class);
                startActivity(i);
                break;
            case R.id.service6:
                i = new Intent(getActivity(), Oil_change.class);
                startActivity(i);
                break;
            case R.id.service7:
                i = new Intent(getActivity(), car_spa.class);
                startActivity(i);
                break;
            case R.id.service8:
                i = new Intent(getActivity(), ac_service.class);
                startActivity(i);
                break;
            case R.id.service9:
                i = new Intent(getActivity(), full_body_paint.class);
                startActivity(i);
                break;
            case R.id.service10:
                i = new Intent(getActivity(), repair_job.class);
                startActivity(i);
                break;
        }
    }
}