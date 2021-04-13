package com.example.instanceservice.ui.home;

import android.os.Bundle;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.instanceservice.R;
import com.example.instanceservice.ui.profile.ProfileFragement;
import com.example.instanceservice.ui.profile.ProfileViewModel;
import com.google.android.material.slider.Slider;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;

public class HomeFragment extends Fragment {

    private SliderLayout sliderLayout;

    private HomeViewModel homeViewModel;
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

        return root;
    }

    private void setSliderView() {
        for (int i = 0; i < 5; i++) {
            DefaultSliderView sliderView = new DefaultSliderView(getContext());

            switch (i){
                case 0:
                    sliderView.setDescription("Image - 1");
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/mechservicies.appspot.com/o/service1.png?alt=media&token=37b6973d-145d-44c4-9690-5f77cfe05860");
                    break;
                case 1:
                    sliderView.setDescription("Image - 2");
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/mechservicies.appspot.com/o/service2.png?alt=media&token=29cd2366-c718-4115-aa60-e42c425742a6");
                    break;
                case 2:
                    sliderView.setDescription("Image - 3");
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/mechservicies.appspot.com/o/service3.jpg?alt=media&token=f7fe4c29-dbe1-4288-895c-6a24ef5d89de");
                    break;
                case 3:
                    sliderView.setDescription("Image - 4");
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/mechservicies.appspot.com/o/service5.jpg?alt=media&token=7acc8e08-2bb0-4928-a61b-cee12589b5da");
                    break;
                case 4:
                    sliderView.setDescription("Image - 5");
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/mechservicies.appspot.com/o/service6.jpg?alt=media&token=09e6f0ce-109e-43a1-aa59-1be32b825216");
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
}