package com.example.instanceservice.ui.service_history;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.instanceservice.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ServiceHistoryFragment extends Fragment {
//
//    ListView listView;
//    FirebaseAuth fAuth;
//    FirebaseUser fUser;
//    FirebaseDatabase database;
//    DatabaseReference ref;
//
//    ArrayList<String> list;
//
//    ArrayAdapter<String> adapter;
//    private ServiceHistoryViewModel mViewModel;
//
//    public static ServiceHistoryFragment newInstance() {
//        return new ServiceHistoryFragment();
//    }
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        View V = inflater.inflate(R.layout.fragment_service_history, container, false);
//
//        listView = V.findViewById(R.id.listView);
//        fAuth = FirebaseAuth.getInstance();
//        fUser = fAuth.getCurrentUser();
//
//        String user = fUser.getUid();
//
//        database = FirebaseDatabase.getInstance();
//        ref = database.getReference("users").child(user).child("Services");
////        service = new Services();
////        list = new ArrayList<>();
////        adapter = new ArrayAdapter<String>(getContext(), R.layout.services, R.id.user_service, list);
////        ref.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                for (DataSnapshot ds: snapshot.getChildren()){
////                    service = ds.getValue(Services.class);
////                    list.add(service.getAmount() +" "+ service.getDate_time());
////                }
////                listView.setAdapter(adapter);
////            }
//
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////
////            }
////        });
////        return V;
////    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(ServiceHistoryViewModel.class);
//        // TODO: Use the ViewModel
//    }

}