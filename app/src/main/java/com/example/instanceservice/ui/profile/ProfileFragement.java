package com.example.instanceservice.ui.profile;

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
import android.widget.TextView;

import com.example.instanceservice.EditProfile;
import com.example.instanceservice.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ProfileFragement extends Fragment {
    TextView name, email, mobile, address;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    Button btnEditProfile;
    private ProfileViewModel mViewModel;

    public static ProfileFragement newInstance() {
        return new ProfileFragement();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View V =  inflater.inflate(R.layout.fragment_profile, container, false);

        name = V.findViewById(R.id.textSetName);
        email = V.findViewById(R.id.textSetEmail);
        mobile = V.findViewById(R.id.textSetMobile);
        btnEditProfile = V.findViewById(R.id.btnEditProfile);
        address = V.findViewById(R.id.textSetAddress);

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
                address.setText(documentSnapshot.getString("Address"));
            }
        });

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), EditProfile.class);
                i.putExtra("name", name.getText());
                i.putExtra("email", email.getText());
                i.putExtra("mobile", mobile.getText());
                i.putExtra("address", address.getText());
                startActivity(i);
            }
        });

        return V;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
    }

}